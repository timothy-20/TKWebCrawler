package com.timothy20.TKWebCrawler.controller;

import com.timothy20.TKWebCrawler.TKWebCrawlerApplication;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Controller
public class TKWebCrawlerController {
    final Logger logger = (Logger) LogManager.getLogger(TKWebCrawlerController.class);
    final WebDriver webDriver;

    public TKWebCrawlerController() {
        System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver");

        ChromeOptions options = new ChromeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setImplicitWaitTimeout(Duration.ofSeconds(2));
        options.setHeadless(true);

        this.webDriver = new ChromeDriver(options);
    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> receiveTargetURL(@RequestBody String targetURL) {
        ResponseEntity<Map<String, Object>> response;

        try {
            this.webDriver.get(targetURL);
            response = new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception exception) {
            HashMap<String, Object> body = new HashMap<>(1);

            body.put("reason", exception.getMessage());

            response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        } finally {
            this.webDriver.quit();
        }

        return response;
    }

}
