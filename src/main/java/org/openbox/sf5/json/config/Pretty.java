package org.openbox.sf5.json.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.xml.bind.Marshaller;

import org.jboss.resteasy.annotations.Decorator;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Decorator(processor = PrettyProcessor.class, target = Marshaller.class)
public @interface Pretty {
}

// https://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/#decorators