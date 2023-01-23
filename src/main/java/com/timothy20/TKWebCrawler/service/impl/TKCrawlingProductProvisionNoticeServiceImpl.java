package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TKCrawlingProductProvisionNoticeServiceImpl implements TKCrawlingService
{
    @Override
    public TKWebDriverTask getWebDriverTask()
    {
        return new TKWebDriverTask((WebDriver webDriver) ->
        {
            List<WebElement> provisionNoticeElements = webDriver.findElements(By.cssSelector("#gvnt-info li"));
        });
    }
}
