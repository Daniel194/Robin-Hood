package com.robin.hood.service;

import com.robin.hood.entity.User;
import com.robin.hood.repository.UserRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InstagramNavigator implements Runnable {

    @Value("${instagram.url:}")
    private String instagramUrl;

    @Value("${instagram.user:}")
    private String instagramUser;

    @Value("${instagram.password}")
    private String instagramPassword;

    private WebDriver browser;

    private ExtractUserInformation extractUserInformation;

    private UserRepository repository;

    @Autowired
    public InstagramNavigator(ExtractUserInformation extractUserInformation, WebDriver webDriver, UserRepository repository) {
        this.extractUserInformation = extractUserInformation;
        this.browser = webDriver;
        this.repository = repository;
    }

    @Override
    public void run() {
        goToInstagram();
        connectToInstagram();
        follow();
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

        User user = extractUserInformation.getUser(url);

        repository.save(user).subscribe();

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
