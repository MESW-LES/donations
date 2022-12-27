package les.donations.backendspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "GEOGRAPHIC_AREAS")
@Entity
public class GeographicArea implements Serializable {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PARISH")
    private String parish;

    @Column(name = "MUNICIPALITY")
    private String municipality;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "GEOGRAPHIC_AREAS_DONEES",
            joinColumns = { @JoinColumn(name = "GEOGRAPHIC_AREA_ID") },
            inverseJoinColumns = { @JoinColumn(name = "DONEE_ID") }
    )
    private List<Donee> donees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "geographicArea", cascade = CascadeType.ALL)
    private List<Address> addresses;

    protected GeographicArea() {
        // for ORM
    }

    public GeographicArea(String city, String parish, String municipality) {
        setCity(city);
        setParish(parish);
        setMunicipality(municipality);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws IllegalArgumentException {
        if(city == null || city.isEmpty()){
            throw new IllegalArgumentException("The city can't be null or empty!");
        }
        this.city = city;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) throws IllegalArgumentException {
        if(parish == null || parish.isEmpty()){
            throw new IllegalArgumentException("The parish can't be null or empty!");
        }
        this.parish = parish;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) throws IllegalArgumentException {
        if(municipality == null || municipality.isEmpty()){
            throw new IllegalArgumentException("The municipality can't be null or empty!");
        }
        this.municipality = municipality;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addDonee(Donee donee){
        donees.add(donee);
    }

    public void clearDonees(){
        donees.clear();
    }

    public List<Donee> getDonees() {
        return donees;
    }

    public void setDonees(List<Donee> donees) {
        this.donees = donees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeographicArea area = (GeographicArea) o;
        return id.equals(area.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GeographicArea{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", parish='" + parish + '\'' +
                ", municipality='" + municipality + '\'' +
                '}';
    }
}
