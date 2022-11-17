package les.donations.backendspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "CATEGORIES")
@Entity
public class Category implements Serializable {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private boolean active;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Donee> donees;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Donation> donations;

    protected Category() {
        // for ORM
    }

    public Category(String code, String name, String description) throws IllegalArgumentException {
        setCode(code);
        setName(name);
        setDescription(description);
        this.active = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void setCode(String code) throws IllegalArgumentException{
        if ((code == null) || (code.isEmpty())) throw new IllegalArgumentException("The code can't be null or empty");
        this.code = code;
    }

    public void setName(String name) throws IllegalArgumentException{
        if ((name == null) || (name.isEmpty())) throw new IllegalArgumentException("The name can't be null or empty");
        this.name = name;
    }

    public void setDescription(String description) throws IllegalArgumentException{
        if ((description == null) || (description.isEmpty())) throw new IllegalArgumentException("The description can't be null or empty");
        this.description = description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Donee> getDonees() {
        return donees;
    }

    public void setDonees(List<Donee> donees) {
        this.donees = donees;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return code.equals(category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
