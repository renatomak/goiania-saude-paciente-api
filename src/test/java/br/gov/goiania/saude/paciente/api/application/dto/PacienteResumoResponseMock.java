package br.gov.goiania.saude.paciente.api.application.dto;

import java.time.LocalDate;

public final class PacienteResumoResponseMock {

    private PacienteResumoResponseMock() { }

    public static PacienteResumoResponse valido() {
        return new PacienteResumoResponse(1L, "Maria", "123", LocalDate.of(1990, 1, 1));
    }

    public static PacienteResumoResponse exemplo() {
        return new PacienteResumoResponse(2L, "Joao", "987", LocalDate.of(1985, 5, 15));
    }
}

