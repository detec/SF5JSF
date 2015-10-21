package org.openbox.sf5.common;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartupBean {

	@PostConstruct
	private void startup() {
		// start TableFiller
		TableFiller filler = new TableFiller();
	}

}
