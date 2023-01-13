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

import java.io.File;
import java.net.FileNameMap;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TKWebCrawlerController
{
    private TKWebDriver webDriver;

    public TKWebCrawlerController()
    {
        try
        {
            this.webDriver = new TKWebDriver();
        }
        catch (RuntimeException exception)
        {
            exception.printStackTrace();
        }
    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> ReceiveTargetURLString(@RequestBody String targetURLString)
    {
        ResponseEntity<Map<String, Object>> response;

        try
        {
            this.webDriver.RunWebDriver(targetURLString, (ChromeDriver chromeDriver) ->
            {
                WebElement mainElement = chromeDriver.findElement(By.cssSelector("#main_result_ul"));
                List<WebElement> mainListElement = mainElement.findElements(By.tagName("li"));
                List<Map<String, Object>> productInfos = new ArrayList<>();

                for (WebElement element : mainListElement)
                {
                    WebElement productDetailElement = element.findElement(By.className("prd-ele"));
                    WebElement productInfoElement = productDetailElement.findElement(By.className("prd-info"));
                    String productDetailURLString = productDetailElement.getAttribute("href");
                    String productBrand = productInfoElement.findElement(By.className("prd-brand")).getText();
                    String productName = productInfoElement.findElement(By.className("prd-name")).getText();
                    String productPrice = productInfoElement.findElement(By.className("prd-price")).getText();

                    System.out.println("Product detail url: " + productDetailURLString);
                    System.out.println("Product brand: " + productBrand);
                    System.out.println("Product name: " + productName);
                    System.out.println("Product price: " + productPrice);
                    System.out.println("==================================================");

                    productInfos.add(Map.of());
                }
            });

            response = new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException runtimeException)
        {
            runtimeException.printStackTrace();

            response = new ResponseEntity<>(Map.of("reason", runtimeException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
