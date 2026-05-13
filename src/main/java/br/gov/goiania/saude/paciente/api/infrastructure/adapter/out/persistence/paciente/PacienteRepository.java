package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepository {

    private static final String PACIENTE_DETALHE_SQL = """
        SELECT
            p.cd_usu_cadsus AS id,
            cns.cd_numero_cartao AS cartaoSus,
            p.nm_usuario AS nome,
            p.apelido AS nomeSocial,
            p.cpf,
            p.sg_sexo AS sexo,
            p.nm_mae AS mae,
            p.nm_pai AS pai,
            p.dt_nascimento AS dataNascimento,
            DATE_PART('year', AGE(p.dt_nascimento))::int AS idade,
            pais_nas.ds_pais AS paisNascimento,
            uf_nas.sigla AS ufNascimento,
            cid_nas.descricao AS municipioNascimento,
            r.ds_raca AS raca,
            et.ds_etnia AS etnia,
            p.nr_telefone AS telefone,
            p.nr_telefone_2 AS telefoneContato,
            p.email,
            e.keyword AS enderecoCompleto,
            tl.ds_tipo_logradouro AS tipoLogradouro,
            e.nm_logradouro AS logradouro,
            e.nm_comp_logradouro AS complemento,
            e.nr_logradouro AS numero,
            e.cep,
            e.nm_bairro AS bairro,
            c.cod_cid AS cidadeId,
            c.descricao AS cidade,
            uf.sigla AS uf,
            'Brasil' AS paisEndereco
        FROM public.usuario_cadsus p
        INNER JOIN public.endereco_usuario_cadsus e ON p.cd_endereco = e.cd_endereco
        INNER JOIN public.cidade c ON e.cod_cid = c.cod_cid
        INNER JOIN public.estado uf ON c.cod_est = uf.cod_est
        INNER JOIN public.tipo_logradouro_cadsus tl ON e.cd_tipo_logradouro = tl.cd_tipo_logradouro
        LEFT JOIN public.usuario_cadsus_cns cns ON cns.cd_usu_cadsus = p.cd_usu_cadsus AND cns.st_excluido = 0
        LEFT JOIN public.cidade cid_nas ON p.cod_cid_nascimento = cid_nas.cod_cid
        LEFT JOIN public.estado uf_nas ON cid_nas.cod_est = uf_nas.cod_est
        LEFT JOIN public.nacionalidade pais_nas ON p.cd_pais_nascimento = pais_nas.cd_pais
        LEFT JOIN public.raca r ON p.cd_raca = r.cd_raca
        LEFT JOIN public.etnia_indigena et ON p.cd_etnia = et.cd_etnia
        WHERE %s
          AND p.st_excluido = 0
        ORDER BY cns.tp_cartao DESC NULLS LAST, cns.dt_atribuicao DESC NULLS LAST
        LIMIT 1
        """;

    private final JdbcTemplate jdbcTemplate;

    public PacienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<PacienteProjection> buscarPorCpf(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return buscarUm(PACIENTE_DETALHE_SQL.formatted("p.cpf = ?"), cpfLimpo);
    }

    public Optional<PacienteProjection> buscarPorId(Long id) {
        return buscarUm(PACIENTE_DETALHE_SQL.formatted("p.cd_usu_cadsus = ?"), id);
    }

    private Optional<PacienteProjection> buscarUm(String sql, Object... params) {
        List<PacienteProjection> results = jdbcTemplate.query(sql, this::mapRowToProjection, params);
        return results.stream().findFirst();
    }

    private PacienteProjection mapRowToProjection(ResultSet rs, int rowNum) throws SQLException {
        Date data = rs.getObject("dataNascimento", Date.class);
        return new PacienteProjection(
                rs.getLong("id"), rs.getString("cartaoSus"),
                rs.getString("nome"), rs.getString("nomeSocial"),
                rs.getString("cpf"), rs.getString("sexo"),
                rs.getString("mae"), rs.getString("pai"),
                data != null ? data.toLocalDate() : null, rs.getObject("idade", Integer.class),
                rs.getString("paisNascimento"), rs.getString("ufNascimento"),
                rs.getString("municipioNascimento"), rs.getString("raca"),
                rs.getString("etnia"), rs.getString("telefone"),
                rs.getString("telefoneContato"), rs.getString("email"),
                rs.getString("enderecoCompleto"), rs.getString("tipoLogradouro"),
                rs.getString("logradouro"), rs.getString("complemento"),
                rs.getString("numero"), rs.getString("cep"),
                rs.getString("bairro"), rs.getLong("cidadeId"),
                rs.getString("cidade"), rs.getString("uf"),
                rs.getString("paisEndereco")
        );
    }
}
