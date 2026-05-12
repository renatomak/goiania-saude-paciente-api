package br.gov.goiania.saude.api.infrastructure.mapper;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.api.application.dto.VacinaResumoResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VacinaRowMapper {

    public RowMapper<VacinaResumoResponse> resumo() {
        return (rs, rowNum) -> new VacinaResumoResponse(
                rs.getLong("id_aplicacao"),
                toLocalDate(rs.getObject("data_aplicacao")),
                rs.getString("vacina"),
                rs.getString("dose"),
                rs.getString("estrategia"),
                rs.getString("laboratorio"),
                rs.getString("estabelecimento"),
                rs.getString("profissional")
        );
    }

    public RowMapper<VacinaDetalheResponse> detalhe() {
        return (rs, rowNum) -> new VacinaDetalheResponse(
                rs.getLong("id_aplicacao"),
                toLong(rs.getObject("nr_atendimento")),
                toInteger(rs.getObject("dose_codigo")),
                rs.getString("dose"),
                rs.getString("estrategia"),
                rs.getString("nome_vacina"),
                rs.getString("descricao_vacina"),
                rs.getString("lote"),
                toLocalDate(rs.getObject("validade_lote")),
                rs.getString("fabricante_nome"),
                rs.getString("fabricante_cnpj"),
                toLocalDate(rs.getObject("data_aplicacao")),
                rs.getString("local_atendimento"),
                rs.getString("turno"),
                rs.getString("grupo_atendimento"),
                rs.getString("observacao"),
                rs.getString("status"),
                rs.getBoolean("gestante"),
                rs.getBoolean("puerpera"),
                rs.getBoolean("historico"),
                rs.getBoolean("fora_esquema"),
                rs.getBoolean("viajante"),
                rs.getBoolean("novo_frasco"),
                rs.getString("via_administracao"),
                rs.getString("local_aplicacao"),
                rs.getString("profissional_nome"),
                rs.getString("profissional_conselho"),
                rs.getString("profissional_registro"),
                rs.getString("profissional_cns"),
                rs.getString("unidade_nome"),
                rs.getString("unidade_cnes"),
                rs.getString("rnds_situacao"),
                rs.getString("rnds_uuid")
        );
    }

    private LocalDate toLocalDate(Object value) {
        if (value instanceof LocalDate localDate) {
            return localDate;
        }
        if (value instanceof java.sql.Date date) {
            return date.toLocalDate();
        }
        return null;
    }

    private Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        return ((Number) value).longValue();
    }

    private Integer toInteger(Object value) {
        if (value == null) {
            return null;
        }
        return ((Number) value).intValue();
    }
}
