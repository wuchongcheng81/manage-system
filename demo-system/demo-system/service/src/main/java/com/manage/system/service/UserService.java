package com.manage.system.service;

import com.manage.system.bean.User;

import java.util.List;

public interface UserService {

    int save(User user);
    int update(User user);
    User findById(String id);
    int queryTotal(User user);
    List<User> queryList(User user);
    User findByUserName(String userName);
    int updateRoleId(String userId, Integer roleId);
    int deleteByIds(String[] ids);
    int updateLogin(String userName);
    int updatePasswordByUserName(String userName, String password);
}
