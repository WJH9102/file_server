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
    /*
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String generalSubject(UserInfo userInfo){
        JSONObject jo = new JSONObject();
        jo.put("id", userInfo.getId());
        jo.put("username", userInfo.getUsername());
        return jo.toJSONString();
    }

    public static String createJWT(String id, String issuer, UserInfo userInfo, long ttlMillis) {
        String subject = generalSubject(userInfo);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    */

//    public static void main(String[] args) {
//        User user = new User();
//        user.setId(1);
//        user.setUsername("admin");
//        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
//        String generalSubject = generalSubject(user);
//        String jwt = createJWT("oschina", "issuerdata", generalSubject, Constant.JWT_TTL);
//        System.out.println(jwt);
//        parseJWT(jwt);
//    }

    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET))
                .parseClaimsJws(jwt).getBody();
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());
        return claims;
    }

}
