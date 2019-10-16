package com.manage.system.backend.controller;

import com.manage.system.bean.Type;
import com.manage.system.response.ResultData;
import com.manage.system.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wucc
 * @date 2019/8/30 17:37
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController extends BaseController<TypeService, Type, Integer> {

    @GetMapping(value = "/findAll")
    public ResultData<List<Type>> findAll() {
        return new ResultData<>(true, mapper.findAll());
    }
}
