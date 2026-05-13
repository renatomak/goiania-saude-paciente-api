package br.gov.goiania.saude.api.application.usecase;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.api.application.port.out.VacinaPortOut;
import br.gov.goiania.saude.api.application.port.in.VacinaPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VacinaServiceImpl implements VacinaPortIn {

    private final VacinaPortOut vacinaPortOut;

    public VacinaServiceImpl(VacinaPortOut vacinaPortOut) {
        this.vacinaPortOut = vacinaPortOut;
    }

    @Override
    public List<VacinaResumoResponse> listarPorPacienteId(Long pacienteId) {
        return vacinaPortOut.listarPorPacienteId(pacienteId);
    }

    @Override
    public VacinaDetalheResponse buscarDetalhePorAplicacaoId(Long idAplicacao) {
        return vacinaPortOut.buscarDetalhePorAplicacaoId(idAplicacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aplicacao de vacina nao encontrada."));
    }
}

