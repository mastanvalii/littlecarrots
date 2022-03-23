package com.lc.sk.auth.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

@Component
public class Compression {

// TODO Remove unused code found by UCDetector
// 	public void method1(File input, File compressedImageFile, Object hint, int width, int height) throws IOException {
// 		// resizeUsingJavaAlgo(input, compressedImageFile, 600,800);
// 
// 		// File input = new File("digital_image_processing.jpg");
// 		BufferedImage image = ImageIO.read(input);
// 		image = getScaledInstance(image, width, height, hint, true);
// 		// File compressedImageFile = new File("compress.jpg");
// 		OutputStream os = new FileOutputStream(compressedImageFile);
// 
// 		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
// 		ImageWriter writer = (ImageWriter) writers.next();
// 
// 		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
// 		writer.setOutput(ios);
// 
// 		ImageWriteParam param = writer.getDefaultWriteParam();
// 
// 		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
// 		param.setCompressionQuality(0.3f);
// 		writer.write(null, new IIOImage(image, null, null), param);
// 
// 		os.close();
// 		ios.close();
// 		writer.dispose();
// 
// 		String end = input.getName().substring(input.getName().indexOf(".") + 1);
// 		for (int i = 0; i <= 10; i++) {
// 	//		System.err.println("Iterator :" + i);
// 			image = Scalr.resize(image, Scalr.Method.BALANCED, width, height);
// 		}
// 		ImageIO.write(image, end, compressedImageFile);
// 		os.close();
// 
// 	}

	private BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
			boolean higherQuality) {
		// REMIND: This only works for opaque images...

		// Use multi-step technique: start with original size, then
		// scale down in multiple passes with drawImage()
		// until the target size is reached
		int iw = img.getWidth();
		int ih = img.getHeight();

		// Object hint = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;

		// First get down to no more than 2x in W & H
		while (iw > targetWidth * 2 || ih > targetHeight * 2) {
			iw = (iw > targetWidth * 2) ? iw / 2 : iw;
			ih = (ih > targetHeight * 2) ? ih / 2 : ih;
			img = scaleImage(img, type, hint, iw, ih);
		}

		// REMIND: Conservative approach:
		// first get W right, then worry about H

		// If still too wide - do a horizontal trilinear blend
		// of img and a half-width img
		if (iw > targetWidth) {
			int iw2 = iw / 2;
			BufferedImage img2 = scaleImage(img, type, hint, iw2, ih);
			if (iw2 < targetWidth) {
				img = scaleImage(img, type, hint, targetWidth, ih);
				img2 = scaleImage(img2, type, hint, targetWidth, ih);
				interp(img2, img, iw - targetWidth, targetWidth - iw2);
			}
			img = img2;
			iw = targetWidth;
		}
		// iw should now be targetWidth or smaller

		// If still too tall - do a vertical trilinear blend
		// of img and a half-height img
		if (ih > targetHeight) {
			int ih2 = ih / 2;
			BufferedImage img2 = scaleImage(img, type, hint, iw, ih2);
			if (ih2 < targetHeight) {
				img = scaleImage(img, type, hint, iw, targetHeight);
				img2 = scaleImage(img2, type, hint, iw, targetHeight);
				interp(img2, img, ih - targetHeight, targetHeight - ih2);
			}
			img = img2;
			ih = targetHeight;
		}
		// ih should now be targetHeight or smaller

		// If we are too small, then it was probably because one of
		// the dimensions was too small from the start.
		if (iw < targetWidth && ih < targetHeight) {
			img = scaleImage(img, type, hint, targetWidth, targetHeight);
		}

		return img;
		/*
		 * int type = (img.getTransparency() == Transparency.OPAQUE) ?
		 * BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB; BufferedImage ret =
		 * (BufferedImage)img; int w, h; if (higherQuality) { // Use multi-step
		 * technique: start with original size, then // scale down in multiple passes
		 * with drawImage() // until the target size is reached w = img.getWidth(); h =
		 * img.getHeight(); } else { // Use one-step technique: scale directly from
		 * original // size to target size with a single drawImage() call w =
		 * targetWidth; h = targetHeight; }
		 * 
		 * do { if (higherQuality && w > targetWidth) { w /= 2; if (w < targetWidth) { w
		 * = targetWidth; } }
		 * 
		 * if (higherQuality && h > targetHeight) { h /= 2; if (h < targetHeight) { h =
		 * targetHeight; } }
		 * 
		 * BufferedImage tmp = new BufferedImage(w, h, type); Graphics2D g2 =
		 * tmp.createGraphics(); g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 * hint); g2.drawImage(ret, 0, 0, w, h, null); g2.dispose();
		 * 
		 * ret = tmp; } while (w != targetWidth || h != targetHeight);
		 * 
		 * return ret;
		 * 
		 */
	}

	private void interp(BufferedImage img1, BufferedImage img2, int weight1, int weight2) {
		float alpha = weight1;
		alpha /= (weight1 + weight2);
		Graphics2D g2 = img1.createGraphics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(img2, 0, 0, null);
		g2.dispose();
	}

	private BufferedImage scaleImage(BufferedImage orig, int type, Object hint, int w, int h) {
		BufferedImage tmp = new BufferedImage(w, h, type);
		Graphics2D g2 = tmp.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
		g2.drawImage(orig, 0, 0, w, h, null);
		g2.dispose();
		return tmp;
	}

// TODO Remove unused code found by UCDetector
// 	public boolean resizeUsingJavaAlgo(File source, File dest, int width, int height) throws IOException {
// 		BufferedImage sourceImage = ImageIO.read(source);
// 		sourceImage = getScaledInstance(sourceImage, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
// 		double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
// 		if (width < 1) {
// 			width = (int) (height * ratio + 0.4);
// 		} else if (height < 1) {
// 			height = (int) (width / ratio + 0.4);
// 		}
// 
// 		Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
// 		BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
// 				BufferedImage.TYPE_INT_RGB);
// 		Graphics2D g2d = bufferedScaled.createGraphics();
// 		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
// 		g2d.drawImage(scaled, 0, 0, width, height, null);
// 		dest.createNewFile();
// 		writeJpeg(bufferedScaled, dest.getCanonicalPath(), 0.1f);
// 		return true;
// 	}

	/**
	 * Write a JPEG file setting the compression quality.
	 *
	 * @param image    a BufferedImage to be saved
	 * @param destFile destination file (absolute or relative path)
	 * @param quality  a float between 0 and 1, where 1 means uncompressed.
	 * @throws IOException in case of problems writing the file
	 */
	private void writeJpeg(BufferedImage image, String destFile, float quality) throws IOException {
		ImageWriter writer = null;
		FileImageOutputStream output = null;
		try {
			writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);
			output = new FileImageOutputStream(new File(destFile));
			writer.setOutput(output);
			IIOImage iioImage = new IIOImage(image, null, null);
			writer.write(null, iioImage, param);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.dispose();
			}
			if (output != null) {
				output.close();
			}
		}
	}
	
}
