package org._99co.publicapi.client;

import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceClient {
    public Mono<Response<User>> createUser(String name){
        return WebClient.create("http://localhost:8081")
                .post()
                .uri("/users")
                .body(BodyInserters.fromFormData("name", name))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<User>>() {
                });
    }

    public Mono<Response<List<User>>> getMultipleUsers(List<Integer> ids){
        return WebClient.create("http://localhost:8081")
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/multiple")
                        .queryParam("user_ids", ids)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<List<User>>>() {
                });
    }

    public Mono<Response<User>> getUserBydId(Integer id){
        return WebClient.create("http://localhost:8081")
                .get()
                .uri("/users/"+id.toString())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<User>>() {
                });
    }
}
