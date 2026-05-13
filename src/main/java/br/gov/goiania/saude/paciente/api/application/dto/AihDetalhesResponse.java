package br.gov.goiania.saude.paciente.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AihDetalhesResponse(
    String dataCadastro,
    String principaisSinais,
    String condicoesInternacao,
    String principaisResultados,
    String diagnosticoInicial
) {}
