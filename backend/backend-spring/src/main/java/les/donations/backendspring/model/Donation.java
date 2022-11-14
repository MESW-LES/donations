package les.donations.backendspring.model;

import liquibase.pro.packaged.L;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "DONATIONS")
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    @GenericGenerator(name = "hibernate_sequences", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.INITIAL_PARAM, value = "100"),
            @org.hibernate.annotations.Parameter(name = org.hibernate.id.enhanced.TableGenerator.CONFIG_PREFER_SEGMENT_PER_ENTITY, value = "true")})
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DONOR_ID")
    private Long donorId;

    public Donation() {}
    public Donation(String name, Long donorId) {
        this.name = name;
        this.donorId = donorId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }
}
