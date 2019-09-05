package com.manage.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.system.bean.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    int queryTotal(User user);

    List<User> queryList(User user);
}
