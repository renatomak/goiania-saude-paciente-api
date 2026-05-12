package br.gov.goiania.saude.api.application.usecase;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.api.application.port.out.VacinaRepository;
import br.gov.goiania.saude.api.application.port.in.VacinaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VacinaServiceImpl implements VacinaService {

    private final VacinaRepository vacinaRepository;

    public VacinaServiceImpl(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    @Override
    public List<VacinaResumoResponse> listarPorPacienteId(Long pacienteId) {
        return vacinaRepository.listarPorPacienteId(pacienteId);
    }

    @Override
    public VacinaDetalheResponse buscarDetalhePorAplicacaoId(Long idAplicacao) {
        return vacinaRepository.buscarDetalhePorAplicacaoId(idAplicacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aplicacao de vacina nao encontrada."));
    }
}

