package br.gov.goiania.saude.paciente.api.application.dto;

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


