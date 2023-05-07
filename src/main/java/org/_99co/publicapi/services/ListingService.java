package org._99co.publicapi.services;

import org._99co.publicapi.client.ListingServiceClient;
import org._99co.publicapi.client.UserServiceClient;
import org._99co.publicapi.helpers.ResponseHelper;
import org._99co.publicapi.models.Listing;
import org._99co.publicapi.models.ListingUser;
import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.enums.ListingType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ListingService {
    private final ListingServiceClient listingServiceClient;
    private final UserServiceClient userServiceClient;
    public ListingService(ListingServiceClient listingServiceClient, UserServiceClient userServiceClient){
        this.listingServiceClient=listingServiceClient;
        this.userServiceClient = userServiceClient;
    }

    public Mono<Response<List<ListingUser>>> getAllListings(Integer userId, Integer page, Integer size){
        return Flux.from(listingServiceClient.getAllListings(userId, page, size))//Mono<Response<List<Listing>>>
                .flatMap(listingResponse -> Flux.fromIterable(listingResponse.getData())
                    .flatMap(listing -> {
                        return userServiceClient.getUserBydId(listing.getUserId())
                            .flatMap(Mono::just)
                            .map(result -> ListingUser.builder()
                                    .id(listing.getId())
                                    .price(listing.getPrice())
                                    .listingType(listing.getListingType())
                                    .createdAt(listing.getCreatedAt())
                                    .updatedAt(listing.getUpdatedAt())
                                    .user(result.getData())
                                    .build());
                    })
                    .collectList()
                    .map(listings -> {
                        listings.sort(Collections.reverseOrder(Comparator.comparing(ListingUser::getCreatedAt)));
                        return listings;
                    })
                    .map(ResponseHelper::ok)).next();
    }

    public Mono<Response<Listing>> createListing(Integer userId, Integer price, ListingType listingType){
        return listingServiceClient.createListing(userId, price, listingType);
    }
}
