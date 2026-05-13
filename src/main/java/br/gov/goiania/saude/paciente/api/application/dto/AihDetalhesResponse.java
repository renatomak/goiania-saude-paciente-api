package br.gov.goiania.saude.paciente.api.application.dto;

public record AihDetalhesResponse(
    String dataCadastro,
    String principaisSinais,
    String condicoesInternacao,
    String principaisResultados,
    String diagnosticoInicial
) { }
