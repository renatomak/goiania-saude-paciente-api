package br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence;

import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteAdapter;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteRepository;
import br.gov.goiania.saude.paciente.api.infrastructure.mapper.PacienteMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PacienteJdbcRepositoryTest {

    @Test
    void deveBuscarPorCpf() {
        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteMapper buscarPacienteMapper = Mockito.mock(PacienteMapper.class);
        PacienteProjection projection = Mockito.mock(PacienteProjection.class);
        Paciente dominio = Mockito.mock(Paciente.class);

        Mockito.when(pacienteRepository.buscarPorCpf("123")).thenReturn(java.util.Optional.of(projection));
        Mockito.when(buscarPacienteMapper.toDomain(projection)).thenReturn(dominio);

        PacienteAdapter repository = new PacienteAdapter(pacienteRepository, buscarPacienteMapper);

        var resultado = repository.buscarPorCpf("123");

        Assertions.assertNotNull(resultado);
        Mockito.verify(pacienteRepository).buscarPorCpf("123");
        Mockito.verify(buscarPacienteMapper).toDomain(projection);
    }

    @Test
    void deveLancarErroQuandoBuscarPorIdNaoEncontrado() {
        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteMapper buscarPacienteMapper = Mockito.mock(PacienteMapper.class);
        Mockito.when(pacienteRepository.buscarPorId(1L)).thenReturn(java.util.Optional.empty());

        PacienteAdapter repository = new PacienteAdapter(pacienteRepository, buscarPacienteMapper);

        Assertions.assertThrows(RuntimeException.class, () -> repository.buscarPorId(1L));
    }
}
