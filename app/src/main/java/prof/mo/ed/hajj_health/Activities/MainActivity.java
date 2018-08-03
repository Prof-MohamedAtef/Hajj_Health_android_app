package prof.mo.ed.hajj_health.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;

import prof.mo.ed.hajj_health.Network.ConnectivityReceiver;
import prof.mo.ed.hajj_health.R;

import static prof.mo.ed.hajj_health.Helpers.Config.isInternetConnected;

public class MainActivity extends AppCompatActivity {

    LinearLayout Linear_Profile_launcher,Linear_doc_request_launcher,Linear_Ambulance_launcher,Linear_consultation_Launcher;

    boolean isInternetConnected;
    private void checkConnection() throws JSONException {
        isInternetConnected = ConnectivityReceiver.isConnected(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTheme(R.style.AppTheme);
        Linear_Profile_launcher=(LinearLayout)findViewById(R.id.Linear_Profile_launcher);
        Linear_doc_request_launcher=(LinearLayout)findViewById(R.id.Linear_doc_request_launcher);
        Linear_Ambulance_launcher=(LinearLayout)findViewById(R.id.Linear_Ambulance_launcher);
        Linear_consultation_Launcher=(LinearLayout)findViewById(R.id.Linear_consultation_Launcher);
        Linear_consultation_Launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Consultancy.class);
                startActivity(intent);
            }
        });
        Linear_Ambulance_launcher.setOnClickListener(new View.OnClickListener() {
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
        Linear_Profile_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseOption();
            }
        });
        Linear_doc_request_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);
            }
        });
    }


    AlertDialog Dialogue;
    private void DialougRUSureMakeRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.requestAmbulance));
        builder.setMessage(getString(R.string.requestambulance_txt));
        builder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogue.dismiss();
                Toast.makeText(getApplicationContext(), "Your request has been submitted successfully!", Toast.LENGTH_LONG).show();
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

    private void DialougMakeRequestConnectionLost() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.approve_offline));
        builder.setMessage(getString(R.string.approve_offlineSent));
        builder.setPositiveButton(getString(R.string.Done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Dialogue = builder.create();
        Dialogue.show();
    }

    private void ChooseOption() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(this.getString(R.string.Choose_option))
                .setCancelable(false)
                .setPositiveButton(getApplicationContext().getString(R.string.qrCode), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
//                        Intent intent=new Intent(getApplicationContext(),QRCodeScanner.class);
//                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Under development!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(getApplicationContext().getString(R.string.nationalID), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
//                        buildAlertMessageNoGpsWarning();
                        Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent);

                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LaunchNotification();
    }

    private void LaunchNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.yellow_car)
                .setContentTitle("Hot Weather")
                .setContentText("Dear hajj! weather is very hot, remember to drink a lot of water!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}