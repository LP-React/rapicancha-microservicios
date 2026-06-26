package com.microservice.auth.auth.dto;

import com.microservice.auth.account.enums.Role;
import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 64, message = "La contraseña debe tener entre 8 y 64 caracteres")
        String password,

        @NotNull(message = "El rol es obligatorio")
        Role role,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        String firstName,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
        String lastName,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos numéricos")
        String phone,

        @NotBlank(message = "El documento de identidad es obligatorio")
        @Size(min = 5, max = 20, message = "El documento de identidad debe tener entre 5 y 20 caracteres")
        String nationalId
) {}
