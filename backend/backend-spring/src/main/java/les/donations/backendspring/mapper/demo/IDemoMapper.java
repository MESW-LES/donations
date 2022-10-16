package les.donations.backendspring.mapper.demo;

import les.donations.backendspring.dto.DemoDTO;
import les.donations.backendspring.model.Demo;

public interface IDemoMapper {

    Demo dtoToModel(final DemoDTO demoDTO);
    DemoDTO modelToDTO(final Demo demo);
}
