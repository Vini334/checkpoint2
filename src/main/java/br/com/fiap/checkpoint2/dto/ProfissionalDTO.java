package br.com.fiap.checkpoint2.dto;

import jakarta.validation.constraints.*;

public record ProfissionalDTO(
        @NotBlank String nome,
        @NotBlank String especialidade,
        @NotNull Double valorHora
) {}
