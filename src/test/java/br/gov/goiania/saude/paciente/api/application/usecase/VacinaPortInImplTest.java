package br.gov.goiania.saude.paciente.api.application.usecase;

import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponseMock;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponseMock;
import br.gov.goiania.saude.paciente.api.application.port.in.VacinaPortIn;
import br.gov.goiania.saude.paciente.api.application.port.out.VacinaPortOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

class VacinaPortInImplTest {

    @Test
    void deveListarVacinasPorPaciente() {
        VacinaPortOut repository = Mockito.mock(VacinaPortOut.class);
        VacinaPortIn service = new VacinaServiceImpl(repository);

        Mockito.when(repository.listarPorPacienteId(10L))
                .thenReturn(List.of(VacinaResumoResponseMock.valido()));

        List<VacinaResumoResponse> vacinas = service.listarPorPacienteId(10L);

        Assertions.assertEquals(1, vacinas.size());
        Assertions.assertEquals("Covid", vacinas.get(0).vacina());
    }

    @Test
    void deveBuscarDetalhePorAplicacaoId() {
        VacinaPortOut repository = Mockito.mock(VacinaPortOut.class);
        VacinaPortIn service = new VacinaServiceImpl(repository);

        VacinaDetalheResponse detalhe = VacinaDetalheResponseMock.valido();

        Mockito.when(repository.buscarDetalhePorAplicacaoId(1L)).thenReturn(Optional.of(detalhe));

        VacinaDetalheResponse retorno = service.buscarDetalhePorAplicacaoId(1L);

        Assertions.assertEquals(1L, retorno.idAplicacao());
        Assertions.assertEquals("Covid", retorno.nomeVacina());
    }

    @Test
    void deveRetornar404QuandoAplicacaoNaoEncontrada() {
        VacinaPortOut repository = Mockito.mock(VacinaPortOut.class);
        VacinaPortIn service = new VacinaServiceImpl(repository);

        Mockito.when(repository.buscarDetalhePorAplicacaoId(99L)).thenReturn(Optional.empty());

        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,
                () -> service.buscarDetalhePorAplicacaoId(99L));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
