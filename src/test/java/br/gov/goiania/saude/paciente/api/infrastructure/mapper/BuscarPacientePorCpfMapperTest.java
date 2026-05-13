package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscarPacientePorCpfMapperTest {

    @Test
    void deveMapearProjectionParaDominio() {
        PacienteProjection projection = new PacienteProjection(
                1L,
                "898001160123456",
                "Maria",
                "Mary",
                "12345678901",
                "F",
                "Ana",
                "Jose",
                LocalDate.of(1990, 1, 1),
                35,
                "Brasil",
                "GO",
                "Goiania",
                "Branca",
                "Nao informada",
                "62999998888",
                "6233334444",
                "maria@example.com",
                "Rua A, 10",
                "Rua",
                "Rua A",
                "Casa",
                "10",
                "74000000",
                "Centro",
                52L,
                "Goiania",
                "GO",
                "Brasil"
        );

        BuscarPacienteMapper mapper = Mappers.getMapper(BuscarPacienteMapper.class);
        Paciente dominio = mapper.toDomain(projection);

        assertEquals(1L, dominio.id());
        assertEquals("Maria", dominio.nome());
        assertEquals("Rua A, 10", dominio.enderecoCompleto());
        assertEquals(35, dominio.idade());
    }
}
