package prof.mo.ed.hajj_health.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Prof-Mohamed Atef on 8/1/2018.
 */
public class SessionManagement {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    public static final String PREFS_Logger = "LoggerFile";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FB_ID = "fb_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_Profile_Pic = "profile_picture";
    public static final String KEY_Location1 = "Location1";
    public static final String KEY_Location1_lat = "KEY_Location1_lat";
    public static final String KEY_Location1_long = "KEY_Location1_long";
    public static final String KEY_TemporaryLocation = "TemporaryLocation";
    public static final String KEY_AccountType = "AccountType";
    public static final String KEY_Mobile1 = "Mobile1";
    public static final String KEY_MarketPhone = "MarketPhone";
    public static final String KEY_MarketTitle = "MarketTitle";
    public static final String KEY_MarketAddress = "MarketAddress";
    public static final String KEY_MarketRating = "MarketRating";
    public static final String KEY_MerchandiseName = "MerchandiseName";

    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFS_Logger, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id,String name, String email,String AccountPhoto){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_FB_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_Profile_Pic, AccountPhoto);
        editor.commit();
    }

    public void UpdateProfileSession(String username, String mobile, String email){
        editor.putString(KEY_NAME, username);
        editor.putString(KEY_Mobile1, mobile);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_FB_ID, pref.getString(KEY_FB_ID, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_Profile_Pic, pref.getString(KEY_Profile_Pic, null));
        user.put(KEY_Location1, pref.getString(KEY_Location1, null));
        user.put(KEY_TemporaryLocation, pref.getString(KEY_TemporaryLocation, null));
        user.put(KEY_Mobile1, pref.getString(KEY_Mobile1, null));
        return user;
    }

//    public void checkLogin() {
//        if (!this.isLoggedIn()) {
//            Intent i = new Intent(_context, Login.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }
//    }

//    public void logoutUser(){
//        // Clearing all data from Shared Preferences
//        editor.clear();
//        editor.commit();
//
//        // After logout redirect user to Loing Activity
//        Intent i = new Intent(_context, androidlistviewactivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);
//    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void CreateLocatedAddress_num1(String addressSTR, String current_Latitude, String currentLongitude) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_Location1, addressSTR);
        editor.putString(KEY_Location1_lat,current_Latitude);
        editor.putString(KEY_Location1_long,currentLongitude);
        editor.commit();
    }

    public HashMap<String, String> getUserLocation_num1() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_Location1, pref.getString(KEY_Location1, null));
        user.put(KEY_Location1_lat,pref.getString(KEY_Location1_lat,null));
        user.put(KEY_Location1_long,pref.getString(KEY_Location1_long,null));
        return user;
    }

    public void CreateTemporaryAddress(String addressSTR) {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(KEY_Location1, addressSTR);
        editor.commit();
    }


    public void CreateAccountType(int user_type_id) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_AccountType, String.valueOf(user_type_id));
        editor.commit();
    }


    public HashMap<String, String> getUserAccountType() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_AccountType, pref.getString(KEY_AccountType, null));
        return user;
    }

    public void createnormalLoginSession(String loggedEmail, String loggedUSerName, String loggedAccountType) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, loggedEmail);
        editor.putString(KEY_NAME, loggedUSerName);
        editor.putString(KEY_AccountType, loggedAccountType);
        editor.commit();
    }

    public HashMap<String, String> getnormalLoginSession(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_AccountType, pref.getString(KEY_AccountType, null));
        return user;
    }

    public void CreatePhoneNum(String phoneNum) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_Mobile1, phoneNum);
        editor.commit();
    }


    public HashMap<String, String> getUserMob() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_Mobile1, pref.getString(KEY_Mobile1, null));
        return user;
    }

    public void CreateTemporaryLocation(String temporaryLocation, String Temporary_latitude, String Temporary_longitude) {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(KEY_TemporaryLocation,temporaryLocation);
        editor.putString(KEY_Location1_lat,Temporary_latitude);
        editor.putString(KEY_Location1_long,Temporary_longitude);
        editor.commit();
    }

    public HashMap<String, String> getTemporaryLocation(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_TemporaryLocation, pref.getString(KEY_TemporaryLocation, null));
        return user;
    }


    public void InsertMarketDetailsSession(String phone, String title, String address, String Rating, String MerchandiseName) {
        editor.putString(KEY_MarketPhone, phone);
        editor.putString(KEY_MarketTitle,title);
        editor.putString(KEY_MarketAddress,address);
        editor.putString(KEY_MarketRating,Rating);
        editor.putString(KEY_MerchandiseName,MerchandiseName);
        editor.commit();
    }

    public HashMap<String, String> getInsertedMarketDetailsSession(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_MarketAddress, pref.getString(KEY_MarketAddress, null));
        user.put(KEY_MarketPhone, pref.getString(KEY_MarketPhone, null));
        user.put(KEY_MarketTitle, pref.getString(KEY_MarketTitle, null));
        user.put(KEY_MarketRating, pref.getString(KEY_MarketRating, null));
        user.put(KEY_MerchandiseName, pref.getString(KEY_MerchandiseName, null));
        return user;
    }

    public void CreateLocatedAddress_num1(String locatedRegionAddress) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_Location1, locatedRegionAddress);
        editor.commit();
    }

    public HashMap<String,String> getLocatedAddressNum1() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_Location1, pref.getString(KEY_Location1, null));
        return user;
    }
}
