package br.gov.goiania.saude.api.application.dto;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.PacienteResumoResponse;

import java.util.List;

public record PacienteSearchResult(
        boolean buscaPorCpf,
        PacienteResponse paciente,
        List<PacienteResumoResponse> pacientes
) {

    public static PacienteSearchResult resultadoCpf(PacienteResponse paciente) {
        return new PacienteSearchResult(true, paciente, List.of());
    }

    public static PacienteSearchResult resultadoNome(List<PacienteResumoResponse> pacientes) {
        return new PacienteSearchResult(false, null, pacientes);
    }
}


