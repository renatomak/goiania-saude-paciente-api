package br.gov.goiania.saude.paciente.api.application.dto;

public record ProfissionalResponse(
    String nome,
    String registro,
    String tipoConselho,
    String cbo,
    String cboDescricao
) {}

