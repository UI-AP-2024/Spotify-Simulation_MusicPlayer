package view.phase2;

import controller.ListenerController;
import controller.SingerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Audio;
import model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistProfilePageController implements Initializable {
    public static Stage ctrlStage;

    @FXML
    private Button ArtistsSidebar;

    @FXML
    private ListView<Audio> AudiosList;

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
    private TextField reportDescription;

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
        ListenerController.getListenerController().followArtist(AllArtistsPagesController.chosenArtist.getUserName());
        SuccessMsgController.textMsg = "you're now following " + AllArtistsPagesController.chosenArtist.getFullName() + " successfully.";
        loadSuccessMsg();
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
    void report(ActionEvent event) {
        ListenerController.getListenerController().submitReport(AllArtistsPagesController.chosenArtist.getUserName(), reportDescription.getText());
        SuccessMsgController.textMsg = "report submitted. Thanks for letting us know!";
        loadSuccessMsg();
    }

    private void loadSuccessMsg() {
        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("successMsg.fxml"));
        Scene scene;
        try {
            Stage secondStage = new Stage();
            scene = new Scene(fxmlLoader2.load(), 350, 200);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
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
        artistNameText.setText(AllArtistsPagesController.chosenArtist.getFullName());
        bioText.setText(AllArtistsPagesController.chosenArtist.getArtistBio());
        for (Audio audio : Database.getDatabase().getAllAudios()) {
            if (audio != null) {
                if (audio.getArtistName().equals(AllArtistsPagesController.chosenArtist.getFullName())) {
                    AudiosList.getItems().add(audio);
                }
            }
        }
    }
}
