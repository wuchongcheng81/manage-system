package com.manage.system.frontend.controller;

import com.manage.system.base.BaseFrontController;
import com.manage.system.bean.Advertisement;
import com.manage.system.response.ResultData;
import com.manage.system.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/15 9:37
 */
@RestController
@RequestMapping(value = "/api/advertisement")
public class AdvertisementFrontController extends BaseFrontController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 根据code查询广告
     * @param code
     * @return
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(String code) {
        List<Advertisement> list = advertisementService.findList(new Advertisement(code));
        return new ResultData(true, list);
    }
}
