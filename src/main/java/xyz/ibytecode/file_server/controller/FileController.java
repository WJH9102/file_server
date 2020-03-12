package xyz.ibytecode.file_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.ibytecode.file_server.utils.FileUtils;
import xyz.ibytecode.file_server.utils.RsBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
public class FileController {
    @Value("${ibytecode.fileServer.url}")
    private String fileServerUrl;

    @PostMapping("/uploadFile")
    public RsBody<Map<String, Object>> uploadFile(MultipartFile file) {
        // 获取文件服务器的根路径
        String filePath = FileUtils.getFilePath();
        // 获取当前年月日
        String[] ymd = FileUtils.getYMD();
        // 拼接当天文件路径
        String nowFilePath = filePath + ymd[0] + File.separator +  ymd[1] + File.separator + ymd[2] + File.separator;
        // 创建文件夹
        File folder = new File(nowFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 当前文件后缀
        String[] fileNames = file.getOriginalFilename().split("\\.");
        String sub = fileNames[fileNames.length - 1];
        // 新文件名
        String newFileName = FileUtils.getRandomString() + "." + sub;
        // 响应体
        RsBody<Map<String, Object>> rsBody = new RsBody<>();
        Map<String, Object> data = new HashMap<>();
        // 文件为空直接响应
        if (file.getSize() <= 0) {
            rsBody.setMessage("上传文件为空");
            rsBody.setCode("-1");
            return rsBody;
        }
        BufferedInputStream bi = null;
        BufferedOutputStream bo = null;
        try {
            bi = new BufferedInputStream(file.getInputStream());
            bo = new BufferedOutputStream(new FileOutputStream(new File(nowFilePath + newFileName)));
            // 缓冲区
            byte[] buf = new byte[1024*1024];
            int len = -1;
            while ((len = bi.read(buf)) != -1){
                bo.write(buf, 0, len);
                bo.flush();
            }
            // 上传文件后的URL
            String fileUrl = fileServerUrl + ymd[0] + File.separator +  ymd[1] + File.separator + ymd[2] + File.separator + newFileName;
            String s = fileUrl.replaceAll("\\\\", "/");
            data.put("fileUrl", s);
            rsBody.setCode("1");
            rsBody.setMessage("上传成功");
            rsBody.setData(data);
        } catch (IOException e) {
            rsBody.setCode("-1");
            rsBody.setMessage("上传失败");
            System.out.println("上传失败1");
        }finally {
            try {
                if (null != bi) bi.close();
                if (null != bo) bo.close();
            } catch (IOException e) {
                System.out.println("流关闭异常");
            }
        }
        return rsBody;
    }

}
