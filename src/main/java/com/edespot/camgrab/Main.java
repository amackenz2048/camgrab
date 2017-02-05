package com.edespot.camgrab;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

/**
 * Created by amackenz on 2/3/17.
 */
public class Main {

	/**
	 * Create config from java properties.
	 *
	 * @return
	 */
	private static AppConfig getConfig() {
		AppConfig config = new AppConfig();

		Properties props = System.getProperties();

		if (props.containsKey("port")) {
			config.setPort(Integer.parseInt(props.getProperty("port")));
		}
		if (props.containsKey("imageWidth")) {
			config.setImageWidth(Integer.parseInt(props.getProperty("imageWidth")));
		}
		if (props.containsKey("imageHeight")) {
			config.setImageHeight(Integer.parseInt(props.getProperty("imageHeight")));
		}
		if (props.containsKey("device")) {
			config.setDevice(Integer.parseInt(props.getProperty("device")));
		}
		if (props.containsKey("showDateTime")) {
			config.setShowDateTime(Boolean.parseBoolean(props.getProperty("showDateTime")));
		}
		if (props.containsKey("dateTimeFormat")) {
			config.setDateTimeFormat(props.getProperty("dateTimeFormat"));
		}
		if (props.containsKey("textColor")) {
			config.setHexColor(props.getProperty("textColor"));
		}


		return config;
	}

	public static final void main(String[] argv) throws Exception {
		AppConfig config = getConfig();

		Grabber grabber = new Grabber(config);
		Server server = new Server(config.getPort());

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.addServlet(new ServletHolder(new ImageServlet(config, grabber)), "/photo");
		server.setHandler(context);

		System.out.println("Starting server on port " + config.getPort());
		server.start();
		server.join();
	}
}
