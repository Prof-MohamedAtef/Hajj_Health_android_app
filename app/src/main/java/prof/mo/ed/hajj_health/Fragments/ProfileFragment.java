package prof.mo.ed.hajj_health.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import prof.mo.ed.hajj_health.Helpers.OptionsEntity;
import prof.mo.ed.hajj_health.R;

/**
 * Created by Prof-Mohamed Atef on 8/2/2018.
 */

public class ProfileFragment extends Fragment {
    OptionsEntity ProfileData;
    String ProfileID, PatientName, Nationality, Sex, DOB, Job, VisaType, PassportNum, Diabetes, Heart, Length, Weight,
            HealthStatus, Blood, haveTat3eem, DoctorName, DiagnoseDate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_details, container, false);
        final Bundle bundle=getArguments();
        if (bundle!=null){
            if (bundle.containsKey("Object")){
                ProfileData = (OptionsEntity) bundle.getSerializable("Object");
                if (ProfileData!=null) {
                    ProfileID = ProfileData.getProfileID();
                    PatientName = ProfileData.getPatientName();
                    Nationality = ProfileData.getNationality();
                    Sex = ProfileData.getSex();
                    DOB = ProfileData.getDOB();
                    PassportNum = ProfileData.getPassportNum();
                    Job = ProfileData.getJob();
                    VisaType = ProfileData.getVisaType();
                    HealthStatus = ProfileData.getHealthStatus();
                    Diabetes = ProfileData.getDiabetes();
                    Heart = ProfileData.getHeart();
                    Blood = ProfileData.getBlood();
                    Length = ProfileData.getLength();
                    Weight = ProfileData.getWeight();
                    haveTat3eem = ProfileData.getHaveTat3eem();
                    DoctorName = ProfileData.getDoctorName();
                    DiagnoseDate = ProfileData.getDiagnoseDate();
                    TextView txt_currentname = (TextView) rootView.findViewById(R.id.txt_currentname);
                    txt_currentname.setText(PatientName.toString());
                    TextView txt_passport= (TextView) rootView.findViewById(R.id.txt_passport);
                    txt_passport.setText(PassportNum.toString());
                    TextView txt_nationality= (TextView) rootView.findViewById(R.id.txt_nationality);
                    txt_nationality.setText(Nationality.toString());
                    TextView sex_= (TextView) rootView.findViewById(R.id.sex_);
                    sex_.setText(Sex.toString());
                    TextView job_=(TextView) rootView.findViewById(R.id.job_);
                    job_.setText(Job.toString());
                    TextView visaType_=(TextView) rootView.findViewById(R.id.visaType_);
                    visaType_.setText(VisaType.toString());
                    TextView patient_status_=(TextView) rootView.findViewById(R.id.patient_status_);
                    patient_status_.setText(HealthStatus.toString());
                    TextView diabets_=(TextView) rootView.findViewById(R.id.diabets_);
                    if (Diabetes.equals("1")){
                        diabets_.setText("yes");
                    }else {
                        diabets_.setText("no");
                    }
                    TextView blood_=(TextView) rootView.findViewById(R.id.blood_);
                    if (Blood.equals("1")){
                        blood_.setText("yes");
                    }else {
                        blood_.setText("no");
                    }
                    TextView heart_=(TextView) rootView.findViewById(R.id.heart_);
                    if (Heart.equals("1")){
                        heart_.setText("yes");
                    }else {
                        heart_.setText("no");
                    }
                    TextView length_=(TextView) rootView.findViewById(R.id.length_);
                    length_.setText(Length.toString());
                    TextView weight_=(TextView) rootView.findViewById(R.id.weight_);
                    weight_.setText(Weight.toString());
                    TextView Tat3eem_=(TextView) rootView.findViewById(R.id.Tat3eem_);
                    Tat3eem_.setText(haveTat3eem.toString());
                    TextView DocName=(TextView) rootView.findViewById(R.id.DocName);
                    DocName.setText(DoctorName.toString());
                    TextView DiagnoseDate_=(TextView) rootView.findViewById(R.id.DiagnoseDate_);
                    DiagnoseDate_.setText(DiagnoseDate.toString());
                }
            }
        }
        return rootView;

    }
}
