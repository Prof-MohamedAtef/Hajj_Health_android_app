package prof.mo.ed.hajj_health.Helpers;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 8/1/2018.
 */

public class OptionsEntity implements Serializable {

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

    String FirstName, LastName, FamilyName, FullName, Email, NationalID, CountryName, CountryID;

    public OptionsEntity(String countryID_str, String countryName_str, String s, String s1, String s2) {
        this.CountryID=countryID_str;
        this.CountryName=countryName_str;
    }

    public String getProfileID() {
        return ProfileID;
    }

    public void setProfileID(String profileID) {
        ProfileID = profileID;
    }

    String ProfileID, PatientName, Nationality, Sex, DOB, Job, VisaType, PassportNum, Diabetes, Heart, Length, Weight,
            HealthStatus, Blood, haveTat3eem, DoctorName, DiagnoseDate;
    public OptionsEntity(String id_s, String name_s, String nationality_s, String sex_s, String dob_s, String job_s,
                         String visa_type_s,String passport_num_s, String diabetes_s, String heart_s, String length_s,
                         String weight_s, String healthStatus_s, String blood_s, String haveTat3em_s, String doctor_s,
                         String diagnose_date_s) {
        this.ProfileID=id_s;
        this.PatientName=name_s;
        this.Nationality=nationality_s;
        this.Sex=sex_s;
        this.DOB=dob_s;
        this.Job=job_s;
        this.VisaType=visa_type_s;
        this.PassportNum=passport_num_s;
        this.Diabetes=diabetes_s;
        this.Heart=heart_s;
        this.Length=length_s;
        this.Weight=weight_s;
        this.HealthStatus=healthStatus_s;
        this.Blood=blood_s;
        this.haveTat3eem=haveTat3em_s;
        this.DoctorName=doctor_s;
        this.DiagnoseDate=diagnose_date_s;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getVisaType() {
        return VisaType;
    }

    public void setVisaType(String visaType) {
        VisaType = visaType;
    }

    public String getDiabetes() {
        return Diabetes;
    }

    public void setDiabetes(String diabetes) {
        Diabetes = diabetes;
    }

    public String getHeart() {
        return Heart;
    }

    public void setHeart(String heart) {
        Heart = heart;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHealthStatus() {
        return HealthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        HealthStatus = healthStatus;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getHaveTat3eem() {
        return haveTat3eem;
    }

    public void setHaveTat3eem(String haveTat3eem) {
        this.haveTat3eem = haveTat3eem;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDiagnoseDate() {
        return DiagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        DiagnoseDate = diagnoseDate;
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

