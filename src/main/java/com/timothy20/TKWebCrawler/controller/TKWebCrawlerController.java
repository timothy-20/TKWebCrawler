package com.timothy20.TKWebCrawler.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.net.FileNameMap;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class TKWebCrawlerController
{
    private final static String TASK_NAME = "TK_WEBDRIVER_TASK";

    public TKWebCrawlerController()
    {
    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> ReceiveTargetURLString(@RequestBody Map<String, Object> requestBody)
    {
        try
        {
            System.out.println(requestBody);

//            this.CrawlingProductDetailURL(targetURLString);
            this.CrawlingProductDetailInformation((String) requestBody.get("url"));

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();

            return new ResponseEntity<>(Map.of("reason", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void CrawlingProductDetailURL(String targetURLString)
    {
        List<String> productInfoURLs = new ArrayList<>();
        TKWebDriverTask task = new TKWebDriverTask((WebDriver webDriver) ->
        {
            WebElement mainElement = webDriver.findElement(By.cssSelector("#main_result_ul"));
            List<WebElement> mainListElement = mainElement.findElements(By.tagName("li"));

            for (WebElement element : mainListElement)
            {
                WebElement productDetailElement = element.findElement(By.className("prd-ele"));
                String productDetailURLString = productDetailElement.getAttribute("href");

                System.out.println("Product detail url: " + productDetailURLString);
                System.out.println("==================================================");

                TKWebDriverTask internalTask = new TKWebDriverTask((WebDriver internalWebDriver) ->
                {

                });


                productInfoURLs.add(productDetailURLString);
            }
        });

        task.setName(TASK_NAME);
        task.start(targetURLString);
    }

    private void CrawlingProductDetailInformation(String targetURLString)
    {
        TKWebDriverTask task = new TKWebDriverTask((WebDriver webDriver) ->
        {
            WebElement infoElement = webDriver.findElement(By.cssSelector(".wrap-in.info"));
            String brandName = infoElement.findElement(By.tagName("a")).getText();
            String code = infoElement.findElement(By.className("end")).getText();
            String name = infoElement.findElement(By.tagName("h2")).getText();
            String price = infoElement.findElement(By.className("prd-price")).getText();
            String tagPrice = null;
            String salePrice = null;

            System.out.println(brandName);
            System.out.println(code);
            System.out.println(name);

            Pattern priceParsePattern = Pattern.compile("[0-9\\,]+(?=\\원)");
            Matcher priceParseMatcher = priceParsePattern.matcher(price);
            Integer count = 0;

            while (priceParseMatcher.find())
            {

                count++;
            }
                System.out.println(priceParseMatcher.group());

//            List<WebElement> provisionNoticeElements = webDriver.findElements(By.cssSelector("#gvnt-info li"));

        });

        task.start(targetURLString);
    }
}
