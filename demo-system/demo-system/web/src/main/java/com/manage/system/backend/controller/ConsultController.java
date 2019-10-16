package com.manage.system.backend.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Consult;
import com.manage.system.service.ConsultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wucc
 * @date 2019/9/9 18:03
 */
@RestController
@RequestMapping(value = "/consult")
public class ConsultController extends BaseController<ConsultService, Consult, Integer> {

}
