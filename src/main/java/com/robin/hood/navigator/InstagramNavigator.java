package com.robin.hood.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public InstagramNavigator() {
        System.setProperty("webdriver.chrome.driver", chromdriverPath);
        browser = new ChromeDriver();
    }

    public String getPageTitle() {
        browser.get(instagramUrl);

        connectToInstagram();

        WebElement href = browser.findElement(By.xpath("//span[@class='glyphsSpriteMobile_nav_type_logo u-__7']"));

        return href.getAttribute("aria-label");
    }

    private void connectToInstagram() {
        browser.findElement(By.xpath("//a[@href='/accounts/login/?source=auth_switcher']")).click();

        browser.findElement(By.xpath("//input[@id='f3ebf37a37b6b94']")).sendKeys(instagramUser);
        browser.findElement(By.xpath("//input[@id='f12c87d31837ca']")).sendKeys(instagramPassword);

        browser.findElement(By.xpath("//button[@class='_0mzm- sqdOP  L3NKy']")).click();
    }

}
