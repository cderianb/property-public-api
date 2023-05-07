package org._99co.publicapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListingUser {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("listing_type")
    private String listingType;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("user")
    private User user;
}
