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
        List<BrandDTO> headBrandList = brandService.findPopularList();
        List<BrandDTO> hotWeekPopularBrandList = brandService.findWeekPopular(0, 5);
        List<InformationFrontDTO> informationList = informationService.findListByColumnCode(InformationEnum.HEADLINE.getCode(), null, null, 0, 5);
        List<BrandDTO> brandRandom = brandService.findRandom();

        IndexDTO indexDTO = new IndexDTO();
        indexDTO.setFocusMapList(focusMapList);
        indexDTO.setAdvertisementList(advertisementList);
        indexDTO.setHeadBrandList(headBrandList);
        indexDTO.setHotBrandList(hotWeekPopularBrandList);
        indexDTO.setInformationList(informationList);
        indexDTO.setBrandRecommend(brandRandom);

        return new ResultData(true, indexDTO);
    }

    /**
     * 品牌推荐
     * @return
     */
    @RequestMapping(value = "/getBrandRecommend")
    public ResultData getBrandRecommend() {
        List<BrandDTO> brandRandom = brandService.findRandom();
        return new ResultData(true, brandRandom);
    }

    /**
     * 查询品牌热榜
     * @param type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getHotBrandList")
    public ResultData getHotBrandList(String type, Integer pageNumber, Integer pageSize) {
        List<BrandDTO> list;
        switch (type) {
            case "week":
                list = brandService.findWeekPopular(pageNumber, pageSize);
                break;
            case "month":
                list = brandService.findMonthPopular(pageNumber, pageSize);
                break;
            default:
                list = brandService.findAllPopular(pageNumber, pageSize);
                break;
        }
        return new ResultData(true, list);
    }

    /**
     * 查询品牌资讯
     * @param type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getInformationList")
    public ResultData getInformationList(String type, Integer pageNumber, Integer pageSize) {
        List<InformationFrontDTO> list;
        switch (type) {
            case "headline":
                list = informationService.findListByColumnCode(InformationEnum.HEADLINE.getCode(), null, null, pageNumber, pageSize);
                break;
            case "evaluate":
                list = informationService.findListByColumnCode(InformationEnum.EVALUATE.getCode(), null, null, pageNumber, pageSize);
                break;
            default:
                list = informationService.findListByColumnCode(null, null, null, pageNumber, pageSize);
                break;
        }
        return new ResultData(true, list);
    }
}
