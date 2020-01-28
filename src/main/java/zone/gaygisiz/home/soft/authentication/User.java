package zone.gaygisiz.home.soft.authentication;

import java.time.LocalDateTime;

public class User {

  private String username;
  private String password;
  private String email;
  private LocalDateTime expired;
  private LockedState lockedState;

}
