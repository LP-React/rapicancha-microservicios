package com.microservice.auth.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerProfileUpdateRequest(

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        String firstName,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
        String lastName,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos numéricos")
        String phone
) {}
