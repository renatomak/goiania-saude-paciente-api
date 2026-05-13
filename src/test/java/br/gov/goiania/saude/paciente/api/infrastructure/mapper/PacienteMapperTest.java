package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteMapperTest {

    @Test
    void testToResponseDominio_deveMapearDominio() {
        Paciente dominio = new Paciente(
                7L,
                "898001160123456",
                "Paula",
                "Paulinha",
                "12345678901",
                "F",
                "Mae",
                "Pai",
                LocalDate.of(1992, 4, 10),
                34,
                "Brasil",
                "GO",
                "Goiania",
                "Parda",
                "Nao informada",
                "62998887766",
                "6233221100",
                "paula@example.com",
                "Rua 10, Centro",
                "Rua",
                "Rua 10",
                "Casa",
                "99",
                "74000000",
                "Centro",
                1L,
                "Goiania",
                "GO",
                "Brasil"
        );

        PacienteResponse dto = PacienteMapper.INSTANCE.toResponse(dominio);

        assertEquals("FEMININO", dto.sexo());
        assertEquals("Paula", dto.nome());
        assertEquals("123.456.789-01", dto.cpf());
        assertEquals("(62) 99888-7766", dto.telefone());
        assertEquals("Rua 10, Centro", dto.endereco().endereco());
    }
}

