package br.gov.goiania.saude.api.application.dto;

public record RegistroDTO(
    String data,
    String tipo,
    ConteudoDTO conteudo
) {}
