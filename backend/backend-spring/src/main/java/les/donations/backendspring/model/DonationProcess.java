package les.donations.backendspring.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "DONATIONS_PROCESS")
@Entity
public class DonationProcess {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_ID", referencedColumnName = Donation.PROPERTY_ID)
    private Donation donation;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONEE_ID")
    private Donee donee;

    protected DonationProcess(){
        // for ORM
    }

    public DonationProcess(Donation donation){
        this.donation = donation;
        status = Status.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationProcess that = (DonationProcess) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DonationProcess{" +
                "id=" + id +
                ", donation=" + donation +
                ", status=" + status +
                ", donee=" + donee +
                '}';
    }
}
