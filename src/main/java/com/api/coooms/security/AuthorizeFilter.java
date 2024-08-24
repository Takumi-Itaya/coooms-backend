package com.api.coooms.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.coooms.Model.JwtParameter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizeFilter extends OncePerRequestFilter {
    
    private JwtParameter secret = new JwtParameter();
    Algorithm algorithm = Algorithm.HMAC256(secret.getSecretKey());
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
        	String xAuthToken = request.getHeader("X-AUTH-TOKEN");
        	if (xAuthToken == null || !xAuthToken.startsWith("Bearer ")) {
        		filterChain.doFilter(request, response);
        		return;
        	}

        	// tokenの検証と認証
        	DecodedJWT decodedJWT = JWT.require(algorithm)
        			.withIssuer(secret.getTokenIssuer())
        			.withSubject(secret.getTokenSubject())
        			.build()
        			.verify(xAuthToken.substring(7));
        	
        	// emailの取得
        	List<String> userEmailList = decodedJWT.getAudience();
        	String email = userEmailList.get(0);
        	
        	
        	// ログイン状態の設定
        	SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>()));
        } catch (TokenExpiredException ex) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        	return;
        } catch (IncorrectClaimException e) {
        	filterChain.doFilter(request, response);
            return;
        } catch (Exception e) {
        	filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
