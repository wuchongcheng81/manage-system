package com.manage.system.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.Advertisement;
import com.manage.system.service.AdvertisementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/advertisement")
public class AdvertisementController extends BaseController<AdvertisementService, Advertisement, Integer> {

}
