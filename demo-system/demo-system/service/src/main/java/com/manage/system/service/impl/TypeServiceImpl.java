package com.manage.system.service.impl;

import com.manage.system.base.AbstractService;
import com.manage.system.bean.Type;
import com.manage.system.dao.TypeMapper;
import com.manage.system.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class TypeServiceImpl extends AbstractService<Type, TypeMapper> implements TypeService {
}
