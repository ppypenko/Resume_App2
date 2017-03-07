package team.afalse.resume_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppypenko on 3/7/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Resumes";

    private static final String TABLE_RESUMES = "ResumeTable";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SUMMARY = "summary";
    private static final String KEY_HEADERS = "headers";
    private static final String KEY_JOBS = "jobs";
    private static final String KEY_JOB_DESCRIPTIONS = "job_descriptions";
    private static final String KEY_SKILLS = "skills";
    private static final String KEY_EDUCATION = "education";
    private static final String KEY_REFERENCES = "references";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CONTACT_INFO = "contact_info";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        init();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //init();
    }

    public void init() {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RESUMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_SUMMARY + " TEXT, " + KEY_HEADERS + " TEXT, "
                + KEY_JOBS + " TEXT, " + KEY_JOB_DESCRIPTIONS + " TEXT, "
                + KEY_SKILLS + " TEXT, " + KEY_EDUCATION + " TEXT, "
                + KEY_REFERENCES + " TEXT, " + KEY_ADDRESS + " TEXT ,"
                + KEY_CONTACT_INFO + " TEXT )";
        getWritableDatabase().execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESUMES);
        init();
        //onCreate(db);
    }

    public void drop() {
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_RESUMES);
    }

    public void addResume(Resume resume) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values = SetResumeContentValues(resume, values);

        db.insertOrThrow(TABLE_RESUMES, null, values);
        db.close();
    }

    private ContentValues SetResumeContentValues(Resume resume, ContentValues values){

        values.put(KEY_NAME, resume.GetName());
        values.put(KEY_SUMMARY, resume.GetSummary());
        values.put(KEY_HEADERS, ConvertArrayToString(resume.GetHeaders()));
        values.put(KEY_JOBS, ConvertArrayToString(resume.GetJobs()));
        values.put(KEY_JOB_DESCRIPTIONS, ConvertArrayToString(resume.GetJobDescriptions()));
        values.put(KEY_SKILLS, ConvertArrayToString(resume.GetSkills()));
        values.put(KEY_EDUCATION, ConvertArrayToString(resume.GetEducation()));
        values.put(KEY_REFERENCES, ConvertArrayToString(resume.GetReferences()));
        values.put(KEY_ADDRESS, resume.GetAddress());
        values.put(KEY_CONTACT_INFO, ConvertArrayToString(resume.GetContactInfo()));
        return values;
    }

    public Resume getResume(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESUMES, new String[]{KEY_ID,
                        KEY_NAME, KEY_SUMMARY, KEY_HEADERS, KEY_JOBS, KEY_JOB_DESCRIPTIONS,
                        KEY_SKILLS, KEY_EDUCATION, KEY_REFERENCES, KEY_ADDRESS, KEY_CONTACT_INFO }, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Resume contact = new Resume(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), ConvertStringToArray(cursor.getString(3)), ConvertStringToArray(cursor.getString(4)),
                ConvertStringToArray(cursor.getString(5)), ConvertStringToArray(cursor.getString(6)),
                ConvertStringToArray(cursor.getString(7)), ConvertStringToArray(cursor.getString(8)),
                cursor.getString(9), ConvertStringToArray(cursor.getString(10)));

        cursor.close();
        return contact;
    }

    public List<Resume> getAllResumes() {
        List<Resume> resumeList = new ArrayList<Resume>();

        String selectQuery = "SELECT * FROM " + TABLE_RESUMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean moveToFirst = cursor.moveToFirst();

        if (moveToFirst) {
            do {
                Resume resume = new Resume(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), ConvertStringToArray(cursor.getString(3)), ConvertStringToArray(cursor.getString(4)),
                        ConvertStringToArray(cursor.getString(5)), ConvertStringToArray(cursor.getString(6)),
                        ConvertStringToArray(cursor.getString(7)), ConvertStringToArray(cursor.getString(8)),
                        cursor.getString(9), ConvertStringToArray(cursor.getString(10)));

                resumeList.add(resume);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return resumeList;
    }

    public int getResumesCount() {
        String countQuery = "SELECT * FROM " + TABLE_RESUMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateResume(Resume resume) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values = SetResumeContentValues(resume, values);

        int rowsUpdated = db.update(TABLE_RESUMES, values, KEY_ID + " = ?", new String[]{String.valueOf(resume.GetId())});
        System.out.println("Rows updated: " + rowsUpdated);
        return rowsUpdated;
    }

    public void deleteResume(Resume resume) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESUMES, KEY_ID + " = ?",
                new String[] { String.valueOf(resume.GetId()) });
        db.close();
    }

    private static String strSeparator = "__,__";
    private static String ConvertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    private static String[] ConvertStringToArray(String str){
        return str.split(strSeparator);
    }
}