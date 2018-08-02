package prof.mo.ed.hajj_health.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prof.mo.ed.hajj_health.R;

/**
 * Created by Prof-Mohamed Atef on 8/2/2018.
 */

public class FifthFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fourth_intro_fragment, container, false);
        return rootView;

    }
}
