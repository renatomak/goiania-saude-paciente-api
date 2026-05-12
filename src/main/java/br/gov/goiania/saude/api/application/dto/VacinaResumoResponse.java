package br.gov.goiania.saude.api.application.dto;

import java.time.LocalDate;

public record VacinaResumoResponse(
        Long idAplicacao,
        LocalDate dataAplicacao,
        String vacina,
        String dose,
        String estrategia,
        String laboratorio,
        String estabelecimento,
        String profissional
) {
}
