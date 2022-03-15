package africa.semicolon.sendAm.data.models;


import java.util.ArrayList;
import java.util.List;

public class Package {
    private int id;
    private User owner;
    private String trackingId;
    private PackageDescription description;
    private final List<Status> statusList = new ArrayList<>();


    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PackageDescription getDescription() {
        return description;
    }

    public void setDescription(PackageDescription description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", owner=" + owner +
                ", description=" + description +
                ", statusList=" + statusList +
                '}';
    }

    public List<Status> getStatusList() {
        return statusList;
    }
}
