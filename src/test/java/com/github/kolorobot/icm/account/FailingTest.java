package com.github.kolorobot.icm.account;

import org.junit.Assert;
import org.junit.Test;

public class FailingTest {
    @Test
    public void shouldFail() throws Exception {
        Assert.assertTrue("AssertionError: True is not false", true);
    }
}
