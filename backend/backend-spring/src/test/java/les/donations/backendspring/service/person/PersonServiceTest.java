package les.donations.backendspring.service.person;

import les.donations.backendspring.dto.PersonDTO;
import les.donations.backendspring.mapper.person.IPersonMapper;
import les.donations.backendspring.model.Person;
import les.donations.backendspring.repository.person.IPersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PersonServiceTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IPersonMapper personMapper;
    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPersonWithWrongPropertyInformationInformation() {
        PersonDTO personDTO = new PersonDTO().id(Long.valueOf("1"));
        when(personMapper.dtoToModel(personDTO)).thenThrow(new IllegalArgumentException("The first name can't be null or empty"));

        try{
            personService.addPerson(personDTO);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("The first name can't be null or empty", e.getMessage());
        }
    }

    @Test
    void addPersonWithExistingNIF() {
        PersonDTO personDTO = new PersonDTO().id(Long.valueOf("1"));
        when(personMapper.dtoToModel(personDTO)).thenReturn(new Person());
        when(personRepository.saveAndFlush(new Person())).thenThrow(new IllegalArgumentException("NIF or email already exists"));

        try{
            personService.addPerson(personDTO);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("NIF or email already exists", e.getMessage());
        }
    }

    @Test
    void addPersonWithValidInformation() {
        PersonDTO personDTO = new PersonDTO().id(Long.valueOf("3"));
        Person person = new Person(Long.valueOf("1"), "Anna",
                "Smith", "023456789", "address", "person.test@gmail.com", "password");
        when(personMapper.dtoToModel(personDTO)).thenReturn(person);
        when(personRepository.saveAndFlush(person)).thenReturn(person);

        Person returned = personService.addPerson(personDTO);
        assertEquals(person, returned);
    }

}