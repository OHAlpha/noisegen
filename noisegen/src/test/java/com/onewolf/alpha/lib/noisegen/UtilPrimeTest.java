package com.onewolf.alpha.lib.noisegen;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;

import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class UtilPrimeTest {
     
    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][] {
            { 2, new int[] { 2, 1 } },
            { 6, new int[] { 2, 1, 3, 1 } },
            { 19, new int[] { 19, 1 } },
            { 22, new int[] { 2, 1, 11, 1 } },
            { 23, new int[] { 23, 1} },
            { 24, new int[] { 2, 3, 3, 1 } },
            { 16, new int[] { 2, 4 } }
        });
    }
    
    private int n;
    
    private int[] factors;
    
    public UtilPrimeTest(int n, int[] factors) {
         this.n = n;
         this.factors = factors;
    }

    @Test
    public void testFactor() 
    {
        int[] actual = Util.factor(n);
        for(int i = 0; i < factors.length/2; i++) {
            Assert.assertEquals("number: "+n+", factor: "+factors[2*i]+" != "+actual[2*i],actual[2*i],factors[2*i]);
            Assert.assertEquals("number: "+n+", factor: "+factors[2*i]+"^"+factors[2*i+1]+" != "+factors[2*i]+"^"+actual[2*i+1], actual[2*i+1],factors[2*i+1]);
        }
    }
}