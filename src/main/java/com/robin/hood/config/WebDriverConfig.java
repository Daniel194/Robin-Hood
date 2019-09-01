package com.robin.hood.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Value("${chromdriver.path:}")
    private String chromdriverPath;

    @Bean
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", chromdriverPath);
        return new ChromeDriver();
    }

}
