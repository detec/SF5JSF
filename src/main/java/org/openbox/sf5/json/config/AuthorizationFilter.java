package org.openbox.sf5.json.config;

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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.reflections.Reflections;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" }) // urlPatterns
// seem not
// to work
// @WebFilter(filterName = "AuthFilter")
@WebFilter(urlPatterns = { "/*" }, filterName = "AuthFilter")
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
		// Of course, one day it should be refactored.
		// boolean itIsLoginpageJSF = (reqURI.indexOf("/login.jsf") > 0);

		// boolean itIsLoginPageXHTML = (reqURI.indexOf("/login.xhtml") > 0);
		boolean itIsLoginPageXHTML = reqURI.contains("/login.xhtml");

		// boolean itIsregsterPageJSF = (reqURI.indexOf("/register.jsf") > 0);
		// boolean itIsregsterPageXHTML = (reqURI.indexOf("/register.xhtml") >
		// 0);
		// boolean itIsRootPath = reqURI.equals("/");
		// boolean itIsIndexPage = reqURI.equals("/index.html");

		List<String> strList = new ArrayList<>();
		strList.add(reqt.getContextPath() + "/login.jsf");
		strList.add(reqt.getContextPath() + "/login.xhtml");
		strList.add(reqt.getContextPath() + "/register.jsf");
		strList.add(reqt.getContextPath() + "/register.xhtml");
		strList.add(reqt.getContextPath() + "/");
		strList.add(reqt.getContextPath() + "/index.html");

		boolean isAllowedPath = strList.contains(reqURI);

		boolean thereIsUsername = (ses != null && ses.getAttribute("username") != null);

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

		if (isAllowedPath || itIsLoginPageXHTML || thereIsUsername || itIsJsonPath || isJAXWSPath) {

			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
		}

	}

	private boolean isJAXWSPath(String reqURI) {

		// int[] result = new int[1];
		//
		// classNames.stream().forEach(t -> {
		// // result[0] = result[0] + reqURI.indexOf(t + "Service");
		// result[0] = result[0] + reqURI.indexOf(t + "OpenboxSF5");
		// });
		//
		// return (result[0] > 0);

		return (reqURI.indexOf("OpenboxSF5") > 0);

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
