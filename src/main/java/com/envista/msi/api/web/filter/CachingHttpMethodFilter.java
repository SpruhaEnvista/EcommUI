package com.envista.msi.api.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.envista.msi.api.config.MSIAppProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This filter is used in production, to put HTTP cache headers with a long (1 month) expiration time.
 */
public class CachingHttpMethodFilter implements Filter {

    // We consider the last modified date is the start up time of the server
    private final static long LAST_MODIFIED = System.currentTimeMillis();

    private long CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(1461L);

    private MSIAppProperties MSIAppProperties;

    public CachingHttpMethodFilter(MSIAppProperties MSIAppProperties) {
        this.MSIAppProperties = MSIAppProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(MSIAppProperties.getHttp().getCache().getTimeToLiveInDays());
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        final HttpServletRequest  httpRequest  = (HttpServletRequest) request;
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {

			String origin = httpRequest.getHeader("Origin");
			if (StringUtils.isEmpty(origin) || "Origin".equalsIgnoreCase(origin)) {
				origin = "http://localhost:8080";
			}
			httpResponse.setHeader("Allow", "GET, POST, OPTIONS");
			httpResponse.setHeader("Access-Control-Request-Headers", "*, Authorization");
			httpResponse.setHeader("Access-Control-Allow-Origin", origin);
			httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
			httpResponse.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			httpResponse.setStatus(204);
		} else {
			String origin = httpRequest.getHeader("Origin");
			if (StringUtils.isEmpty(origin) || "Origin".equalsIgnoreCase(origin)) {
				origin = "http://localhost:8080";
			}
			httpResponse.setHeader("Access-Control-Allow-Origin", origin);
			httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
			chain.doFilter(request, response);
		}
    }
}
