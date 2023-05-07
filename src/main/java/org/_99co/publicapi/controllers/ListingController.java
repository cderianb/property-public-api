package org._99co.publicapi.controllers;

import org._99co.publicapi.client.ListingServiceClient;
import org._99co.publicapi.models.Listing;
import org._99co.publicapi.models.ListingUser;
import org._99co.publicapi.models.Response;
import org._99co.publicapi.models.User;
import org._99co.publicapi.models.enums.ListingType;
import org._99co.publicapi.services.ListingService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/public-api/listings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService){
        this.listingService=listingService;
    }


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<List<ListingUser>>> getAllListing(@RequestParam(value = "user_id", required = false) Integer userId
            , @RequestParam(value = "page_num", defaultValue = "1") Integer page
            , @RequestParam(value = "page_size", defaultValue = "10") Integer size){
        return listingService.getAllListings(userId, page, size);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Listing>> postListing(@RequestBody MultiValueMap<String, String> paramMap){
        Integer userId = Integer.parseInt((String) paramMap.get("user_id").get(0));
        Integer price = Integer.parseInt((String) paramMap.get("price").get(0));
        ListingType listingType = ListingType.valueOf(paramMap.get("listing_type").get(0).toLowerCase());

        return listingService.createListing(userId, price, listingType);
    }
}
