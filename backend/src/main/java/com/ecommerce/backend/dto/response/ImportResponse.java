package com.ecommerce.backend.dto.response;

import java.util.List;

public record ImportResponse(String message, Integer success, List<String> errors) {

}
