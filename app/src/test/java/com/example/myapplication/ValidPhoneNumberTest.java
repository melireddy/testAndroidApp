package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class ValidPhoneNumberTest {
    @Test
    public void validPhoneNumber_test(){
        ValidPhoneNumber validPhoneNumber = new ValidPhoneNumber();
        Assert.assertTrue(validPhoneNumber.isPhoneNumValid("0824630844"));
        Assert.assertFalse(validPhoneNumber.isPhoneNumValid("sat6666666666666"));
    }
}
