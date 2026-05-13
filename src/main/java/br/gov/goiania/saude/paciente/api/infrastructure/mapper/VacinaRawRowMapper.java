package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VacinaRawRowMapper {
    public RowMapper<VacinaRaw> resumo() {
        return (rs, rowNum) -> {
            VacinaRaw raw = new VacinaRaw();
            raw.setIdAplicacao(rs.getLong("id_aplicacao"));
            raw.setDataAplicacao(rs.getObject("data_aplicacao"));
            raw.setVacina(rs.getString("vacina"));
            raw.setDose(rs.getString("dose"));
            raw.setEstrategia(rs.getString("estrategia"));
            raw.setLaboratorio(rs.getString("laboratorio"));
            raw.setEstabelecimento(rs.getString("estabelecimento"));
            raw.setProfissional(rs.getString("profissional"));
            return raw;
        };
    }

    public RowMapper<VacinaRaw> detalhe() {
        return this::mapRowToDetalhe;
    }

    private VacinaRaw mapRowToDetalhe(ResultSet rs, int rowNum) throws SQLException {
        VacinaRaw raw = new VacinaRaw();
        preencherCamposBasicos(rs, raw);
        preencherCamposAdicionais(rs, raw);
        return raw;
    }

    private void preencherCamposBasicos(ResultSet rs, VacinaRaw raw) throws SQLException {
        raw.setIdAplicacao(rs.getLong("id_aplicacao"));
        raw.setNrAtendimento(rs.getObject("nr_atendimento"));
        raw.setDoseCodigo(rs.getObject("dose_codigo"));
        raw.setDose(rs.getString("dose"));
        raw.setEstrategia(rs.getString("estrategia"));
        raw.setNomeVacina(rs.getString("nome_vacina"));
        raw.setDescricaoVacina(rs.getString("descricao_vacina"));
        raw.setLote(rs.getString("lote"));
        raw.setValidadeLote(rs.getObject("validade_lote"));
        raw.setFabricanteNome(rs.getString("fabricante_nome"));
        raw.setFabricanteCnpj(rs.getString("fabricante_cnpj"));
        raw.setDataAplicacao(rs.getObject("data_aplicacao"));
        raw.setLocalAtendimento(rs.getString("local_atendimento"));
        raw.setTurno(rs.getString("turno"));
        raw.setGrupoAtendimento(rs.getString("grupo_atendimento"));
        raw.setObservacao(rs.getString("observacao"));
        raw.setStatus(rs.getString("status"));
    }

    private void preencherCamposAdicionais(ResultSet rs, VacinaRaw raw) throws SQLException {
        raw.setGestante(rs.getBoolean("gestante"));
        raw.setPuerpera(rs.getBoolean("puerpera"));
        raw.setHistorico(rs.getBoolean("historico"));
        raw.setForaEsquema(rs.getBoolean("fora_esquema"));
        raw.setViajante(rs.getBoolean("viajante"));
        raw.setNovoFrasco(rs.getBoolean("novo_frasco"));
        raw.setViaAdministracao(rs.getString("via_administracao"));
        raw.setLocalAplicacao(rs.getString("local_aplicacao"));
        raw.setProfissionalNome(rs.getString("profissional_nome"));
        raw.setProfissionalConselho(rs.getString("profissional_conselho"));
        raw.setProfissionalRegistro(rs.getString("profissional_registro"));
        raw.setProfissionalCns(rs.getString("profissional_cns"));
        raw.setUnidadeNome(rs.getString("unidade_nome"));
        raw.setUnidadeCnes(rs.getString("unidade_cnes"));
        raw.setRndsSituacao(rs.getString("rnds_situacao"));
        raw.setRndsUuid(rs.getString("rnds_uuid"));
    }
}
