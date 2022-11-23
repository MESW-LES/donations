package les.donations.backendspring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "DONATIONS")
@Entity
public class Donation {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "DONATIONS_CATEGORIES",
            joinColumns = { @JoinColumn(name = "DONATION_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_CODE") }
    )
    private List<Category> categories;

    @Column(name = "ACTIVE")
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donation", cascade = CascadeType.ALL)
    private List<DonationImage> donationImages;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "donation", cascade = CascadeType.ALL)
    private DonationProcess donationProcess;

    protected Donation() {
        // for ORM
    }

    public Donation(String title, String description) throws IllegalArgumentException{
        setTitle(title);
        setDescription(description);
        createdDate = new Date();
        active = true;
        categories = new ArrayList<>();
        donationImages = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException{
        // if the title is null or empty
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("The title can't be null or empty!");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException{
        // if the description is null or empty
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("The description can't be null or empty!");
        }
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<DonationImage> getDonationImages() {
        return donationImages;
    }

    public void setDonationImages(List<DonationImage> donationImages) {
        this.donationImages = donationImages;
    }

    public DonationProcess getDonationProcess() {
        return donationProcess;
    }

    public void setDonationProcess(DonationProcess donationProcess) {
        this.donationProcess = donationProcess;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void addDonationImage(DonationImage donationImage){
        donationImages.add(donationImage);
    }

    public void clearCategories(){
        categories.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donation donation = (Donation) o;
        return id.equals(donation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
