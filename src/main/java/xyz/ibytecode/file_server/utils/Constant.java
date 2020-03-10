package xyz.ibytecode.file_server.utils;

/**
 * @Author WJH
 * @Description
 * @date 2019/12/21 16:36
 */
public class Constant {
    public static final String JWT_ID = "jwttest";
    public static final String JWT_ISSURE = "WJH";
    public static final String JWT_SECRET = "LLL blog";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_TEST = 10000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}
