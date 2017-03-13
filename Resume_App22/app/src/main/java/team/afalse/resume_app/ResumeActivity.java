package team.afalse.resume_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ResumeActivity extends AppCompatActivity {

    private Resume resumeCopy;
    private int questionNum;
    private int currentQuestion;
    private String[] questions;
    private DBHandler dbHandler;
    private boolean isNew;
    private ArrayList<String> multipleAnswerList;
    private int stringArraysStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        multipleAnswerList = new ArrayList<>();
        stringArraysStart = 5;
        questions = Questions.getQuestions();
        questionNum = questions.length;
        currentQuestion = 0;

        // LOOK HERE FOR PASSING RESUME ID, PASS -1 FOR NEW RESUME
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int tmpId = extras.getInt("id");

        dbHandler = new DBHandler(this);
        if(tmpId >= 0)
        {
            resumeCopy = dbHandler.getResume(tmpId);
            isNew = false;
        }
        else
        {
            resumeCopy = new Resume();
            isNew = true;
        }
        changeQuestion(currentQuestion);
        ((Button) findViewById(R.id.btn_add)).setEnabled(false);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_prev:
                goToPreviousQuestion();
                break;
            case R.id.btn_next:
                goToNextQuestion();
                break;
            case R.id.btn_save:
                flushToDB();
                break;
            case R.id.btn_add:
                addAnswer();
                break;
            default:
                break;
        }
    }

    protected void addAnswer(){
        EditText answerField = (EditText) findViewById(R.id.txt_answer);
        multipleAnswerList.add(answerField.getText().toString());
        ((EditText) findViewById(R.id.txt_answer)).setText("");
    }

    protected void goToPreviousQuestion(){
        if (currentQuestion > 0)
        {
            --currentQuestion;
            changeQuestion(currentQuestion);
        }
    }

    protected void goToNextQuestion(){
        if(currentQuestion < stringArraysStart - 1){
            setFieldByAnswer(((EditText) findViewById(R.id.txt_answer)).getText().toString(), currentQuestion);
        }
        else if(currentQuestion >= stringArraysStart - 1){
            findViewById(R.id.btn_add).setEnabled(true);
            String[] array = convertToStringArray(multipleAnswerList);
            setFieldByAnswer(array, currentQuestion);
            multipleAnswerList.clear();
        }
        ++currentQuestion;
        if(currentQuestion < questionNum){
            changeQuestion(currentQuestion);
        }
        else{
            flushToDB();
            finish();
        }
    }

    protected String[] convertToStringArray(ArrayList<String> strings){
        String[] conversion = new String[strings.size()];
        for (int k = 0; k < strings.size(); ++k){
            conversion[k] = strings.get(k);
        }
        return conversion;
    }

    protected void flushToDB(){
        if(!isNew){
            dbHandler.updateResume(resumeCopy);
        }
        else{
            resumeCopy.setId(dbHandler.addResume(resumeCopy));
            isNew = false;
        }
    }

    protected void changeQuestion(int p_questionNum){
        TextView view = (TextView) findViewById(R.id.txt_question);
        view.setText(questions[p_questionNum]);
        ((EditText) findViewById(R.id.txt_answer)).setText("");
    }

    protected void setFieldByAnswer(String answer, int p_questionNum){
        switch (p_questionNum){
            case 0:
                resumeCopy.setName(answer);
                break;
            case 1:
                resumeCopy.setPhone(answer);
                break;
            case 2:
                resumeCopy.setEmail(answer);
                break;
            case 3:
                resumeCopy.setLink(answer);
                break;
            case 4:
                resumeCopy.setSummary(answer);
                break;
            default:
                break;
        }
    }

    protected void setFieldByAnswer(String[] answer, int p_questionNum){
        switch (p_questionNum){
            case 5:
                resumeCopy.setSkills(answer);
                break;
            case 6:
                resumeCopy.setJobTitles(answer);
                break;
            case 7:
                resumeCopy.setJobDescriptions(answer);
                break;
            case 8:
                resumeCopy.setEducationTitles(answer);
                break;
            case 9:
                resumeCopy.setEducationDescription(answer);
                break;
            default:
                break;
        }
    }
}
