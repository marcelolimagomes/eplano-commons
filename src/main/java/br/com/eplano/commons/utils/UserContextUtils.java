package br.com.eplano.commons.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class UserContextUtils {

    private static final String USER_EMAIL_HEADER = "X-User-Email";
    private static final String USER_ROLES_HEADER = "X-User-Roles";
    private static final String USER_ID_HEADER = "X-User-Id";

    public static String getCurrentUserEmail() {
        HttpServletRequest request = getCurrentRequest();
        return request != null ? request.getHeader(USER_EMAIL_HEADER) : null;
    }

    public static List<String> getCurrentUserRoles() {
        HttpServletRequest request = getCurrentRequest();
        String rolesHeader = request != null ? request.getHeader(USER_ROLES_HEADER) : null;
        return rolesHeader != null && !rolesHeader.isEmpty()
                ? Arrays.asList(rolesHeader.split(","))
                : List.of();
    }

    public static String getCurrentUserId() {
        HttpServletRequest request = getCurrentRequest();
        String userIdHeader = request != null ? request.getHeader(USER_ID_HEADER) : null;
        try {
            return userIdHeader != null && !userIdHeader.isEmpty()
                    ? userIdHeader
                    : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean hasRole(String role) {
        return getCurrentUserRoles().contains(role);
    }

    public static boolean isAdmin() {
        return hasRole("ADMIN");
    }

    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }
}