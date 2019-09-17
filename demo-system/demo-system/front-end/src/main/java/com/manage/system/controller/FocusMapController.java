package com.manage.system.controller;

import com.manage.system.bean.FocusMap;
import com.manage.system.response.ResultData;
import com.manage.system.service.FocusMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/17 17:57
 */
@RestController
@RequestMapping(value = "/api/focusMap")
public class FocusMapController {

    @Autowired
    private FocusMapService focusMapService;

    @RequestMapping(value = "findList")
    public ResultData findList(String code) {
        FocusMap focusMap = new FocusMap();
        focusMap.setImgIsShow(1);
        focusMap.setPositionCode(code);
        List<FocusMap> list = focusMapService.findList(focusMap);
        return new ResultData(true, list);
    }
}
