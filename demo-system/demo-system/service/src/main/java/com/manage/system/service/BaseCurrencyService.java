package com.manage.system.service;

import com.manage.system.bean.BaseCurrency;

import java.util.List;

/**
 * @author wucc
 * @date 2019/8/26 17:01
 */
public interface BaseCurrencyService {
    List<BaseCurrency> findList(BaseCurrency entity);
}
