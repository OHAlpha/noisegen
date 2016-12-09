package com.onewolf.alpha.lib.noisegen;

import java.util.LinkedList;
import java.util.List;

public class Util {
    
    public static int[] factor(int n) {
        List<int[]> fs = new LinkedList<>();
        int i = 2;
        while(n>1) {
            if(n%i==0) {
                int[] f = {i,0};
                while(n%i==0) {
                    f[1]++;
                    n /= i;
                }
                fs.add(f);
            }
            i++;
        }
        int[] out = new int[fs.size()*2];
        for(i = 0; i < fs.size(); i++) {
            int[] f = fs.get(i);
            out[2*i] = f[0];
            out[2*i+1] = f[1];
        }
        return out;
    }
    
    public static int greatestPower(int n) {
        int a = Math.abs(n), b = a & (a-1);
        while(b != 0) {
            a = b;
            b = a & (a-1);
        }
        return a;
    }
    
}