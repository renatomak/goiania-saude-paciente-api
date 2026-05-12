package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheDTO;
import br.gov.goiania.saude.api.application.dto.VacinaResumoDTO;

import java.util.List;

public interface VacinaService {

    List<VacinaResumoDTO> listarPorPacienteId(Long pacienteId);

    VacinaDetalheDTO buscarDetalhePorAplicacaoId(Long idAplicacao);
}


