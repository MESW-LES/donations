package les.donations.backendspring.service.person;

import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.mapper.person.IPersonMapper;
import les.donations.backendspring.model.Person;
import les.donations.backendspring.repository.person.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public Person addPerson(PersonDTO personDTO) throws IllegalArgumentException {
        Person person = personMapper.dtoToModel(personDTO);
        try {
            personRepository.saveAndFlush(person);
            personDTO.id = person.getId();
        } catch (Exception ex) {
            throw new IllegalArgumentException("NIF or email already exists");
        }

        return person;
    }
}
