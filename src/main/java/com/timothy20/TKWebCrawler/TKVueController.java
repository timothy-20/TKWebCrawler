package com.timothy20.TKWebCrawler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TKVueController {
    @GetMapping("/vue")
    public String vue() {
        return "vue/index";
    }
}
