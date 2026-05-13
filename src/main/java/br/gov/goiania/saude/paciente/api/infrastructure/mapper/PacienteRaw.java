package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteRaw {
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    private String mae;
    private String pai;
    private LocalDate dataNascimento;
    private String telefone;
    private String cartaoSus;
    private String nomeSocial;
    private String paisNascimento;
    private String ufNascimento;
    private String municipioNascimento;
    private String raca;
    private String etnia;
    private String telefoneContato;
    private String email;
    private String endereco;
    private String tipoLogradouro;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cep;
    private String bairro;
    private Long cidadeId;
    private String cidade;
    private String uf;
    private String paisEndereco;
}
