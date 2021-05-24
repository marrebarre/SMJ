package sample.controller;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import sample.controller.Controller;
import sample.model.*;

import java.util.ArrayList;

public class Journal extends Controller {

    @FXML
    VBox searchList;
    @FXML
    AnchorPane paper;

    //Person info in Document
    @FXML
    Label nameLbl;
    @FXML
    Label civicLbl;
    @FXML
    Label dateLbl;

    @FXML
    Label commentLbl1;
    @FXML
    Label commentLbl2;

    @FXML
    TextArea commentTxt1;
    @FXML
    TextArea commentTxt2;


    @FXML
    TextField searchTxt;

    @FXML
    Pane displayComments;
    @FXML
    Pane editComments;


    public void search() throws Exception {


        Body body = new Body(searchTxt.getText());


        String result = post("/persons", gson.toJson(body));
        //String result = post("/persons", searchTxt.getText());

        System.out.println(result);

        Persons persons = gson.fromJson(result, Persons.class);

        loadPersonList(persons.getPersons());

    }

    private void loadPersonList(ArrayList<Person> persons) {

        searchTxt = (TextField) searchList.getChildren().get(0);
        searchList.getChildren().clear();
        searchList.getChildren().add(searchTxt);
        paper.setVisible(false);


        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            Button personBtn = new Button(swedify(person.getName()) + ":" + person.getCivic());

            personBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    nameLbl.setText(swedify(person.getName()));
                    civicLbl.setText(swedify(person.getCivic()));


                    String result = null;
                    try {
                        Body body = new Body(person.getCivic());
                        result = post("/documents", gson.toJson(body));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Documents documents = gson.fromJson(result, Documents.class);

                    System.out.println(documents.getDocuments());
                    loadDocumentList(documents.getDocuments());

                }
            });

            searchList.getChildren().add(personBtn);
        }

    }

    private void loadDocumentList(ArrayList<Document> documents) {

        searchTxt = (TextField) searchList.getChildren().get(0);

        searchList.getChildren().clear();
        searchList.getChildren().add(searchTxt);

        for (int i = 0; i < documents.size(); i++) {
            Document document = documents.get(i);
            Button documentBtn = new Button(document.getTimestamp());


            documentBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    dateLbl.setText(document.getTimestamp());
                    commentLbl1.setText(swedify(document.getComment()));
                    commentLbl2.setText(swedify(document.getDoctorscomment()));
                    paper.setVisible(true);
                    editComments.setVisible(false);
                    displayComments.setVisible(true);
                }
            });




            searchList.getChildren().add(documentBtn);

        }
        Button newDocumentBtn = new Button("+");
        newDocumentBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paper.setVisible(true);
                editComments.setVisible(true);
                displayComments.setVisible(false);
                dateLbl.setText(getTime());
            }
        });
        searchList.getChildren().add(newDocumentBtn);

    }

    public void saveDocument(){

        Document document = new Document(dateLbl.getText(),commentTxt1.getText(),searchList.getChildren().size()-1,civicLbl.getText(), commentTxt2.getText());
        System.out.println(document);

        try {
            post("/documents/add", gson.toJson(document));
            Body body = new Body(document.getCivicid());
            String result = post("/documents", gson.toJson(body));
            Documents documents = gson.fromJson(result, Documents.class);

            loadDocumentList(documents.getDocuments());

        } catch (Exception e) {
            e.printStackTrace();
        }

        commentTxt1.clear();
        commentTxt2.clear();
        editComments.setVisible(false);
        displayComments.setVisible(true);
        paper.setVisible(false);

    }

    public void addPerson(){
        newScene("sample.fxml");
    }

}
