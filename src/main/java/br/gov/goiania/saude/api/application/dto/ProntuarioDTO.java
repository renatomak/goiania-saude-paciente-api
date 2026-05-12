package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ProntuarioDTO(
    Long pacienteId,
    List<AtendimentoDTO> atendimentos
) {}

