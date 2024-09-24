package pia.task.library_automation.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import pia.task.library_automation.service.JwtService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;


	public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, JwtService jwtService, UserDetailsService userDetailsService) {
		this.handlerExceptionResolver = handlerExceptionResolver;
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException
	{
		System.out.println("internal filer is invoked");
		final String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer "))
		{
			System.out.println("inside if query");
			filterChain.doFilter(request, response);
			return;
		}

		try {
			System.out.println("internal filter try block");
			final String jwt = authHeader.substring(7);
			System.out.println("nefore jwtservice.ExtractUser");
			final String userId = jwtService.extractUserId(jwt);
			System.out.println("before authentication");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("before if block");
			if ( userId != null && authentication == null)
			{
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);
				if(jwtService.isTokenValid(jwt, userDetails))
				{
					System.out.println("is token valid");
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities()
					);

					System.out.println("before security context holder");
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
					System.out.println("end of if block inside the try");
				}
			}

			System.out.println("doFilter invoked");
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
	}
}
