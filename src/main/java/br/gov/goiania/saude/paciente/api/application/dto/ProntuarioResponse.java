package br.gov.goiania.saude.paciente.api.application.dto;

import java.util.List;

public record ProntuarioResponse(
    Long pacienteId,
    List<AtendimentoResponse> atendimentos
) {}

