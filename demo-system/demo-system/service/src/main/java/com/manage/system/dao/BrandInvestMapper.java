package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.BrandInvest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/20 17:48
 */
public interface BrandInvestMapper extends BaseMapper<BrandInvest> {
    List<BrandInvest> findList(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
}
