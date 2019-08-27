package com.manage.system.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wucc
 * @date 2019/8/26 16:57
 */
@Data
@TableName("bas_currency")
public class BaseCurrency {
    @TableId
    private String id;
    private String code;
    private String numberCode;
    private String name;
    private String symbol;
    @TableField("conversionRMB")
    private String conversionRMB;
    @TableField("conversionRMB")
    private String RMBexchange;
    private String languages;
}
