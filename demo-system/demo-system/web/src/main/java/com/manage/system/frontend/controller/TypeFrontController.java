package com.manage.system.frontend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.manage.system.bean.Type;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.TypeDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandService;
import com.manage.system.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/14 14:16
 */
@RestController
@RequestMapping(value = "/api/type")
public class TypeFrontController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;

    /**
     * 查询品牌类别列表（分页）
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(Integer pageNumber, Integer pageSize) {

        Type type = new Type();
        type.setPageNumber(pageNumber);
        type.setPageSize(pageSize);
        IPage iPage = typeService.findPage(type);
        List<TypeDTO> resultList = Lists.newArrayList();
        if(iPage != null && !CollectionUtils.isEmpty(iPage.getRecords())) {
            List<Type> typeList = iPage.getRecords();
            typeList.forEach(
                    t -> {
                        TypeDTO typeDTO = new TypeDTO();
                        BeanUtils.copyProperties(t, typeDTO);
                        List<BrandDTO> brandList = brandService.findPageByTypeId(0, 10, t.getId());
                        typeDTO.setBrandList(brandList);
                        resultList.add(typeDTO);
                    }
            );
        }
        return new ResultData(true, resultList);
    }

    /**
     * 查询所有品牌类别
     * @return
     */
    @RequestMapping(value = "/findAll")
    public ResultData findAll() {
        return new ResultData<>(true, typeService.findAll());
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int typeId) {
        return new ResultData(true, typeService.findById(typeId));
    }
}
