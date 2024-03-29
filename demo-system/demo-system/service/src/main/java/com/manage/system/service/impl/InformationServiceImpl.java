package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.manage.system.base.AbstractService;
import com.manage.system.bean.Information;
import com.manage.system.dao.InformationMapper;
import com.manage.system.dto.InformationDTO;
import com.manage.system.dto.InformationFrontDTO;
import com.manage.system.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
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
        QueryWrapper<Information> wrapper = getWrapper(entity);
        IPage<Information> result = mapper.selectPage(new Page<>(entity.getPageNumber(), entity.getPageSize()), wrapper);
        return result;
    }

    @Override
    public List<Information> findListByPage(InformationDTO informationDTO) {
        return mapper.findListByPage(informationDTO);
    }

    @Override
    public int queryTotal(InformationDTO informationDTO) {
        return mapper.queryTotal(informationDTO);
    }

    @Override
    public List<InformationFrontDTO> findListByColumnCode(String columnCode, Integer typeId, Integer brandId, Integer pageNumber, Integer pageSize) {
        if(pageNumber == null)
            pageNumber = 0;
        if(pageSize == null)
            pageSize = 5;
        pageNumber = setPageNumber(pageNumber, pageSize);
        return mapper.findListByColumnCode(columnCode, typeId, brandId, pageNumber, pageSize);
    }

    @Override
    public void addClickNum(int id) {
        mapper.addClickNum(id);
    }

    @Override
    public List<InformationFrontDTO> findLastWeek(Date startTime, Integer typeId,
                                                  Integer brandId, Integer searchCount) {
        if(searchCount == null)
            searchCount = 4;
        List<InformationFrontDTO> list = mapper.findLastWeek(startTime, typeId, brandId);
        List<InformationFrontDTO> newList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(list)) {
            Collections.shuffle(list);
            if(list.size() == 1 || searchCount == 1) {
                if(list.size() == 1) {
                    return list;
                }
                newList.add(list.get(0));
                return newList;
            }
            if(searchCount >= list.size()) {
                searchCount = list.size();
            }
            for(int i = 0; i < searchCount; i++) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    @Override
    public Information findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public int save(Information entity) {
        if(entity.getIsPublish() == 1) {
            entity.setPublishTime(new Date());
        }
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public int update(Information entity) {
        Information i = findById(entity.getId());
        if((i.getIsPublish() == null || i.getIsPublish() != 1) && entity.getIsPublish() == 1) {
            entity.setPublishTime(new Date());
        }
        return super.update(entity);
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
        if(StringUtils.isNotBlank(entity.getColumnCode()))
            wrapper.eq("column_code", entity.getColumnCode());
        wrapper.orderByDesc("");
        return wrapper;
    }
}
