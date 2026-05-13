package br.gov.goiania.saude.api.infrastructure.mapper;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.domain.model.Paciente;
import br.gov.goiania.saude.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteMapperTest {
    @Test
    void testToPacienteResponse_sexoFeminino() {
        PacienteRaw raw = new PacienteRaw();
        raw.id = 1L;
        raw.nome = "Maria";
        raw.cpf = "123";
        raw.sexo = "F";
        raw.mae = "Ana";
        raw.pai = "Jose";
        raw.dataNascimento = LocalDate.of(1990, 1, 1);
        raw.telefone = "6299";
        raw.endereco = "kw";
        raw.tipoLogradouro = "Rua";
        raw.logradouro = "A";
        raw.complemento = "Casa";
        raw.numero = "10";
        raw.cep = "70000";
        raw.bairro = "Centro";
        raw.cidadeId = 12L;
        raw.cidade = "Goiania";
        raw.uf = "GO";

        PacienteResponse dto = PacienteMapper.INSTANCE.toPacienteResponse(raw);
        assertEquals("FEMININO", dto.sexo());
        assertEquals("Maria", dto.nome());
        assertEquals("Goiania", dto.endereco().cidade());
    }

    @Test
    void testToPacienteResponse_sexoMasculino() {
        PacienteRaw raw = new PacienteRaw();
        raw.id = 2L;
        raw.nome = "Joao";
        raw.cpf = "456";
        raw.sexo = "M";
        raw.mae = "Ana";
        raw.pai = "Jose";
        raw.dataNascimento = LocalDate.of(1985, 5, 10);
        raw.telefone = "6298";
        raw.endereco = "kw";
        raw.tipoLogradouro = "Rua";
        raw.logradouro = "B";
        raw.complemento = "Apto";
        raw.numero = "20";
        raw.cep = "70001";
        raw.bairro = "Setor";
        raw.cidadeId = 13L;
        raw.cidade = "Anapolis";
        raw.uf = "GO";

        PacienteResponse dto = PacienteMapper.INSTANCE.toPacienteResponse(raw);
        assertEquals("MASCULINO", dto.sexo());
        assertEquals("Joao", dto.nome());
        assertEquals("Anapolis", dto.endereco().cidade());
    }

    @Test
    void testToPacienteResponse_sexoOutro() {
        PacienteRaw raw = new PacienteRaw();
        raw.id = 3L;
        raw.nome = "Alex";
        raw.cpf = "789";
        raw.sexo = "X";
        raw.mae = "Ana";
        raw.pai = "Jose";
        raw.dataNascimento = LocalDate.of(2000, 12, 31);
        raw.telefone = "6297";
        raw.endereco = "kw";
        raw.tipoLogradouro = "Avenida";
        raw.logradouro = "C";
        raw.complemento = "Bloco";
        raw.numero = "30";
        raw.cep = "70002";
        raw.bairro = "Bairro";
        raw.cidadeId = 14L;
        raw.cidade = "Trindade";
        raw.uf = "GO";

        PacienteResponse dto = PacienteMapper.INSTANCE.toPacienteResponse(raw);
        assertEquals("-", dto.sexo());
        assertEquals("Alex", dto.nome());
        assertEquals("Trindade", dto.endereco().cidade());
    }

    @Test
    void testToPacienteResponseProjection_deveMapearProjection() {
        PacienteProjection projection = new PacienteProjection(
                4L,
                "898001160123456",
                "Carla",
                "Carlinha",
                "12345678901",
                "F",
                "Maria",
                "Jose",
                LocalDate.of(1995, 6, 15),
                30,
                "Brasil",
                "GO",
                "Goiania",
                "Branca",
                "Nao informada",
                "62999998888",
                "6233334444",
                "carla@example.com",
                "Qd 1",
                "Rua",
                "Avenida Central",
                "Casa",
                "100",
                "74000000",
                "Centro",
                52L,
                "Goiania",
                "GO",
                "Brasil"
        );

        PacienteResponse dto = PacienteMapper.INSTANCE.toPacienteResponse(projection);

        assertEquals("FEMININO", dto.sexo());
        assertEquals("Carla", dto.nome());
        assertEquals("123.456.789-01", dto.cpf());
        assertEquals("(62) 99999-8888", dto.telefone());
        assertEquals("Goiania", dto.endereco().cidade());
        assertEquals("74000-000", dto.endereco().cep());
    }

    @Test
    void testToPacienteResponseDominio_deveMapearDominio() {
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

        PacienteResponse dto = PacienteMapper.INSTANCE.toPacienteResponse(dominio);

        assertEquals("FEMININO", dto.sexo());
        assertEquals("Paula", dto.nome());
        assertEquals("123.456.789-01", dto.cpf());
        assertEquals("(62) 99888-7766", dto.telefone());
        assertEquals("Rua 10, Centro", dto.endereco().keyword());
    }
}

