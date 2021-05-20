package sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Documents {

    @SerializedName("results")
    ArrayList<Document> documents;

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public Documents(ArrayList<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "documents=" + documents +
                '}';
    }
}
