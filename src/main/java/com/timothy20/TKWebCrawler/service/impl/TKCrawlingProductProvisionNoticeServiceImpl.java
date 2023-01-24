package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.dto.TKProductProvisionNoticeDTO;
import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TKCrawlingProductProvisionNoticeServiceImpl implements TKCrawlingService<List<TKProductProvisionNoticeDTO>>
{
    @Override
    public TKWebDriverTask<List<TKProductProvisionNoticeDTO>> getWebDriverTask(String targetURLString)
    {
        return new TKWebDriverTask<>(targetURLString, (WebDriver webDriver) ->
        {
            List<TKProductProvisionNoticeDTO> results = new ArrayList<>(1);
            List<WebElement> provisionNoticeElements = webDriver.findElements(By.cssSelector("#gvnt-info li"));

            return results;
        });
    }
}
