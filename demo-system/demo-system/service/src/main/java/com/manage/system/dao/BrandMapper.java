package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.BrandParamDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:15
 */
public interface BrandMapper extends BaseMapper<Brand> {
    Brand findById(int id);
    List<BrandDTO> findPopularList(@Param("isPublish") Integer isPublish);
    List<BrandDTO> findWeekPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("isPublish") Integer isPublish);
    List<BrandDTO> findMonthPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("isPublish") Integer isPublish);
    List<BrandDTO> findAllPopular(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("isPublish") Integer isPublish, @Param("typeId") Integer typeId);
    List<BrandDTO> findRandom(BrandParamDTO param);
    List<BrandDTO> findPageByTypeId(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("typeId") Integer typeId, @Param("isPublish") Integer isPublish);
    List<BrandDTO> findAll(@Param("typeId") Integer typeId, @Param("isPublish") Integer isPublish);

    Brand getByIdWithType(@Param("id") int id, @Param("isPublish") Integer isPublish);

    int countByTypeId(@Param("typeId") int typeId);

    List<BrandDTO> findByKeyWord(@Param("keyWord") String keyWord, @Param("isPublish") Integer isPublish, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

    void addPopularById(@Param("id") int id);

    int addPopularByIdAndPopular(@Param("brandPopular") int brandPopular, @Param("id") int id);

    int countByTypeParentId(@Param("typeId") int typeId);

}
