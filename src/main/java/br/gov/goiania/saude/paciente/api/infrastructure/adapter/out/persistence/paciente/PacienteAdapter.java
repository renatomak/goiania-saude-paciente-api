package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente;

import br.gov.goiania.saude.paciente.api.application.port.out.PacientePortOut;
import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.PacienteMapper;
import org.springframework.stereotype.Component;

@Component
public class PacienteAdapter implements PacientePortOut {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper mapper;

    public PacienteAdapter(PacienteRepository pacienteRepository,
                           PacienteMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Paciente buscarPorCpf(String cpf) {
        return pacienteRepository.buscarPorCpf(cpf)
            .map(mapper::toDomain)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o CPF: " + cpf));
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.buscarPorId(id)
            .map(mapper::toDomain)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o ID: " + id));
    }
}
