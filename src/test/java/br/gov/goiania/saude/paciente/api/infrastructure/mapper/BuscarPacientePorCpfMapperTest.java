package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjectionMock;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscarPacientePorCpfMapperTest {

    @Test
    void deveMapearProjectionParaDominio() {
        PacienteProjection projection = PacienteProjectionMock.valido();

        PacienteMapper mapper = Mappers.getMapper(PacienteMapper.class);
        Paciente dominio = mapper.toDomain(projection);

        assertEquals(1L, dominio.id());
        assertEquals("Maria", dominio.nome());
        assertEquals("Rua A, 10", dominio.enderecoCompleto());
        assertEquals(35, dominio.idade());
    }
}
