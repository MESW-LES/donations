package les.donations.backendspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "ADDRESSES")
@Entity
public class Address implements Serializable {
    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSE_NUMBER")
    private Long houseNumber;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<Donor> donors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEOGRAPHIC_AREA_ID")
    private GeographicArea geographicArea;

    protected Address() {
        // for ORM
    }

    public Address(String street, Long houseNumber, String postalCode) throws IllegalArgumentException{
        setStreet(street);
        setHouseNumber(houseNumber);
        setPostalCode(postalCode);
        donors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws IllegalArgumentException {
        if(street == null || street.isEmpty()){
            throw new IllegalArgumentException("The street can't be null or empty!");
        }
        this.street = street;
    }

    public Long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Long houseNumber) {
        if(houseNumber < 1) {
            throw new IllegalArgumentException("The house number can't be less then 1!");
        }
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if(postalCode == null || postalCode.isEmpty()){
            throw new IllegalArgumentException("The postal code can't be null or empty!");
        }
        this.postalCode = postalCode;
    }

    public List<Donor> getDonors() {
        return donors;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }

    public void addDonor(Donor donor) {
        donors.add(donor);
    }

    public GeographicArea getGeographicArea() {
        return geographicArea;
    }

    public void setGeographicArea(GeographicArea geographicArea) {
        this.geographicArea = geographicArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", house number='" + houseNumber + '\'' +
                ", postal code=" + postalCode +
                '}';
    }
}
