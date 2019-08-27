package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.BaseCurrency;
import com.manage.system.dao.BaseCurrencyMapper;
import com.manage.system.service.BaseCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author wucc
 * @date 2019/8/26 17:01
 */
@Slf4j
@Component
@Transactional
public class BaseCurrencyServiceImpl extends AbstractService<BaseCurrency, BaseCurrencyMapper> implements BaseCurrencyService {
    @Override
    public List<BaseCurrency> findList(BaseCurrency entity) {
        QueryWrapper<BaseCurrency> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getName())) {
            wrapper.like("name", entity.getName());
        }
        List<BaseCurrency> list = mapper.selectList(wrapper);
        return list;
    }
}
