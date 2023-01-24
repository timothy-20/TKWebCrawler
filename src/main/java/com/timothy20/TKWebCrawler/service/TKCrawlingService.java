package com.timothy20.TKWebCrawler.service;

public interface TKCrawlingService <T>
{
    TKWebDriverTask<T> getWebDriverTask(String targetURLString);
}
