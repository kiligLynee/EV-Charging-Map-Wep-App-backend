package com.charging.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("vehicles")
public class VehiclesEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    // private String userId;
    private String carName;
    private String kwh;
    private String chargingPort;

}
