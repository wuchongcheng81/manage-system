package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:15
 */
public interface BrandMapper extends BaseMapper<Brand> {
    Brand findById(int id);
    List<BrandDTO> findPopularList();
    List<BrandDTO> findWeekPopular();
    List<BrandDTO> findMonthPopular();
    List<BrandDTO> findAllPopular();
    List<BrandDTO> findRandom();
}
