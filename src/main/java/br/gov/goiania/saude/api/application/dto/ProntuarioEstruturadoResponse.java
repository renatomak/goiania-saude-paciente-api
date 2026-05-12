package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ProntuarioEstruturadoResponse(
    PacienteDTO paciente,
    List<AtendimentoDTO> atendimentos
) {}

