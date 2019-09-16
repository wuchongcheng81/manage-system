package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Consult;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/9 17:20
 */
public interface ConsultService extends BaseService<Consult, Integer> {
    List<Consult> findByIpAndType(String ip, String type);
}
