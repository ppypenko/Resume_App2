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
    private String[] jobTitle;
    private String[] jobDescriptions;
    private String[] skills;
    private String[] educationTitle;
    private String[] education;

    public Resume(){}

    public Resume(int id, String resumeName, String name, String phone, String email, String link, String summary, String[] jobTitle, String[] jobDescriptions, String[] skills, String[] educationTitle, String[] education) {
        this.id = id;
        this.resumeName = resumeName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.link = link;
        this.summary = summary;
        this.jobTitle = jobTitle;
        this.jobDescriptions = jobDescriptions;
        this.skills = skills;
        this.educationTitle = educationTitle;
        this.education = education;
    }

    public Resume(String resumeName, String name, String phone, String email, String link, String summary, String[] jobTitle, String[] jobDescriptions, String[] skills, String[] educationTitle, String[] education) {
        this.resumeName = resumeName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.link = link;
        this.summary = summary;
        this.jobTitle = jobTitle;
        this.jobDescriptions = jobDescriptions;
        this.skills = skills;
        this.educationTitle = educationTitle;
        this.education = education;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String[] getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String[] jobTitle) {
        this.jobTitle = jobTitle;
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

    public String[] getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(String[] educationTitle) {
        this.educationTitle = educationTitle;
    }

    public String[] getEducation() {
        return education;
    }

    public void setEducation(String[] education) {
        this.education = education;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }
}
