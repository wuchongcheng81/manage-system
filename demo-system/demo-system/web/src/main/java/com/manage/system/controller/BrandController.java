package com.manage.system.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Brand;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:32
 */
@RestController
@RequestMapping(value = "/brand")
public class BrandController extends BaseController<BrandService, Brand, Integer> {

    @RequestMapping(value = "/findListByType")
    public ResultData<List<Brand>> findListByType(Integer typeId) {
        Brand brand = new Brand();
        brand.setTypeId(typeId);
        return new ResultData<>(true, mapper.findList(brand));
    }
}
