package org.test;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;


public class HelloWorld extends AbstractHandler {
	static final Logger logger = LogManager.getLogger(HelloWorld.class.getName());
	
	private static int id = -1;
	
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
		response.getWriter().println("<h2>Server id = " + id + "</h2>");
		
		logger.info(target);
	}

	public static void main(String[] args) throws Exception {
		
		// Generate id
		Random randomGenerator = new Random();
		id = randomGenerator.nextInt(10000);
		
		// Start server
		Server server = new Server(8080);
		server.setHandler(new HelloWorld());

		server.start();
		server.join();
	}
}
