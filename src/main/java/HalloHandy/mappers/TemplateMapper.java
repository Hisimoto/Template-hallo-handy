package HalloHandy.mappers;

import HalloHandy.dto.TemplateDto;
import HalloHandy.entity.Template;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TemplateMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Template toModel(TemplateDto dto) {return modelMapper.map(dto, Template.class);}
    public static List<Template> toModelList(List<TemplateDto> dtos) {
        return dtos.stream()
                .map(TemplateMapper::toModel)
                .collect(Collectors.toList());
    }
    public static TemplateDto toDTO(Template template) {
        if (template != null){
            TemplateDto dto = modelMapper.map(template, TemplateDto.class);
            return dto;
        }
        return null;
    }
    public static List<TemplateDto> toDTOs(List<Template> templates) {
        List<TemplateDto> dtos = templates.stream()
                .map(template -> toDTO(template))
                .collect(Collectors.toList());
        return dtos;
    }
}
