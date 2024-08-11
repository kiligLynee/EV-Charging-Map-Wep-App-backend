package com.charging.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("td_userinfo")
public class UserinfoEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String username;
    private String password;
    private String email;
    private String token;
    private Integer status;

}
