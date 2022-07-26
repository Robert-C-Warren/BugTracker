package com.GenSpark.BugTracker.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserDetailsService userDetailsService, JWTTokenHelper jwtTokenHelper){
        this.userDetailsService = userDetailsService;
        this.jwtTokenHelper = jwtTokenHelper;
    }
    @Override // Validate the token we got.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken =jwtTokenHelper.getToken(request);
        if(null != authToken){
            String user = jwtTokenHelper.getUsernameFromToken(authToken);
        if(null != user){
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);

            // if token is valid configure Spring Security to manually set
            // authentication
            if(jwtTokenHelper.validateToken(authToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));

                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        }
        filterChain.doFilter(request, response);
    }
}
