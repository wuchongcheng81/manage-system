package com.manage.system.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author wucc
 * @date 2019/9/2 8:45
 */
public enum InformationEnum {

    HEADLINE("headline", "头条"),
    EVALUATE("evaluate", "评测");

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

    InformationEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescription(String code) {
        List<InformationEnum> list = Arrays.asList(InformationEnum.values());
        for (InformationEnum f : list) {
            if(f.getCode().equals(code)) {
                return f.getDescription();
            }
        }
        return "";
    }
}
