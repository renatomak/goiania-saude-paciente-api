package br.gov.goiania.saude.api.application.usecase;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.api.application.port.out.BuscarPacientePortOut;
import br.gov.goiania.saude.api.infrastructure.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteUseCase implements PacientePortIn {

    private final BuscarPacientePortOut adapter;
    private final PacienteMapper mapper;

    @Override
    public PacienteResponse buscarPorCpf(String cpf) {
        return mapper.toPacienteResponse(adapter.buscarPorCpf(cpf));
    }

    @Override
    public PacienteResponse buscarPorId(Long id) {
        return mapper.toPacienteResponse(adapter.buscarPorId(id));
    }
}
