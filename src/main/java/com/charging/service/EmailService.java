package com.charging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${email.activateUrl}")
    private String activationLink;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendHtmlMessage(String to, String subject, String username, String userId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String url = activationLink+"?userId="+userId;
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activationLink", url);

        String htmlContent = templateEngine.process("email-template", context); // 假设你有一个名为email-template的Thymeleaf模板

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(fromEmail);
        helper.setText(htmlContent, true); // 第二个参数设为true表示这是HTML内容

        javaMailSender.send(mimeMessage);
        System.out.println("激活地址："+url);
    }
}
