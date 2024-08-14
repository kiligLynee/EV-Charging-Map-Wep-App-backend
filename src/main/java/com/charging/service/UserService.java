package com.charging.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charging.common.ApiResponse;
import com.charging.common.JwtUtil;
import com.charging.entity.UserinfoEntity;
import com.charging.mapper.UserinfoMapper;
import com.charging.request.LoginRequest;
import com.charging.request.RegisterRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.HashSet;

@Service
public class UserService {

    private final UserinfoMapper userinfoMapper;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public UserService(UserinfoMapper userinfoMapper, JwtUtil jwtUtil, EmailService emailService) {
        this.userinfoMapper = userinfoMapper;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    public ApiResponse login(LoginRequest loginRequest) {
        // 检查用户名密码是否存在
        QueryWrapper<UserinfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("email", loginRequest.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserinfoEntity userinfoEntity = userinfoMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(userinfoEntity)) {
            return ApiResponse.error(HttpStatus.HTTP_NO_CONTENT, "Userinfo is not exist");
        }
        if (!encoder.matches(loginRequest.getPassword(), userinfoEntity.getPassword())) {
            return ApiResponse.error(HttpStatus.HTTP_NO_CONTENT, "password is wrong");
        }
        if (userinfoEntity.getStatus() == 0) {
            return ApiResponse.error(HttpStatus.HTTP_NO_CONTENT,
                    "Account is not activated, please check your email to activate your account");
        }
        if (userinfoEntity.getStatus() == 2) {
            return ApiResponse.error(HttpStatus.HTTP_NO_CONTENT,
                    "The account status is wrong, please contact the administrator");
        }
        String token = jwtUtil.generateToken(new User(userinfoEntity.getId(), "", new HashSet<>()));
        userinfoEntity.setToken(token);
        userinfoMapper.updateById(userinfoEntity);
        return ApiResponse.success("login successful", userinfoEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiResponse register(RegisterRequest registerRequest) throws MessagingException {
        QueryWrapper<UserinfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("email", registerRequest.getEmail());
        UserinfoEntity userinfoEntity = userinfoMapper.selectOne(wrapper);
        if (ObjectUtil.isNotNull(userinfoEntity)) {
            return ApiResponse.error(HttpStatus.HTTP_NO_CONTENT, "The mailbox has been registered");
        }
        userinfoEntity = new UserinfoEntity();
        String name = registerRequest.getUsername();
        // 生成8位用户名
        String username = name != null ? name : RandomStringUtils.randomAlphabetic(8);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(registerRequest.getPassword());
        String id = IdUtil.getSnowflakeNextIdStr();
        userinfoEntity.setId(id);
        userinfoEntity.setEmail(registerRequest.getEmail());
        userinfoEntity.setUsername(username);
        userinfoEntity.setPassword(password);
        userinfoMapper.insert(userinfoEntity);
//       emailService.sendHtmlMessage(registerRequest.getEmail(), "激活邮箱",
//       registerRequest.getEmail(), id);
        return ApiResponse.success("registration success");
    }

    public ApiResponse logout() {
        SecurityContext context = SecurityContextHolder.getContext();
        User principal = (User) context.getAuthentication().getPrincipal();
        // 获取生成token时的userId
        String userId = principal.getUsername();
        UserinfoEntity entity = new UserinfoEntity();
        entity.setToken("");
        entity.setId(userId);
        userinfoMapper.updateById(entity);
        return ApiResponse.success("exit successfully");
    }

    public Boolean activation(String userId) {
        if (!StringUtils.hasText(userId)) {
            return Boolean.FALSE;
        }
        // 判断userId在数据库中是否存在
        if (ObjectUtil.isNull(userinfoMapper.selectById(userId))) {
            return Boolean.FALSE;
        }
        UserinfoEntity entity = new UserinfoEntity();
        entity.setId(userId);
        entity.setStatus(1);
        userinfoMapper.updateById(entity);
        return true;
    }
}
