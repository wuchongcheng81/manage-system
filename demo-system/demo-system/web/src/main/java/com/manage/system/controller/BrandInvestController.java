package com.manage.system.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.BrandInvest;
import com.manage.system.service.BrandInvestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wucc
 * @date 2019/9/20 18:04
 */
@RestController
@RequestMapping(value = "/brandInvest")
public class BrandInvestController extends BaseController<BrandInvestService, BrandInvest, Integer> {
}
