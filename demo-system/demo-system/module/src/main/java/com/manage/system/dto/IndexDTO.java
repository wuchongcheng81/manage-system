package com.manage.system.dto;

import com.manage.system.bean.Advertisement;
import com.manage.system.bean.FocusMap;
import lombok.Data;

import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 17:04
 */
@Data
public class IndexDTO {
    private List<FocusMap> focusMapList;
    private List<Advertisement> advertisementList;
    private List<BrandDTO> headBrandList;
    private List<BrandDTO> hotBrandList;
    private List<InformationFrontDTO> informationList;
    private List<BrandDTO> brandRecommend;
}
