package com.robin.hood.config;

import com.robin.hood.service.InstagramNavigator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class AppConfiguration {

    @Value("${chromdriver.path:}")
    private String chromdriverPath;

    @Autowired
    private InstagramNavigator navigator;

    @Bean
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", chromdriverPath);
        return new ChromeDriver();
    }

    @Bean("simpleTaskExecutor")
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("simpleTaskExecutor") TaskExecutor taskExecutor) {
        return args -> taskExecutor.execute(navigator);
    }

}
