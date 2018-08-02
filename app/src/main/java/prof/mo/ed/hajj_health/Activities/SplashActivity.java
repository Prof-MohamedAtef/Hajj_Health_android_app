package prof.mo.ed.hajj_health.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.HashMap;

import prof.mo.ed.hajj_health.Helpers.SessionManagement;
import prof.mo.ed.hajj_health.R;

/*
Created by Prof-Mohamed on 6/26/2018.
This to be launched first and redirect the user to the suitable screen
according to whether the user has logged in later or not
in order to sign up or login or to go to DiaryActivity to fetch diary entries
 */


public class SplashActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    @Override
    protected void onResume() {
        super.onResume();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {hideProgressDialog();
                    startActivity(new Intent(SplashActivity.this, Intro_activity.class));
                    finish();
                }
            },5500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showProgressDialog();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.ArishTheme);
        mProgressDialog = new ProgressDialog(this);
    }
}