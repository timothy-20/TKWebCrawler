package com.timothy20.TKWebCrawler.controller;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class TKWebDriver
{
    @FunctionalInterface
    interface WebDriverUsageScope
    {
        public void operate(ChromeDriver webDriver);
    }

    private final static String WEB_DRIVER_CLASS = "webdriver.chrome.driver";
    private ChromeDriver webDriver;
    private boolean isWebDriverRunning; //

    public TKWebDriver()
    {
        this.isWebDriverRunning = false;

        try
        {
            this.webDriver = this.PrepareChromeDriver();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public boolean IsRunning()
    {
        return this.isWebDriverRunning;
    }

    public void RunWebDriver(String targetURLString, WebDriverUsageScope completionHandler)
    {
        this.isWebDriverRunning = true;

        this.webDriver.get(URLDecoder.decode(targetURLString, StandardCharsets.UTF_8));
        completionHandler.operate(this.webDriver);
        this.webDriver.quit();
    }

    // Utils
    private ChromeDriver PrepareChromeDriver() throws RuntimeException
    {
        try
        {
            System.setProperty(WEB_DRIVER_CLASS, this.GetWebDriverFilePath());

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

    private String GetWebDriverFilePath() throws RuntimeException
    {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer filePath = new StringBuffer(new File(".").getAbsolutePath());

        if (osName.contains("win"))
            return filePath.append("\\webdriver\\chromedriver.exe").toString();
        else if (osName.contains("mac"))
            return filePath.append("/webdriver/chromedriver").toString();
        else
            throw new RuntimeException("This is unsupported os.");
    }
}
