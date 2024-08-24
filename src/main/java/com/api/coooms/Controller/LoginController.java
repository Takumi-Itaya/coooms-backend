package com.api.coooms.Controller;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;

import com.api.coooms.Model.UserForm;
import com.api.coooms.Model.JwtParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://coooms.com")
public class LoginController {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    
    private static final Long EXPIRATION_TIME = 1000L * 60L * 60L * 3L * 1L; //30分
    
    private JwtParameter secret = new JwtParameter();
    Algorithm algorithm = Algorithm.HMAC256(secret.getSecretKey());


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserForm request) {
    	//生成のため、日時データを取得する
        Date issuedAt = new Date(); 
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
    	
        try {
            // DaoAuthenticationProviderを用いた認証を行う
            daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            // JWTトークンの生成
            String token = JWT.create()
            		.withIssuer(secret.getTokenIssuer())
            		.withSubject(secret.getTokenSubject())
            		.withAudience(request.getEmail())
            		.withIssuedAt(issuedAt)
            		.withNotBefore(notBefore)
            		.withExpiresAt(expiresAt)
                    .sign(algorithm);
            
            // トークンをクライアントに返す
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("x-auth-token",token);
            return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public ResponseEntity<String> verity(HttpServletRequest request) {
    	// headersのkeyを指定してトークンを取得します
        String xAuthToken = request.getHeader("X-AUTH-TOKEN");
        if (xAuthToken == null || !xAuthToken.startsWith("Bearer ")) {
            return new ResponseEntity<String>(HttpStatus.OK);
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
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("email",email);
        httpHeaders.add("emailPram",request.getParameter("email"));
        if(request.getParameter("email").equals(email)) {
        	httpHeaders.add("equalsEmail", "true");	
        } else {
        	httpHeaders.add("equalsEmail", "false");	
        }
        
        return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
    }
}
