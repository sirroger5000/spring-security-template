package ch.staublisoftwaresolutions.securitytemplate.controller;

import ch.staublisoftwaresolutions.securitytemplate.model.HelloWorld;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    @GetMapping
    public HelloWorld getUser() {
        return HelloWorld.builder().build();
    }
}
