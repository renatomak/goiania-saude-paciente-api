package br.gov.goiania.saude.paciente.api.application.usecase;

import br.gov.goiania.saude.paciente.api.application.dto.ExameResponse;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.ExameRepository;
import org.springframework.stereotype.Service;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExameService {
    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }

    public List<ExameResponse> buscarExamesPorAtendimento(Long nrAtendimento) {
        List<Object[]> results = exameRepository.buscarExamesPorAtendimento(nrAtendimento);
        List<ExameResponse> exames = new ArrayList<>();
        for (Object[] row : results) {
            String grupo = (String) row[0];
            List<String> itens = new ArrayList<>();
            if (row[1] instanceof Array arr) {
                try {
                    Object[] arrObj = (Object[]) arr.getArray();
                    for (Object o : arrObj) {
                        itens.add(o != null ? o.toString() : null);
                    }
                } catch (Exception e) {
                    // fallback
                }
            } else if (row[1] instanceof String[] arrStr) {
                itens = Arrays.asList(arrStr);
            }
            exames.add(new ExameResponse(grupo, itens));
        }
        return exames;
    }
}

