package com.onewolf.alpha.lib;

import com.onewolf.alpha.lib.noisegen.*;
import java.io.*;

public class Turbulence
{
    public static void main( String[] args ) throws IOException
    {
        String name = args[0];
        int size = Integer.parseInt(args[1]);
        int levels = Integer.parseInt(args[2]);
        int width = size, height = size;
        System.out.printf("Arguments: name = '%s', size = %d, levels = %d\n", name, size, levels);
        int[][] rand = new int[levels][];
        int scale = 1;
        for(int i = 0; i < levels; i++) {
            rand[i] = Noise.soften(
                Noise.randomGrid(
                    width * scale + 1,
                    height * scale + 1,
                    new HighQualityRandomGenerator(),
                    new DefaultGridPermuter()
                ),
                width * scale + 1,
                height * scale + 1
            );
            scale *= 2;
        }
        int iscale = scale;
        scale = 1;
        for(int i = 0; i < levels; i++) {
            iscale /= 2;
            rand[i] = Noise.scale(
                rand[i],
                width * scale,
                height * scale,
                iscale
            );
            scale *= 2;
        }
        double[] factor = new double[levels];
        scale = 1;
        for(int i = 0; i < levels; i++) {
            factor[i] = 1.0 / scale;
            scale *= 2;
        }
        scale /= 2;
        Noise.saveImage(
            Noise.createImage(
                Noise.grayscale(
                    Noise.weightedSum(
                        levels,
                        rand,
                        factor,
                        0.5
                    )
                ),
                width * scale,
                height * scale
            ),
            name + ".png"
        );
    }
}
