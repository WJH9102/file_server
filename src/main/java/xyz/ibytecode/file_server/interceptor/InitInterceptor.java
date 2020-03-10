package xyz.ibytecode.file_server.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.ibytecode.file_server.utils.RsBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class InitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!"fileUpload".equals(token)) return false;
        return true;
        /*可扩展
    try {
        String token = request.getHeader("X-Token");
        if (StringUtils.isEmpty(token)) return false;
        Claims claims = AuthUtil.parseJWT(token);
        String userInfo = claims.getSubject();
        Map<String, Object> userMap = (Map<String, Object>) JSON.parse(userInfo);
        return true;
    }catch (ExpiredJwtException e){
//            System.out.println(e.getClaims().getExpiration());
        System.out.println("token已失效");
        returnJson(response);
        return false;
    }catch (SignatureException e){
        System.out.println("签名异常");
        returnJson(response);
        return false;
    }catch (MalformedJwtException e){
        System.out.println("非法的token");
        returnJson(response);
        return false;
    }catch (UnsupportedJwtException e){
        System.out.println("不支持的token");
        returnJson(response);
        return false;
    }
     */
    }


    private void returnJson(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            RsBody<Map<String, Object>> rsBody = new RsBody<>();
            rsBody.setCode("-10000");
            rsBody.setMessage("登录已失效请重新登录");
//            System.out.println(JSON.toJSONString(rsBody));
            writer.write(JSON.toJSONString(rsBody));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
