package com.example.myapplication;


import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        EmailValidator emailValidator = new EmailValidator();
        Assert.assertTrue(emailValidator.isEmailValid("name@email.com"));
    }

}
