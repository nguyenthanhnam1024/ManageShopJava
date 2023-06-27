package com.example.manage_shops.jwt;

import com.example.manage_shops.config.UserDetailsImp;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public String generateJwt(UserDetailsImp userDetailsImp) {
        return Jwts.builder()
                .setSubject(userDetailsImp.getUsername())
                .claim("idShop", userDetailsImp.getShopId())
                .claim("roles", userDetailsImp.getAuthorities())
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
        throw new CredentialsExpiredException("Expiration jwt");
    }

    public Boolean validateJwt(String token, HttpServletResponse httpServletResponse){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            httpServletResponse.setStatus(401);
        }
        return false;
    }
}
