package zone.gaygisiz.home.soft.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtOperator {

  public static final int HOURS_24 = 1000 * 60 * 60 * 24;
  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


  public String buildToken(UserDetails userDetails) {
    return Jwts.builder()
      .setClaims(Collections.EMPTY_MAP)
      .setSubject(userDetails.getUsername())
      .signWith(key)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + HOURS_24))
      .compact();
  }

  public String extractSubject(String jwt){
    return extractClaim(jwt).getSubject();
  }

  public Claims extractClaim(String jwt){
    return Jwts.parser()
      .setSigningKey(key)
      .parseClaimsJws(jwt)
      .getBody();
  }


  public boolean isValidToken(String jwt, UserDetails userDetails) {
    String subjectOfToken = extractSubject(jwt);
    boolean isExpired = isTokenExpired(jwt);
    return userDetails.getUsername().equals(subjectOfToken) && !isExpired;
  }

  private boolean isTokenExpired(String jwt) {
    Date expiration = extractClaim(jwt).getExpiration();
    return Objects.nonNull(expiration) ? new Date().after(expiration) : Boolean.FALSE;
  }
}
