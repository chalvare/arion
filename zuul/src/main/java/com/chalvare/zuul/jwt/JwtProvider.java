package com.chalvare.zuul.jwt;

import com.chalvare.zuul.security.entity.CustomerMain;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Clase que genera el token y valida que este bien formado y no este expirado
 */
@Component
public class JwtProvider {

    // Implementamos un logger para ver cual metodo da error en caso de falla
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //Valores que tenemos en el aplicattion.properties

    private static final String SECRET = "secret";

    private static final int EXPIRATION = 43200;

    /**
     *setIssuedAt --> Asigna fecha de creción del token
     *setExpiration --> Asigna fehca de expiración
     * signWith --> Firma
     */
    public String generateToken(Authentication authentication){
        CustomerMain customerMain = (CustomerMain) authentication.getPrincipal();
        return Jwts.builder().setSubject(customerMain.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //subject --> Nombre del usuario
    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Bad construction Token");
        }catch (UnsupportedJwtException e){
            logger.error("No supported Token");
        }catch (ExpiredJwtException e){
            logger.error("Expired Token");
        }catch (IllegalArgumentException e){
            logger.error("Empty Token");
        }catch (SignatureException e){
            logger.error("Fail with the firm");
        }
        return false;
    }
}
