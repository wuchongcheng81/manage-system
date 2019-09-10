package com.manage.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/10 9:52
 */
public enum AdvertiseEnum {

    INDEX("index", "首页广告图"),
    BRAND_INFOR("brandInfor", "品牌资讯广告图"),
    HEAD_INFOR("headInfor", "头条资讯广告图"),
    EVALUATE_INFOR("evaluateInfor", "评测资讯广告图"),
    INFOR_DETAIL("inforDetail", "资讯详情广告图");

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    AdvertiseEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        List<FocusMapEnum> list = Arrays.asList(FocusMapEnum.values());
        for (FocusMapEnum f : list) {
            if(f.getCode().equals(code)) {
                return f.getDescription();
            }
        }
        return "";
    }
}
