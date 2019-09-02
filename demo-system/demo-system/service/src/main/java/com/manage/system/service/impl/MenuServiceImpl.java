package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Menu;
import com.manage.system.dao.MenuMapper;
import com.manage.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Transactional
public class MenuServiceImpl extends AbstractService<Menu, Integer, MenuMapper> implements MenuService {
    @Override
    public int save(Menu menu) {
        return mapper.insert(menu);
    }

    @Override
    public int update(Menu menu) {
        return mapper.updateById(menu);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return mapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int queryTotal(Menu entity) {
        QueryWrapper<Menu> wrapper = getWrapper(entity);
        return mapper.selectCount(wrapper);
    }

    @Override
    public IPage<Menu> findPage(Menu entity) {
        return null;
    }

    @Override
    public List<Menu> queryList() {
        Menu menu = new Menu();
        menu.setLevel(0);
        List<Menu> parent = mapper.selectList(getWrapper(menu));
        menu.setLevel(1);
        List<Menu> child = mapper.selectList(getWrapper(menu));
        parent.forEach(p -> {
            List<Menu> childMenu = new ArrayList<>();
            child.forEach(c -> {
                if (p.getId() == c.getParentId()) {
                    childMenu.add(c);
                }
            });
            p.setChilds(childMenu);
        });
        return parent;
    }

    @Override
    public List<Menu> queryListByPage(Menu entity) {
        QueryWrapper<Menu> wrapper = getWrapper(entity);
        IPage<Menu> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result.getRecords();
    }

    @Override
    public List<Menu> findByUserId(String userId) {
        List<Menu> menus = new ArrayList<>();

        List<Menu> menuEntities = mapper.findByUserId(userId);
        menuEntities.forEach(m -> {
            if (m.getLevel() == 0) {
                menus.add(m);
            }
        });
        menuEntities.removeAll(menus);

        menus.forEach(m -> {
            List<Menu> childs = new ArrayList<>();
            menuEntities.forEach(c -> {
                if (c.getParentId() == m.getId()) {
                    childs.add(c);
                }
            });
            m.setChilds(childs);
        });

        return menus;
    }

    private QueryWrapper getWrapper(Menu menu) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (menu.getLevel() != null)
            wrapper.eq("level", menu.getLevel());
        if (menu.getParentId() != null)
            wrapper.eq("parent_id", menu.getParentId());
        if (StringUtils.isNotBlank(menu.getName()))
            wrapper.like("name", menu.getName());
        wrapper.orderByAsc("sort");
        return wrapper;
    }
}
