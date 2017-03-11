package team.afalse.resume_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner resumeGo;
    private ListView resumeList;
    private ArrayList<String> resumes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resumeList = (ListView)findViewById(R.id.resumeList);
        resumeGo = (Spinner)findViewById(R.id.resumeGo);

        resumeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = resumeList.getItemAtPosition(position).toString();
                System.out.println(type);
            }
        });
        loadResumeList();
    }

    private void loadResumeList(){

    }

    public void goToView(){

    }

    public void creatResume(View v){
        String go = resumeGo.getSelectedItem().toString();
        if("Classic".equals(go)) {
            Intent classic = new Intent(this, ResumeActivity.class);
            startActivity(classic);
        }
//        else if ("Target".equals(go)){
//            Intent target = new Intent(this, TargetActivity.class);
//            startActivity(target);
//        }
    }
}
