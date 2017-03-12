package team.afalse.resume_app;

/**
 * Created by ppypenko on 3/7/2017.
 */

public class Resume {
    private int id;
    private String resumeName;
    private String name;
    private String phone;
    private String email;
    private String link;
    private String summary;
    private String[] jobTitles;
    private String[] jobDescriptions;
    private String[] skills;
    private String[] educationTitles;
    private String[] educationDescription;

    public Resume(){}


    public Resume(String resumeName, String name, String phone, String email, String link, String summary, String[] jobTitles, String[] jobDescriptions, String[] skills, String[] educationTitles, String[] educationDescription) {
        this.resumeName = resumeName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.link = link;
        this.summary = summary;
        this.jobTitles = jobTitles;
        this.jobDescriptions = jobDescriptions;
        this.skills = skills;
        this.educationTitles = educationTitles;
        this.educationDescription = educationDescription;
    }

    public Resume(int id, String resumeName, String name, String phone, String email, String link, String summary, String[] jobTitles, String[] jobDescriptions, String[] skills, String[] educationTitles, String[] educationDescription) {
        this.id = id;
        this.resumeName = resumeName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.link = link;
        this.summary = summary;
        this.jobTitles = jobTitles;
        this.jobDescriptions = jobDescriptions;
        this.skills = skills;
        this.educationTitles = educationTitles;
        this.educationDescription = educationDescription;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(String[] jobTitles) {
        this.jobTitles = jobTitles;
    }

    public String[] getJobDescriptions() {
        return jobDescriptions;
    }

    public void setJobDescriptions(String[] jobDescriptions) {
        this.jobDescriptions = jobDescriptions;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getEducationTitles() {
        return educationTitles;
    }

    public void setEducationTitles(String[] educationTitles) {
        this.educationTitles = educationTitles;
    }

    public String[] getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(String[] educationDescription) {
        this.educationDescription = educationDescription;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
