package sample.model;

public class Document {

    private String timestamp;
    private String comment;
    private int id;
    private String civicid;
    private String doctorscomment;

    public Document(String timestamp, String comment, int id, String civicid, String doctorscomment) {
        this.timestamp = timestamp;
        this.comment = comment;
        this.id = id;
        this.civicid = civicid;
        this.doctorscomment = doctorscomment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCivicid() {
        return civicid;
    }

    public void setCivicid(String civicid) {
        this.civicid = civicid;
    }

    public String getDoctorscomment() {
        return doctorscomment;
    }

    public void setDoctorscomment(String doctorscomment) {
        this.doctorscomment = doctorscomment;
    }

    @Override
    public String toString() {
        return "Document{" +
                "timestamp='" + timestamp + '\'' +
                ", comment='" + comment + '\'' +
                ", id=" + id +
                ", civicid='" + civicid + '\'' +
                ", doctorscomment='" + doctorscomment + '\'' +
                '}';
    }
}
