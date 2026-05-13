package br.gov.goiania.saude.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.PacienteResumoResponse;
import br.gov.goiania.saude.api.application.port.in.PacientePortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacientePortIn pacientePortIn;

    public PacienteController(PacientePortIn pacientePortIn) {
        this.pacientePortIn = pacientePortIn;
    }

    @GetMapping("/search/cpf")
    public ResponseEntity<PacienteResponse> buscarPorCpf(@RequestParam("cpf") String cpf) {
        PacienteResponse paciente = pacientePortIn.buscarPorCpf(cpf);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/search/nome")
    public ResponseEntity<List<PacienteResumoResponse>> buscarPorNome(@RequestParam("nome") String nome) {
        List<PacienteResumoResponse> pacientes = pacientePortIn.buscarPorNome(nome);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacientePortIn.buscarPorId(id));
    }
}

