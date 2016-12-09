package com.onewolf.alpha.lib;

import com.onewolf.alpha.lib.noisegen.*;
import java.io.*;

public class SoftLevels 
{
    public static void main( String[] args ) throws IOException
    {
        String name = args[0];
        int size = Integer.parseInt(args[1]);
        int levels = Integer.parseInt(args[2]);
        int width = size, height = size;
        System.out.printf("Arguments: name = '%s', size = %d, levels = %d\n", name, size, levels);
        int[] rand = Noise.randomGrid(
            width + levels,
            height + levels,
            new HighQualityRandomGenerator(),
            new DefaultGridPermuter()
        );
        int[][] soft = new int[levels][];
        int[] t = rand;
        for(int i = 0; i < levels; i++)
            t = soft[i] = Noise.soften(
                t,
                width + levels - i,
                height + levels - i
            );
        Noise.saveImage(
            Noise.createImage(
                Noise.grayscale(
                    rand
                ),
                width + levels,
                height + levels
            ),
            name + "-rand.png"
        );
        for(int i = 0; i < levels; i++)
            Noise.saveImage(
                Noise.createImage(
                    Noise.grayscale(
                        soft[i]
                    ),
                    width + levels - 1 - i,
                    height + levels - 1 - i
                ),
                name + "-soft-" + (i+1) + ".png"
            );
    }
}
