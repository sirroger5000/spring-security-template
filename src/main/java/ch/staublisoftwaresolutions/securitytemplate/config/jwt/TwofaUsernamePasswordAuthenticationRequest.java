package ch.staublisoftwaresolutions.securitytemplate.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwofaUsernamePasswordAuthenticationRequest {

    private String username;
    private String password;
    private String code;
}
