package org.openbox.sf5.json.config;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Taken from http://stackoverflow.com/questions/17667442/jersey-2-0-and-moxy-internal-server-error-but-no-server-log

@Provider
public class ExceptionListener implements ApplicationEventListener {

	@Override
	public void onEvent(ApplicationEvent event) {

	}

	@Override
	public RequestEventListener onRequest(RequestEvent requestEvent) {
		return new ExceptionRequestEventListener();
	}

	public static class ExceptionRequestEventListener implements RequestEventListener {
		private final Logger logger;

		public ExceptionRequestEventListener() {
			logger = LoggerFactory.getLogger(getClass());
		}

		@Override
		public void onEvent(RequestEvent event) {
			switch (event.getType()) {
			case ON_EXCEPTION:
				Throwable t = event.getException();
				logger.error("Found exception for requestType: " + event.getType(), t);
			default:
				break;
			}
		}
	}

}
