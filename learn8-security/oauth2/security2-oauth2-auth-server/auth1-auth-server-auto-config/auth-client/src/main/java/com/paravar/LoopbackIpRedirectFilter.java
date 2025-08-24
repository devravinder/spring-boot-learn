package com.paravar;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * This filter ensures that the loopback IP <code>127.0.0.1</code> is used to access the
 * application so that the sample works correctly, due to the fact that redirect URIs with
 * "localhost" are rejected by the Spring Authorization Server, because the OAuth 2.1
 * draft specification states:
 *
 * <pre>
 *     While redirect URIs using localhost (i.e.,
 *     "http://localhost:{port}/{path}") function similarly to loopback IP
 *     redirects described in Section 10.3.3, the use of "localhost" is NOT
 *     RECOMMENDED.
 * </pre>
 *
 * @author Steve Riesenberg
 * @see <a href=
 * "https://tools.ietf.org/html/draft-ietf-oauth-v2-1-01#section-9.7.1">Loopback Redirect
 * Considerations in Native Apps</a>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoopbackIpRedirectFilter extends OncePerRequestFilter {

	@Value("${server.port}")
	private int PORT;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServerName().equals("localhost") && request.getHeader("host") != null) {
			UriComponents uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
					//UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
				.host("127.0.0.1")
					.port(PORT)
				.build();
			response.sendRedirect(uri.toUriString());
			return;
		}
		filterChain.doFilter(request, response);
	}

}
