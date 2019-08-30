package com.manage.system.controller;

import com.manage.system.bean.Type;
import com.manage.system.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wucc
 * @date 2019/8/30 17:37
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping(value = "/get")
    public Type get(Integer id) {
        return typeService.findById(id);
    }
}
