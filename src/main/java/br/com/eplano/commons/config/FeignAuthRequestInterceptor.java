package br.com.eplano.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FeignAuthRequestInterceptor {
    public static final String USER_EMAIL_HEADER = "x-user-email";
    public static final String USER_ROLES_HEADER = "x-user-roles";
    public static final String USER_ID_HEADER = "x-user-id";

    @Bean
    public RequestInterceptor interceptor() {
        log.debug("Criando interceptor Feign para autenticação");

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                log.debug("Interceptando requisição Feign: URL:{}-{}", template.url(), template);
                log.debug("Request Attributes: {}", RequestContextHolder.getRequestAttributes());

                // Recupera o HttpServletRequest, se existir no contexto
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
                    HttpServletRequest request = servletRequestAttributes.getRequest();
                    template.header(USER_EMAIL_HEADER, request.getHeader(USER_EMAIL_HEADER));
                    template.header(USER_ROLES_HEADER, request.getHeader(USER_ROLES_HEADER));
                    template.header(USER_ID_HEADER, request.getHeader(USER_ID_HEADER));
                    template.header("authorization", request.getHeader("authorization"));

                    request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
                        String headerValue = request.getHeader(headerName);
                        log.debug("Repassando Header {}={}", headerName, headerValue);
                    });
                }
            }
        };
    }
}
