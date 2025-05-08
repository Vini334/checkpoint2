package br.com.fiap.checkpoint2.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PacienteDTO(
        @NotBlank String nome,
        @NotBlank String endereco,
        @NotBlank String bairro,
        @Email String email,
        @NotBlank String telefoneCompleto,
        @NotNull LocalDate dataNascimento
) {}
