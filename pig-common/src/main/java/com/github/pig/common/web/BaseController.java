package com.github.pig.common.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author lengleng
 * @date 2017/10/28
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    /**
     * 根据请求heard中的token获取用户
     * @return
     */
    public String getUser() {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        String key = Base64.getEncoder().encodeToString("pig".getBytes());
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.get("user_name").toString();
    }
}
