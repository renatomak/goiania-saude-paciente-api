package br.gov.goiania.saude.api.application.dto;

import br.gov.goiania.saude.api.application.dto.PacienteDTO;
import br.gov.goiania.saude.api.application.dto.PacienteResumoDTO;

import java.util.List;

public record PacienteSearchResult(
        boolean buscaPorCpf,
        PacienteDTO paciente,
        List<PacienteResumoDTO> pacientes
) {

    public static PacienteSearchResult resultadoCpf(PacienteDTO paciente) {
        return new PacienteSearchResult(true, paciente, List.of());
    }

    public static PacienteSearchResult resultadoNome(List<PacienteResumoDTO> pacientes) {
        return new PacienteSearchResult(false, null, pacientes);
    }
}


