package com.charging.api;

import com.charging.common.ApiResponse;
import com.charging.service.VehiclesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
public class VehiclesApi {
    private final VehiclesService vehiclesService;

    // 调用Service的接口查询全部数据
    @GetMapping("/list")
    public ApiResponse getList() {
        return vehiclesService.getList();
    }

}
