package com.onewolf.alpha.lib.image;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage createImage(int[] grid, int width, int height) {
		int[] bitMasks = new int[] { 0xFF0000, 0xFF00, 0xFF, 0xFF000000 };
		SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, width, height,
				bitMasks);
		DataBufferInt db = new DataBufferInt(grid, grid.length);
		WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
		BufferedImage image = new BufferedImage(ColorModel.getRGBdefault(), wr, false, null);
		return image;
	}

	public static void saveImage(BufferedImage image, String filename) throws IOException {
		ImageIO.write(image, "png", new File(filename));
	}

}
