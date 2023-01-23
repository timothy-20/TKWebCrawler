package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TKCrawlingProductDetailServiceImpl implements TKCrawlingService
{
    @Override
    public TKWebDriverTask getWebDriverTask()
    {
        return new TKWebDriverTask((WebDriver webDriver) ->
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

            while (priceParseMatcher.find())
            {
                System.out.println(priceParseMatcher.group());
            }
        });
    }
}
