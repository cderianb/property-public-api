package org._99co.publicapi.client;

import org._99co.publicapi.models.Listing;
import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.enums.ListingType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ListingServiceClient {
    public Mono<Response<List<Listing>>> getAllListings(Integer userId, Integer pageNum, Integer pageSize){
        return WebClient.create("http://localhost:8080")
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/listings")
                        .queryParam("user_id", userId)
                        .queryParam("page_num", pageNum)
                        .queryParam("page_size", pageSize)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<List<Listing>>>() {
                });
    }

    public Mono<Response<Listing>> createListing(Integer userId, Integer price, ListingType listingType){
        return WebClient.create("http://localhost:8080")
                .post()
                .uri("/listings")
                .body(BodyInserters.fromFormData("user_id", userId.toString())
                        .with("price", price.toString())
                        .with("listing_type", String.valueOf(listingType)))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<Listing>>() {
                });
    }
}
