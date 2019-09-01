package com.robin.hood.service;

import com.robin.hood.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractUserInformation {

    private WebDriver browser;

    @Autowired
    public ExtractUserInformation(WebDriver browser) {
        this.browser = browser;
    }

    public User getUser(String url) {
        User user = new User();
        user.setProfileLink(url);
        user.setUserName(getUserName());

        return user;
    }

    private String getUserName() {
        return browser.findElements(By.xpath("//div[contains(@class, 'nZSzR')]/h1")).get(0).getText();
    }

}
