package com.timothy20.TKWebCrawler.service;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class TKWebDriverTask extends Thread
{
    @FunctionalInterface
    public interface CompletionHandler
    {
        void handler(WebDriver webDriver);
    }

    private final static String WEB_DRIVER_CLASS = "webdriver.chrome.driver";
    private final CompletionHandler completionHandler;
    private String targetURLString;

    public TKWebDriverTask(CompletionHandler completionHandler)
    {
        this.completionHandler = completionHandler;
    }

    public synchronized void start(String targetURLString) {
        this.targetURLString = targetURLString;

        super.start();
    }

    @Override
    public void run()
    {
        ChromeDriver chromeDriver = null;

        try
        {
            if (this.targetURLString == null)
                throw new RuntimeException("Target url is null");

            chromeDriver = this.PrepareChromeDriver();

            chromeDriver.get(URLDecoder.decode(this.targetURLString, StandardCharsets.UTF_8));
            this.completionHandler.handler(chromeDriver);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            chromeDriver.quit();
        }
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
            chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(5));

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
