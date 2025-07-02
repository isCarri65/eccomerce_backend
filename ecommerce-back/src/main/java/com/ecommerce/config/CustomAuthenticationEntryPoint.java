package com.ecommerce.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        // Obtenemos la excepción específica desde el atributo del request
        Exception exception = (Exception) request.getAttribute("jwt_exception");

        String message = "No autorizado";

        if (exception instanceof ExpiredJwtException) {
            message = "Token expirado";
        } else if (exception instanceof MalformedJwtException) {
            message = "Token mal formado";
        } else if (exception instanceof SignatureException) {
            message = "Firma del token inválida";
        } else if (exception instanceof IllegalArgumentException) {
            message = "Token inválido";
        } else if (exception == null) {
            message = "Requiere Autorizacion";
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
