package com.ecommerce.backend.dto.response;

//PONEMOS EL MAPEO SEA DE TIPO OBJECT PARA QUE SEA CUALQUIER TIPO DE ERROR
public record ErrorResponse(Integer status, String msm, Object details) {

}
