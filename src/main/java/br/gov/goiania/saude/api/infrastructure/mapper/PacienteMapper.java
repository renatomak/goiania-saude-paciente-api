package br.gov.goiania.saude.api.infrastructure.mapper;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.EnderecoResponse;
import br.gov.goiania.saude.api.domain.model.Paciente;
import br.gov.goiania.saude.api.infrastructure.adapter.out.persistence.paciente.PacienteProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import br.gov.goiania.saude.api.shared.util.FormatadorUtil;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    @Mapping(target = "sexo", source = "sexo", qualifiedByName = "converterSexo")
    @Mapping(target = "endereco", expression = "java(toEnderecoResponse(raw))")
    @Mapping(target = "dataNascimento", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarData(raw.dataNascimento))")
    @Mapping(target = "cpf", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarCpf(raw.cpf))")
    @Mapping(target = "telefone", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarTelefone(raw.telefone))")
    @Mapping(target = "cartaoSus", source = "cartaoSus")
    @Mapping(target = "nomeSocial", source = "nomeSocial")
    @Mapping(target = "paisNascimento", source = "paisNascimento")
    @Mapping(target = "ufNascimento", source = "ufNascimento")
    @Mapping(target = "municipioNascimento", source = "municipioNascimento")
    @Mapping(target = "raca", source = "raca")
    @Mapping(target = "etnia", source = "etnia")
    @Mapping(target = "telefoneContato", source = "telefoneContato")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "paisEndereco", source = "paisEndereco")
    PacienteResponse toPacienteResponse(PacienteRaw raw);

    @Mapping(target = "sexo", source = "sexo", qualifiedByName = "converterSexo")
    @Mapping(target = "endereco", expression = "java(toEnderecoResponse(projection))")
    @Mapping(target = "dataNascimento", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarData(projection.dataNascimento()))")
    @Mapping(target = "cpf", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarCpf(projection.cpf()))")
    @Mapping(target = "telefone", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarTelefone(projection.telefone()))")
    @Mapping(target = "cartaoSus", source = "cartaoSus")
    @Mapping(target = "nomeSocial", source = "nomeSocial")
    @Mapping(target = "paisNascimento", source = "paisNascimento")
    @Mapping(target = "ufNascimento", source = "ufNascimento")
    @Mapping(target = "municipioNascimento", source = "municipioNascimento")
    @Mapping(target = "raca", source = "raca")
    @Mapping(target = "etnia", source = "etnia")
    @Mapping(target = "telefoneContato", source = "telefoneContato")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "paisEndereco", source = "paisEndereco")
    PacienteResponse toPacienteResponse(PacienteProjection projection);

    @Mapping(target = "sexo", source = "sexo", qualifiedByName = "converterSexo")
    @Mapping(target = "endereco", expression = "java(toEnderecoResponse(domain))")
    @Mapping(target = "dataNascimento", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarData(domain.dataNascimento()))")
    @Mapping(target = "cpf", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarCpf(domain.cpf()))")
    @Mapping(target = "telefone", expression = "java(br.gov.goiania.saude.api.shared.util.FormatadorUtil.formatarTelefone(domain.telefone()))")
    @Mapping(target = "cartaoSus", source = "cartaoSus")
    @Mapping(target = "nomeSocial", source = "nomeSocial")
    @Mapping(target = "paisNascimento", source = "paisNascimento")
    @Mapping(target = "ufNascimento", source = "ufNascimento")
    @Mapping(target = "municipioNascimento", source = "municipioNascimento")
    @Mapping(target = "raca", source = "raca")
    @Mapping(target = "etnia", source = "etnia")
    @Mapping(target = "telefoneContato", source = "telefoneContato")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "paisEndereco", source = "paisEndereco")
    PacienteResponse toPacienteResponse(Paciente domain);

    @Named("converterSexo")
    static String converterSexo(String sexo) {
        if (sexo == null) return "-";
        return switch (sexo.trim().toUpperCase()) {
            case "F" -> "FEMININO";
            case "M" -> "MASCULINO";
            default -> "-";
        };
    }

    default EnderecoResponse toEnderecoResponse(PacienteRaw raw) {
        return new EnderecoResponse(
                raw.endereco,
                raw.tipoLogradouro,
                raw.logradouro,
                raw.complemento,
                raw.numero,
                FormatadorUtil.formatarCep(raw.cep),
                raw.bairro,
                raw.cidadeId,
                raw.cidade,
                raw.uf
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
