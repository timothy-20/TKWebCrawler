package com.timothy20.TKWebCrawler.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TKWebCrawlerController
{
    private WebDriver webDriver;

    public TKWebCrawlerController()
    {
        try
        {
            this.webDriver = PrepareChromeDriver();
        }
        catch (RuntimeException exception)
        {
            exception.printStackTrace();
        }
    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> ReceiveTargetURL(@RequestBody String targetURL)
    {
        ResponseEntity<Map<String, Object>> response;

        try
        {
            this.webDriver.get(URLDecoder.decode(targetURL, "UTF-8"));

            WebElement mainElement = this.webDriver.findElement(By.cssSelector("#main_result_ul"));
            List<WebElement> mainListElement = mainElement.findElements(By.tagName("li"));

            for (WebElement element : mainListElement)
            {
                System.out.println("value: " + element.getText());

                // ...
            }

            response = new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception)
        {
            HashMap<String, Object> body = new HashMap<>(1);

            body.put("reason", exception.getMessage());
            exception.printStackTrace();

            response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        finally
        {
            this.webDriver.quit();
        }

        return response;
    }

    // Utils
    private final static String WEB_DRIVER_CLASS = "webdriver.chrome.driver";
    private final static String WEB_DRIVER_PATH = "/Users/imjeong-un/Documents/개인 프로젝트/Java Projects/TKWebCrawler/webdriver/chromedriver";

    private ChromeDriver PrepareChromeDriver() throws RuntimeException
    {
        try
        {
            System.setProperty(WEB_DRIVER_CLASS, WEB_DRIVER_PATH);

            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.setHeadless(true);
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(10));

            return new ChromeDriver(chromeOptions);
        }
        catch (RuntimeException exception)
        {
            exception.printStackTrace();
            throw exception;
        }
    }
}
