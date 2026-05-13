package br.gov.goiania.saude.paciente.api.application.dto;

public final class VacinaAplicacaoResponseMock {

    private VacinaAplicacaoResponseMock() { }

    public static VacinaAplicacaoResponse valido() {
        return new VacinaAplicacaoResponse(
                1L, "1", "Covid", "Comirnaty", "1a Dose", "Adulto",
                "L123", "2025-01-01", "Pfizer", "123", "2024-01-01",
                "Joao", "CRM", "1234", "999", "UBS A", "1234567", "UBS",
                "Manha", "Grupo", "N", "N", "N", "N", "N", "N", "IM",
                "Braco", "obs", "Aplicada", "100", "OK", "uuid"
        );
    }

    public static VacinaAplicacaoResponse exemplo() {
        return new VacinaAplicacaoResponse(
                2L, "2", "Influenza", "Trivalente", "Dose Unica", "Campanha",
                "L456", "2026-06-01", "Butantan", "456", "2025-06-20", "Maria",
                "COREN", "5678", "888", "UBS Campinas", "7654321", "UBS Campinas",
                "Tarde", "Idosos", "N", "N", "N", "N", "N", "N", "IM",
                "Braco", "sem observacao", "Aplicada", "200", "ENVIADO", "uuid-2"
        );
    }
}

