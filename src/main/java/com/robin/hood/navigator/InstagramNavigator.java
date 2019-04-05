package com.robin.hood.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramNavigator {
    private static final String INSTAGRAM_URL = "https://www.instagram.com/";
    private static final String CHROMDRIVER_PATH = "/Users/daniellungu/Workspace/robin-hood/chromedriver";

    public String getPageTitle() {
        System.setProperty("webdriver.chrome.driver", CHROMDRIVER_PATH);
        WebDriver browser = new ChromeDriver();
        browser.get(INSTAGRAM_URL);
        WebElement href = browser.findElement(By.xpath("//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']"));

        return href.getText();
    }
}
