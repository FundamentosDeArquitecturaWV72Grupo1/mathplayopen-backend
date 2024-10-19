package com.user.service.mathplayopen.application.external.resttemplate;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {
    public String extractTokenFromRequest(HttpServletRequest request) {
        // Obtener el encabezado "Authorization"
        String bearerToken = request.getHeader("Authorization");

        // Verifica si el token empieza con "Bearer "
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Retorna el token sin el prefijo "Bearer "
            return bearerToken.substring(7);
        }

        // Si no se encuentra o no es válido, retorna null
        return null;
    }
}
