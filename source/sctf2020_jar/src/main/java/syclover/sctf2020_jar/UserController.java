package syclover.sctf2020_jar;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @PostMapping({"/doLogin"})
    public String doLoginPage(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(name = "rememberme",defaultValue = "") String rememberMe) {
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, password, rememberMe.equals("remember-me")));
            return "redirect:/";
        } catch (AuthenticationException e) {
            return "forward:/unauth";
        }
    }

    @RequestMapping({"/"})
    public String helloPage() {
        return "index";
    }

    @RequestMapping({"/unauth"})
    public String errorPage() {
        return "error";
    }

    @RequestMapping({"/login"})
    public String loginPage() {
        return "login";
    }
}
