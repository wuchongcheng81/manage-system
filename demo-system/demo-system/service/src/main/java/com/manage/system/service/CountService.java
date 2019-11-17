package com.manage.system.service;

import com.manage.system.dto.CountDetailDTO;
import com.manage.system.dto.CountListDetailDTO;

/**
 * @author wcc
 * @date 2019/11/17 13:13
 */
public interface CountService {

    CountDetailDTO getCount(Integer brandId);

    CountListDetailDTO getCountList(int beforeDay, Integer brandId);
}
