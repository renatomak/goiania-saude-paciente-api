package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.PacienteDTO;
import br.gov.goiania.saude.api.application.dto.PacienteSearchResult;
import br.gov.goiania.saude.api.application.dto.PacienteResumoDTO;

public interface PacienteService {


    PacienteSearchResult buscarPorQuery(String query);

    PacienteDTO buscarPorCpf(String cpf);

    java.util.List<PacienteResumoDTO> buscarPorNome(String nome);

    PacienteDTO buscarPorId(Long id);
}


