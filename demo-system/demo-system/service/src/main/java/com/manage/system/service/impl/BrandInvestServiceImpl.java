package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BrandInvest;
import com.manage.system.dao.BrandInvestMapper;
import com.manage.system.service.BrandInvestService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wucc
 * @date 2019/9/20 17:50
 */
public class BrandInvestServiceImpl extends AbstractService<BrandInvest, Integer, BrandInvestMapper> implements BrandInvestService {

    @Override
    public IPage<BrandInvest> findPage(BrandInvest entity) {
        QueryWrapper<BrandInvest> wrapper = getWrapper(entity);
        IPage<BrandInvest> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    private QueryWrapper getWrapper(BrandInvest entity) {
        QueryWrapper<BrandInvest> wrapper = new QueryWrapper<>();
        if (entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        if(StringUtils.isNotBlank(entity.getTitle())) {
            wrapper.like("title", entity.getTitle());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }
}
