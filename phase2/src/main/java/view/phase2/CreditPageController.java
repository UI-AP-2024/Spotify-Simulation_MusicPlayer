package view.phase2;

import controller.ListenerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class CreditPageController {
    public static Stage ctrlStage;

    @FXML
    private Button backBtn;

    @FXML
    private TextField creditTextField;

    @FXML
    private Button payBtn;

    @FXML
    private TextField cvvText;

    @FXML
    private TextField dateText;

    @FXML
    private TextField cardText;

    @FXML
    private Button pinBtn;

    @FXML
    private TextField pinText;
    private long pin;

    @FXML
    void pinAct(ActionEvent event) {
        Random random = new Random();
        pin = random.nextInt(900000) + 100000;
        System.out.println("Your 6-digit PIN : " + pin);
    }

    @FXML
    void backAct(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PremiumPage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void payAct(ActionEvent event) {
        if (cardText.getText().length() != 0 && dateText.getText().length() != 0 && cvvText.getText().length() != 0
                && pinText.getText().length() != 0 && Long.parseLong(pinText.getText()) == pin) {
            try {
                ListenerController.getListenerController().getListener().setListenerCredit(Double.parseDouble(creditTextField.getText()) + ListenerController.getListenerController().getListener().getListenerCredit());
                SuccessMsgController.textMsg = "Payment was successful";
                ArtistProfilePageController.loadSuccessMsg();
            } catch (NumberFormatException e) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage2.fxml"));
                try {
                    Stage secondStage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 300, 120);
                    secondStage.initModality(Modality.APPLICATION_MODAL);
                    secondStage.setScene(scene);
                    secondStage.setTitle("Error!");
                    secondStage.show();
                } catch (IOException exp) {
                    throw new RuntimeException(exp);
                }
            }

        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage2.fxml"));
            try {
                Stage secondStage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 300, 120);
                secondStage.initModality(Modality.APPLICATION_MODAL);
                secondStage.setScene(scene);
                secondStage.setTitle("Error!");
                secondStage.show();
            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
        }
    }

}

