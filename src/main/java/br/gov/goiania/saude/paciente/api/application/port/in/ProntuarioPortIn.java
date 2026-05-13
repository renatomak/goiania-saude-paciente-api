package br.gov.goiania.saude.paciente.api.application.port.in;

import br.gov.goiania.saude.paciente.api.application.dto.ProntuarioEstruturadoResponse;

public interface ProntuarioPortIn {
    ProntuarioEstruturadoResponse buscarProntuarioEstruturado(Long pacienteId);
}
