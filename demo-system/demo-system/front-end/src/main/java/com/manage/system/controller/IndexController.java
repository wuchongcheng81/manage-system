package com.manage.system.controller;

import com.manage.system.bean.Advertisement;
import com.manage.system.bean.Brand;
import com.manage.system.bean.FocusMap;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.IndexDTO;
import com.manage.system.dto.InformationDTO;
import com.manage.system.dto.InformationFrontDTO;
import com.manage.system.enums.AdvertiseEnum;
import com.manage.system.enums.FocusMapEnum;
import com.manage.system.enums.InformationEnum;
import com.manage.system.response.ResultData;
import com.manage.system.service.AdvertisementService;
import com.manage.system.service.BrandService;
import com.manage.system.service.FocusMapService;
import com.manage.system.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 9:46
 */
@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private FocusMapService focusMapService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/home")
    public ResultData home() {
        List<FocusMap> focusMapList = focusMapService.findList(new FocusMap(FocusMapEnum.INDEX.getCode()));
        List<Advertisement> advertisementList = advertisementService.findList(new Advertisement(AdvertiseEnum.INDEX.getCode()));
        List<BrandDTO> headPolularBrandList = brandService.findPopularList();
        List<BrandDTO> hotWeekPolularBrandList = brandService.findWeekPopular();
        List<InformationFrontDTO> headlineInformationList = informationService.findListByColumnCode(InformationEnum.HEADLINE.getCode());
        List<BrandDTO> brandRandom = brandService.findRandom();

        IndexDTO indexDTO = new IndexDTO();
        indexDTO.setFocusMapList(focusMapList);
        indexDTO.setAdvertisementList(advertisementList);
        indexDTO.setHeadPolularBrandList(headPolularBrandList);
        indexDTO.setHotPolularBrandList(hotWeekPolularBrandList);
        indexDTO.setInformationList(headlineInformationList);
        indexDTO.setBrandRandom(brandRandom);

        return new ResultData(true, indexDTO);
    }
}
