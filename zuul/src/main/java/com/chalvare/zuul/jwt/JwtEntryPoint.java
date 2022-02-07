package com.chalvare.zuul.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Comprueba si existe un token si no devuelve un 401 no autorizado
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    // Implementamos un logger para ver cual metodo da error en caso de falla
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    //Metodo implementado de AuthenticationEntryPoint
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("error in method commence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Customer is no authorized");
    }
}
