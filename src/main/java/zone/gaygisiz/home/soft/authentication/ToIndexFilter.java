package zone.gaygisiz.home.soft.authentication;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class ToIndexFilter extends OncePerRequestFilter {

  public static final String API = "/api";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {

    if (isToRoute(request)){
      filterChain.doFilter(request, response);
    }else{
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
      requestDispatcher.forward(request, response);
    }
  }

  private boolean isToRoute(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith(API) || path.endsWith(".js");
  }
}
