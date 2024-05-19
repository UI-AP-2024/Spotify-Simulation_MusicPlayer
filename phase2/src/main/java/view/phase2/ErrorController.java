package view.phase2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {

    @FXML
    private Text errorText;
    public static String textMsg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setText(textMsg);
    }
}
