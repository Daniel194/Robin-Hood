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
        user.setRealName(getRealName());
        user.setDescription(getDescription());
        user.setFollowers(getFollowers());
        user.setFollowing(getFollowing());
        user.setPosts(getPosts());

        return user;
    }

    private String getUserName() {
        return browser.findElements(By.xpath("//div[contains(@class, 'nZSzR')]/h1")).get(0).getText();
    }

    private String getRealName() {
        return browser.findElements(By.xpath("//div[contains(@class, '-vDIg')]/h1")).get(0).getText();
    }

    private String getDescription() {
        try {
            return browser.findElements(By.xpath("//div[contains(@class, '-vDIg')]/span")).get(0).getText();
        } catch (Exception e) {
            return "";
        }
    }

    private Integer getPosts() {
        String posts = browser.findElements(By.xpath("//span[contains(@class, 'g47SY')]")).get(0).getText();

        return convertStringToInteger(posts);
    }

    private Integer getFollowers() {
        String followers = browser.findElements(By.xpath("//span[contains(@class, 'g47SY ')]")).get(1).getAttribute("title");

        return convertStringToInteger(followers);
    }

    private Integer getFollowing() {
        String followers = browser.findElements(By.xpath("//span[contains(@class, 'g47SY ')]")).get(2).getText();

        return convertStringToInteger(followers);
    }

    private Integer convertStringToInteger(String s) {
        s = s.replace(".", "");

        return Integer.decode(s);
    }

}
