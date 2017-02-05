package com.edespot.camgrab;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_core.IplImage;


/**
 * Created by amackenz on 2/3/17.
 */
public class Grabber {
	private AppConfig config;
	private FrameGrabber grabber;

	public Grabber() {
		this(new AppConfig());
	}

	public Grabber(AppConfig config) {
		this.config = config;
	}

	public void start() throws FrameGrabber.Exception {
		this.grabber = FrameGrabber.createDefault(config.getDevice());
		this.grabber.setImageWidth(config.getImageWidth());
		this.grabber.setImageHeight(config.getImageHeight());
		this.grabber.start();
	}

	public Frame grabFrame() throws FrameGrabber.Exception {
		System.out.println("Grabbing frame.");

		this.grabber.flush();
		return this.grabber.grab();
	}


}
