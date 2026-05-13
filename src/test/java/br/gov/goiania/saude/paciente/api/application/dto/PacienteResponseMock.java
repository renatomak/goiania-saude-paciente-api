package br.gov.goiania.saude.paciente.api.application.dto;

public final class PacienteResponseMock {

    private PacienteResponseMock() { }

    public static PacienteResponse valido() {
        return new PacienteResponse(
                1L, "Maria", "123", "F", "Ana", "Jose", "01/01/1990",
                "62", "123456789012345", "Maria Social", "Brasil", "GO",
                "Goiania", "Branca", "Nenhuma", "62988887777",
                "maria@email.com", EnderecoResponseMock.valido(), "Brasil"
        );
    }

    public static PacienteResponse exemploSemEndereco() {
        return new PacienteResponse(
                1L, "Maria", "123", "F", "Ana", "Jose", "28/04/2026",
                "62", "123456789012345", "Maria Social", "Brasil", "GO",
                "Goiania", "Branca", "Nenhuma", "62988887777",
                "maria@email.com", null, "Brasil"
        );
    }
}

