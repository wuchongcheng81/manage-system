package com.manage.system.controller;

import com.manage.system.bean.Consult;
import com.manage.system.response.ResultData;
import com.manage.system.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcc
 * @date 2019/9/14 13:08
 */
@RestController
@RequestMapping(value = "/api/consult")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    /**
     * 加入我们
     * @param name
     * @param phone
     * @param brandName
     * @return
     */
    @RequestMapping(value = "/joinUs")
    public ResultData joinUs(@RequestParam String name, @RequestParam String phone, @RequestParam String brandName) {
        Consult consult = new Consult();
        consult.setCoustomerName(name);
        consult.setCoustomerPhone(phone);
        consult.setCoustomerBrand(brandName);
        consult.setType(Consult.JOIN_STATUS);
        consultService.save(consult);
        return new ResultData(true);
    }

    /**
     * 咨询加盟
     * @param name
     * @param phone
     * @param content
     * @return
     */
    @RequestMapping(value = "/addition")
    public ResultData addition(@RequestParam String name, @RequestParam String phone, @RequestParam String content) {
        Consult consult = new Consult();
        consult.setCoustomerName(name);
        consult.setCoustomerPhone(phone);
        consult.setCoustomerContent(content);
        consult.setType(Consult.CONSULT_STATUS);
        consultService.save(consult);
        return new ResultData(true);
    }
}
