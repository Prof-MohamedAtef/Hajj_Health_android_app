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

    String FirstName, LastName, FamilyName, FullName, Email, PassportNum, NationalID, CountryName, CountryID;

    public OptionsEntity(String countryID_str, String countryName_str, String s, String s1, String s2) {
        this.CountryID=countryID_str;
        this.CountryName=countryName_str;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassportNum() {
        return PassportNum;
    }

    public void setPassportNum(String passportNum) {
        PassportNum = passportNum;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryID() {
        return CountryID;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public OptionsEntity(String firstName_str, String lastName_str, String familyName_str, String fullName_str, String email_str, String passportNum_str, String nationalID_str, String countryName_str, String countryID_str) {
        this.FirstName=firstName_str;
        this.LastName=lastName_str;
        this.FamilyName=familyName_str;
        this.FullName=fullName_str;
        this.Email=email_str;
        this.PassportNum=passportNum_str;
        this.NationalID=nationalID_str;
        this.CountryName=countryName_str;
        this.CountryID=countryID_str;
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

