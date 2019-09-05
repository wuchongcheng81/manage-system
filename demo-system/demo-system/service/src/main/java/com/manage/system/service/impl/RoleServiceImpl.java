package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Role;
import com.manage.system.dao.RoleMapper;
import com.manage.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Transactional
public class RoleServiceImpl extends AbstractService<Role, Long, RoleMapper> implements RoleService {
    @Override
    public int save(Role entity) {
        return mapper.insert(entity);
    }

    @Override
    public int update(Role entity) {
        return mapper.updateById(entity);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return mapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int queryTotal(Role entity) {
        return mapper.queryTotal(entity);
    }

    @Override
    public IPage<Role> findPage(Role entity) {
        return null;
    }

    @Override
    public List<Role> queryListByPage(Role entity) {
        return mapper.queryListByPage(entity);
    }

    private QueryWrapper getWrapper(Role entity) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getName()))
            wrapper.like("name", entity.getName());
        return wrapper;
    }
}
