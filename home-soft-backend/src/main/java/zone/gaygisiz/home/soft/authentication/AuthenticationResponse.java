package zone.gaygisiz.home.soft.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
