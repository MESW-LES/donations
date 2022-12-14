package les.donations.backendspring.service.person;

import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.model.Person;

public interface IPersonService {
    /**
     * Creates a person
     * @param personDTO, containing information about the person
     * @return an information about the person if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, code is not unique or name is not unique
     */
    Person addPerson(PersonDTO personDTO) throws IllegalArgumentException;

    /**
     * Method that checks if exists a person with the provided email
     * @param email the person's email
     * @return true if exists, false if not
     */
    boolean existsPersonByEmail(String email);
}
