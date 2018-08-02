package prof.mo.ed.hajj_health.Activities;

import android.Manifest;
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

import prof.mo.ed.hajj_health.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout Linear_Profile_launcher,Linear_doc_request_launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Linear_Profile_launcher=(LinearLayout)findViewById(R.id.Linear_Profile_launcher);
        Linear_doc_request_launcher=(LinearLayout)findViewById(R.id.Linear_doc_request_launcher);
        Linear_Profile_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
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

    private void ChooseOption() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(getApplicationContext().getString(R.string.CallPermissionAlert))
                .setCancelable(false)
                .setPositiveButton(getApplicationContext().getString(android.R.string.qrCode), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {

                    }
                })
                .setNegativeButton(getApplicationContext().getString(android.R.string.nationalID), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
//                        buildAlertMessageNoGpsWarning();

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