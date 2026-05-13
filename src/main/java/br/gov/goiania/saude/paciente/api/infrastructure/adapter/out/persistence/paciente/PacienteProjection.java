package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente;

import java.time.LocalDate;

public record PacienteProjection(
    Long id,
    String cartaoSus,
    String nome,
    String nomeSocial,
    String cpf,
    String sexo,
    String mae,
    String pai,
    LocalDate dataNascimento,
    Integer idade,
    String paisNascimento,
    String ufNascimento,
    String municipioNascimento,
    String raca,
    String etnia,
    String telefone,
    String telefoneContato,
    String email,
    String enderecoCompleto,
    String tipoLogradouro,
    String logradouro,
    String complemento,
    String numero,
    String cep,
    String bairro,
    Long cidadeId,
    String cidade,
    String uf,
    String paisEndereco
) {
}
