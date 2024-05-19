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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageNotLoggedIn.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    public void loginAction(MouseEvent mouseEvent) {
        String text;
        try {
            UserAccountController.getUserAccountController().login(usernameTextField.getText(), passwordTextField.getText());
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageLoggedInController.fxml"));
//            Scene scene;
//            try {
//                scene = new Scene(fxmlLoader.load(), 700, 450);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            ctrlStage.setScene(scene);
        } catch (Exception e) {
            text = e.getMessage();
            ErrorController.textMsg = text;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            Scene scene;
            try {
                Stage secondStage = new Stage();
                scene = new Scene(fxmlLoader.load(), 350, 150);
                secondStage.initModality(Modality.APPLICATION_MODAL);
                secondStage.setScene(scene);
                secondStage.setTitle("Error!");
                secondStage.show();
            } catch (IOException exp) {
                throw new RuntimeException(e);
            }
        }
    }
}
