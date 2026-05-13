package br.gov.goiania.saude.paciente.api.infrastructure.adapter.in.web;

import br.gov.goiania.saude.paciente.api.application.dto.EnderecoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.paciente.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.paciente.api.application.port.in.VacinaPortIn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacientePortIn pacientePortIn;

    @MockBean
    private VacinaPortIn vacinaPortIn;

    @Test
    @DisplayName("Deve buscar paciente por CPF com sucesso")
    void deveBuscarPacientePorCpf() throws Exception {
        when(pacientePortIn.buscarPorCpf(eq("121.694.411-31")))
            .thenReturn(pacienteExemplo());

        mockMvc.perform(get("/api/pacientes/cpf/121.694.411-31"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve detalhar uma aplicação de vacina")
    void deveDetalharAplicacaoVacina() throws Exception {
        when(vacinaPortIn.buscarDetalhePorAplicacaoId(1L)).thenReturn(vacinaDetalheExemplo());

        mockMvc.perform(get("/api/vacinas/aplicacoes/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idAplicacao").value(1))
            .andExpect(jsonPath("$.nomeVacina").value("Covid"));
    }

    @Test
    @DisplayName("Deve retornar 404 quando paciente não existir")
     void deveRetornarErroQuandoPacienteNaoEncontrado() throws Exception {
         when(pacientePortIn.buscarPorId(404L))
                 .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente nao encontrado"));

         mockMvc.perform(get("/api/pacientes/404"))
             .andExpect(status().isNotFound());
     }

    @Test
    @DisplayName("Deve retornar 500 em caso de falha inesperada")
    void deveRetornarErroInesperado() throws Exception {
        when(vacinaPortIn.buscarDetalhePorAplicacaoId(anyLong()))
            .thenThrow(new RuntimeException("falha inesperada"));

        mockMvc.perform(get("/api/vacinas/aplicacoes/500"))
            .andExpect(status().isInternalServerError());
    }

     @Test
     @DisplayName("Deve retornar 400 para CPF inválido")
     void deveRetornar400ParaCpfInvalido() throws Exception {
         when(pacientePortIn.buscarPorCpf(eq("123")))
             .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF invalido."));

         mockMvc.perform(get("/api/pacientes/cpf/123"))
             .andExpect(status().isBadRequest());
     }

    private PacienteResponse pacienteExemplo() {
        return new PacienteResponse(
            1L, "Maria", "12345678901", "F", "Ana", "Jose",
            "01/01/1990", "62999999999",
            "123456789012345",
            "Maria Social",
            "Brasil",
            "GO",
            "Goiania",
            "Branca",
            "Nenhuma",
            "62988887777",
            "maria@email.com",
            new EnderecoResponse("kw", "Rua", "A", "Casa", "10", "70000", "Centro", 1L, "Goiania", "GO"),
            "Brasil"
        );
    }

    private VacinaDetalheResponse vacinaDetalheExemplo() {
        return new VacinaDetalheResponse(
            1L, 100L, 1, "1a Dose", "Adulto", "Covid", "Comirnaty", "L123",
            LocalDate.of(2025, 1, 1), "Pfizer", "123", LocalDate.of(2024, 1, 1),
            "UBS", "Manha", "Grupo", "obs", "Aplicada", false, false,
            false, false, false, false, "IM", "Braco", "Joao", "CRM",
            "1234", "999", "UBS A", "1234567", "OK", "uuid"
        );
    }
}
