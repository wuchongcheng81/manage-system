package com.manage.system.backend.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Type;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandService;
import com.manage.system.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wucc
 * @date 2019/8/30 17:37
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController extends BaseController<TypeService, Type, Integer> {

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "/findAll")
    public ResultData<List<Type>> findAll() {
        return new ResultData<>(true, mapper.findAll());
    }

    @GetMapping(value = "/findAllList")
    public List<Type> findAllList() {
        return mapper.findAllWithBrandCount();
    }

    @GetMapping(value = "/findAllParent")
    public List<Type> findAllParent() { return mapper.findAllParent();}

    @PostMapping(value = "/deleteParent")
    public ResultData deleteParent(int id) {
        int brandCount = brandService.countByTypeParentId(id);
        if(brandCount > 0)
            return new ResultData(false, null, "该父级的子分类下还关联品牌，无法直接删除！");
        Integer typeCount = mapper.findListByParentId(id);
        if(typeCount != null && typeCount > 0)
            return new ResultData(false, null, "该父级还存在子分类，无法直接删除！");
        mapper.delete(id);
        return new ResultData(true);
    }

    @PostMapping(value = "/deleteType")
    public ResultData deleteType(int id) {
        int brandCount = brandService.countByTypeId(id);
        if(brandCount > 0)
            return new ResultData(false, null, "该分类下还关联品牌，无法直接删除！");
        mapper.delete(id);
        return new ResultData(true);
    }
}
