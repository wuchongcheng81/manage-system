package com.manage.system.controller;

import com.manage.system.bean.Information;
import com.manage.system.dto.InformationFrontDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/15 9:49
 */
@RestController
@RequestMapping(value = "/api/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    /**
     * 查询资讯列表（分页）
     * @param code
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(String code, Integer typeId, Integer brandId, Integer pageNumber, Integer pageSize) {
        List<InformationFrontDTO> list = informationService.findListByColumnCode(code, typeId, brandId, pageNumber, pageSize);
        return new ResultData(true, list);
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int informationId) {
        return new ResultData(true, informationService.findById(informationId));
    }


}
