package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Brand;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.BrandParamDTO;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/6 13:16
 */
public interface BrandService extends BaseService<Brand, Integer> {
    List<Brand> findList(Brand entity);
    List<BrandDTO> findPopularList();
    List<BrandDTO> findWeekPopular(Integer pageNumber, Integer pageSize);
    List<BrandDTO> findMonthPopular(Integer pageNumber, Integer pageSize);
    List<BrandDTO> findAllPopular(Integer pageNumber, Integer pageSize, Integer typeId);
    List<BrandDTO> findRandom(BrandParamDTO param);
    List<BrandDTO> findPageByTypeId(Integer pageNumber, Integer pageSize, Integer typeId);
    List<BrandDTO> findAll(Integer typeId);

    int countByTypeId(int typeId);
    Brand getByIdWithType(int id);

    List<BrandDTO> findByKeyWord(String keyWord, Integer pageNumber, Integer pageSize);

    void addPopularById(int id);

    int addPopularByIdAndPopular(int brandPopular, int id);
}
