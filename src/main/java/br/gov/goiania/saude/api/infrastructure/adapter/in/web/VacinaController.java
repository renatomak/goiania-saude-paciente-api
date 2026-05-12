package br.gov.goiania.saude.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.api.application.port.in.VacinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping("/pacientes/{id}/vacinas")
    public ResponseEntity<List<VacinaResumoResponse>> listarVacinasPorPaciente(@PathVariable("id") Long pacienteId) {
        return ResponseEntity.ok(vacinaService.listarPorPacienteId(pacienteId));
    }

    @GetMapping("/vacinas/aplicacoes/{idAplicacao}")
    public ResponseEntity<VacinaDetalheResponse> detalharAplicacao(@PathVariable Long idAplicacao) {
        return ResponseEntity.ok(vacinaService.buscarDetalhePorAplicacaoId(idAplicacao));
    }
}

