package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;

public interface PacientePortIn {
    PacienteResponse buscarPorCpf(String cpf);

    PacienteResponse buscarPorId(Long id);
}
