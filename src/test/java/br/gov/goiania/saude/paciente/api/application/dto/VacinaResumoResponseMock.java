package br.gov.goiania.saude.paciente.api.application.dto;

import java.time.LocalDate;

public final class VacinaResumoResponseMock {

    private VacinaResumoResponseMock() { }

    public static VacinaResumoResponse valido() {
        return new VacinaResumoResponse(
                1L,
                LocalDate.of(2024, 1, 10),
                "Covid",
                "Reforco",
                "Adulto",
                "Pfizer",
                "UBS Central",
                "Joao Profissional"
        );
    }

    public static VacinaResumoResponse exemplo() {
        return new VacinaResumoResponse(
                2L,
                LocalDate.of(2025, 6, 20),
                "Influenza",
                "Dose Unica",
                "Campanha",
                "Butantan",
                "UBS Campinas",
                "Maria Profissional"
        );
    }
}

