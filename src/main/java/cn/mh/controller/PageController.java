package cn.mh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/page")
    public String testPage(Model model){

        System.out.println("123");
        return null;
    }
}
