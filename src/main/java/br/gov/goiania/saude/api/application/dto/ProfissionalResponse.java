package br.gov.goiania.saude.api.application.dto;

public record ProfissionalResponse(
    String nome,
    String registro,
    String tipoConselho,
    String cbo,
    String cboDescricao
) {}

