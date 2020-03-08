package zone.gaygisiz.home.soft.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

  public Optional<String> extractSubject(String jwt){
    return extractClaim(jwt)
      .map(Claims::getSubject);
  }

  public Optional<Claims> extractClaim(String jwt){
    JwtParser jwtParser = Jwts.parser()
      .setSigningKey(key);

    return Optional.ofNullable(jwt)
      .filter(StringUtils::hasText)
      .map(jwtParser::parseClaimsJws)
      .map(Jwt::getBody);
  }


  public boolean isValidToken(String jwt, UserDetails userDetails) {
    Optional<String> subjectOfToken = extractSubject(jwt);
    boolean isExpired = isTokenExpired(jwt);
    return Optional.ofNullable(userDetails)
      .map(UserDetails::getUsername)
      .map(s -> s.equals(subjectOfToken.orElse(null)))
      .orElse(Boolean.FALSE) && !isExpired;
  }

  private boolean isTokenExpired(String jwt) {
    return extractClaim(jwt)
      .map(Claims::getExpiration)
      .map(new Date()::after)
      .orElse(Boolean.FALSE);
  }
}
