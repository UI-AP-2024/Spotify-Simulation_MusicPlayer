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
import model.Audio;
import model.Database;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AllAudiosPageController implements Initializable {
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
    void allArtistsAct(MouseEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
    }

    @FXML
    void backAct(MouseEvent event) {
        SearchController.backMethodLogged(ctrlStage);
    }

    @FXML
    void homeAct(MouseEvent event) {
        AllArtistsPagesController.loadHomeLogged(ctrlStage);
    }

    @FXML
    void libAct(MouseEvent event) {
        HomePageLoggedInController.libraryMethod(ctrlStage);
    }

    @FXML
    void logOutAct(MouseEvent event) {
        HomePageLoggedInController.logoutMethod(ctrlStage);
    }

    @FXML
    void searchAct(MouseEvent event) {
        AllArtistsPagesController.searchActionSide(ctrlStage);
    }

    public static Audio chosenAudio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                VBox mainVBox = new VBox();
                String path = Paths.get(Database.getDatabase().getAllAudios().get(counter).getCover()).toAbsolutePath().toString();
                Image image = new Image(path);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);
                imageView.setTranslateX(25);
                imageView.setTranslateY(10);
                Label label = new Label(Database.getDatabase().getAllAudios().get(counter).getAudioTitle());
                label.setTranslateY(20);
                label.setTranslateX(25);
                int finalCounter = counter;
                mainVBox.setOnMouseClicked(event -> {
                    chosenAudio = Database.getDatabase().getAllAudios().get(finalCounter);
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Audio-page.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 600, 470);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ctrlStage.setScene(scene);
                    ctrlStage.show();
                });
                counter++;
                mainVBox.getChildren().addAll(imageView, label);
                gridpane.add(mainVBox, j, i);
            }
        }
    }
}
