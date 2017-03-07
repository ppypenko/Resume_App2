package team.afalse.resume_app;

/**
 * Created by ppypenko on 3/7/2017.
 */

public class Resume {
    private int id;
    private String name;
    private String summary;
    private String[] headers;
    private String[] jobs;
    private String[] jobDescriptions;
    private String[] skills;
    private String[] education;
    private String[] contactInfo;

    public Resume(){}

    public Resume(int id, String name, String summary, String[] headers , String[] jobs, String[] jobDescriptions,
                  String[] skills, String[] education, String[] contactInfo){
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.headers = headers;
        this.jobs = jobs;
        this.jobDescriptions = jobDescriptions;
        this.skills = skills;
        this.education = education;
        this.contactInfo = contactInfo;
    }

    public int GetId(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetSummary() {
        return summary;
    }

    public void SetSummary(String summary) {
        this.summary = summary;
    }

    public String[] GetHeaders() {
        return headers;
    }

    public void SetHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] GetJobs() {
        return jobs;
    }

    public void SetJobs(String[] jobs) {
        this.jobs = jobs;
    }

    public String[] GetJobDescriptions() {
        return jobDescriptions;
    }

    public void SetJobDescriptions(String[] jobDescriptions) {
        this.jobDescriptions = jobDescriptions;
    }

    public String[] GetSkills() {
        return skills;
    }

    public void SetSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] GetEducation() {
        return education;
    }

    public void SetEducation(String[] education) {
        this.education = education;
    }

    public String[] GetContactInfo() {
        return contactInfo;
    }

    public void SetContactInfo(String[] contactInfo) {
        this.contactInfo = contactInfo;
    }
}
