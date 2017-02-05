package com.edespot.camgrab;

public class AppConfig {
	private int port;
	private int imageWidth;
	private int imageHeight;
	private int device;

	private boolean showDateTime;
	private String dateTimeFormat;
	private String hexColor;

	/**
	 * Setup reasonable defaults.
	 */
	public AppConfig() {
		this.port = 8080;
		this.imageWidth = 640;
		this.imageHeight = 480;
		this.device = 0;
		this.showDateTime = true;
		this.dateTimeFormat = "yyyy/MM/dd kk:mm:ss";
		this.hexColor = "#FF0000";
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getDevice() {
		return device;
	}

	public void setDevice(int device) {
		this.device = device;
	}

	public boolean isShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(boolean showDateTime) {
		this.showDateTime = showDateTime;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}

	public String getHexColor() {
		return hexColor;
	}

	public void setHexColor(String hexColor) {
		this.hexColor = hexColor;
	}
}
