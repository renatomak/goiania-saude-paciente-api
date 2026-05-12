package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ProntuarioEstruturadoResponse(
    PacienteResponse paciente,
    List<AtendimentoResponse> atendimentos
) {}

