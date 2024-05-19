package view.phase2;

import controller.UserAccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class HomePageNotLoggedInController implements Initializable {
    public static Stage ctrlStage;

    @FXML
    private Button ArtistsSidebar;

    @FXML
    private ImageView HomeSideBar;

    @FXML
    private ImageView HomeSignUpBtn;

    @FXML
    private ImageView LibSidebar;

    @FXML
    private ImageView LoginBtn;

    @FXML
    private ImageView Searchsidebar;

    @FXML
    private Button audiosSidebar;

    @FXML
    private GridPane gridpane;

    @FXML
    private Button logoutBtn;

    @FXML
    private ScrollPane scrollPaneHome;

    @FXML
    private AnchorPane sideBar;

    @FXML
    void openLoginPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }

    @FXML
    void openSignupPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        ctrlStage.setScene(scene);
        ctrlStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                VBox mainVBox = new VBox();
                String path = Paths.get(UserAccountController.sortBasedOnLikes().get(counter).getCover()).toAbsolutePath().toString();
                Image image = new Image(path);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);
                imageView.setTranslateX(25);
                imageView.setTranslateY(10);
                Label label = new Label(UserAccountController.sortBasedOnLikes().get(counter).getAudioTitle());
                label.setTranslateY(20);
                label.setTranslateX(25);
                counter++;
                mainVBox.getChildren().addAll(imageView, label);
                gridpane.add(mainVBox, j, i);
            }
        }
    }
}
