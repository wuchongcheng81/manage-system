package com.manage.system.controller;

import com.google.common.collect.Lists;
import com.manage.system.bean.BrandInvest;
import com.manage.system.dto.BrandInvestDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandInvestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author wcc
 * @date 2019/9/22 10:58
 */
@RestController
@RequestMapping(value = "/api/brandInvest")
public class BrandInvestController {

    @Autowired
    private BrandInvestService brandInvestService;

    @RequestMapping(value = "/findList")
    public ResultData findList(Integer pageSize, Integer pageNumber) {
        List<BrandInvest> list = brandInvestService.findList(pageNumber, pageSize);
        List<BrandInvestDTO> newList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(list)) {
            list.forEach(brand-> {
                BrandInvestDTO brandInvestDTO = getDTO(brand);
                newList.add(brandInvestDTO);
            });
        }
        return new ResultData(true, newList);
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int id) {
        BrandInvest brand = brandInvestService.findById(id);
        if(brand != null) {
            BrandInvestDTO brandInvestDTO = getDTO(brand);
            return new ResultData(true, brandInvestDTO);
        }
        return new ResultData(false);
    }

    private BrandInvestDTO getDTO(BrandInvest brand) {
        if(brand != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BrandInvestDTO brandInvestDTO = new BrandInvestDTO();
            BeanUtils.copyProperties(brand, brandInvestDTO);
            brandInvestDTO.setCreateTimeStr(brand.getCreateTime() != null ? sdf.format(brand.getCreateTime()) : "");
            brandInvestDTO.setPublishTimeStr(brand.getPublishTime() != null ? sdf.format(brand.getPublishTime()) : "");
            brandInvestDTO.setExpireDateStr(brand.getExpireDate() != null ? sdf.format(brand.getExpireDate()) : "");
            return brandInvestDTO;
        }
        return null;
    }
}
