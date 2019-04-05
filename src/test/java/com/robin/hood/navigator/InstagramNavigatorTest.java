package com.robin.hood.navigator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InstagramNavigatorTest {

    @Test
    public void shouldRetrievePageTitle() {
        InstagramNavigator navigator = new InstagramNavigator();

        assertThat(navigator.getPageTitle(), is("Instagram"));
    }

}
