package com.manage.system.controller;

import com.manage.system.base.BaseController;
import com.manage.system.bean.FocusMap;
import com.manage.system.service.FocusMapService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wucc
 * @date 2019/9/2 8:41
 */
@RestController
@RequestMapping(value = "/focusMap")
public class FocusMapController extends BaseController<FocusMapService, FocusMap, Integer> {
}
