package br.gov.goiania.saude.paciente.api.application.dto;

import java.time.LocalDate;

public record PacienteResumoResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento
) {
}


