package br.gov.goiania.saude.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.port.in.PacientePortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacientePortIn paciente;

    public PacienteController(PacientePortIn paciente) {
        this.paciente = paciente;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PacienteResponse> buscarPorCpf(@PathVariable("cpf") String cpf) {
        PacienteResponse paciente = this.paciente.buscarPorCpf(cpf);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(paciente.buscarPorId(id));
    }
}
