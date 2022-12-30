package les.donations.backendspring.service.geographicArea;

import les.donations.backendspring.dto.GeographicAreaDTO;
import les.donations.backendspring.dto.ModelDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.geographicArea.IGeographicAreaMapper;
import les.donations.backendspring.model.GeographicArea;
import les.donations.backendspring.repository.geographicArea.GeographicAreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeographicAreaService implements IGeographicAreaService {
    @Autowired
    private GeographicAreaDao geographicAreaDao;
    @Autowired
    private IGeographicAreaMapper geographicAreaMapper;

    @Override
    public GeographicAreaDTO registerGeographicArea(GeographicAreaDTO geographicAreaDTO)
            throws IllegalArgumentException {
        GeographicArea geographicArea = geographicAreaMapper.dtoToModel(geographicAreaDTO);
        try {
            geographicAreaDao.saveAndFlush(geographicArea);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Geographic area saving is failed");
        }

        return geographicAreaDTO;
    }

    @Override
    public PaginationDTO getGeographicAreas() {
        // gets the categories
        List<GeographicArea> areas = geographicAreaDao.findAll();
        // converts them into DTO
        List<ModelDTO> areasDTO = areas.stream().map(area -> geographicAreaMapper.modelToDTO(area)).
                collect(Collectors.toList());
        return new PaginationDTO().results(areasDTO).countResults(areasDTO.size());
    }

    @Override
    public GeographicAreaDTO deleteGeographicArea(Long geographicAreaId) throws NotFoundEntityException, IllegalArgumentException {
        Optional<GeographicArea> areaOptional = geographicAreaDao.findById(geographicAreaId);
        if(!areaOptional.isPresent()){
            throw new NotFoundEntityException("The geographic area does not exist!");
        }
        try {
            geographicAreaDao.deleteById(geographicAreaId);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Geographic area deleting is failed");
        }
        return geographicAreaMapper.modelToDTO(areaOptional.get());
    }

    @Override
    public GeographicArea getGeographicAreaModel(Long id) throws NotFoundEntityException {
        Optional geographicArea = geographicAreaDao.findById(id);
        if (!geographicArea.isPresent()){
            throw new NotFoundEntityException("The geographic area with the id " + id + " does not exist!");
        }
        return (GeographicArea) geographicArea.get();
    }
}
