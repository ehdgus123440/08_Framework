package edu.kh.demo.common.config;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CommonFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String ip = getRemoteAddr(req);
//		if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
//		    InetAddress inetAddress=InetAddress.getLocalHost();
//		    ip=inetAddress.getHostAddress();
//		}
		System.out.println("request IP : " + ip);
		System.out.println("request URI : " + req.getRequestURI());
		Map<String, String[]> paramMap = req.getParameterMap();

		Set<String> keys = paramMap.keySet();
		for (String key : keys) {
			System.out.print("key : " + key);
			System.out.println(" --> " + Arrays.toString(paramMap.get(key)));
		}

		System.out.println("----------------------------------------------");

		if (!ip.equals("127.0.0.1") && !ip.equals("0:0:0:0:0:0:0:1") && !req.getRequestURI().equals("/user/block")) {
			resp.sendRedirect("/user/block");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	private String getRemoteAddr(HttpServletRequest request) {

		String ip = null;

		ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
}
