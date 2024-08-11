package com.charging.api;

import com.charging.common.ApiResponse;
import com.charging.common.JwtUtil;
import com.charging.request.LoginRequest;
import com.charging.request.RegisterRequest;
import com.charging.service.EmailService;
import com.charging.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserService userService;
    private final EmailService emailService;

    public UserApi(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("register")
    public ApiResponse register(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException {
        return userService.register(registerRequest);
    }

    @PostMapping("/logout")
    public ApiResponse logout(){
        return userService.logout();
    }

    @PostMapping("/sendEmailTest")
    public void sendEmail() throws MessagingException {
        emailService.sendHtmlMessage("2660180374@qq.com", "感谢您注册Charging", "吴老师", "http://localhost:8080/user/activation?token=123456");
    }

}
