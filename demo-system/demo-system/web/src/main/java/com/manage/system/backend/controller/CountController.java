package com.manage.system.backend.controller;

import com.google.common.collect.Lists;
import com.manage.system.dto.CountDetailDTO;
import com.manage.system.dto.CountListDetailDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.CountService;
import com.manage.system.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wcc
 * @date 2019/11/16 21:05
 */
@RestController
@RequestMapping(value = "/count/data")
public class CountController {

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/getCount")
    public ResultData getCount(Integer brandId) {
        CountDetailDTO countDetailDTO = countService.getCount(brandId);
        return new ResultData(true, countDetailDTO);
    }

    @RequestMapping(value = "/getCountList")
    public ResultData getCountList(int beforeDay, Integer brandId) {
        CountListDetailDTO countListDetailDTO = countService.getCountList(beforeDay, brandId);
        return new ResultData(true, countListDetailDTO);
    }
}
