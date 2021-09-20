package ch.staublisoftwaresolutions.securitytemplate.controller;

import ch.staublisoftwaresolutions.securitytemplate.model.HelloWorld;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shared")
public class SharedController {

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public HelloWorld getShared() {
        return HelloWorld.builder().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public void postShared(@RequestBody HelloWorld helloWorld) {
        System.out.println("Object received: " + helloWorld.toString());
    }
}
