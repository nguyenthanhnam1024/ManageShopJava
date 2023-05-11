package com.example.manage_shops.jwt;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.exception.MyAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public String generateJwt(UserDetailsImp userDetailsImp) {
        return Jwts.builder()
                .setSubject(userDetailsImp.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000L))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Claims getClaimFromJwt(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    public Boolean isExpirationJwt(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = this.getClaimFromJwt(token);
        if (claims == null) {
            throw new BadCredentialsException("Invalid Jwt");
        }
        if (this.isExpirationJwt(claims)) {
            return claims.getSubject();
        }
        throw new CredentialsExpiredException("Expiration Jwt");
    }

    public Boolean validateJwt(String token) throws IOException {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new MyAuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Invalid jwt");
        } catch (MalformedJwtException ex) {
            throw new MyAuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Malformed jwt");
        } catch (ExpiredJwtException ex) {
            throw new MyAuthenticationException(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized jwt");
        } catch (UnsupportedJwtException ex) {
            throw new MyAuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported jwt");
        } catch (IllegalArgumentException ex) {
            throw new MyAuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Illegal argument jwt");
        }
    }
}
