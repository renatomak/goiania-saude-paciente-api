package br.gov.goiania.saude.api.infrastructure.mapper;

import br.gov.goiania.saude.api.application.dto.PacienteResponse;
import br.gov.goiania.saude.api.application.dto.EnderecoResponse;
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

    // ...

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
                raw.keyword,
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
}
