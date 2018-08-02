package prof.mo.ed.hajj_health.Utils;

import org.json.JSONException;
import org.json.JSONObject;
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

    public static OptionsEntity parseProfileJson(String json) throws JSONException {
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
    }

    public static OptionsEntity parseCountriesJson(String json) throws JSONException {
        ProfileFormattedJson = new JSONObject(json);
        CountryID_STR = ProfileFormattedJson.getString("id");
        CountryName_STR = ProfileFormattedJson.getString("name");
        optionsEntity = new OptionsEntity(CountryID_STR, CountryName_STR,"","","");
        return optionsEntity;
    }
}