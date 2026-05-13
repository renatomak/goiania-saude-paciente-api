package br.gov.goiania.saude.api.application.usecase;

import br.gov.goiania.saude.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.api.application.port.out.ProntuarioPortOut;
import br.gov.goiania.saude.api.application.port.in.ProntuarioPortIn;
import org.springframework.stereotype.Service;

import br.gov.goiania.saude.api.application.dto.ProntuarioEstruturadoResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProntuarioPortInImpl implements ProntuarioPortIn {
    private final ProntuarioPortOut prontuarioPortOut;
    private final PacientePortIn pacientePortIn;
    private static final Logger log = LoggerFactory.getLogger(ProntuarioPortInImpl.class);

    public ProntuarioPortInImpl(ProntuarioPortOut prontuarioPortOut, PacientePortIn pacientePortIn) {
        this.prontuarioPortOut = prontuarioPortOut;
        this.pacientePortIn = pacientePortIn;
    }

    @Override
    public ProntuarioEstruturadoResponse buscarProntuarioEstruturado(Long pacienteId) {
        try {
            var paciente = pacientePortIn.buscarPorId(pacienteId);
            var atendimentos = prontuarioPortOut.buscarAtendimentosComRegistrosPorPaciente(pacienteId);
            if (atendimentos == null) atendimentos = java.util.Collections.emptyList();
            return new ProntuarioEstruturadoResponse(paciente, atendimentos);
        } catch (Exception e) {
            log.error("Erro ao buscar prontuário estruturado para paciente {}: {}", pacienteId, e.getMessage(), e);
            throw e;
        }
    }
}
