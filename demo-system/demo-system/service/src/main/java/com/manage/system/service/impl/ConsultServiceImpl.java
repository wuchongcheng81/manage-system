package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Consult;
import com.manage.system.dao.ConsultMapper;
import com.manage.system.service.ConsultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/9 17:21
 */
@Slf4j
@Component
@Transactional
public class ConsultServiceImpl extends AbstractService<Consult, Integer, ConsultMapper> implements ConsultService {
    @Override
    public IPage<Consult> findPage(Consult entity) {
        QueryWrapper<Consult> wrapper = getWrapper(entity);
        IPage<Consult> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    @Override
    public int save(Consult entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public int update(Consult entity) {
        entity.setUpdateTime(new Date());
        return super.update(entity);
    }

    @Override
    public List<Consult> findByIpAndType(String ip, String type) {
        Consult consult = new Consult();
        consult.setRequestIp(ip);
        consult.setType(type);
        QueryWrapper<Consult> wrapper = getWrapper(consult);
        return mapper.selectList(wrapper);
    }

    private QueryWrapper getWrapper(Consult entity) {
        QueryWrapper<Consult> wrapper = new QueryWrapper<>();
        if (entity.getId() != null) {
            wrapper.eq("id", entity.getId());
        }
        if(StringUtils.isNotBlank(entity.getCoustomerName())) {
            wrapper.like("coustomer_name", entity.getCoustomerName());
        }
        if(StringUtils.isNotBlank(entity.getType())) {
            wrapper.eq("type", entity.getType());
        }
        if(StringUtils.isNotBlank(entity.getRequestIp())) {
            wrapper.eq("request_ip", entity.getRequestIp());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

}
