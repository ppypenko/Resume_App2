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
    private static final String KEY_RESUME_NAME = "resume_name";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_LINK = "link";
    private static final String KEY_SUMMARY = "summary";
    private static final String KEY_JOB_TITLES = "job_titles";
    private static final String KEY_JOB_DESCRIPTIONS = "job_descriptions";
    private static final String KEY_SKILLS = "skills";
    private static final String KEY_EDUCATION_TITLES = "education_titles";
    private static final String KEY_EDUCATION_DESCRIPTIONS = "education_descriptions";

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
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_RESUME_NAME + " TEXT, "
                + KEY_NAME + " TEXT, " + KEY_PHONE + " TEXT, "
                + KEY_EMAIL + " TEXT, " + KEY_LINK + " TEXT, "
                + KEY_SUMMARY + " TEXT, " + KEY_JOB_TITLES + " TEXT, "
                + KEY_JOB_DESCRIPTIONS + " TEXT, " + KEY_SKILLS + " TEXT, "
                + KEY_EDUCATION_TITLES + " TEXT, " + KEY_EDUCATION_DESCRIPTIONS + " TEXT)";
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

    public int addResume(Resume resume) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values = SetResumeContentValues(resume, values);

        int id = (int)db.insertOrThrow(TABLE_RESUMES, null, values);
        db.close();
        return id;
    }

    private ContentValues SetResumeContentValues(Resume resume, ContentValues values){

        values.put(KEY_RESUME_NAME, resume.getResumeName());
        values.put(KEY_NAME, resume.getName());
        values.put(KEY_PHONE, resume.getPhone());
        values.put(KEY_EMAIL, resume.getEmail());
        values.put(KEY_LINK, resume.getLink());
        values.put(KEY_SUMMARY, resume.getSummary());
        values.put(KEY_JOB_TITLES, ConvertArrayToString(resume.getJobTitles()));
        values.put(KEY_JOB_DESCRIPTIONS, ConvertArrayToString(resume.getJobDescriptions()));
        values.put(KEY_SKILLS, ConvertArrayToString(resume.getSkills()));
        values.put(KEY_EDUCATION_TITLES, ConvertArrayToString(resume.getEducationTitles()));
        values.put(KEY_EDUCATION_DESCRIPTIONS, ConvertArrayToString(resume.getEducationDescription()));
        return values;
    }

    public Resume getResume(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESUMES, new String[]{KEY_ID, KEY_RESUME_NAME,
                        KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_LINK, KEY_SUMMARY,
                        KEY_JOB_TITLES, KEY_JOB_DESCRIPTIONS, KEY_SKILLS, KEY_EDUCATION_TITLES,
                        KEY_EDUCATION_DESCRIPTIONS }, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Resume contact = new Resume(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),
                ConvertStringToArray(cursor.getString(7)), ConvertStringToArray(cursor.getString(8)),
                ConvertStringToArray(cursor.getString(9)), ConvertStringToArray(cursor.getString(10)),
                ConvertStringToArray(cursor.getString(10)));

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
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6),
                        ConvertStringToArray(cursor.getString(7)), ConvertStringToArray(cursor.getString(8)),
                        ConvertStringToArray(cursor.getString(9)), ConvertStringToArray(cursor.getString(10)),
                        ConvertStringToArray(cursor.getString(10)));

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

        int rowsUpdated = db.update(TABLE_RESUMES, values, KEY_ID + " = ?", new String[]{String.valueOf(resume.getId())});
        System.out.println("Rows updated: " + rowsUpdated);
        return rowsUpdated;
    }

    public void deleteResume(Resume resume) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESUMES, KEY_ID + " = ?",
                new String[] { String.valueOf(resume.getId()) });
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
