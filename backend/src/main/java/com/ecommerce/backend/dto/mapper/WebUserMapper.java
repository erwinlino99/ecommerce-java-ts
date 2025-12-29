package com.ecommerce.backend.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.backend.dto.WebUserDto;
import com.ecommerce.backend.models.WebUser;
public class WebUserMapper {

    public static WebUserDto toDto(WebUser entity) {
        if (entity == null) return null;

        return new WebUserDto(
            entity.getId(),
            entity.getName(),
            entity.getLastName(),
            entity.getEmail(),
            entity.getCif(),
            null, // IMPORTANTE: Nunca devolvemos la password al front por seguridad
            entity.getCreated(),
            entity.getModified(),
            entity.getDeleted(),
            entity.getIsActive(),
            entity.getIsBlocked(),
            entity.getLastTimeLogin()
        );
    }


    public static WebUser toEntity(WebUserDto dto) {
        if (dto == null) return null;

        WebUser entity = new WebUser();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setLastName(dto.lastName());
        entity.setEmail(dto.email());
        entity.setCif(dto.cif());
        entity.setPassword(dto.password()); // Aquí sí mapeamos password para guardar/crear
        entity.setCreated(dto.created());
        entity.setModified(dto.modified());
        entity.setDeleted(dto.deleted());
        entity.setIsActive(dto.isActive());
        entity.setIsBlocked(dto.isBlocked());
        entity.setLastTimeLogin(dto.lastTimeLogin());
        
        return entity;
    }


    public static List<WebUserDto> toDtoList(List<WebUser> entities) {
        if (entities == null) return null;
        return entities.stream()
                       .map(WebUserMapper::toDto)
                       .collect(Collectors.toList());
    }
}