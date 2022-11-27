package les.donations.backendspring.model;

public enum Status {

    CREATED(1, "Created", true, true),
    REQUESTED(2, "Requested", false, true),
    ONGOING(3, "On Going", false, false),
    FINISHED(4, "Finished", false, false);

    private final int id;
    private final String designation;
    private final boolean canEditDonation;
    private final boolean canDeleteDonation;

    Status(int id, String designation, boolean canEditDonation, boolean canDeleteDonation){
        this.id = id;
        this.designation = designation;
        this.canEditDonation = canEditDonation;
        this.canDeleteDonation = canDeleteDonation;
    }

    public boolean isCanEditDonation() {
        return canEditDonation;
    }

    public boolean isCanDeleteDonation() {
        return canDeleteDonation;
    }

    public static Status getById(Integer id){
        return id == null ? null : Status.values()[id - 1];
    }
}
