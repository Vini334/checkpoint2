package br.com.fiap.checkpoint2.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ConsultaDTO(
        @NotNull Long profissionalId,
        @NotNull Long pacienteId,
        @NotNull LocalDateTime dataConsulta,
        @NotBlank String statusConsulta,
        @NotNull Integer quantidadeHoras,
        @NotNull Double valorConsulta
) {}
