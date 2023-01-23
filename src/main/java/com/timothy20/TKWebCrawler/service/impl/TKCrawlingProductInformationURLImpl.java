package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TKCrawlingProductInformationURLImpl implements TKCrawlingService
{
    @Override
    public TKWebDriverTask getWebDriverTask()
    {
        return new TKWebDriverTask((WebDriver webDriver) ->
        {
            WebElement mainElement = webDriver.findElement(By.cssSelector("#main_result_ul"));
            List<WebElement> mainListElement = mainElement.findElements(By.tagName("li"));

            for (WebElement element : mainListElement)
            {
                WebElement productDetailElement = element.findElement(By.className("prd-ele"));
                String productDetailURLString = productDetailElement.getAttribute("href");

                System.out.println("Product detail url: " + productDetailURLString);
                System.out.println("==================================================");
            }
        });
    }
}
