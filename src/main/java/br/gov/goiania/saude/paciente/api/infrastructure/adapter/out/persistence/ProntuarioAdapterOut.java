package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence;

import br.gov.goiania.saude.paciente.api.application.dto.AtendimentoResponse;
import br.gov.goiania.saude.paciente.api.application.port.out.ProntuarioPortOut;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.projection.ProntuarioRaw;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.projection.ProntuarioRawRecord;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.ProntuarioMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProntuarioAdapterOut implements ProntuarioPortOut {

    private static final String PRONTUARIO_SQL = """
            SELECT
                a.nr_atendimento,
                a.dt_chegada,
                e.descricao AS unidadeNome,
                e.telefone AS unidadeTelefone,
                ta.ds_tipo_atendimento,
                COALESCE(p.nm_profissional, p_aih.nm_profissional) AS profissionalNome,
                COALESCE(p.nr_registro, p_aih.nr_registro) AS profissionalRegistro,
                COALESCE(oe.sg_orgao_emissor, oe_aih.sg_orgao_emissor) AS profissionalConselho,
                tc.cd_cbo AS prof_cbo,
                tc.ds_cbo AS prof_cbo_ds,
                ap2.data AS registro_data,
                ap2.tipo_registro AS registro_tipo_id,
                ap2.descricao AS registro_conteudo,
                cr.descricao AS classificacao_risco_nome,
                a2.dt_cadastro AS aih_dt,
                a2.principais_sinais AS aih_sinais,
                a2.condicoes_just_intern AS aih_cond,
                a2.principais_resultados AS aih_res,
                a2.diagnostico_inicial AS aih_diag,
                CASE WHEN a2.nr_atendimento IS NOT NULL
                     THEN 'Sim' ELSE 'Não' END AS possui_aih
            FROM atendimento a
            INNER JOIN empresa e ON e.empresa = a.empresa
            LEFT JOIN atendimento_prontuario ap2 ON ap2.nr_atendimento = a.nr_atendimento
            LEFT JOIN profissional p ON p.cd_profissional = a.cd_profissional
            LEFT JOIN orgao_emissor oe ON oe.cd_orgao_emissor = p.cd_con_classe
            LEFT JOIN tabela_cbo tc ON tc.cd_cbo = a.cd_cbo
            LEFT JOIN classificacao_risco cr ON cr.cd_classificacao_risco = a.classificacao_risco
            LEFT JOIN natureza_procura_tp_atendimento npta
                ON npta.cd_nat_proc_tp_atendimento = a.cd_nat_proc_tp_atendimento
            LEFT JOIN tipo_atendimento ta ON ta.cd_tp_atendimento = npta.cd_tp_atendimento
            LEFT JOIN aih a2 ON a2.nr_atendimento = a.nr_atendimento
            LEFT JOIN profissional p_aih ON p_aih.cd_profissional = a2.cd_profissional
            LEFT JOIN orgao_emissor oe_aih ON oe_aih.cd_orgao_emissor = p_aih.cd_con_classe
            WHERE a.cd_usu_cadsus = :idPaciente
              AND a.status IN (1, 4, 5, 8, 9)
              AND NOT (a2.nr_atendimento IS NULL AND a.dt_atendimento IS NULL)
            ORDER BY a.dt_chegada DESC, ap2.data ASC
            """;

    @PersistenceContext
    private EntityManager entityManager;

    private final ProntuarioMapper prontuarioMapper;

    public ProntuarioAdapterOut(ProntuarioMapper prontuarioMapper) {
        this.prontuarioMapper = prontuarioMapper;
    }

    public List<ProntuarioRaw> buscarProntuarioRawPorPaciente(Long pacienteId) {
        Query query = entityManager.createNativeQuery(PRONTUARIO_SQL);
        query.setParameter("idPaciente", pacienteId);

        List<Object[]> results = query.getResultList();
        return results.stream()
            .map(this::mapRowToProntuarioRaw)
            .toList();
    }

    @Override
    public List<AtendimentoResponse> buscarAtendimentosComRegistrosPorPaciente(Long pacienteId) {
        var raws = buscarProntuarioRawPorPaciente(pacienteId);
        return prontuarioMapper.toProntuarioResponse(pacienteId, raws).atendimentos();
    }

    private ProntuarioRaw mapRowToProntuarioRaw(Object[] row) {
        return new ProntuarioRawRecord(
            row[0] != null ? ((Number) row[0]).longValue() : null,
            convertToLocalDateTime(row[1]),
            (String) row[2],
            (String) row[3],
            (String) row[4],
            (String) row[5],
            (String) row[6],
            (String) row[7],
            (String) row[8],
            (String) row[9],
            convertToLocalDateTime(row[10]),
            row[11] != null ? ((Number) row[11]).intValue() : null,
            (String) row[12],
            (String) row[13],
            convertToLocalDateTime(row[14]),
            (String) row[15],
            (String) row[16],
            (String) row[17],
            (String) row[18],
            "Sim".equals(row[19])
        );
    }

    private LocalDateTime convertToLocalDateTime(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Timestamp ts) {
            return ts.toLocalDateTime();
        }
        if (obj instanceof java.time.LocalDateTime ldt) {
            return ldt;
        }
        return null;
    }
}
