package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.domain.model.PacienteMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteMapperTest {

    @Test
    void testToResponseDominio_deveMapearDominio() {
        Paciente dominio = PacienteMock.valido();

        PacienteResponse dto = PacienteMapper.INSTANCE.toResponse(dominio);

        assertEquals("FEMININO", dto.sexo());
        assertEquals("Maria", dto.nome());
        assertEquals("123.456.789-01", dto.cpf());
        assertEquals("(62) 99999-9999", dto.telefone());
        assertEquals("Rua A, 10", dto.endereco().endereco());
    }
}
