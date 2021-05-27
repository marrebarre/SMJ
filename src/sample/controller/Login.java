package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.Account;


public class Login extends Controller {

    @FXML
    TextField usernameTxt;
    @FXML
    TextField passwordTxt;
    @FXML
    Label err;

    public void loginPressed() {

        Account account = new Account(usernameTxt.getText(), passwordTxt.getText());

        String tmp = gson.toJson(account);

        try {
            String status = post("/login", tmp);

            if (status.equals("OK")){
                changeScene("journal.fxml");
            }else{
                err.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
