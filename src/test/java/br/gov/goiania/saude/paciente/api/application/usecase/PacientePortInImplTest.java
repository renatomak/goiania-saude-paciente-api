package br.gov.goiania.saude.paciente.api.application.usecase;

import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.paciente.api.application.port.out.PacientePortOut;
import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.domain.model.PacienteMock;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.PacienteMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


class PacientePortInImplTest {

    private final PacienteMapper mapper = Mappers.getMapper(PacienteMapper.class);

    @Test
    void deveBuscarPorCpfComSucesso() {
        PacientePortOut repository = Mockito.mock(PacientePortOut.class);
        PacientePortIn service = new PacienteUseCase(repository, mapper);
        Paciente paciente = PacienteMock.valido();

        Mockito.when(repository.buscarPorCpf("12345678901")).thenReturn(paciente);

        PacienteResponse retorno = service.buscarPorCpf("123.456.789-01");

        Assertions.assertEquals("Maria", retorno.nome());
        Mockito.verify(repository).buscarPorCpf("12345678901");
    }

    @Test
    void deveRetornar400QuandoBuscarPorCpfInvalido() {
        PacientePortOut repository = Mockito.mock(PacientePortOut.class);
        PacientePortIn service = new PacienteUseCase(repository, mapper);

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> service.buscarPorCpf("123"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void deveRetornar404QuandoBuscarPorCpfNaoEncontrado() {
        PacientePortOut repository = Mockito.mock(PacientePortOut.class);
        PacientePortIn service = new PacienteUseCase(repository, mapper);

        Mockito.when(repository.buscarPorCpf("00000000000")).thenThrow(new RuntimeException("nao encontrado"));

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> service.buscarPorCpf("00000000000"));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        PacientePortOut repository = Mockito.mock(PacientePortOut.class);
        PacientePortIn service = new PacienteUseCase(repository, mapper);
        Paciente paciente = PacienteMock.valido();

        Mockito.when(repository.buscarPorId(1L)).thenReturn(paciente);

        PacienteResponse retorno = service.buscarPorId(1L);

        Assertions.assertEquals("Maria", retorno.nome());
    }

    @Test
    void deveRetornar404QuandoIdNaoEncontrado() {
        PacientePortOut repository = Mockito.mock(PacientePortOut.class);
        PacientePortIn service = new PacienteUseCase(repository, mapper);

        Mockito.when(repository.buscarPorId(1L)).thenThrow(new RuntimeException("nao encontrado"));

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> service.buscarPorId(1L));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
