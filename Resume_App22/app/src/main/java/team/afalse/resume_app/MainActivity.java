package team.afalse.resume_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner resumeGo;
    private ListView resumeList;
    private List<Resume> resumes;
    private ArrayList<String> names = new ArrayList<String>();
    private DBHandler db;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resumeList = (ListView)findViewById(R.id.resumeList);
        resumeGo = (Spinner)findViewById(R.id.resumeGo);
        db = new DBHandler(this);

        resumeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = resumeList.getItemAtPosition(position).toString();
                goToView(position);
            }
        });
        Log.d("testing", "does it work?");
        loadResumeList();
    }

    private void loadResumeList(){
        resumes = db.getAllResumes();
        Log.d("testing2", resumes.toString());
        Log.d("testing3", "does it work 2?");
        for(int i = 0; i < resumes.size(); i++){
            names.add(resumes.get(i).getName());
            Log.d("testing3", names.toString());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        resumeList.setAdapter(adapter);
    }

    public void goToView(int index){
        int id = resumes.get(index).getId();
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void creatResume(View v){
        String go = resumeGo.getSelectedItem().toString();
        if("Classic".equals(go)) {
            Intent classic = new Intent(this, ResumeActivity.class);
            classic.putExtra("id", -1);
            startActivity(classic);
        }
    }
}
