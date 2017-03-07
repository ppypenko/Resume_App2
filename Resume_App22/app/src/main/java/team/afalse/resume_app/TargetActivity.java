package team.afalse.resume_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class TargetActivity extends AppCompatActivity {

    LinearLayout resumeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        resumeName = (LinearLayout)findViewById(R.id.resume_Name);
    }
}
