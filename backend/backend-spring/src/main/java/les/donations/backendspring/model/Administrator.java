package les.donations.backendspring.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "ADMINISTRATORS")
@Entity
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE")
    private boolean active;

    protected Administrator(){
        // for ORM
    }

    public Administrator(String firstName, String lastName, String email) throws IllegalArgumentException{
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IllegalArgumentException{
        if(firstName == null || firstName.isEmpty()){
            throw new IllegalArgumentException("The first name can't be null or empty!");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException{
        if(lastName == null || lastName.isEmpty()){
            throw new IllegalArgumentException("The last name can't be null or empty!");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException{
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("The email can't be null or empty!");
        }
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
