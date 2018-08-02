package prof.mo.ed.hajj_health.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import prof.mo.ed.hajj_health.Helpers.OptionsEntity;
import prof.mo.ed.hajj_health.R;
import prof.mo.ed.hajj_health.Utils.JsonUtils;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }


    public  void  makeRequest()
    {
//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Please wait...", false, false);
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "http://5eee74dd.ngrok.io/api/users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        loading.dismiss();
                        //Showing toast message of the response
                        try {
                            OptionsEntity optionsEntity=JsonUtils.parseProfileJson(response);
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

    //Pass_num
    //https://russia-worldcup-results-app.000webhostapp.com/select_PMR_details_by_passport_num.php

    public  void  getCountryAndNationalID()
    {
//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Please wait...", false, false);
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "http://5eee74dd.ngrok.io/api/countries",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        loading.dismiss();
                        //Showing toast message of the response
                        try {
                            OptionsEntity optionsEntity=JsonUtils.parseCountriesJson(response);
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
}
