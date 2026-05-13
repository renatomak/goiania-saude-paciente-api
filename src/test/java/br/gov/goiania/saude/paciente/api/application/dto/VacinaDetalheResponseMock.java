package br.gov.goiania.saude.paciente.api.application.dto;

import java.time.LocalDate;

public final class VacinaDetalheResponseMock {

    private VacinaDetalheResponseMock() { }

    public static VacinaDetalheResponse valido() {
        return createVacinaDetalheResponse(
                1L, 100L, 1, "1a Dose", "Adulto", "Covid", "Comirnaty", "L123",
                LocalDate.of(2025, 1, 1), "Pfizer", "123", LocalDate.of(2024, 1, 1),
                "UBS", "Manha", "Grupo", "obs", "Aplicada", false, false,
                false, false, false, false, "IM", "Braco", "Joao", "CRM",
                "1234", "999", "UBS A", "1234567", "OK", "uuid"
        );
    }

    public static VacinaDetalheResponse exemplo() {
        return createVacinaDetalheResponse(
                2L, 200L, 2, "2a Dose", "Campanha", "Influenza", "Trivalente", "L456",
                LocalDate.of(2026, 6, 1), "Butantan", "456", LocalDate.of(2025, 6, 20),
                "UBS Campinas", "Tarde", "Idosos", "sem observacao", "Aplicada", false,
                false, false, false, false, false, "IM", "Braco", "Maria", "COREN",
                "5678", "888", "UBS Campinas", "7654321", "ENVIADO", "uuid-2"
        );
    }

    private static VacinaDetalheResponse createVacinaDetalheResponse(
            Long idAplicacao, Long nrAtendimento, Integer doseCodigo, String dose,
            String estrategia, String nomeVacina, String descricaoVacina, String lote,
            LocalDate validadeLote, String fabricanteNome, String fabricanteCnpj,
            LocalDate dataAplicacao, String localAtendimento, String turno,
            String grupoAtendimento, String observacao, String status,
            Boolean gestante, Boolean puerpera, Boolean historico, Boolean foraEsquema,
            Boolean viajante, Boolean novoFrasco, String viaAdministracao,
            String localAplicacao, String profissionalNome, String profissionalConselho,
            String profissionalRegistro, String profissionalCns, String unidadeNome,
            String unidadeCnes, String rndsSituacao, String rndsUuid) {
        return new VacinaDetalheResponse(
                idAplicacao, nrAtendimento, doseCodigo, dose, estrategia,
                nomeVacina, descricaoVacina, lote, validadeLote, fabricanteNome,
                fabricanteCnpj, dataAplicacao, localAtendimento, turno,
                grupoAtendimento, observacao, status, gestante, puerpera,
                historico, foraEsquema, viajante, novoFrasco, viaAdministracao,
                localAplicacao, profissionalNome, profissionalConselho,
                profissionalRegistro, profissionalCns, unidadeNome, unidadeCnes,
                rndsSituacao, rndsUuid
        );
    }
}

