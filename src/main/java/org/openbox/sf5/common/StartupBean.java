package org.openbox.sf5.common;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupBean {

	@PostConstruct
	private void startup() {

		filler.init();
	}

	@Inject
	TableFiller filler;

}
