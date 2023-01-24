package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TKCrawlingProductInformationURLImpl implements TKCrawlingService<List<String>>
{
    @Override
    public TKWebDriverTask<List<String>> getWebDriverTask(String targetURLString)
    {
        return new TKWebDriverTask<>(targetURLString, (WebDriver webDriver) ->
        {
            List<String> results = new ArrayList<>(100);
            WebElement mainElement = webDriver.findElement(By.cssSelector("#main_result_ul"));
            List<WebElement> mainListElement = mainElement.findElements(By.tagName("li"));

            for (WebElement element : mainListElement)
            {
                WebElement productDetailElement = element.findElement(By.className("prd-ele"));

                results.add(productDetailElement.getAttribute("href"));
            }

            return results;
        });
    }
}
