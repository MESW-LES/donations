package les.donations.backendspring.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "DEMOS")
@Entity
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    @GenericGenerator(name = "hibernate_sequences", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @Parameter(name = org.hibernate.id.enhanced.TableGenerator.INITIAL_PARAM, value = "100"),
            @Parameter(name = org.hibernate.id.enhanced.TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true")})
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    public Demo() {
        // for ORM
    }

    public Demo(String description) {
        setDescription(description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return id.equals(demo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
