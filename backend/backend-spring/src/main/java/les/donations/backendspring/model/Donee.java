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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    @GenericGenerator(name = "hibernate_sequences", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @Parameter(name = org.hibernate.id.enhanced.TableGenerator.INITIAL_PARAM, value = "100"),
            @Parameter(name = org.hibernate.id.enhanced.TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true")})
    @Column(name = "ID")
    private Long id;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = Company.PROPERTY_ID)
    private Company company;

    @Column(name = "ACTIVE")
    private boolean active;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "COMPANIES_CATEGORIES",
            joinColumns = { @JoinColumn(name = "COMPANY_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID") }
    )
    private List<Category> categories;

    protected Donee() {
        // for ORM
    }

    public Donee(String password, Company company) throws IllegalArgumentException{
        setPassword(password);
        this.company = company;
        this.categories =  new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException{
        // checks if the password is null or empty
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("The password can't be null or empty!");
        }
        this.password = password;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
