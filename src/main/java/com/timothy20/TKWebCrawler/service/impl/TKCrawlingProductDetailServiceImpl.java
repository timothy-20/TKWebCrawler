package com.timothy20.TKWebCrawler.service.impl;

import com.timothy20.TKWebCrawler.dto.TKProductDetailDTO;
import com.timothy20.TKWebCrawler.service.TKCrawlingService;
import com.timothy20.TKWebCrawler.service.TKWebDriverTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TKCrawlingProductDetailServiceImpl implements TKCrawlingService<List<TKProductDetailDTO>>
{
    @Override
    public TKWebDriverTask<List<TKProductDetailDTO>> getWebDriverTask(String targetURLString)
    {
        return new TKWebDriverTask<>(targetURLString, (WebDriver webDriver) ->
        {
            WebElement infoElement = webDriver.findElement(By.cssSelector(".wrap-in.info"));
            String brandName = infoElement.findElement(By.tagName("a")).getText();
            String code = infoElement.findElement(By.className("end")).getText();
            String name = infoElement.findElement(By.tagName("h2")).getText();
            String price = infoElement.findElement(By.className("prd-price")).getText();
            Pattern priceParsePattern = Pattern.compile("[0-9\\,]+(?=\\원)");
            Matcher priceParseMatcher = priceParsePattern.matcher(price);
            List<String> prices = new ArrayList<>(2);

            while (priceParseMatcher.find())
                prices.add(priceParseMatcher.group());

            Integer tagPrice = Integer.parseInt(prices.get(prices.size() - 1));
            Integer salePrice = Integer.parseInt(prices.get(0));
            List<TKProductDetailDTO> results = new ArrayList<>(1);

            results.add(new TKProductDetailDTO(brandName, name, code, tagPrice, salePrice));

            return results;
        });
    }
}
