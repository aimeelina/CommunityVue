package com.aimeelina.communityvue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pageController {
    @RequestMapping(path = "/newpage", method = RequestMethod.GET)
    public ModelAndView getNewPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","aimeelina");
        mav.addObject("sex","23");
        mav.setViewName("/homePage");
        return mav;
    }
}
