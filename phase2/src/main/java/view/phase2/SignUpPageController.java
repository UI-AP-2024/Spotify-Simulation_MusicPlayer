package view.phase2;

import controller.ListenerController;
import controller.SingerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MyException;

import java.io.IOException;
import java.time.LocalDate;

public class SignUpPageController {

    public static Stage ctrlStage;

    @FXML
    private TextField PassTextField;

    @FXML
    private TextField birthTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button signupBack;

    @FXML
    private TextField usernameTextField;

    @FXML
    void backAction(ActionEvent event) {
        backMethod(ctrlStage);
    }
    @FXML
    void signUpAct(MouseEvent event) {
        try {
            String answer = birthTextField.getText();
            String[] answers = answer.split("-");
            ListenerController.listenerSignUp(usernameTextField.getText(), PassTextField.getText(),
                    nameTextField.getText(), emailTextField.getText(), numberTextField.getText(), LocalDate.of(Integer.parseInt(answers[0]), Integer.parseInt(answers[1]), Integer.parseInt(answers[2])));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GenrePicking-page.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 450);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ctrlStage.setScene(scene);
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader;
            Scene scene;
            if (e instanceof MyException) {
                fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
                errorMethod(e, fxmlLoader);
            }
            else {
                fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage2.fxml"));
                try {
                    Stage secondStage = new Stage();
                    scene = new Scene(fxmlLoader.load(), 300, 120);
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

    static void errorMethod(Exception e, FXMLLoader fxmlLoader) {
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

    static void backMethod(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageNotLoggedIn.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

}
