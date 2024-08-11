package com.charging.api;

import com.charging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class ActivateApi {

    private final UserService userService;

    @Autowired
    public ActivateApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/activate")
    public String activateAccount(@RequestParam String userId, Model model) {
        // 逻辑处理，例如验证用户ID，激活账户等
        if (userService.activation(userId)) {
            // 激活成功
            model.addAttribute("message", "Your account has been activated successfully!");
        } else {
            // 激活失败
            model.addAttribute("message", "Activation failed. Please check your user ID and try again.");
        }
        // 渲染激活成功或失败页面
        return "success";
    }
}
