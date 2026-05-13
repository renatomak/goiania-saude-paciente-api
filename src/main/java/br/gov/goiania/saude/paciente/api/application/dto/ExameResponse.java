package br.gov.goiania.saude.paciente.api.application.dto;

import java.util.List;

public record ExameResponse(String grupo, List<String> itens) { }

