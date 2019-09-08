package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Website;
import com.manage.system.dao.WebsiteMapper;
import com.manage.system.service.WebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/8 10:16
 */
@Slf4j
@Component
@Transactional
public class WebsiteServiceImpl extends AbstractService<Website, Integer, WebsiteMapper> implements WebsiteService {
    @Override
    public IPage<Website> findPage(Website entity) {
        return null;
    }

    @Override
    public Website getOne() {
        List<Website> list = mapper.selectList(null);
        if(!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
