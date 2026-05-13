package br.gov.goiania.saude.paciente.api.application.usecase;

import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.paciente.api.application.port.out.PacientePortOut;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PacienteUseCase implements PacientePortIn {

    private final PacientePortOut adapter;
    private final PacienteMapper mapper;

    @Override
    public PacienteResponse buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O parametro cpf e obrigatorio.");
        }
        String cpfSemMascara = cpf.replaceAll("\\D", "");
        if (cpfSemMascara.length() != 11) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF invalido.");
        }
        try {
            return mapper.toPacienteResponse(adapter.buscarPorCpf(cpfSemMascara));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente nao encontrado para o CPF informado.");
        }
    }

    @Override
    public PacienteResponse buscarPorId(Long id) {
        try {
            return mapper.toPacienteResponse(adapter.buscarPorId(id));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente nao encontrado para o id informado.");
        }
    }
}
