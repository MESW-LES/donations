package les.donations.backendspring.service.demo;

import les.donations.backendspring.dto.DemoDTO;
import les.donations.backendspring.mapper.demo.IDemoMapper;
import les.donations.backendspring.model.Demo;
import les.donations.backendspring.repository.demo.IDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService implements IDemoService {

    @Autowired
    private IDemoRepository demoRepository;
    @Autowired
    private IDemoMapper demoMapper;

    @Override
    public DemoDTO getDemo() {
        final Demo demo = demoRepository.getDemoById(1L);
        return demoMapper.modelToDTO(demo);
    }
}
