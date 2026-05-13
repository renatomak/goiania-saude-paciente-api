package br.gov.goiania.saude.paciente.api.application.usecase;

import br.gov.goiania.saude.paciente.api.application.port.in.PacientePortIn;
import br.gov.goiania.saude.paciente.api.application.port.out.ProntuarioPortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProntuarioPortInImplTest {
    private ProntuarioPortOut prontuarioPortOut;
    private PacientePortIn pacientePortIn;
    private ProntuarioPortInImpl prontuarioService;

    @BeforeEach
    void setUp() {
        prontuarioPortOut = mock(ProntuarioPortOut.class);
        pacientePortIn = mock(PacientePortIn.class);
        prontuarioService = new ProntuarioPortInImpl(prontuarioPortOut, pacientePortIn);
    }

    @Test
    void deveRetornarConteudoSemHtml() {

    }
}
