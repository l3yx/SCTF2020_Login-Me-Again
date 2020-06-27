package org.syclover.srpingbootshiro;

import ch.qos.logback.core.util.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class LoginController {
    @PostMapping("/doLogin")
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            //System.out.println("Successful login!");
            return "Successful login!";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //System.out.println("login failure!");
            return "login failure!";
        }
    }
    @GetMapping("/admin/page")
    public String admin() {
        return "admin page";
    }

    @PostMapping("/admin/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        //生成uuid
        String fileUUIDname = UUID.randomUUID().toString();
        //获取文件后缀
        String prefix=filename.substring(filename.lastIndexOf(".")+1);
        //检查后缀
        if(!prefix.equals("jpg")){
            return "Illegal file names";
        }
        //重命名文件
        String NewFileName = fileUUIDname + "." + prefix;
        //获取到系统路径
        String sysPath = System.getProperty("catalina.home") + "/webapps";
        //生成文件地址
        String filePath= "/uploads/" + NewFileName;
        String finalPath = sysPath + filePath;
        File targetFile = new File(finalPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            multipartFile.transferTo(new File(sysPath + filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @GetMapping("/login")
    public String  login() {
        return "please login!";
    }
}