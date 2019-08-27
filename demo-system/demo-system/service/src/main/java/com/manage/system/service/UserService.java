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
    int uploadRoleId(String userId, String roleId);
    int deleteByIds(String[] ids);
    int uploadLogin(String userName);
    int uploadPasswordByUserName(String userName, String password);
}
