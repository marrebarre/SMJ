package sample.controller;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
    TextField searchTxt;

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
            Button personBtn = new Button(person.getName() + ":" + person.getCivic());

            personBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    nameLbl.setText(person.getName());
                    civicLbl.setText(person.getCivic());

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
                    commentLbl1.setText(document.getComment());
                    commentLbl2.setText(document.getDoctorscomment());
                    paper.setVisible(true);

                }
            });


            searchList.getChildren().add(documentBtn);
        }

    }

}
