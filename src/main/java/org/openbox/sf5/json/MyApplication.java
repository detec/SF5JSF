package org.openbox.sf5.json;

import org.glassfish.jersey.server.ResourceConfig;

// http://stackoverflow.com/questions/18872931/custom-objectmapper-with-jersey-2-2-and-jackson-2-1

public class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(MarshallingFeature.class);

     }
 }
