package les.donations.backendspring.mapper.demo;

import les.donations.backendspring.dto.DemoDTO;
import les.donations.backendspring.model.Demo;
import org.springframework.stereotype.Component;

@Component
public class DemoMapper implements IDemoMapper{

    public Demo dtoToModel(final DemoDTO demoDTO){
        return new Demo(demoDTO.description);
    }

    public DemoDTO modelToDTO(final Demo demo){
        return new DemoDTO().id(demo.getId()).description(demo.getDescription());
    }
}
