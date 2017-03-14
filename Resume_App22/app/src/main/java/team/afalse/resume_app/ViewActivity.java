package team.afalse.resume_app;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.style.HeadingStyle;


public class ViewActivity extends AppCompatActivity {

    private int id;
    private IDocument doc;
    private File file;
    private DBHandler db;
    private TextView resumeView;
    private Resume resume;
    private Button btnSave, btnBack, btnEdit;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        resumeView = (TextView)findViewById(R.id.resumeView);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDoc();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResume();
            }
        });
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int tmpId = extras.getInt("id");

        db = new DBHandler(this);
        if(tmpId >= 0)
        {
            resume = db.getResume(tmpId);
            setView();
        }else{
            goToMain();
        }
    }

    private void setView(){
        text = buildDoc();
        resumeView.setText(text.toString());
    }

    private void saveDoc(){
        if(isExternalStorageWritable()){
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(resume.getName() + ".txt", Context.MODE_PRIVATE);
                outputStream.write(text.getBytes());
                outputStream.close();
                System.out.println("File generated with name " + resume.getName() + ".txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private String buildDoc(){
        String doc = ""+ resume.getName() + "\n\n\n";
        doc += "" + resume.getPhone() + " : " + resume.getEmail() + " : " + resume.getLink() + "\n\n";
        doc += "SUMMARY\n" + resume.getSummary() + "\n\n";
        doc += "SKILLS\n";
        int length = resume.getSkills().length;
        for(int i = 0; i < length; i += 3){
            String col1 = ifExists(length, i)? "&bull;" + resume.getSkills()[i] : "";
            String col2 = ifExists(length, i + 1)? "&bull;" + resume.getSkills()[i + 1] : "";
            String col3 = ifExists(length, i + 2)? "&bull;" + resume.getSkills()[i + 2] : "";
            doc += "" + col1 + " | " + col2 + " | " + col3 + "\n";
        }
        doc += "\n\n";
        doc += "EDUCATION\n";
        length = resume.getEducationTitles().length;
        for(int i = 0; i < length; i++){
            doc += "" + resume.getEducationTitles()[i] + "\n";
            doc += "" + resume.getEducationDescription()[i] + "\n";
            doc += "\n";
        }
        doc += "\n\n";
        length = resume.getJobTitles().length;
        doc += "JOBS\n";
        for(int i = 0; i < length; i++){
            doc += "" + resume.getJobTitles()[i] + "\n";
            doc += "" + resume.getJobDescriptions()[i] + "\n";
            doc += "\n";
        }
//        doc = new Document2004();
//        boolean done1 = false, done2 = false;
//        doc.encoding(Document2004.Encoding.UTF_8);
//        doc.addEle(Heading1.with(resume.getName()).withStyle().align(HeadingStyle.Align.CENTER).bold().create());
//        doc.addEle(Heading2.with(resume.getPhone() + " : " + resume.getEmail() + " : " + resume.getLink()).withStyle().align(HeadingStyle.Align.CENTER).create());
//        doc.addEle(new BreakLine(3));
//        doc.addEle(Heading3.with("SUMMARY").withStyle().align(HeadingStyle.Align.CENTER).bold().create());
//        doc.addEle(Paragraph.with(resume.getSummary()).create());
//        doc.addEle(new BreakLine(2));
//        doc.addEle(Heading2.with("SKILLS").withStyle().align(HeadingStyle.Align.CENTER).create());
//        Table tbl = new Table();
//        int length = resume.getSkills().length;
//        for(int i = 0; i < length; i += 3){
//            String col1 = ifExists(length, i)? "&bull;" + resume.getSkills()[i] : "";
//            String col2 = ifExists(length, i + 1)? "&bull;" + resume.getSkills()[i + 1] : "";
//            String col3 = ifExists(length, i + 2)? "&bull;" + resume.getSkills()[i + 2] : "";
//            tbl.addTableEle(TableEle.TD, col1, col2, col3);
//        }
//        doc.addEle(tbl);
//        doc.addEle(new BreakLine(2));
//        doc.addEle(Heading2.with("EDUCATION").withStyle().align(HeadingStyle.Align.CENTER).create());
//        length = resume.getEducationTitles().length;
//        for(int i = 0; i < length; i++){
//            doc.addEle(Heading3.with(resume.getEducationTitles()[i]).withStyle().align(HeadingStyle.Align.LEFT).create());
//            doc.addEle(Heading3.with(resume.getEducationDescription()[i]).withStyle().align(HeadingStyle.Align.LEFT).create());
//            doc.addEle(new BreakLine(1));
//        }
//        doc.addEle(new BreakLine(2));
//        length = resume.getJobTitles().length;
//        for(int i = 0; i < length; i++){
//            doc.addEle(Heading3.with(resume.getJobTitles()[i]).withStyle().align(HeadingStyle.Align.LEFT).create());
//            doc.addEle(Heading3.with(resume.getJobDescriptions()[i]).withStyle().align(HeadingStyle.Align.LEFT).create());
//            doc.addEle(new BreakLine(1));
//        }
//        return doc.getContent();
        return doc;
    }

    private boolean ifExists(int length, int index){
        return length > index;
    }

    private void goToMain(){
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    private void goToResume(){
        Intent classic = new Intent(this, ResumeActivity.class);
        classic.putExtra("id", id);
        startActivity(classic);
    }
}
