package sample.controller;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Controller {

    @FXML
    Label lbl = new Label();
    @FXML
    TextField txt = new TextField();

    String hostAddress = "https://smj-backend.herokuapp.com";
    //String hostAddress = "http://localhost:8080";

    Gson gson = new Gson();

    public void postData() throws Exception {

        //Simple JSON SIMULATOR
        Person person = new Person("Sebbekung", "95nåntingjaoo", 1337);
        String jsonPerson = gson.toJson(person);

        System.out.println(jsonPerson);

        //REQUESTS
        //post("/getWordLengthFrequency", jsonPerson);
        get("/");
        //post("/addPerson", jsonPerson);
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

    //GET WITH PARAMETER
    public String get(String route, String string) throws Exception {
        URL url = new URL(hostAddress + route);

        //HTTP osv...
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        //Body
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(string);
        writer.flush();

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

}
