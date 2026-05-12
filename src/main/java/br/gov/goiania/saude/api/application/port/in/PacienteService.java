package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.PacienteSearchResult;
import br.gov.goiania.saude.api.application.dto.PacienteResumoResponse;

public interface PacienteService {


    PacienteSearchResult buscarPorQuery(String query);

    PacienteResponse buscarPorCpf(String cpf);

    java.util.List<PacienteResumoResponse> buscarPorNome(String nome);

    PacienteResponse buscarPorId(Long id);
}


