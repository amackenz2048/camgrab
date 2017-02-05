package com.edespot.camgrab;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_imgproc.getTextSize;
import static org.bytedeco.javacpp.opencv_imgproc.putText;

/**
 * Various image utils.
 */
public class ImageUtil {
	private static final OpenCVFrameConverter.ToIplImage iplImageConverter = new OpenCVFrameConverter.ToIplImage();

	public static BufferedImage frameToBufferedImage(Frame frame) {
		opencv_core.IplImage img;
		img = iplImageConverter.convert(frame);
		return ImageUtil.iplImageToBufferedImage(img);
	}


	public static void addDateTime(opencv_core.Mat mat, DateTimeFormatter format, opencv_core.Scalar color) {
		LocalDateTime dateTime = LocalDateTime.now();
		String text = dateTime.format(format);

		// Some defaults.
		final float scale = 1.0f;
		final int font = FONT_HERSHEY_PLAIN;
		final IntPointer baseline = new IntPointer();
		final int thickness = 1;
		final int lineType = 8;
		final boolean bottomLeftOrigin = false;

		opencv_core.Size textSize = getTextSize(text, font, scale, thickness, baseline);
		int textHeight = textSize.height();
		int textWidth = textSize.width();

		// calculate offset for text.
		int matHeight = mat.rows();
		int matWidth = mat.cols();
		System.out.printf("Mat: %d x %d%n", matWidth, matHeight);

		int textY = matHeight - textHeight;
		int textX = matWidth - (textWidth + 10); // 10px buffer.
		opencv_core.Point startPoint = new opencv_core.Point(textX, textY);

		putText(mat, text, startPoint, font, scale, color, thickness, lineType, bottomLeftOrigin);
	}

	public static opencv_core.Scalar hexToScalar(String hex) {
		int red = Integer.valueOf(hex.substring(1, 3), 16);
		int green = Integer.valueOf(hex.substring(3, 5), 16);
		int blue = Integer.valueOf(hex.substring(5, 7), 16);
		return new opencv_core.Scalar(blue, green, red, 2);
	}

	public static BufferedImage iplImageToBufferedImage(opencv_core.IplImage src) {
		OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
		Java2DFrameConverter paintConverter = new Java2DFrameConverter();
		Frame frame = grabberConverter.convert(src);
		return paintConverter.getBufferedImage(frame, 1);
	}
}
