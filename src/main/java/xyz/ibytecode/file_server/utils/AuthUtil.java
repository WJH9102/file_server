package xyz.ibytecode.file_server.utils;

import ch.qos.logback.core.net.LoginAuthenticator;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;





public class AuthUtil {


    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
