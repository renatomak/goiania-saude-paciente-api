package br.gov.goiania.saude.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.api.application.dto.ExameResponseDTO;
import br.gov.goiania.saude.api.application.usecase.ExameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/atendimentos/{nrAtendimento}/exames")
public class ExameController {
    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping
    public ResponseEntity<List<ExameResponseDTO>> buscarExames(@PathVariable Long nrAtendimento) {
        List<ExameResponseDTO> exames = exameService.buscarExamesPorAtendimento(nrAtendimento);
        if (exames.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(exames);
    }
}

