package com.manage.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/2 8:45
 */
public enum FocusMapEnum {

    INDEX("index", "首页焦点图"),
    TYPE_RANK("typeRank", "品牌排行焦点图"),
    TYPE_INFORMATION("typeInformation", "品牌资讯焦点图");

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

    FocusMapEnum(String code, String description) {
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
