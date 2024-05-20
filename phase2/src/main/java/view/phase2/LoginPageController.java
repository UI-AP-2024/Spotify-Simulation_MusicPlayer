package view.phase2;

import controller.UserAccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController {
    public static Stage ctrlStage;
    @FXML
    private Button loginBack;

    @FXML
    private ImageView loginButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void backAction(ActionEvent event) {
        SignUpPageController.backMethod(ctrlStage);
    }

    public void loginAction(MouseEvent mouseEvent) {
        String text;
        try {
            UserAccountController.getUserAccountController().login(usernameTextField.getText(), passwordTextField.getText());
            AllArtistsPagesController.loadHomeLogged(ctrlStage);
        } catch (Exception e) {
            text = e.getMessage();
            ErrorController.textMsg = text;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            Scene scene;
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }
}
