package prof.mo.ed.hajj_health.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import prof.mo.ed.hajj_health.Helpers.OptionsEntity;

public class JsonUtils {

    private static OptionsEntity optionsEntity;
    private static JSONObject ProfileFormattedJson;
    private static JSONObject Country;
    private static String FirstName_STR;
    private static String LastName_STR;
    private static String FamilyName_STR;
    private static String FullName_STR;
    private static String Email_STR;
    private static String PassportNum_STR;
    private static String CountryID_STR;
    private static String CountryName_STR;
    private static String NationalID_STR;
    private static ArrayList CountriesArray;

    /*public static OptionsEntity parseProfileJson(String json) throws JSONException {
        ProfileFormattedJson = new JSONObject(json);
        FirstName_STR = ProfileFormattedJson.getString("first_name");
        LastName_STR = ProfileFormattedJson.getString("last_name");
        FamilyName_STR = ProfileFormattedJson.getString("family_name");
        FullName_STR = ProfileFormattedJson.getString("full_name");
        Email_STR = ProfileFormattedJson.getString("email");
        PassportNum_STR = ProfileFormattedJson.getString("passport_no");
        NationalID_STR = ProfileFormattedJson.getString("national_id");
        CountryID_STR = ProfileFormattedJson.getString("country_id");
        CountryName_STR= ProfileFormattedJson.getString("country_name");

        optionsEntity = new OptionsEntity(FirstName_STR,LastName_STR,FamilyName_STR,FullName_STR,Email_STR,PassportNum_STR,NationalID_STR,CountryName_STR,CountryID_STR);
        return optionsEntity;
    }*/

    public static ArrayList<OptionsEntity> parseCountriesJson(String json) throws JSONException {
        CountriesArray=new ArrayList();
        ProfileFormattedJson = new JSONObject(json);
        CountryID_STR = ProfileFormattedJson.getString("id");
        CountryName_STR = ProfileFormattedJson.getString("name");
        optionsEntity = new OptionsEntity(CountryID_STR, CountryName_STR,"","","");
        CountriesArray.add(optionsEntity);
        return CountriesArray;
    }

    public static OptionsEntity parseProfileJson(String json) throws JSONException {
        String main_List = "Result";
        String ID_S, Name_S, Nationality_S, Sex_S, DOB_S, Job_S, Visa_Type_S, Passport_num_S, Diabetes_S, Heart_S, Length_S, Weight_S,
                HealthStatus_S, blood_S,HaveTat3em_S,Doctor_S,DiagnoseDate_S;
        JSONArray ProfileArray;
        JSONObject Profile= new JSONObject(json);
        JSONObject oneProfile;
        ProfileArray= Profile.getJSONArray(main_List);
        for (int i = 0; i < ProfileArray.length(); i++) {
            // Get the JSON object representing a movie per each loop
            oneProfile = ProfileArray.getJSONObject(i);
            ID_S = oneProfile.getString("id");
            Name_S = oneProfile.getString("Name");
            Nationality_S = oneProfile.getString("Nationality");
            Sex_S = oneProfile.getString("Sex");
            DOB_S = oneProfile.getString("DOB");
            Job_S = oneProfile.getString("Job");
            Visa_Type_S = oneProfile.getString("Visa_Type");
            Passport_num_S = oneProfile.getString("Passport_number");
            Diabetes_S = oneProfile.getString("Diabetes");
            Heart_S = oneProfile.getString("heart_disease");
            Length_S = oneProfile.getString("Length");
            Weight_S = oneProfile.getString("weight");
            HealthStatus_S = oneProfile.getString("Health_status");
            blood_S = oneProfile.getString("blood_disease");
            HaveTat3em_S = oneProfile.getString("Have_tat3eem");
            Doctor_S = oneProfile.getString("DocName");
            DiagnoseDate_S = oneProfile.getString("Diagnose_Date");

            optionsEntity = new OptionsEntity(ID_S, Name_S, Nationality_S, Sex_S, DOB_S, Job_S, Visa_Type_S, Passport_num_S, Diabetes_S, Heart_S, Length_S, Weight_S,
                    HealthStatus_S, blood_S, HaveTat3em_S, Doctor_S, DiagnoseDate_S);

        }
            return optionsEntity;
    }
}