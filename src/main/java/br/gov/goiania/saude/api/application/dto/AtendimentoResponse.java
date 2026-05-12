package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record AtendimentoResponse(
    Long numeroAtendimento,
    String dataChegada,
    UnidadeResponse unidade,
    String tipoAtendimento,
    ProfissionalResponse profissional,
    String classificacaoRisco,
    Boolean possuiAih,
    AihDetalhesResponse aihDetalhes,
    List<RegistroResponse> registros
) {}
