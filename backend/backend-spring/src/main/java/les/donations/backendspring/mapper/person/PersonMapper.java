package les.donations.backendspring.mapper.person;

import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.model.Person;
import liquibase.pro.packaged.P;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements  IPersonMapper{

    @Override
    public Person dtoToModel(final PersonDTO personDTO) throws IllegalArgumentException{
        return new Person(personDTO.id, personDTO.firstName, personDTO.lastName, personDTO.nif, personDTO.address,
                personDTO.email, personDTO.password);
    }

    @Override
    public PersonDTO modelToDTO(final Person person){
        return (new PersonDTO().id(person.getId()).firstName(person.getFirstName()).lastName(person.getLastName())
                .nif(person.getNif()).address(person.getAddress()).email(person.getEmail())).password(person.getPassword());
    }
}
