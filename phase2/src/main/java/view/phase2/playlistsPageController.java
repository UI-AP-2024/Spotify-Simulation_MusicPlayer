package view.phase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Audio;

import java.net.URL;
import java.util.ResourceBundle;

public class playlistsPageController implements Initializable {
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
    private Button logoutBtn;

    @FXML
    private Text playlistName;

    @FXML
    private AnchorPane sideBar;

    @FXML
    private ListView<Audio> tracksList;
    @FXML
    void back(ActionEvent event) {
        SearchController.backMethodLogged(ctrlStage);
    }


    @FXML
    void allArtistsAct(MouseEvent event) {
        HomePageLoggedInController.allArtistsSideBar(ctrlStage);
    }

    @FXML
    void allAudiosAct(MouseEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
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
        AllArtistsPagesController.searchActionSide(ctrlStage);
    }

    @FXML
    void homeSideAct(MouseEvent event) {
        AllArtistsPagesController.loadHomeLogged(ctrlStage);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playlistName.setText(ListenerPageController.chosenPlaylist.getPlaylistName());
        tracksList.getItems().addAll(ListenerPageController.chosenPlaylist.getPlaylistAudios());
    }
}
