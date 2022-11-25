package les.donations.backendspring.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "DONATION_IMAGES")
@Entity
public class DonationImage {

    protected static final String PROPERTY_ID = "ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "DIRECTORY_PATH")
    private String directoryPath;

    @Column(name = "FILE_EXTENSION")
    private String fileExtension;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_ID", referencedColumnName = Donation.PROPERTY_ID)
    private Donation donation;

    @Column(name = "ACTIVE")
    private boolean active;

    protected DonationImage(){
        // for ORM
    }

    public DonationImage(String originalName, String fileName, String directoryPath, String fileExtension, Long fileSize){
        this.originalName = originalName;
        this.fileName = fileName;
        this.directoryPath = directoryPath;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationImage that = (DonationImage) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DonationImage{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", directoryPath='" + directoryPath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
