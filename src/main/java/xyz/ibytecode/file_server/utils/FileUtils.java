package xyz.ibytecode.file_server.utils;


import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FileUtils {
    /**
     * 获取当前项目根路径
     * @return
     */
    public static String getFilePath() {
//        String projectPath = this.getClass().getResource("/").getPath();
        String projectPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        String[] split = projectPath.split("/");
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (int i = 1; i < split.length-2; i++){
            sb.append(split[i] + File.separator);
        }
        String filePath = sb.toString() + "files" + File.separator;
//        System.out.println(filePath);
        return filePath;
    }

    /**
     * 获取当天年月日，用来作为文件夹区分
     * @return
     */
    public static String[] getYMD(){
        Calendar cal = Calendar.getInstance();
        String year = Integer.toString(cal.get(Calendar.YEAR));
        String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        String[] ymd = new String[]{year, month, day};
        return ymd;
    }

    /**
     * 生成随机11位字符串 作为文件名
     * @return
     */
    public static String getRandomString(){
        int length = 11;
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
