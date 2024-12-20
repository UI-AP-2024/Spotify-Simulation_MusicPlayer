package view.phase2;

import controller.ListenerController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Audio;
import model.Playlist;

import java.io.IOException;
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
        tracksList.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
            if (n != null) {
                for (Audio audio : ListenerPageController.chosenPlaylist.getPlaylistAudios()) {
                    if (audio.equals(n)) {
                        PlayPageController.chosenAudio = audio;
                        PlayPageController.audioList = ListenerPageController.chosenPlaylist.getPlaylistAudios();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PlayPage.fxml"));
                        Scene scene = null;
                        try {
                            Stage secondStage = new Stage();
                            scene = new Scene(fxmlLoader.load(), 400, 220);
                            secondStage.initModality(Modality.APPLICATION_MODAL);
                            secondStage.setOnCloseRequest((WindowEvent w) -> {
                                PlayPageController.mediaPlayer.pause();
                            });
                            secondStage.setScene(scene);
                            secondStage.setTitle("Audio Page");
                            secondStage.show();
                        } catch (IOException exp) {
                            throw new RuntimeException(exp);
                        }
                    }
                }
            }
        });
    }
}
