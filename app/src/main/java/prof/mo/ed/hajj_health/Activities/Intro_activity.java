package prof.mo.ed.hajj_health.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;
import com.github.paolorotolo.appintro.model.SliderPage;

import prof.mo.ed.hajj_health.Fragments.FirstFragment;
import prof.mo.ed.hajj_health.Fragments.FourthFragment;
import prof.mo.ed.hajj_health.Fragments.SecondFragment;
import prof.mo.ed.hajj_health.Fragments.ThirddFragment;
import prof.mo.ed.hajj_health.R;

public class Intro_activity extends AppIntro {//implements ISlideBackgroundColorHolder{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirstFragment firstFragment=new FirstFragment();
        SecondFragment secondFragment=new SecondFragment();
        ThirddFragment thirddFragment=new ThirddFragment();
        FourthFragment fourthFragment= new FourthFragment();
        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirddFragment);
        addSlide(fourthFragment);
//        SliderPage sliderPage = new SliderPage();
//        sliderPage.setTitle("Hajj public Health");
//        sliderPage.setDescription("app provide hajj visitors with immediate response for their health");
//        sliderPage.setImageDrawable(R.drawable.icon);
//        sliderPage.setBgColor(getResources().getColor(R.color.primary_text));
//        addSlide(AppIntroFragment.newInstance(sliderPage));
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
//        showSkipButton(false);
//        setProgressButtonEnabled(false);
        setFadeAnimation();
//        setZoomAnimation();
//        setFlowAnimation();
//        setSlideOverAnimation();
//        setDepthAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

//
//    @Override
//    public int getDefaultBackgroundColor() {
//        return Color.parseColor("#000000");
//    }
//
//    @Override
//    public void setBackgroundColor(int backgroundColor) {
//        if (getResources().getLayout(R.layout.activity_intro_activity)!= null) {
//            get.setBackgroundColor(backgroundColor);
//        }
//    }
}