package com.manage.system.controller;

import com.manage.system.bean.Consult;
import com.manage.system.response.ResultData;
import com.manage.system.service.ConsultService;
import com.manage.system.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ResultData joinUs(
            HttpServletRequest request,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String brandName) {

        String ip = HttpUtil.getIpAddr(request);
        List<Consult> list = consultService.findByIpAndType(ip, Consult.JOIN_STATUS);
        if(!CollectionUtils.isEmpty(list) && list.size() >= 5) {
            return new ResultData(false, "提交次数已超过限制");
        }

        Consult consult = new Consult();
        consult.setCoustomerName(name);
        consult.setCoustomerPhone(phone);
        consult.setCoustomerBrand(brandName);
        consult.setType(Consult.JOIN_STATUS);
        consult.setRequestIp(ip);
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
    public ResultData addition(
            HttpServletRequest request,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String content) {

        String ip = HttpUtil.getIpAddr(request);
        List<Consult> list = consultService.findByIpAndType(ip, Consult.CONSULT_STATUS);
        if(!CollectionUtils.isEmpty(list) && list.size() >= 5) {
            return new ResultData(false, "提交次数已超过限制");
        }

        Consult consult = new Consult();
        consult.setCoustomerName(name);
        consult.setCoustomerPhone(phone);
        consult.setCoustomerContent(content);
        consult.setType(Consult.CONSULT_STATUS);
        consult.setRequestIp(ip);
        consultService.save(consult);
        return new ResultData(true);
    }
}
