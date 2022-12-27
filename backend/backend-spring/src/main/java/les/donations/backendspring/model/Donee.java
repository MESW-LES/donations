package les.donations.backendspring.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "DONEES")
@Entity
public class Donee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = Company.PROPERTY_ID)
    private Company company;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "DONEES_CATEGORIES",
            joinColumns = { @JoinColumn(name = "DONEE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_CODE") }
    )
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donee")
    private List<DonationProcess> donationProcesses;

    protected Donee() {
        // for ORM
    }

    public Donee(Company company) throws IllegalArgumentException{
        this.company = company;
        this.categories =  new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Methods that adds a category to the company categories
     * @param category the category to add
     */
    public void addCategory(Category category){
        categories.add(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donee donee = (Donee) o;
        return id == null || id.equals(donee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Donee{" +
                "id=" + id +
                ", company=" + company +
                '}';
    }
}
