package br.gov.goiania.saude.paciente.api.infrastructure.mapper;

import br.gov.goiania.saude.paciente.api.application.dto.EnderecoResponse;
import br.gov.goiania.saude.paciente.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.paciente.api.domain.model.Paciente;
import br.gov.goiania.saude.paciente.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import br.gov.goiania.saude.paciente.api.shared.util.FormatadorUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    Paciente toDomain(PacienteProjection projection);

    @Mapping(target = "sexo", source = "sexo", qualifiedByName = "converterSexo")
    @Mapping(target = "endereco", expression = "java(toEnderecoResponse(domain))")
    @Mapping(target = "dataNascimento",
            expression = "java(br.gov.goiania.saude.paciente.api.shared.util.FormatadorUtil.formatarData(domain.dataNascimento()))")
    @Mapping(target = "cpf",
            expression = "java(br.gov.goiania.saude.paciente.api.shared.util.FormatadorUtil.formatarCpf(domain.cpf()))")
    @Mapping(target = "telefone",
            expression = "java(br.gov.goiania.saude.paciente.api.shared.util.FormatadorUtil.formatarTelefone(domain.telefone()))")
    PacienteResponse toResponse(Paciente domain);

    @Named("converterSexo")
    static String converterSexo(String sexo) {
        if (sexo == null) {
            return "-";
        }
        return switch (sexo.trim().toUpperCase()) {
            case "F" -> "FEMININO";
            case "M" -> "MASCULINO";
            default -> "-";
        };
    }

    default EnderecoResponse toEnderecoResponse(Paciente domain) {
        return new EnderecoResponse(
                domain.enderecoCompleto(),
                domain.tipoLogradouro(),
                domain.logradouro(),
                domain.complemento(),
                domain.numero(),
                FormatadorUtil.formatarCep(domain.cep()),
                domain.bairro(),
                domain.cidadeId(),
                domain.cidade(),
                domain.uf()
        );
    }
}
