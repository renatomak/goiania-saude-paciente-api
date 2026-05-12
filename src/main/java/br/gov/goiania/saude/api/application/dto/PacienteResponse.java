package br.gov.goiania.saude.api.application.dto;


public record PacienteResponse(
        Long id,
        String nome,
        String cpf,
        String sexo,
        String nomeMae,
        String nomePai,
        String dataNascimento,
        String telefone,
        String cartaoSus,
        String nomeSocial,
        String paisNascimento,
        String ufNascimento,
        String municipioNascimento,
        String raca,
        String etnia,
        String telefoneContato,
        String email,
        EnderecoResponse endereco,
        String paisEndereco
) {}

