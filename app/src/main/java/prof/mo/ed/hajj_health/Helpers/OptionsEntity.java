package prof.mo.ed.hajj_health.Helpers;

/**
 * Created by Prof-Mohamed Atef on 8/1/2018.
 */

public class OptionsEntity {

    String FormattedAddress;

    public OptionsEntity(String formattedAddress,String null1, String nulll){
        this.FormattedAddress=formattedAddress;
    }

    String Latitude;
    String Longitude;
    public OptionsEntity(String lat, String aLong, String s, String s1) {
        this.Latitude=lat;
        this.Longitude=aLong;
    }

    public String getFormattedAddress() {
        return FormattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        FormattedAddress = formattedAddress;
    }

    String DriverName;
    String DriverDistance;
    String DriverCarNum;

    public String getDriverDistance() {
        return DriverDistance;
    }

    public void setDriverDistance(String driverDistance) {
        DriverDistance = driverDistance;
    }

    public String getDriverCarNum() {
        return DriverCarNum;
    }

    public void setDriverCarNum(String driverCarNum) {
        DriverCarNum = driverCarNum;
    }

    String DriverLocation;
    String DriverPhone;
    String DriverAddress;
    String DriverProfilePic;
    String RequestID;
    String RequestDate;
    String RequestResponseState;


    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getDriverLocation() {
        return DriverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        DriverLocation = driverLocation;
    }

    public String getDriverPhone() {
        return DriverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        DriverPhone = driverPhone;
    }

    public String getDriverAddress() {
        return DriverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        DriverAddress = driverAddress;
    }

    public String getDriverProfilePic() {
        return DriverProfilePic;
    }

    public void setDriverProfilePic(String driverProfilePic) {
        DriverProfilePic = driverProfilePic;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public String getRequestResponseState() {
        return RequestResponseState;
    }

    public void setRequestResponseState(String requestResponseState) {
        RequestResponseState = requestResponseState;
    }

    public OptionsEntity(){

    }

    public OptionsEntity(String requestDate){
        this.RequestDate=requestDate;
    }

    public OptionsEntity(String requestDate, String requestResponseState){
        this.RequestDate=requestDate;
        this.RequestResponseState=requestResponseState;
    }
}

