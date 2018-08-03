package prof.mo.ed.hajj_health.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;
import prof.mo.ed.hajj_health.Helpers.Config;
import prof.mo.ed.hajj_health.Helpers.Constants;
import prof.mo.ed.hajj_health.Helpers.OptionsEntity;
import prof.mo.ed.hajj_health.Helpers.SessionManagement;
import prof.mo.ed.hajj_health.Network.ConnectivityReceiver;
import prof.mo.ed.hajj_health.R;

/**
 * Created by Prof-Mohamed Atef on 8/1/2018.
 */

public class MapFragment extends android.support.v4.app.Fragment implements ConnectivityReceiver.ConnectivityReceiverListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener{

    private final String LOG_TAG = MapFragment.class.getSimpleName();
    View rootview;
    String LoggedEmail, LoggedProfilePic, LoggedMobile1, LoggedUserName, LoggedLocation, TemporaryLocation, LoggedFBID;
    SessionManagement sessionManagement;
    HashMap<String, String> user;
    FloatingActionButton buttonMakeRequest;
    TextView urin_txt, txt_username;
    CircleImageView ProfilePic;
    LinearLayout nearbyLinear;

    Context mContext;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManagement= new SessionManagement(getActivity());
        mContext= getActivity();
        handler = new Handler();
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.pleasewait));
        progressDialog.setCancelable(true);
        try {
            checkConnection();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetConnected==true){
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    // The next two lines tell the new client that “this” current class will handle connection stuff
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    //fourth line adds the LocationServices API endpoint from GooglePlayServices
                    .addApi(LocationServices.API)
                    .build();
            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        }/*else if (isInternetConnected==false) {
            DialogueCheckNetworkConnection();
        }
        user =sessionManagement.getUserDetails();
        if (user!=null){
            fb_LoggedEmail = user.get(SessionManagement.KEY_EMAIL);
            fb_Logged_FBID=user.get(SessionManagement.KEY_FB_ID);
            fb_LoggedUSerName=user.get(SessionManagement.KEY_NAME);
            fb_LoggedProfilePic_Url=user.get(SessionManagement.KEY_Profile_Pic);
        }*/
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.home_fragment, container, false);
        try {
            checkConnection();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetConnected == true) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    // The next two lines tell the new client that “this” current class will handle connection stuff
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    //fourth line adds the LocationServices API endpoint from GooglePlayServices
                    .addApi(LocationServices.API)
                    .build();
            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        }
        sessionManagement = new SessionManagement(getActivity());
        buttonMakeRequest = (FloatingActionButton) rootview.findViewById(R.id.makeRequest);
        urin_txt = (TextView) rootview.findViewById(R.id.urin_txt);
        txt_username = (TextView) rootview.findViewById(R.id.txt_username);
        ProfilePic = (CircleImageView) rootview.findViewById(R.id.ProfilePic);
        nearbyLinear = (LinearLayout) rootview.findViewById(R.id.nearby);
        user = sessionManagement.getUserDetails();
        if (user != null) {
            LoggedFBID=user.get(SessionManagement.KEY_FB_ID);
            LoggedEmail = user.get(SessionManagement.KEY_EMAIL);
            LoggedUserName = user.get(SessionManagement.KEY_NAME);
            LoggedProfilePic = user.get(SessionManagement.KEY_Profile_Pic);
            LoggedMobile1 = user.get(SessionManagement.KEY_Mobile1);
            txt_username.setText(LoggedUserName);
            Picasso.with(getActivity()).load(LoggedProfilePic).error(R.drawable.water1).into(ProfilePic);
            if (LoggedMobile1==null){
//                CheckMobileRegisteration(LoggedFBID,LoggedUserName, LoggedEmail, LoggedProfilePic);
            }
        }

        ActionBar actionBar = new ActionBar(getActivity());
        actionBar.setBackgroundColor(getResources().getColor(R.color.icons));
//        try {
//            DB = new DBHelper(getApplicationContext());
//        } catch (Exception e) {
//            Log.e(LOG_TAG, "Didn't Create Database", e);
//        }
        buttonMakeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkConnection();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isInternetConnected == true) {
                    DialougRUSureMakeRequest();
                } else if (isInternetConnected == false) {
                    DialougMakeRequestConnectionLost();
                }

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return rootview;
        }

        LocationManager manager;

    public void statusCheck() {
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void DialougMakeRequestConnectionLost() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.approve_offline));
        builder.setMessage(getString(R.string.approve_offlineSent));
        builder.setPositiveButton(getString(R.string.Done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    AlertDialog Dialogue;
    private void DialougRUSureMakeRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.makeOnlinerequest));
        builder.setMessage(getString(R.string.request_txt));
        builder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogue.dismiss();
                Toast.makeText(getActivity(), "Your request has been submitted successfully!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogue.dismiss();
            }
        });
        Dialogue = builder.create();
        Dialogue.show();
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.location_services_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        buildAlertMessageNoGpsWarning();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void buildAlertMessageNoGpsWarning() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.location_services_message_warning))
                .setNegativeButton(getString(R.string.refuse), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buildAlertMessageNoGpsWarning();
                    }
                })
                .setPositiveButton(getString(R.string.agree), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void NetworkProblemsDialouge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.warning));
        builder.setMessage(getString(R.string.networkproblem));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private void NetworkAbortedDialouge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.opps));
        builder.setMessage(getString(R.string.networkproblem));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    String main_locationList = "results", FormattedAddress = "formatted_address";
    String FormattedAddress_STR;
    ArrayList<OptionsEntity> Address_list = new ArrayList<OptionsEntity>();
    public JSONObject AddressFormattedJson;
    public JSONArray AddressFormattedJsonAray;
    public JSONObject oneFormattedData;
    private ProgressDialog progressDialog;

    private void startFetchingAddress() {
        try {
            FetchLoggerLocation fetchTrailers = new FetchLoggerLocation();
            String URL = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + currentLatitude + "%2C" + currentLongitude + "&sensor=true";
            fetchTrailers.execute(URL);
            Config.Locat_Latitiude = currentLatitude;
            Config.Locat_Longitude = currentLongitude;
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute Desires");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            statusCheck();
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            Toast.makeText(getActivity(), currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
            if (LoggedLocation == null) {
                startFetchingAddress();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isInternetConnected){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    Location locationA;
    Location locationB;
    @Override
    public boolean onMarkerClick(Marker marker) {
        float[] results= new float[10];
        LatLng x= marker.getPosition();
        currentLatitude=x.latitude;
        currentLongitude=x.longitude;
//        results[0]=0;
//        Location.distanceBetween(Config.Locat_Latitiude, Config.Locat_Longitude,
//                currentLatitude, currentLongitude,results);
//        Toast.makeText(getActivity(), String.valueOf(results[0]), Toast.LENGTH_LONG).show();
//        MarkerOptions markerOptions=new MarkerOptions();
//        markerOptions.snippet("distance = "+results[0]);
//        mMap.addMarker(markerOptions);
        locationA=new Location("");
        locationA.setLatitude(Config.Locat_Latitiude);
        locationA.setLongitude(Config.Locat_Longitude);
        locationB=new Location("");
        locationB.setLatitude(x.latitude);
        locationB.setLongitude(x.longitude);
        double distance=0;
        distance=locationA.distanceTo(locationB)/1000;
        Toast.makeText(getActivity(), "distance = "+String.valueOf(distance), Toast.LENGTH_LONG).show();
        //https://www.youtube.com/watch?v=9V0p_2lVoJo
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
    }

    ArrayList <OptionsEntity>Latitudes;
    ArrayList <OptionsEntity>Longitudes;
    GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(31.1358861, 33.7873194))
                .zoom(25)
                .bearing(0)
                .tilt(45)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex));
        googleMap.setOnMarkerClickListener(this);
        Latitudes=new ArrayList<OptionsEntity>();
        Longitudes=new ArrayList<OptionsEntity>();
        for (int i=0;i< Constants.Latitude.length;i++){
            double Lat =Constants.Latitude[i];
            double Long=Constants.Longitude[i];
            String driver= Constants.Drivers[i];
            String Snippet=Constants.Snippet[i];
//            OptionsEntity optionsEntity=new OptionsEntity(Lat,Long,",","");
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Lat, Long))
                    .title(driver)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_car))
                    .snippet(Snippet));
        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        try {
            checkConnection();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class FetchLoggerLocation extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {
        private final String LOG_TAG = FetchLoggerLocation.class.getSimpleName();
        private ArrayList<OptionsEntity> getUsersDesiresFromJson(String UsersDesires)
                throws JSONException {
            AddressFormattedJson = new JSONObject(UsersDesires);
            AddressFormattedJsonAray = AddressFormattedJson.getJSONArray(main_locationList);
            Address_list.clear();
            while (Address_list.size()==0){
                oneFormattedData = AddressFormattedJsonAray.getJSONObject(0);
                FormattedAddress_STR= oneFormattedData.getString(FormattedAddress);
                OptionsEntity entity = new OptionsEntity(FormattedAddress_STR,"","");
                Address_list.add(entity);

//                OptionsEntity entity = new OptionsEntity(FormattedAddress_STR);
//                entity.setFormattedAddress(FormattedAddress_STR);
//                Address_list.add(entity);
            }
            return Address_list;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressDialog = ProgressDialog.show(getActivity(), getString(R.string.pleasewait), "Error", true);
            if (Address_list.size()>0){
                progressDialog.cancel();
            }
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {
            String UsersDesires_JsonSTR = null;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            if (params.length == 0) {
                return null;
            }
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    UsersDesires_JsonSTR = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                UsersDesires_JsonSTR = buffer.toString();
                Log.v(LOG_TAG, "Formatted Address String: " + UsersDesires_JsonSTR);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error here Exactly ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getUsersDesiresFromJson(UsersDesires_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Users Desires from getJsonData method", e);
                e.printStackTrace();
            }
            return null;
        }

        OptionsEntity optionsEntity;
        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result!=null&& getActivity()!=null) {
                Address_list=result;
//                optionsEntity=new OptionsEntity();
//                TemporaryLocation=optionsEntity.getFormattedAddress();
                TemporaryLocation=Address_list.get(0).getFormattedAddress();
                urin_txt.setText("");
                urin_txt.append(getString(R.string.penddingconnection));
                urin_txt.append(TemporaryLocation);
                sessionManagement.CreateTemporaryLocation(TemporaryLocation,String.valueOf(currentLatitude),String.valueOf(currentLongitude));
            }
        }
    }

    public class ActionBar extends FloatingActionButton{

        public ActionBar(Context context) {
            super(context);
        }

        @Override
        public void setBackgroundColor(int color) {
            super.setBackgroundColor(color);
//            setBackgroundColor(getResources().getColor(R.color.white));
//            setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    boolean isInternetConnected;
    private void checkConnection() throws JSONException {
        isInternetConnected = ConnectivityReceiver.isConnected(getActivity());
//        showSnack(isInternetConnected);
    }
}