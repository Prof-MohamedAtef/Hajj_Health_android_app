package prof.mo.ed.hajj_health.Activities;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import prof.mo.ed.hajj_health.Fragments.ProfileFragment;
import prof.mo.ed.hajj_health.Helpers.Config;
import prof.mo.ed.hajj_health.Helpers.CustomSpinnerAdapter;
import prof.mo.ed.hajj_health.Helpers.OptionsEntity;
import prof.mo.ed.hajj_health.R;
import prof.mo.ed.hajj_health.Utils.JsonUtils;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
//        CountriesArray= new ArrayList<>();
//        getCountryAndCountryID();
    }

    Spinner SpinnerPostTage;
    private String Str_PostType;
    Button SearchPAtientButton;
    TextView NAtionalID_txt;
    ArrayList<OptionsEntity> CountriesArray;
    String NAtionalID_STR;
    private ProgressDialog tProgressDialog;

    private void showProgress(){
        new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                tProgressDialog.show();
            }
        }.sendEmptyMessage(1);
    }

    private void hideProgress(){
        new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                tProgressDialog.dismiss();
            }
        }.sendEmptyMessage(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SearchPAtientButton=(Button)findViewById(R.id.search_patient);
        NAtionalID_txt=(TextView)findViewById(R.id.NAtionalID);

        tProgressDialog = new ProgressDialog(this);
        tProgressDialog.setMessage(getString(R.string.loading));
        tProgressDialog.setIndeterminate(true);
        SearchPAtientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAtionalID_STR=NAtionalID_txt.getText().toString();
                makeRequest(NAtionalID_STR);
            }
        });
//        SpinnerPostTage=(Spinner)findViewById(R.id.PostTage_spinner);
//        SpinnerPostTage.setVisibility(View.GONE);
//        SpinnerPostTage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Str_PostType= SpinnerPostTage.getSelectedItem().toString();
//                OptionsEntity optionsEntity= Config.CountriesArray.get(position);
//                Config.CountryID=optionsEntity.getCountryID().toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
    }


    public  void  makeRequest(final String nationalID)
    {
        showProgress();
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
//                "http://demo3212817.mockable.io/api/users",
                "https://russia-worldcup-results-app.000webhostapp.com/select_PMR_details_by_passport_num.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgress();
                        //Showing toast message of the response
                        try {
                            OptionsEntity optionsEntity=JsonUtils.parseProfileJson(response);
                            PopulateProfileDate(optionsEntity);
//                            getComments(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                //Showing toast
                if (error!=null){
                    Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "error in php files or DB", Toast.LENGTH_LONG).show();
                }
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
//                hashMap.put("country_id",PostID);
                hashMap.put("Pass_num",nationalID);
                return  hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    //Pass_num
    //https://russia-worldcup-results-app.000webhostapp.com/select_PMR_details_by_passport_num.php

    public  void  getCountryAndCountryID()
    {
//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Please wait...", false, false);
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
//                "http://demo3212817.mockable.io/api/countries",
                "https://russia-worldcup-results-app.000webhostapp.com/select_PMR_details_by_passport_num.php",
//                "http://d1e8eb80.ngrok.io/api/countries",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        loading.dismiss();
                        //Showing toast message of the response
                        try {
                            ArrayList<OptionsEntity> arrayList=JsonUtils.parseCountriesJson(response);
//                            LaunchSpinnerData(arrayList);
//                            getComments(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //Showing toast
                if (error!=null){
                    Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "error in php files or DB", Toast.LENGTH_LONG).show();
                }
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
//                hashMap.put("country_id",PostID);
//                hashMap.put("national_id",PostID);
                return  hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

//    private void LaunchSpinnerData(ArrayList<OptionsEntity> arrayList) {
//        CustomSpinnerAdapter customSpinnerAdapterPostType = new CustomSpinnerAdapter(getApplicationContext(), arrayList);
//        Config.CountriesArray=arrayList;
//        SpinnerPostTage.setAdapter(customSpinnerAdapterPostType);
//    }

    public void PopulateProfileDate(OptionsEntity optionsEntity){
        ProfileFragment profileFragment=new ProfileFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("Object",optionsEntity);
        profileFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.profile_details,profileFragment,"profile").commit();
    }
}