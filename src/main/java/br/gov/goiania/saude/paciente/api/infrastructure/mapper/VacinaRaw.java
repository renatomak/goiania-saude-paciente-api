package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacinaRaw {
    private Long idAplicacao;
    private Object nrAtendimento;
    private Object doseCodigo;
    private String dose;
    private String estrategia;
    private String nomeVacina;
    private String descricaoVacina;
    private String lote;
    private Object validadeLote;
    private String fabricanteNome;
    private String fabricanteCnpj;
    private Object dataAplicacao;
    private String localAtendimento;
    private String turno;
    private String grupoAtendimento;
    private String observacao;
    private String status;
    private Boolean gestante;
    private Boolean puerpera;
    private Boolean historico;
    private Boolean foraEsquema;
    private Boolean viajante;
    private Boolean novoFrasco;
    private String viaAdministracao;
    private String localAplicacao;
    private String profissionalNome;
    private String profissionalConselho;
    private String profissionalRegistro;
    private String profissionalCns;
    private String unidadeNome;
    private String unidadeCnes;
    private String rndsSituacao;
    private String rndsUuid;
    private String vacina;
    private String laboratorio;
    private String estabelecimento;
    private String profissional;
}
