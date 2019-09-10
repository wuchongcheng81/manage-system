package com.manage.system.service;

import com.manage.system.base.BaseService;
import com.manage.system.bean.Advertisement;

import java.util.List;

public interface AdvertisementService extends BaseService<Advertisement, Integer> {
    List<Advertisement> findList(Advertisement entity);
}
