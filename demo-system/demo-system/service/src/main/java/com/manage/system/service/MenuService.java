package com.manage.system.service;

import com.manage.system.bean.Menu;

import java.util.List;

public interface MenuService {
    int save(Menu menu);
    int update(Menu menu);
    int deleteByIds(String[] ids);
    int queryTotal(Menu entity);
    List<Menu> queryList();
    List<Menu> queryListByPage(Menu entity);
    List<Menu> findByUserId(String userId);
}
