package br.gov.goiania.saude.api.application.dto;

public record RegistroResponse(
    String data,
    String tipo,
    ConteudoResponse conteudo
) {}
