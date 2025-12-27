package com.ecommerce.backend.dto.mapper;

import java.util.Collections;
import java.util.List;
import com.ecommerce.backend.dto.PokemonGiftDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
@Converter
public class PokemonGiftConverter implements AttributeConverter<List<PokemonGiftDto>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<PokemonGiftDto> attribute) {
        if (attribute == null)
            return null;
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            return "[]"; // Fallback seguro
        }
    }

    @Override
    public List<PokemonGiftDto> convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return Collections.emptyList();
        try {
            //ERROR AQUI
            return mapper.readValue(dbData, new TypeReference<List<PokemonGiftDto>>() {
            });
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}