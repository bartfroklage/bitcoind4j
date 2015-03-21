package org.bfroklage.bitcoind4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MockBitcoindServer extends AbstractHandler  {
	
	private Server server;
	private String authString;
	private String responseBody;
	
	private List<String> responses = new ArrayList<String>();
	
	public MockBitcoindServer() {
		server = new Server(8080);
		server.setHandler(this);		
	}
	
	public void begin() throws Exception {
		server.start();
	}
	
	public void end() throws Exception {
		server.stop();
	}
	
	public void setAuthString(String authString) {
		this.authString = authString;
	}
	
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
		
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, 
	          HttpServletResponse response) throws IOException,  ServletException {		
		response.setStatus(200);
		
		if (authString != null && !authString.equals(request.getHeader("Authorization"))) {
			response.setStatus(401);
			response.flushBuffer();
 		} else {
 			if (responses.size() > 0) { 				
 				response.getWriter().write(responses.get(0));
 				responses.remove(0);
 				
 			} else {
 				response.getWriter().write(responseBody);
 			} 			
 			response.flushBuffer();
 		} 		
	}
	
	public void addResponse(String msg) {
		responses.add(msg);
	}

}
