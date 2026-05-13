package br.gov.goiania.saude.paciente.api.application.dto;

public final class EnderecoResponseMock {

    private EnderecoResponseMock() { }

    public static EnderecoResponse valido() {
        return new EnderecoResponse(
                "kw", "Rua", "A", "Casa", "10", "70000", "Centro",
                1L, "Goiania", "GO"
        );
    }

    public static EnderecoResponse exemplo() {
        return new EnderecoResponse(
                "Av B, 25", "Avenida", "B", "Apartamento", "25", "01234567",
                "Consolacao", 2L, "Sao Paulo", "SP"
        );
    }
}

