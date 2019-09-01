package com.manage.system.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Type;
import com.manage.system.response.ResultData;
import com.manage.system.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wucc
 * @date 2019/8/30 17:37
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController extends BaseController<TypeService, Type, Integer> {
}
