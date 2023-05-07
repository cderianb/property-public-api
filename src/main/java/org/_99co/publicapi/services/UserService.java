package org._99co.publicapi.services;

import org._99co.publicapi.client.UserServiceClient;
import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserServiceClient userServiceClient;

    public UserService(UserServiceClient userServiceClient){
        this.userServiceClient=userServiceClient;
    }

    public Mono<Response<User>> createUser(String name){
        return userServiceClient.createUser(name);
    }
}
