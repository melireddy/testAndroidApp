package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class ValidNameTest {
    ValidName validName = new ValidName();
    @Test
    public void validName_test(){
        Assert.assertTrue(validName.isNameValid("Thomas"));
        Assert.assertFalse(validName.isNameValid("Thomas123"));
    }
    @Test
    public void twoNames_test(){
        Assert.assertTrue(validName.validTwoNames("Thomas James"));
        Assert.assertFalse(validName.validTwoNames("Thomas 123"));
    }
}
