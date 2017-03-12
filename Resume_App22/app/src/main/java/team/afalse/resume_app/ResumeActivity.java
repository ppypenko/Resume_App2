package team.afalse.resume_app;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ResumeActivity extends AppCompatActivity {

    private Resume resumeCopy;
    private int questionNum;
    private int currentQuestion;
    private String[] questions;
    private DBHandler dbHandler;
    private boolean isNew;
    private List<String> multipleAnswerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        questions = Questions.getQuestions();
        questionNum = questions.length;
        currentQuestion = 0;
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
            default:
                break;
        }
    }

    protected void goToPreviousQuestion(){

    }

    protected void goToNextQuestion(){
        if(currentQuestion == questionNum - 1){
            flushToDB();
            //TODO end here
            return;
        }
        ++currentQuestion;
        if(currentQuestion < questionNum){
            changeQuestion(currentQuestion);
        }
    }

    protected void flushToDB(){
        if(isNew){
            dbHandler.updateResume(resumeCopy);
        }
        else{
            dbHandler.addResume(resumeCopy);
            //TODO get the id somehow
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
                resumeCopy.setJobTitles(answer);
                break;
            case 6:
                resumeCopy.setJobDescriptions(answer);
                break;
            case 7:
                resumeCopy.setSkills(answer);
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
