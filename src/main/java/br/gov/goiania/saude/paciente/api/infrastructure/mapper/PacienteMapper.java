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
            expression = "java(FormatadorUtil.formatarData(domain.dataNascimento()))")
    @Mapping(target = "cpf",
            expression = "java(FormatadorUtil.formatarCpf(domain.cpf()))")
    @Mapping(target = "telefone",
            expression = "java(FormatadorUtil.formatarTelefone(domain.telefone()))")
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

    default EnderecoResponse toEnderecoResponse(PacienteRaw raw) {
        return new EnderecoResponse(
                raw.getEndereco(),
                raw.getTipoLogradouro(),
                raw.getLogradouro(),
                raw.getComplemento(),
                raw.getNumero(),
                FormatadorUtil.formatarCep(raw.getCep()),
                raw.getBairro(),
                raw.getCidadeId(),
                raw.getCidade(),
                raw.getUf()
        );
    }

    default EnderecoResponse toEnderecoResponse(PacienteProjection projection) {
        return new EnderecoResponse(
                projection.enderecoCompleto(),
                projection.tipoLogradouro(),
                projection.logradouro(),
                projection.complemento(),
                projection.numero(),
                FormatadorUtil.formatarCep(projection.cep()),
                projection.bairro(),
                projection.cidadeId(),
                projection.cidade(),
                projection.uf()
        );
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
