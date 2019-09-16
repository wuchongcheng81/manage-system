package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:15
 */
public interface BrandMapper extends BaseMapper<Brand> {
    Brand findById(int id);
    List<BrandDTO> findPopularList();
    List<BrandDTO> findWeekPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<BrandDTO> findMonthPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<BrandDTO> findAllPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<BrandDTO> findRandom();
    List<BrandDTO> findPageByTypeId(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("typeId") Integer typeId);
    List<BrandDTO> findAll(@Param("typeId") Integer typeId);

    int countByTypeId(@Param("typeId") int typeId);
}
