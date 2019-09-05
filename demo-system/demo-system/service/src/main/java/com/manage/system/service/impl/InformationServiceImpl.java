package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Information;
import com.manage.system.dao.InformationMapper;
import com.manage.system.dto.InformationDTO;
import com.manage.system.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcc
 * @date 2019/9/4 21:56
 */
@Slf4j
@Component
@Transactional
public class InformationServiceImpl extends AbstractService<Information, Integer, InformationMapper> implements InformationService {
    @Override
    @Transactional(readOnly = true)
    public IPage<Information> findPage(Information entity) {
        return null;
    }

    private QueryWrapper getWrapper(Information entity) {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del", 0);
        if (entity.getId() != null)
            wrapper.eq("id", entity.getId());
        if(StringUtils.isNotBlank(entity.getTitle()))
            wrapper.eq("title", entity.getTitle());
        if(entity.getTypeId() != null)
            wrapper.eq("type_id", entity.getTypeId());
        return wrapper;
    }

    @Override
    public List<Information> findListByPage(InformationDTO informationDTO) {
        return mapper.findListByPage(informationDTO);
    }

    @Override
    public int queryTotal(InformationDTO informationDTO) {
        return mapper.queryTotal(informationDTO);
    }
}
