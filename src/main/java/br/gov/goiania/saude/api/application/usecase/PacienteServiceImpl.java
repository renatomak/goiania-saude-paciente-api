package br.gov.goiania.saude.api.application.usecase;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.PacienteResumoResponse;
import br.gov.goiania.saude.api.application.port.out.PacientePortOut;
import br.gov.goiania.saude.api.application.dto.PacienteSearchResult;
import br.gov.goiania.saude.api.application.port.in.PacientePortIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacientePortIn {

    private final PacientePortOut pacientePortOut;
    private final int limiteBuscaNome;

    public PacienteServiceImpl(PacientePortOut pacientePortOut,
                               @Value("${app.search.nome-limit:50}") int limiteBuscaNome) {
        this.pacientePortOut = pacientePortOut;
        this.limiteBuscaNome = limiteBuscaNome;
    }

    @Override
    public PacienteSearchResult buscarPorQuery(String query) {
        if (query == null || query.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O parametro query e obrigatorio.");
        }

        String queryNormalizada = query.trim();
        String cpfSemMascara = queryNormalizada.replaceAll("\\D", "");

        if (cpfSemMascara.length() == 11) {
            PacienteResponse paciente = pacientePortOut.buscarDetalhePorCpf(cpfSemMascara)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Paciente nao encontrado para o CPF informado."));
            return PacienteSearchResult.resultadoCpf(paciente);
        }

        List<PacienteResumoResponse> pacientes = pacientePortOut.buscarResumoPorNome(queryNormalizada, limiteBuscaNome);
        return PacienteSearchResult.resultadoNome(pacientes);
    }

    @Override
    public PacienteResponse buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O parametro cpf e obrigatorio.");
        }
        String cpfSemMascara = cpf.replaceAll("\\D", "");
        if (cpfSemMascara.length() != 11) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF invalido.");
        }
        return pacientePortOut.buscarDetalhePorCpf(cpfSemMascara)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente nao encontrado para o CPF informado."));
    }

    @Override
    public List<PacienteResumoResponse> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O parametro nome e obrigatorio.");
        }
        String nomeNormalizado = nome.trim();
        return pacientePortOut.buscarResumoPorNome(nomeNormalizado, limiteBuscaNome);
    }

    @Override
    public PacienteResponse buscarPorId(Long id) {
        return pacientePortOut.buscarDetalhePorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente nao encontrado para o id informado."));
    }
}
