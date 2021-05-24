package sample.controller;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Controller {


    @FXML
    TextField name = new TextField();
    @FXML
    TextField civic = new TextField();

    String hostAddress = "https://smj-backend.herokuapp.com";


    Gson gson = new Gson();

    public void postData() throws Exception {

        //Simple JSON SIMULATOR
        Person person = new Person(name.getText(), civic.getText(), 1337);
        String jsonPerson = gson.toJson(person);

        System.out.println(jsonPerson);

        System.out.println(post("/persons/add", jsonPerson));
        name.clear();
        civic.clear();
    }


    public String post(String route, String string) throws Exception {


        URL url = new URL(hostAddress + route);

        //HTTP osv...
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        //Body
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(string);
        writer.flush();

        System.out.println(string);


        //Res
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }


        in.close();

        con.disconnect();
        return content.toString();


    }

    //GET WITHOUT PARAMETER
    public String get(String route) throws Exception {


        URL url = new URL(hostAddress + route);

        //HTTP osv...
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(false);

        //Res
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        System.out.println(content.toString());

        in.close();

        con.disconnect();
        return content.toString();

    }



    public void changeScene(String view) {
        Stage StageTest = Main.getPrimaryStage();
        StageTest.hide(); //Or Close


        try {
            // This Call the Stage you want
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/" + view));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("SMJournal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Could not change scene...");
        }
    }

    public void newScene(String view) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/" + view));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Add person");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            System.out.println("Could not load new Scene.");
        }
    }

    public String swedify(String string){
        String newString = string.replace("†","å");
        newString = newString.replace("”", "ö");
        newString = newString.replace("„", "ä");
        return newString;

    }

    public String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.now().format(formatter);
    }

}
