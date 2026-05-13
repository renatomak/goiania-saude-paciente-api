package br.gov.goiania.saude.paciente.api.application.port.out;

import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponse;

import java.util.List;
import java.util.Optional;

public interface VacinaPortOut {

    List<VacinaResumoResponse> listarPorPacienteId(Long pacienteId);

    Optional<VacinaDetalheResponse> buscarDetalhePorAplicacaoId(Long idAplicacao);
}

