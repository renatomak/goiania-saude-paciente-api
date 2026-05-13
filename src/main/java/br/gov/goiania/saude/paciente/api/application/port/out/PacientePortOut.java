package br.gov.goiania.saude.paciente.api.application.port.out;

import br.gov.goiania.saude.paciente.api.domain.model.Paciente;

public interface PacientePortOut {
    Paciente buscarPorCpf(String cpf);
    Paciente buscarPorId(Long id);
}
