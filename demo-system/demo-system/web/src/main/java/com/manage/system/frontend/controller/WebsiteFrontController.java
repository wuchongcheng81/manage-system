package com.manage.system.frontend.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Website;
import com.manage.system.response.ResultData;
import com.manage.system.service.WebsiteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcc
 * @date 2019/9/8 10:15
 */
@RestController
@RequestMapping(value = "/api/website")
public class WebsiteFrontController extends BaseController<WebsiteService, Website, Integer> {

    @GetMapping(value = "/getOne")
    public ResultData<Website> getOne() {
        Website website = mapper.getOne();
        if(website != null) {
            return new ResultData<>(true, website);
        }
        return new ResultData<>(false);
    }
}
