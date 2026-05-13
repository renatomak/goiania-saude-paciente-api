package br.gov.goiania.saude.paciente.api.application.dto;

public record EnderecoResponse(
         String endereco,
         String tipoLogradouro,
         String logradouro,
         String complemento,
         String numero,
         String cep,
         String bairro,
         Long cidadeId,
         String cidade,
         String uf
) {
}


