package prof.mo.ed.hajj_health.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import prof.mo.ed.hajj_health.Fragments.MapFragment;
import prof.mo.ed.hajj_health.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.posts_container_frame, new MapFragment(), "posts")
                .commit();
    }
}
