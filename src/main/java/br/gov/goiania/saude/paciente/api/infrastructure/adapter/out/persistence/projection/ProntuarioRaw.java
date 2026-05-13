package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.projection;

import java.time.LocalDateTime;

public interface ProntuarioRaw {
    Long getNrAtendimento();
    LocalDateTime getDtChegada();
    String getUnidadeNome();
    String getUnidadeTelefone();
    String getTipoAtendimento();
    String getClassificacaoRiscoNome();

    String getProfissionalNome();
    String getProfissionalRegistro();
    String getProfissionalTipoConselho();
    String getProfissionalCbo();
    String getProfissionalCboDescricao();

    LocalDateTime getRegistroData();
    Integer getRegistroTipoId();
    String getRegistroConteudo();

    LocalDateTime getAihDataCadastro();
    String getAihPrincipaisSinais();
    String getAihCondicoesInternacao();
    String getAihPrincipaisResultados();
    String getAihDiagnosticoInicial();
    Boolean getPossuiAih();
}
