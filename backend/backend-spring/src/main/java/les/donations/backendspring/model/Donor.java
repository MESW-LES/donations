package les.donations.backendspring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Table(name = "DONORS")
@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    @GenericGenerator(name = "hibernate_sequences", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.INITIAL_PARAM, value = "100"),
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true")})
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = Person.PROPERTY_ID)
    private Person person;

    public Donor() {}

    public Donor(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donor donor = (Donor) o;
        return id.equals(donor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

