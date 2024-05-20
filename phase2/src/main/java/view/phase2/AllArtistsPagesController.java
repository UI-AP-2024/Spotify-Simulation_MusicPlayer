package view.phase2;

import controller.SingerController;
import controller.UserAccountController;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Artist;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AllArtistsPagesController implements Initializable {
    public static Stage ctrlStage;

    @FXML
    private Button ArtistsSidebar;

    @FXML
    private ImageView HomeSideBar;

    @FXML
    private ImageView LibSidebar;

    @FXML
    private ImageView Searchsidebar;

    @FXML
    private Button audiosSidebar;

    @FXML
    private Button backBtn;

    @FXML
    private GridPane gridpane;

    @FXML
    private Button logoutBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane sideBar;
    @FXML
    void AudiosAct(MouseEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
    }

    @FXML
    void backAct(ActionEvent event) {
        SearchController.backMethodLogged(ctrlStage);
    }

    @FXML
    void homeAct(MouseEvent event) {
        loadHomeLogged(ctrlStage);
    }

    static void loadHomeLogged(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageLoggedIn.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void libAct(MouseEvent event) {
        HomePageLoggedInController.libraryMethod(ctrlStage);
    }

    @FXML
    void logoutAct(ActionEvent event) {
        HomePageLoggedInController.logoutMethod(ctrlStage);
    }

    @FXML
    void searchAct(MouseEvent event) {
        searchActionSide(ctrlStage);
    }

    static void searchActionSide(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Search.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    public static Artist chosenArtist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                VBox mainVBox = new VBox();
                Text name = new Text(UserAccountController.getUserAccountController().showAllArtists().get(counter).getFullName());
                name.setTranslateX(50);
                name.setFill(Color.WHITE);
                name.setStyle("-fx-font-size: 24");
                int finalCounter = counter;
                mainVBox.setOnMouseClicked(event -> {
                    chosenArtist = UserAccountController.getUserAccountController().showAllArtists().get(finalCounter);
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ArtistProfilePage.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 738, 470);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ctrlStage.setScene(scene);
                    ctrlStage.show();
                });
                counter++;
                mainVBox.getChildren().addAll(name);
                gridpane.add(mainVBox, j, i);
            }
        }
    }
}
