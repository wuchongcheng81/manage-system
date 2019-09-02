package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.FocusMap;
import com.manage.system.dao.FocusMapMapper;
import com.manage.system.service.FocusMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wucc
 * @date 2019/9/2 8:38
 */
@Slf4j
@Component
@Transactional
public class FocusMapServiceImpl extends AbstractService<FocusMap, Integer, FocusMapMapper> implements FocusMapService {
    @Override
    @Transactional(readOnly = true)
    public IPage<FocusMap> findPage(FocusMap entity) {
        QueryWrapper<FocusMap> wrapper = getWrapper(entity);
        IPage<FocusMap> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    private QueryWrapper getWrapper(FocusMap entity) {
        QueryWrapper<FocusMap> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", 0);
        if (entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}
