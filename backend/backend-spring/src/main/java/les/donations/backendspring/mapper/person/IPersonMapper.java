package les.donations.backendspring.mapper.person;

import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.model.Person;

public interface IPersonMapper {
    /**
     * Converts a DTO to a modal
     * @param personDTO, the information about the person
     * @return the person
     * @throws IllegalArgumentException if the information is wrong
     */
    Person dtoToModel(final PersonDTO personDTO) throws IllegalArgumentException;
    PersonDTO modelToDTO(final Person person);
}
