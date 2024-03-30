package com.buildoc.buildocDemo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Verificar si el token está presente en la solicitud
        String token = request.getHeader("Authorization");

        // Validar el token (aquí puede implementar su lógica de validación de token)
        // Si la validación falla, puede devolver false y configurar la respuesta con HTTP 401 (No autorizado)
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Si la validación es exitosa, permita que la solicitud continúe
        return true;
    }
}
