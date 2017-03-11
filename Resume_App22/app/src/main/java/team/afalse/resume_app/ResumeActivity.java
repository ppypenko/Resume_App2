package team.afalse.resume_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResumeActivity extends AppCompatActivity {

    private Resume resumeCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int tmpId = extras.getInt("id");

        DBHandler dbHandler = new DBHandler(this);

        dbHandler.getResume(tmpId);
    }


}
