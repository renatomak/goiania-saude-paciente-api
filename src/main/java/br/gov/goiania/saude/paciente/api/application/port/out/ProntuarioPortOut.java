package br.gov.goiania.saude.paciente.api.application.port.out;

import br.gov.goiania.saude.paciente.api.application.dto.AtendimentoResponse;
import java.util.List;

public interface ProntuarioPortOut {
    List<AtendimentoResponse> buscarAtendimentosComRegistrosPorPaciente(Long pacienteId);
}
