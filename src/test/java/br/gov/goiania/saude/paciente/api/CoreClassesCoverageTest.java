package br.gov.goiania.saude.paciente.api;

import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponseMock;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteResumoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteResumoResponseMock;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaAplicacaoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaAplicacaoResponseMock;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaDetalheResponseMock;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.VacinaResumoResponseMock;
import br.gov.goiania.saude.paciente.api.domain.exception.BadRequestException;
import br.gov.goiania.saude.paciente.api.domain.exception.ResourceNotFoundException;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteSearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CoreClassesCoverageTest {

    @Test
    void deveCobrirFactoriesDePacienteSearchResult() {
        PacienteResponse paciente = PacienteResponseMock.exemploSemEndereco();
        PacienteResumoResponse resumo = PacienteResumoResponseMock.valido();

        PacienteSearchResult porCpf = PacienteSearchResult.resultadoCpf(paciente);
        PacienteSearchResult porNome = PacienteSearchResult.resultadoNome(List.of(resumo));

        Assertions.assertTrue(porCpf.buscaPorCpf());
        Assertions.assertFalse(porNome.buscaPorCpf());
    }

    @Test
    void deveCobrirRecordsEExceptions() {
        PacienteResponse paciente = PacienteResponseMock.valido();
        PacienteResumoResponse pacienteResumo = PacienteResumoResponseMock.valido();
        VacinaResumoResponse vacinaResumo = VacinaResumoResponseMock.valido();
        VacinaDetalheResponse vacinaDetalhe = VacinaDetalheResponseMock.valido();
        VacinaAplicacaoResponse vacinaAplicacao = VacinaAplicacaoResponseMock.valido();

        BadRequestException badRequest = new BadRequestException("erro");
        ResourceNotFoundException notFound =
                new ResourceNotFoundException("nao encontrado");

        Assertions.assertEquals("Goiania", paciente.endereco().cidade());
        Assertions.assertEquals("Maria", pacienteResumo.nome());
        Assertions.assertEquals("Covid", vacinaResumo.vacina());
        Assertions.assertEquals("uuid", vacinaDetalhe.rndsUuid());
        Assertions.assertEquals("uuid", vacinaAplicacao.uuidRnds());
        Assertions.assertEquals("erro", badRequest.getMessage());
        Assertions.assertEquals("nao encontrado", notFound.getMessage());
    }

}
