package com.robin.hood.service;

import com.robin.hood.RobinHoodApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RobinHoodApplication.class)
public class InstagramNavigatorTest {

    @Autowired
    private InstagramNavigator navigator;

    @Test
    public void shouldRetrievePageTitle() {
        assertThat(navigator.navigate(), is(true));
    }

}
