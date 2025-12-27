package com.ecommerce.backend.dto.response;

/**
 * DTO para capturar la respuesta de la PokeAPI.
 * Se coloca en un paquete separado para diferenciarlo de nuestros DTOs
 * internos.
 */
public record PokeApiResponse(
        Integer id,
        String name,
        Sprites sprites) {
}