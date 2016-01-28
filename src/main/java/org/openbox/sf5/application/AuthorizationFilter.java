package org.openbox.sf5.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openbox.sf5.json.config.AppPathReader;
import org.reflections.Reflections;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" }) // urlPatterns
// seem not
// to work
public class AuthorizationFilter implements Filter {

	private List<String> classNames = new ArrayList<>();

	@Override
	public void destroy() {

	}

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest reqt = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession ses = reqt.getSession(false);

		String reqURI = reqt.getRequestURI();

		// Let's split conditions into separate parts
		// changed >= to >
		boolean itIsLoginpage = (reqURI.indexOf("/login.xhtml") > 0);
		boolean itIsregsterPage = (reqURI.indexOf("/register.xhtml") > 0);
		boolean therIsUsername = (ses != null && ses.getAttribute("username") != null);

		boolean itIsJsonPath = (reqURI.indexOf(AppPathReader.JAXRS_PATH) > 0);

		if (classNames.size() == 0) {
			setJAXWSEndpoints();
		}

		// count elements where iterable string is part of URL
		// doesn't work
		// boolean isJAXWSPath = (classNames.stream().filter(t ->
		// reqURI.indexOf(t) > 0).collect(Collectors.toList())
		// .size() > 0);

		boolean isJAXWSPath = isJAXWSPath(reqURI);

		if (itIsLoginpage || therIsUsername || itIsregsterPage || itIsJsonPath || isJAXWSPath) {

			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
		}

	}

	private boolean isJAXWSPath(String reqURI) {

		int[] result = new int[1];

		classNames.stream().forEach(t -> {
			result[0] = result[0] + reqURI.indexOf(t + "Service");
		});

		return (result[0] > 0);

	}

	// fill list of endpoint classes to let them go through filter
	private void setJAXWSEndpoints() {
		Reflections reflections = new Reflections("org.openbox.sf5.jaxws");
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(WebService.class);

		// List<String> classNames = new ArrayList<>();
		classes.stream().forEach(t -> classNames.add(t.getSimpleName()));

		// return classNames;
	}

}
