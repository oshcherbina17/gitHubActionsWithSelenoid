package org.demo;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;  

public class SelenoidTest {
   // private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Logger logger = LogManager.getLogger(SelenoidTest.class);  

    public String url = "https://rozetka.com.ua/ua/";

    @Test
    public void testTitleText() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.get(url);
        
        String title = driver.getTitle();
        String expectedTitle = "Інтернет-магазин ROZETKA™: офіційний сайт найпопулярнішого онлайн-гіпермаркету в Україні";
        
        int maxWaitTime = 10;

        WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        
        System.out.println("its title: " + title);
        
        Assert.assertEquals(title, expectedTitle, "Title text not equals as expected");
       // LOGGER.info("All ok.");
        BasicConfigurator.configure();  
        logger.info("Hello world");          
        //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File("reports/screenshot.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        driver.quit();
    }
}
