package com.ecommerce.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Sprites(
        @JsonProperty("front_default") String frontDefault) {
}