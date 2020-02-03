package zone.gaygisiz.home.soft.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AppUserDetailService appUserDetailService;

  @Autowired
  private JwtAuthentificationFilter jwtAuthentificationFilter;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(appUserDetailService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
        .antMatchers("/api/authentification")
        .permitAll()
        .and()
      .authorizeRequests()
        .antMatchers("/api/.*")
        .authenticated()
        .and()
      .authorizeRequests()
        .anyRequest()
        .permitAll()
        .and()
      .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
      .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(new ToIndexFilter(), JwtAuthentificationFilter.class)
    ;

  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    //TODO: FIX ME MAYBE
    return NoOpPasswordEncoder.getInstance();
  }
}
