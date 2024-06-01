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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class dwPageController implements Initializable {
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
    private AnchorPane sideBar;

    @FXML
    private ListView<Audio> tracksList;
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

    @FXML
    void back(ActionEvent event) {
        SearchController.backMethodLogged(ctrlStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Audio> shuffledList = ListenerController.getListenerController().suggestAudios();
        Collections.shuffle(shuffledList);
        for (int i = 0; i < 3; i++) {
            tracksList.getItems().add(shuffledList.get(i));
        }
        tracksList.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> {
            if (n != null) {
                for (Audio audio : shuffledList) {
                    if (audio.equals(n)) {
                        PlayPageController.chosenAudio = audio;
                        PlayPageController.audioList = shuffledList;
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
