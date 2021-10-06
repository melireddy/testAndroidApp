package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class ValidIDNumberTest {
    @Test
    public void validID_test() {
        ValidIDNumber validIDNumber = new ValidIDNumber();
        Assert.assertTrue(validIDNumber.isIDValid("9911290195083"));
        Assert.assertFalse(validIDNumber.isIDValid("abc01"));
    }
}
