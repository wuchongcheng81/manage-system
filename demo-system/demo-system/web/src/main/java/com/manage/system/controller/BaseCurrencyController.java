package com.manage.system.controller;

import com.manage.system.service.BaseCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wucc
 * @date 2019/8/26 17:27
 */
@RestController
@RequestMapping("/currency")
public class BaseCurrencyController {

    @Autowired
    private BaseCurrencyService baseCurrencyService;

    @PostMapping("/findList")
    public List<BaseCurrency> findList(@RequestBody BaseCurrency entity) {
        return baseCurrencyService.findList(entity);
    }
}
