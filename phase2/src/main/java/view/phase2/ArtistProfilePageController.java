package view.phase2;

import controller.SingerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistProfilePageController implements Initializable {
    public static Stage ctrlStage;

    @FXML
    private Button ArtistsSidebar;

    @FXML
    private ListView<?> AudiosList;

    @FXML
    private ImageView HomeSideBar;

    @FXML
    private ImageView LibSidebar;

    @FXML
    private ImageView Searchsidebar;

    @FXML
    private Text artistNameText;

    @FXML
    private Button audiosSidebar;

    @FXML
    private Button backBtn;

    @FXML
    private Text bioText;

    @FXML
    private Button followBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private AnchorPane sideBar;

    @FXML
    void back(ActionEvent event) {
    }


    @FXML
    void allArtistsAct(MouseEvent event) {
        HomePageLoggedInController.allArtistsSideBar(ctrlStage);
    }

    @FXML
    void allAudiosAct(MouseEvent event) {
        allAudiosSideBar(ctrlStage);
    }

    static void allAudiosSideBar(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllAudiosPage.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void follow(ActionEvent event) {

    }

    @FXML
    void libAct(MouseEvent event) {

    }

    @FXML
    void logoutAct(ActionEvent event) {

    }

    @FXML
    void report(ActionEvent event) {

    }

    @FXML
    void searchAct(MouseEvent event) {

    }

    @FXML
    void homeSideAct(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artistNameText.setText(AllArtistsPagesController.chosenArtist.getFullName());
        bioText.setText(AllArtistsPagesController.chosenArtist.getArtistBio());
    }
}
