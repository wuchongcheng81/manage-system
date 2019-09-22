package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.BrandInvest;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/20 17:49
 */
public interface BrandInvestService extends BaseService<BrandInvest, Integer> {
    List<BrandInvest> findList(Integer pageNumber, Integer pageSize);
}
