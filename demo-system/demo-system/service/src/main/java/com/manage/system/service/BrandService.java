package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:16
 */
public interface BrandService extends BaseService<Brand, Integer> {
    List<Brand> findList(Brand entity);
    List<BrandDTO> findPopularList();
    List<BrandDTO> findWeekPopular();
    List<BrandDTO> findMonthPopular();
    List<BrandDTO> findAllPopular();
    List<BrandDTO> findRandom();
}
