package com.manage.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wucc
 * @date 2019/8/28 16:52
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/view/{dir}/{templateName}")
    public ModelAndView view(@PathVariable String dir, @PathVariable String templateName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(dir + "/" + templateName);
        return mav;
    }
}
