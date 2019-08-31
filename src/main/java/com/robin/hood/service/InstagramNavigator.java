package com.robin.hood.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public boolean navigate() {
        goToInstagram();
        connectToInstagram();
        follow();

        return true;
    }

    private void goToInstagram() {
        browser.get(instagramUrl);

        waitFor(5);
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

    private void follow() {
        browser.findElement(By.xpath("//a[@href='/explore/people/']")).click();

        waitFor(5);

        List<String> urls = getUrls(browser.findElements(By.xpath("//a")));

        urls.forEach(this::follow);

    }

    private List<String> getUrls(List<WebElement> hrefs) {
        return hrefs.stream()
                .map(href -> href.getAttribute("href"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void follow(String url) {
        browser.get(url);

        waitFor(5);

        browser.findElements(By.xpath("//button")).get(0).click();

        browser.navigate().back();

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
