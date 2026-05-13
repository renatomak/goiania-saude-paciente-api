package br.gov.goiania.saude.paciente.api.domain.model;

import java.time.LocalDate;

public final class PacienteMock {

    private PacienteMock() { }

    public static Paciente valido() {
        return createPaciente(
                1L, "123456789012345", "Maria", "Maria Social",
                "12345678901", "F", "Ana", "Jose",
                LocalDate.of(1990, 1, 1), 35,
                "Brasil", "GO", "Goiania", "Branca", "Nenhuma",
                "62999999999", "62988887777", "maria@email.com",
                "Rua A, 10", "Rua", "A", "Casa",
                "10", "70000", "Centro",
                1L, "Goiania", "GO", "Brasil"
        );
    }

    public static Paciente exemplo() {
        return createPaciente(
                2L, "987654321098765", "Joao", "Joao Silva",
                "98765432100", "M", "Maria", "Francisco",
                LocalDate.of(1985, 5, 15), 40,
                "Brasil", "SP", "Sao Paulo", "Pardo", "Nenhuma",
                "11987654321", "1133334444", "joao@email.com",
                "Avenida B, 25", "Avenida", "B", "Apartamento",
                "25", "01234567", "Consolacao",
                2L, "Sao Paulo", "SP", "Brasil"
        );
    }

    private static Paciente createPaciente(
            Long id, String cartaoSus, String nome, String nomeSocial,
            String cpf, String sexo, String mae, String pai,
            LocalDate dataNascimento, Integer idade,
            String paisNascimento, String ufNascimento, String municipioNascimento,
            String raca, String etnia, String telefone, String telefoneContato,
            String email, String enderecoCompleto, String tipoLogradouro,
            String logradouro, String complemento, String numero, String cep,
            String bairro, Long cidadeId, String cidade, String uf,
            String paisEndereco) {
        return new Paciente(
                id, cartaoSus, nome, nomeSocial, cpf, sexo, mae, pai,
                dataNascimento, idade, paisNascimento, ufNascimento,
                municipioNascimento, raca, etnia, telefone, telefoneContato,
                email, enderecoCompleto, tipoLogradouro, logradouro,
                complemento, numero, cep, bairro, cidadeId, cidade, uf,
                paisEndereco
        );
    }
}

