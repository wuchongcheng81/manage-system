package com.manage.system.backend.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Information;
import com.manage.system.dto.InformationDTO;
import com.manage.system.response.ResultPage;
import com.manage.system.service.InformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcc
 * @date 2019/9/4 22:33
 */
@RestController
@RequestMapping(value = "/information")
public class InformationController extends BaseController<InformationService, Information, Integer> {


    @RequestMapping(value = "/findListByPage")
    public ResultPage<Information> findListByPage(InformationDTO information) {
        ResultPage<Information> resultPage = new ResultPage<>();
        int total = mapper.queryTotal(information);
        resultPage.setTotal(Long.valueOf(total));
        resultPage.setRows(mapper.findListByPage(information));
        return resultPage;
    }
}
