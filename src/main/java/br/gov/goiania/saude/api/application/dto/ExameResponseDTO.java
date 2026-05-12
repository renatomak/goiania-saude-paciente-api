package br.gov.goiania.saude.api.application.dto;

import java.util.List;

public record ExameResponseDTO(String grupo, List<String> itens) {}

