package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class isPasswordHashedTest {
    @Test
    public void encryptPass_test() throws Exception {
        isPasswordHashed isPasswordHashed = new isPasswordHashed();
        Assert.assertTrue(isPasswordHashed.isHashed(isPasswordHashed.hashPass("Tangerines123"),"Tangerines123"));
        Assert.assertFalse(isPasswordHashed.isHashed("Tangerines123","Tangerines123"));
    }

    @Test
    public void decryptPass_test() throws Exception{
        isPasswordHashed isPasswordHashed = new isPasswordHashed();
        Assert.assertTrue(isPasswordHashed.isDecrypted(isPasswordHashed.hashPass("PineappleApplePen345"),"PineappleApplePen345"));
        Assert.assertFalse(isPasswordHashed.isDecrypted(isPasswordHashed.hashPass("PineappleApplePen345"),"PineappleApplePen"));
    }
}
