package org.openbox.sf5.json.config;

import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

public class MOXyJsonContextResolver implements ContextResolver<MoxyJsonConfig> {

	private final MoxyJsonConfig config;

	public MOXyJsonContextResolver() {
		config = new MoxyJsonConfig().setAttributePrefix("").setFormattedOutput(true).setMarshalEmptyCollections(true);
	}

	@Override
	public MoxyJsonConfig getContext(Class<?> objectType) {
		return config;
	}

}
