package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente;

import java.time.LocalDate;

public final class PacienteProjectionMock {

    private PacienteProjectionMock() { }

    public static PacienteProjection valido() {
        return createPacienteProjection(
                1L, "898001160123456", "Maria", "Mary",
                "12345678901", "F", "Ana", "Jose",
                LocalDate.of(1990, 1, 1), 35,
                "Brasil", "GO", "Goiania", "Branca", "Nao informada",
                "62999998888", "6233334444", "maria@example.com",
                "Rua A, 10", "Rua", "Rua A", "Casa",
                "10", "74000000", "Centro",
                52L, "Goiania", "GO", "Brasil"
        );
    }

    public static PacienteProjection exemplo() {
        return createPacienteProjection(
                2L, "123456789012345", "Joao", "Joao Silva",
                "98765432100", "M", "Maria", "Francisco",
                LocalDate.of(1985, 5, 15), 40,
                "Brasil", "SP", "Sao Paulo", "Pardo", "Nao informada",
                "11987654321", "1133334444", "joao@example.com",
                "Avenida B, 25", "Avenida", "Avenida B", "Apartamento",
                "25", "01234567", "Consolacao",
                100L, "Sao Paulo", "SP", "Brasil"
        );
    }

    private static PacienteProjection createPacienteProjection(
            Long id, String cartaoSus, String nome, String nomeSocial,
            String cpf, String sexo, String mae, String pai,
            LocalDate dataNascimento, Integer idade,
            String paisNascimento, String ufNascimento, String municipioNascimento,
            String raca, String etnia, String telefone, String telefoneContato,
            String email, String enderecoCompleto, String tipoLogradouro,
            String logradouro, String complemento, String numero, String cep,
            String bairro, Long cidadeId, String cidade, String uf,
            String paisEndereco) {
        return new PacienteProjection(
                id, cartaoSus, nome, nomeSocial, cpf, sexo, mae, pai,
                dataNascimento, idade, paisNascimento, ufNascimento,
                municipioNascimento, raca, etnia, telefone, telefoneContato,
                email, enderecoCompleto, tipoLogradouro, logradouro,
                complemento, numero, cep, bairro, cidadeId, cidade, uf,
                paisEndereco
        );
    }
}



