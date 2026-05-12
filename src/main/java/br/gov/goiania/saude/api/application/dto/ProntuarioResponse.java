package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ProntuarioResponse(
    Long pacienteId,
    List<AtendimentoResponse> atendimentos
) {}

