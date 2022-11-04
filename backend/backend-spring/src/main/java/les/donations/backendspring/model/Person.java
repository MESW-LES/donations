package les.donations.backendspring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Table(name = "PERSONS")
@Entity
public class Person {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    @GenericGenerator(name = "hibernate_sequences", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.INITIAL_PARAM, value = "100"),
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true")})
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "NIF")
    private String nif;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    public Person() {}

    public Person(Long id, String firstName, String lastName, String nif, String address, String email, String password) throws IllegalArgumentException {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setNif(nif);
        setAddress(address);
        setEmail(email);
        setPassword(password);
    }

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getNif() { return nif; }

    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public void setId(Long id) { this.id = id; }

    public void setFirstName(String firstName) throws IllegalArgumentException{
        if ((firstName == null) || (firstName.isEmpty())) throw new IllegalArgumentException("The first name can't be null or empty");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if ((lastName == null) || (lastName.isEmpty())) throw new IllegalArgumentException("The last name can't be null or empty");
        this.lastName = lastName;
    }

    public void setNif(String nif) throws IllegalArgumentException {
        if ((nif == null) || (nif.isEmpty())) throw new IllegalArgumentException("The nif can't be null or empty");
        Pattern pattern = Pattern.compile("[0-9]{9}");
        Matcher matcher = pattern.matcher(nif);
        boolean isMatch = matcher.matches();
        if (!isMatch) {
            throw new IllegalArgumentException("The NIF has illegal format. NIF must contain 9 digits");
        }
        this.nif = nif;
    }

    public void setAddress(String address) {
        if ((address == null) || (address.isEmpty())) throw new IllegalArgumentException("The address can't be null or empty");
        this.address = address;
    }

    public void setEmail(String email) {
        if ((email == null) || (email.isEmpty())) throw new IllegalArgumentException("The email can't be null or empty");
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        boolean isMatch = matcher.matches();
        if (!isMatch) {
            throw new IllegalArgumentException("The email has illegal format");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if ((password == null) || (password.isEmpty())) throw new IllegalArgumentException("The password can't be null or empty");
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastNumber='" + lastName + '\'' +
                '}';
    }
}
