package br.gov.goiania.saude.api.application.port.in;

import br.gov.goiania.saude.api.application.dto.ProntuarioEstruturadoResponse;

public interface ProntuarioPortIn {
    ProntuarioEstruturadoResponse buscarProntuarioEstruturado(Long pacienteId);
}
