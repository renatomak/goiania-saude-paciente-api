package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence;

import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.VacinaRawRowMapper;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.VacinaRaw;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.VacinaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

class VacinaJdbcRepositoryTest {

    @SuppressWarnings("unchecked")
    @Test
    void deveListarPorPacienteId() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        VacinaRawRowMapper rawRowMapper = Mockito.mock(VacinaRawRowMapper.class);
        RowMapper<VacinaRaw> resumoMapper = Mockito.mock(RowMapper.class);
        VacinaMapper vacinaMapper = Mockito.mock(VacinaMapper.class);
        Mockito.when(rawRowMapper.resumo()).thenReturn(resumoMapper);

        VacinaRaw raw = Mockito.mock(VacinaRaw.class);
        VacinaResumoResponse dto = Mockito.mock(VacinaResumoResponse.class);
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.eq(resumoMapper), Mockito.eq(1L)))
                .thenReturn(List.of(raw));
        Mockito.when(vacinaMapper.toVacinaResumoResponse(raw)).thenReturn(dto);

        VacinaJdbcPortOut repository = new VacinaJdbcPortOut(jdbcTemplate, rawRowMapper, vacinaMapper);

        List<VacinaResumoResponse> retorno = repository.listarPorPacienteId(1L);

        Assertions.assertEquals(1, retorno.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void deveBuscarDetalhePorAplicacaoIdComResultado() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        VacinaRawRowMapper rawRowMapper = Mockito.mock(VacinaRawRowMapper.class);
        RowMapper<VacinaRaw> detalheMapper = Mockito.mock(RowMapper.class);
        VacinaMapper vacinaMapper = Mockito.mock(VacinaMapper.class);
        Mockito.when(rawRowMapper.detalhe()).thenReturn(detalheMapper);

        VacinaRaw raw = Mockito.mock(VacinaRaw.class);
        VacinaDetalheResponse detalhe = Mockito.mock(VacinaDetalheResponse.class);
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.eq(detalheMapper), Mockito.eq(99L)))
                .thenReturn(List.of(raw));
        Mockito.when(vacinaMapper.toVacinaDetalheResponse(raw)).thenReturn(detalhe);

        VacinaJdbcPortOut repository = new VacinaJdbcPortOut(jdbcTemplate, rawRowMapper, vacinaMapper);

        var retorno = repository.buscarDetalhePorAplicacaoId(99L);

        Assertions.assertTrue(retorno.isPresent());
    }

    @SuppressWarnings("unchecked")
    @Test
    void deveBuscarDetalhePorAplicacaoIdSemResultado() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        VacinaRawRowMapper rawRowMapper = Mockito.mock(VacinaRawRowMapper.class);
        RowMapper<VacinaRaw> detalheMapper = Mockito.mock(RowMapper.class);
        VacinaMapper vacinaMapper = Mockito.mock(VacinaMapper.class);
        Mockito.when(rawRowMapper.detalhe()).thenReturn(detalheMapper);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.eq(detalheMapper), Mockito.eq(99L)))
                .thenReturn(List.of());

        VacinaJdbcPortOut repository = new VacinaJdbcPortOut(jdbcTemplate, rawRowMapper, vacinaMapper);

        var retorno = repository.buscarDetalhePorAplicacaoId(99L);

        Assertions.assertTrue(retorno.isEmpty());
    }
}
