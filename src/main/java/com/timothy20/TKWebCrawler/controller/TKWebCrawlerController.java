package com.timothy20.TKWebCrawler.controller;

import com.timothy20.TKWebCrawler.dto.TKProductInformationURLQueryDTO;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import com.timothy20.TKWebCrawler.service.impl.TKCrawlingProductInformationURLImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TKWebCrawlerController
{
    private final static String TASK_NAME = "TK_WEBDRIVER_TASK";
    private TKCrawlingProductInformationURLImpl service;

    @Autowired
    public TKWebCrawlerController(TKCrawlingProductInformationURLImpl service)
    {
        this.service = service;
    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> ReceiveTargetURLString(@RequestBody Map<String, Object> requestBody)
    {
        try
        {
            String urlString = (String) requestBody.get("url");
            TKProductInformationURLQueryDTO urlQuery = new TKProductInformationURLQueryDTO(urlString);

            TKWebDriverTask<List<String>> task = this.service.getWebDriverTask(urlString);

            task.call().forEach((String u) ->
            {

            });
//
//            System.out.println("Result: " + urls);
//            System.out.println("Result count: " + urls.size());

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();

            return new ResponseEntity<>(Map.of("reason", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
