package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuscarPacienteMapper {

    Paciente toDomain(PacienteProjection projection);
}

