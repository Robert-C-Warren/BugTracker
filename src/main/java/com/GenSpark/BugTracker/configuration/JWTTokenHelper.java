package com.GenSpark.BugTracker.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
public class JWTTokenHelper {
    // Set Variable with values from application properties
    @Value("${jwt.auth.app}")
    private String appName;

    @Value("${jwt.auth.secret_key}")
    private String secretKey;

    @Value("${jwt.auth.expires_in}")
    private int expiresIn;
    // Set secret key algorithm
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    // Creating the token -
    // Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    // Sign the JWT using the signature algorithm (HS256) and secret key.
    // Compaction of the JWT to a URL-safe string
    public String generateToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return Jwts.builder()
                .setIssuer( appName )
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, secretKey )
                .compact();
    }

    //Generate Token expiration date
    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + expiresIn * 1000);
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username != null &&
                        username.equals(userDetails.getUsername()) &&
                        !isTokenExpired(token)
        );
    }

    public boolean isTokenExpired(String token) {
        Date expireDate=getExpirationDate(token);
        return expireDate.before(new Date());
    }

    //retrieve expiration date from jwt token
    private Date getExpirationDate(String token) {
        Date expireDate;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expireDate = claims.getExpiration();
        } catch (Exception e) {
            expireDate = null;
        }
        return expireDate;
    }

    //retrieve issue date from token
    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    // retrieve to token where header is "Authorization"
    // JWT Token is in the form "Bearer token". Remove Bearer word and get
    // Return only the token
    public String getToken( HttpServletRequest request ) {

        String authHeader = getAuthHeaderFromHeader( request );
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
    // Get Auth header where it is "Authorization"
    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader("Authorization");
    }
}


