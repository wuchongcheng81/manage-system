package com.manage.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wucc
 * @date 2019/8/28 16:52
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/view/{dir}/{templateName}")
    public String view(@PathVariable String dir, @PathVariable String templateName) {
        return dir + "/" + templateName;
    }
}
