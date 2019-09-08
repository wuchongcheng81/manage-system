package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Website;

/**
 * @author wcc
 * @date 2019/9/8 10:16
 */
public interface WebsiteService extends BaseService<Website, Integer> {
    Website getOne();
}
