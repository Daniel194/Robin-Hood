package com.robin.hood.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InstagramNavigator {

    @Value("${instagram.url:}")
    private String instagramUrl;

    @Value("${instagram.user:}")
    private String instagramUser;

    @Value("${instagram.password}")
    private String instagramPassword;

    @Value("${chromdriver.path:}")
    private String chromdriverPath;

    private WebDriver browser;

    @PostConstruct
    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", chromdriverPath);
        browser = new ChromeDriver();
    }

    public String getPageTitle() {
        browser.get(instagramUrl);

        waitFor(5);

        connectToInstagram();

        WebElement href = browser.findElement(By.xpath("//span[@class='glyphsSpriteMobile_nav_type_logo u-__7']"));

        return href.getAttribute("aria-label");
    }

    private void connectToInstagram() {
        browser.findElement(By.xpath("//a[@href='/accounts/login/?source=auth_switcher']")).click();

        waitFor(5);

        List<WebElement> inputs = browser.findElements(By.xpath("//input"));

        inputs.get(0).sendKeys(instagramUser);
        inputs.get(1).sendKeys(instagramPassword);

        browser.findElement(By.xpath("//button[@type='submit']")).click();

        waitFor(5);
    }


    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
