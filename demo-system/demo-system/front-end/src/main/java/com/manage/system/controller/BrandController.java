package com.manage.system.controller;

import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.BrandDetailDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/15 10:50
 */
@RestController
@RequestMapping(value = "/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     *  查询品牌列表（分页）
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(Integer typeId, Integer pageNumber, Integer pageSize) {
        List<BrandDTO> brandList = brandService.findPageByTypeId(pageNumber, pageSize, typeId);
        return new ResultData(true, brandList);
    }

    @RequestMapping(value = "/findAll")
    public ResultData findAll(Integer typeId) {
        List<BrandDTO> brandList = brandService.findAll(typeId);
        return new ResultData(true, brandList);
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int id) {
        Brand brand = brandService.findById(id);
        BrandDetailDTO brandDetailDTO = new BrandDetailDTO();
        BeanUtils.copyProperties(brand, brandDetailDTO);
        return new ResultData(true, brandDetailDTO);
    }

}
