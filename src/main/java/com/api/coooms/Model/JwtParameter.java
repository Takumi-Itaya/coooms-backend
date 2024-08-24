package com.api.coooms.Model;

public class JwtParameter {
	private static String secretKey = "d:ok]hd;tjka]bf;olrjm";
    private static String tokenIssuer = "TAKUMIITAYA";
    private static String tokenSubject = "CooomsAPI";

    public String getSecretKey() {
        return secretKey;
    }
    
    public String getTokenIssuer() {
        return tokenIssuer;
    }
    
    public String getTokenSubject() {
        return tokenSubject;
    }
}
