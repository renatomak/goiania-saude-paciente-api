package br.gov.goiania.saude.paciente.api.application.dto;

public record RegistroResponse(
    String data,
    String tipo,
    ConteudoResponse conteudo
) {}
