package com.wwb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Frank on 2/29/16.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDemoPage(){
        ModelAndView view = new ModelAndView("demo/demo");
        view.addObject("author","FrankBian");
        System.out.println("request url ~ ");
        return view;
    }
}
