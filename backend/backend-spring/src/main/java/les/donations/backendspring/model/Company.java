package les.donations.backendspring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name = "COMPANIES")
@Entity
public class Company {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TAX_NUMBER")
    private String taxNumber;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "REGISTER_DATE")
    private Date registerDate;

    @Column(name = "REMOVE_DATE")
    private Date removeDate;

    @Transient
    private String email;

    protected Company() {
        // for ORM
    }

    public Company(String name, String description, String taxNumber, Long phone) throws IllegalArgumentException{
        setName(name);
        setDescription(description);
        setTaxNumber(taxNumber);
        setPhone(phone);
        this.active = true;
        this.registerDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        // checks if the name is null or empty
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("The name can't be null or empty!");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        // checks if the description is null or empty
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("The description can't be null or empty!");
        }
        this.description = description;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        // checks if the tax number is null or empty
        if(taxNumber == null || taxNumber.isEmpty()){
            throw new IllegalArgumentException("The tax number can't be null or empty!");
        }
        this.taxNumber = taxNumber;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        // checks if the number is null or does not respect the nine character length
        if(phone == null || phone.toString().length() != 9){
            throw new IllegalArgumentException("The phone can't be null or has a wrong format!");
        }
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id.equals(company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                '}';
    }
}
