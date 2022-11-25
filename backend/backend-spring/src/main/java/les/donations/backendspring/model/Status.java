package les.donations.backendspring.model;

public enum Status {

    CREATED(1, "Created", true),
    PENDING(2, "Pending", false),
    ONGOING(3, "On Going", false),
    FINISHED(4, "Finished", false);

    private final int id;
    private final String designation;
    private final boolean canEditDonation;

    Status(int id, String designation, boolean canEditDonation){
        this.id = id;
        this.designation = designation;
        this.canEditDonation = canEditDonation;
    }

    public boolean isCanEditDonation() {
        return canEditDonation;
    }
}
