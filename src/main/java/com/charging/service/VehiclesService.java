package com.charging.service;

import com.charging.common.ApiResponse;
import com.charging.mapper.VehiclesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehiclesService {


    private final VehiclesMapper vehiclesMapper;

    public ApiResponse getList(){
        return ApiResponse.success(vehiclesMapper.selectList(null));
    }

}
