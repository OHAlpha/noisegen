package com.onewolf.alpha.lib.noisegen;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;

import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class UtilPowerTest {
     
    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][] {
            { 2, 2 },
            { 6, 4 },
            { 19, 16 },
            { 22, 16 },
            { 23, 16 },
            { 24, 16 },
            { 16, 16 },
            { 50, 32 }
        });
    }
    
    private int n;
    
    private int power;
    
    public UtilPowerTest(int n, int power) {
         this.n = n;
         this.power = power;
    }

    @Test
    public void testPower() 
    {
        int actual = Util.greatestPower(n);
        Assert.assertEquals(actual,power);
    }
}