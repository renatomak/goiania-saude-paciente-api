package br.gov.goiania.saude.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.api.application.dto.ProntuarioEstruturadoResponse;
import br.gov.goiania.saude.api.application.port.in.ProntuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prontuario")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<ProntuarioEstruturadoResponse> buscarProntuarioEstruturado(@PathVariable Long pacienteId) {
        ProntuarioEstruturadoResponse response = prontuarioService.buscarProntuarioEstruturado(pacienteId);
        return ResponseEntity.ok(response);
    }
}
