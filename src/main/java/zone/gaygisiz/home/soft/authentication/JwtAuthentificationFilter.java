package zone.gaygisiz.home.soft.authentication;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

  public static final String AUTHORIZATION = "Authorization";
  public static final String BEARER = "Bearer";

  @Autowired
  private JwtOperator jwtOperator;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader(AUTHORIZATION);


    Optional<String> possibleJwt = Optional.ofNullable(authorization)
      .filter(s -> s.startsWith(BEARER))
      .map(s -> s.replace(BEARER, ""))
      .map(String::trim);

    Optional<String> user = possibleJwt.map(jwtOperator::extractSubject);

    if (user.filter(StringUtils::hasText).isPresent() &&
      Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.get());
        if (jwtOperator.isValidToken(possibleJwt.get(), userDetails)) {
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
    filterChain.doFilter(request, response);
  }
}
