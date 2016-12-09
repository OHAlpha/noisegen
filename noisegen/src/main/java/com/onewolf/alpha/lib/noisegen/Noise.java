package com.onewolf.alpha.lib.noisegen;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.Point;

public class Noise {
	
	public static int[] randomGrid(int width, int height, RandomGenerator gen, GridPermuter perm) {
	    int[] grid = new int[width*height];
	    int[] rand = gen.bulkInt(width*height);
	    GridPermuter.applyGrid(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
	    return grid;
	}
	
	public static int[][] randomGrid2D(int width, int height, RandomGenerator gen, GridPermuter perm) {
	    int[][] grid = new int[height][width];
	    int[] rand = gen.bulkInt(width*height);
	    GridPermuter.applyGrid2D(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
	    return grid;
	}
	
	public static int[] grayscale(int[] grid) {
	    for(int i = 0; i < grid.length; i++) {
	        //int gray = (int) (1.0 * grid[i] / Integer.MAX_VALUE);
	        int gray = (int) ((0.5 * grid[i] / Integer.MAX_VALUE + 0.5) * 255);
	        if( gray > 255 )
	            gray = 255;
	        else if( gray < 0 )
	            gray = 0;
	        grid[i] = 0xff000000 | ((gray & 0xff)<<16) | ((gray & 0xff)<<8) | (gray & 0xff);
	    }
	    return grid;
	}
	
	public static int[] soften(int[] grid, int width, int height) {
	    int[] out = new int[(width-1)*(height-1)];
	    for(int x = 0; x < width-1; x++)
	        for(int y = 0; y < height-1; y++) {
	            int sum = 0;
	            for(int i = 0; i < 3; i++)
	                for(int j = 0; j < 3; j++)
	                    sum += grid[wrapindex(width, height, x-1+i, y-1+j)];
	            out[y*(width-1)+x] = sum / 9;
	        }
	    return out;
	}
	
	protected static int wrapindex(int width, int height, int x, int y) {
	    return ((y+height)%height)*width+((x+width)%width);
	}
	
	public static int[] softenX(int[] grid, int width, int height, int levels) {
	    int[] out = grid;
	    for(int i = 0; i < levels; i++)
	        out = soften(out, width - i, height - i);
	    return out;
	}
	
	public static BufferedImage createImage(int[] grid, int width, int height) {
	    int[] bitMasks = new int[]{0xFF0000, 0xFF00, 0xFF, 0xFF000000};
        SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(
            DataBuffer.TYPE_INT, width, height, bitMasks);
        DataBufferInt db = new DataBufferInt(grid, grid.length);
        WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
        //wr.setPixels(0, 0, width, height, grid);
        BufferedImage image = new BufferedImage(ColorModel.getRGBdefault(), wr, false, null);
        return image;
	}
	
	public static void saveImage(BufferedImage image, String filename) throws IOException {
	    ImageIO.write(image, "png", new File(filename));
	}
	
	public static int[] scale(int[] grid, int width, int height, int scale) {
	    int W = width * scale;
	    int H = height * scale;
	    int[] out = new int[width*height*scale*scale];
	    /*
	    for(int x = 0; x < width; x++)
	        for(int y = 0; y < height; y++)
	            for(int i = 0; i < scale; i++)
	                for(int j = 0; j < scale; j++)
	                    out[(y*scale+j)*W+x*scale+i] = grid[y*width+x];
	    */
	    for(int i = 0; i < W; i++)
	        for(int j = 0; j < H; j++)
	            out[j*W+i] = grid[(j/scale)*width+i/scale];
	    return out;
	    
	}
	
	public static int[] weightedSum(int n, int[][] grid, double[] factors, double factor) {
	    int[] out = new int[grid[0].length];
	    for(int i = 0; i < out.length; i++) {
	        long sum = 0;
	        for(int j = 0; j < n; j++)
	            sum += factor * factors[j] * grid[j][i];
	        out[i] = (int) (sum / grid.length);
	    }
	    return out;
	}
	
}
