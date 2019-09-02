package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.User;
import com.manage.system.dao.UserMapper;
import com.manage.system.service.UserService;
import com.manage.system.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Transactional
public class UserServiceImpl extends AbstractService<User, String, UserMapper> implements UserService {
    @Override
    public int save(User user) {
        Date now = new Date();
        user.setId(StringUtils.isNotBlank(user.getId()) ? user.getId() : uuid());
        user.setStatus(1);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setPassword(MD5Util.getPasswordMD5(user.getPassword()));
        return mapper.insert(user);
    }

    @Override
    public int update(User user) {
        user.setUpdateTime(new Date());
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(MD5Util.getPasswordMD5(user.getPassword()));
        }
        return mapper.updateById(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public IPage<User> findPage(User entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public int queryTotal(User user) {
        QueryWrapper<User> wrapper = getWrapper(user);
        return mapper.selectCount(wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> queryList(User user) {
        QueryWrapper<User> wrapper = getWrapper(user);
        IPage<User> result = mapper.selectPage(new Page<>(user.getPageNumber(), user.getPageSize()), wrapper);
        return result.getRecords();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        QueryWrapper<User> wrapper = getWrapper(user);
        return mapper.selectOne(wrapper);
    }

    @Override
    public int updateRoleId(String userId, Integer roleId) {
        User user = new User();
        user.setId(userId);
        user.setRoleId(roleId.toString());
        return mapper.updateById(user);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return mapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateLogin(String userName) {
        User user = new User();
        user.setLoginTime(new Date());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        return mapper.update(user, wrapper);
    }

    @Override
    public int updatePasswordByUserName(String userName, String password) {
        User user = new User();
        user.setLoginTime(new Date());
        user.setPassword(MD5Util.getPasswordMD5(user.getPassword()));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        return mapper.update(user, wrapper);
    }

    private QueryWrapper getWrapper(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("status", 0);
        if (user.getStatus() != null)
            wrapper.eq("status", user.getStatus());
        if (StringUtils.isNotBlank(user.getUserName()))
            wrapper.like("user_name", user.getUserName());
        if (StringUtils.isNotBlank(user.getEmail()))
            wrapper.like("email", user.getEmail());
        if (StringUtils.isNotBlank(user.getPhone()))
            wrapper.like("phone", user.getPhone());
        if (StringUtils.isNotBlank(user.getId()) && StringUtils.isNotBlank(user.getRoleCode()) && "admin".equals(user.getRoleCode()))
            wrapper.ne("id", user.getId());
        wrapper.orderByDesc("create_time");
        return wrapper;
    }
}
