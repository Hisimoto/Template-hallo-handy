package HalloHandy.mappers;

import HalloHandy.dto.TemplateDto;
import HalloHandy.entity.Template;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TemplateMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Template toModel(TemplateDto dto) {
        Template template = modelMapper.map(dto, Template.class);
        template.setSignature(dto.getSignature().getBytes());
        return template;
    }
    public static List<Template> toModelList(List<TemplateDto> dtos) {
        return dtos.stream()
                .map(TemplateMapper::toModel)
                .collect(Collectors.toList());
    }
    public static TemplateDto toDTO(Template template) {
        if (template != null){
            TemplateDto dto = modelMapper.map(template, TemplateDto.class);
            if (template.getSignature() != null) {
                dto.setSignature(new String(template.getSignature()));
            }
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
