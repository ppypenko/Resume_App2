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

    private Button btnGo;
    private Spinner resumeGo;
    private ListView resumeList;
    private ArrayList<String> resumes = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo = (Button)findViewById(R.id.btnGo);
        resumeList = (ListView)findViewById(R.id.resumeList);
        resumeGo = (Spinner)findViewById(R.id.resumeGo);

        resumeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = resumeList.getItemAtPosition(position).toString();
                System.out.println(type);
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String go = resumeGo.getSelectedItem().toString();
                if(go == "Classic"){
                    Intent classic = new Intent(ResumeActivity.this, ResumeActivity.class);
                    startActivity(classic);
                }else if (go == "Target"){

                }
            }
        });
    }
}
