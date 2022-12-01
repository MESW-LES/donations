package les.donations.backendspring.model;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "ONGOING_DATE")
    private Date ongoingDate;

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

    public Date getOngoingDate() {
        return ongoingDate;
    }

    public void setOngoingDate(Date ongoingDate) {
        this.ongoingDate = ongoingDate;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    /**
     * Method that changes a donation process status from CREATED to REQUESTED
     * @param donee the donee that requested the donation
     * @throws IllegalArgumentException if the donation can't change to REQUESTED status
     */
    public void toRequestedStatus(Donee donee) throws IllegalArgumentException{
        // if its not in created status then it cant change to request status
        if(!status.isCanEditDonation()){
            throw new IllegalArgumentException("The donation can't change to request status!");
        }
        // change to requested
        status = Status.REQUESTED;
        // sets the donee that requested the donation
        this.donee = donee;
    }

    public void decide(Boolean decision) throws IllegalArgumentException{
        // if its not in requested status then it cant change to ongoing status
        if(!(status.isCanDeleteDonation() && !status.isCanEditDonation())){
            throw new IllegalArgumentException("The donation can't change to ongoing status!");
        }
        // if the decision is true then changes to ongoing
        if(decision){
            status = Status.ONGOING;
            ongoingDate = new Date();
            return;
        }
        // if the decision is false then changes to created
        status = Status.CREATED;
        donee = null;
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
