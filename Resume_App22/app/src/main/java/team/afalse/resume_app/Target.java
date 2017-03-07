package team.afalse.resume_app;

/**
 * Created by ppypenko on 3/7/2017.
 */

public class Target {
    private String name;
    private String phone;
    private String email;
    private String link;
    private String[] requirements;
    private String[] answers;
    public Target(){}

    public Target(String name, String phone, String email, String link, String[] requirements, String[] answers) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.link = link;
        this.requirements = requirements;
        this.answers = answers;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
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

    public String[] getRequirements() {
        return requirements;
    }

    public void setRequirements(String[] requirements) {
        this.requirements = requirements;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
