package br.gov.goiania.saude.api.application.dto;

public record EnderecoDTO(
         String keyword,
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


