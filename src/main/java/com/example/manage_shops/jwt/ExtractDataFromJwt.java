package com.example.manage_shops.jwt;

import com.example.manage_shops.exception.MyValidateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExtractDataFromJwt {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public final AuthFilter authFilter;

    @Autowired
    public ExtractDataFromJwt(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    public int extractIdShopFromJwt(HttpServletRequest httpServletRequest) {
        String jwt = authFilter.getJwtFromRequest(httpServletRequest);
        Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
        return (int) claims.getBody().get("idShop");
    }

    public List<String> extractRoleNamesFromJwt(HttpServletRequest httpServletRequest) throws MyValidateException {
        String jwt = authFilter.getJwtFromRequest(httpServletRequest);
        Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
        List<Map<String, String>> listMapRole = (List<Map<String, String>>) claims.getBody().get("roles");
        List<String> roles = new ArrayList<>();
        if (!listMapRole.isEmpty()) {
            for (Map<String,String> mapRole : listMapRole) {
                roles.add(mapRole.get("authority"));
            }
            if (!roles.isEmpty()) {
                return roles;
            }
        }
        throw new MyValidateException("you no have permission execute th function ");
    }
}
