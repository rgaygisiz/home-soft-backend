package zone.gaygisiz.home.soft.authentication;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/api/authentification")
public class AuthenticationEndpoint {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtOperator jwtOperator;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
    throws Exception {

    if(!authenticationRequest.getUsername().equals(authenticationRequest.getPassword())){
      throw new BadCredentialsException("Authentifizierung fehl geschlagen");
    }

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
      authenticationRequest.getUsername(),
      authenticationRequest.getUsername(),
      Collections.emptyList());

    try{
      authenticationManager.authenticate(
        authentication);
    }catch (BadCredentialsException e){
      throw new Exception("Fehlerhafte Username oder Password", e);
    }

    UserDetails userDetails = userDetailsService
      .loadUserByUsername(authenticationRequest.getUsername());
    String jwt = jwtOperator.buildToken(userDetails);

    return ResponseEntity.ok( AuthenticationResponse.builder().jwt(jwt).build() );
  }

}
