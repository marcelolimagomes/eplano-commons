package br.com.eplano.commons.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserContextUtils {

    public static final String USER_EMAIL_HEADER = "x-user-email";
    public static final String USER_ROLES_HEADER = "x-user-roles";
    public static final String USER_ID_HEADER = "x-user-id";

    public static String getCurrentUserEmail() {
        HttpServletRequest request = getCurrentRequest();
        String userEmailHeader = request != null ? request.getHeader(USER_EMAIL_HEADER) : null;
        return userEmailHeader != null && !userEmailHeader.isEmpty()
                ? userEmailHeader
                : null;
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
        return userIdHeader != null && !userIdHeader.isEmpty()
                ? userIdHeader
                : null;

    }

    public static boolean hasRole(String role) {
        return getCurrentUserRoles().contains(role);
    }

    public static boolean isAdmin() {
        return hasRole("ADMIN");
    }

    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        log.debug("Obtendo request atual: {}",
                attributes != null ? attributes.getRequest().getRequestURI() : "Nenhum request encontrado");

        if (attributes != null && attributes.getRequest() != null) {
            attributes.getRequest().getHeaderNames().asIterator().forEachRemaining(headerName -> {
                String headerValue = attributes.getRequest().getHeader(headerName);
                log.debug("Header received: {} = {}", headerName, headerValue);
            });
        }

        return attributes != null ? attributes.getRequest() : null;
    }
}