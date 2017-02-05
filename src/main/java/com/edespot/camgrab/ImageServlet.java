package com.edespot.camgrab;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ImageServlet extends HttpServlet {
	private Grabber grabber;
	private AppConfig config;
	private DateTimeFormatter dateTimeFormatter;
	private opencv_core.Scalar colorScalar;

	OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

	public ImageServlet(AppConfig config, Grabber grabber) throws FrameGrabber.Exception {
		this.config = config;
		this.grabber = grabber;
		this.grabber.start();
		dateTimeFormatter = DateTimeFormatter.ofPattern(config.getDateTimeFormat());
		colorScalar = ImageUtil.hexToScalar(config.getHexColor());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");

		response.setStatus(200);
		response.setContentType("image/jpeg");

		Frame frame = grabber.grabFrame();
		opencv_core.Mat mat = converterToMat.convert(frame);

		if ( config.isShowDateTime() ) {
			ImageUtil.addDateTime(mat, dateTimeFormatter, colorScalar);
		}

		BufferedImage bufferedImage = ImageUtil.frameToBufferedImage(frame);
		ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
	}
}
