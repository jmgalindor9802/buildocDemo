package com.buildoc.buildocDemo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String SIGN_UP_URL = "/buildoc/auth/register";
    private static final String LOGOUT_URL = "/buildoc/auth/logout";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Obtiene la URL de la solicitud actual
        String requestURI = request.getRequestURI();

        // Verificar si la solicitud es para registro o cierre de sesión
        if (requestURI.equals(SIGN_UP_URL) || requestURI.equals(LOGOUT_URL)) {
            // Si es una solicitud de registro o cierre de sesión, permite que continúe sin verificar el token
            return true;
        }

        // Para todas las demás solicitudes, verificar la presencia y validez del token
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            // Si el token no está presente o no es válido, configurar la respuesta con HTTP 401 (No autorizado)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Si la validación es exitosa, permitir que la solicitud continúe
        return true;
    }
}
