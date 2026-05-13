package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.api.application.dto.VacinaResumoResponse;

import java.util.List;

public interface VacinaPortIn {

    List<VacinaResumoResponse> listarPorPacienteId(Long pacienteId);

    VacinaDetalheResponse buscarDetalhePorAplicacaoId(Long idAplicacao);
}


