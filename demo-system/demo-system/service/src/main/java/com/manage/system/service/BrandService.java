package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Brand;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:16
 */
public interface BrandService extends BaseService<Brand, Integer> {
    List<Brand> findList(Brand entity);
}
