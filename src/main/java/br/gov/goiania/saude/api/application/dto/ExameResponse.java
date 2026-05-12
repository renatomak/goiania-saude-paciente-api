package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ExameResponse(String grupo, List<String> itens) {}

