package org._99co.publicapi.controllers;

import org._99co.publicapi.client.ListingServiceClient;
import org._99co.publicapi.client.UserServiceClient;
import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.User;
import org._99co.publicapi.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/public-api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(path = "")
    public Mono<Response<User>> createUser(String name){
        return userService.createUser(name);
    }
}
