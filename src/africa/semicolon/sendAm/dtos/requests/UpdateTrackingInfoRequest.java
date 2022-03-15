package africa.semicolon.sendAm.dtos.requests;

public class UpdateTrackingInfoRequest {

    private String trackingNumber;
    private String trackingInfo;

    public void setTrackingInfo(String trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    public String getTrackingInfo(){
        return trackingInfo;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber(){
        return trackingNumber;
    }
}
