package view.phase2;

import controller.ListenerController;
import controller.UserAccountController;
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
import javafx.stage.Stage;
import model.Artist;
import model.Playlist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListenerPageController implements Initializable {
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
    private Text addPlaylistBtn;

    @FXML
    private Button audiosSidebar;

    @FXML
    private Button backBtn;

    @FXML
    public static ListView<Artist> followingListView = new ListView<>();

    @FXML
    private Button logoutBtn;

    @FXML
    private Text nameText;

    @FXML
    private ListView<Playlist> playlistListView;

    @FXML
    private Button premiumBtn;

    @FXML
    private AnchorPane sideBar;
    @FXML
    private TextField playListNameTextField;
    @FXML
    void allArtistsAct(ActionEvent event) {
        HomePageLoggedInController.allArtistsSideBar(ctrlStage);
    }

    @FXML
    void allAudiosAct(ActionEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
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
    void logoutAct(ActionEvent event) {
        HomePageLoggedInController.logoutMethod(ctrlStage);
    }

    @FXML
    void searchAct(MouseEvent event) {
        AllArtistsPagesController.searchActionSide(ctrlStage);
    }
    @FXML
    void backAct(ActionEvent event) {
        SearchController.backMethodLogged(ctrlStage);
    }
    @FXML
    void premiumAct(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PremiumPage.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }
    @FXML
    void addPlaylistAct(MouseEvent event) {
        try {
            Playlist playlist = ListenerController.getListenerController().createPlaylist(playListNameTextField.getText());
            playListNameTextField.clear();
            SuccessMsgController.textMsg = "playlist created successfully.";
            playlistListView.getItems().add(playlist);
            ArtistProfilePageController.loadSuccessMsg();
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameText.setText(ListenerController.getListenerController().getListener().getFullName());
        for (Artist followings : ListenerController.getListenerController().getListener().getFollowings()) {
            if (followings != null) {
                followingListView.getItems().add(followings);
            } else followingListView.getItems().clear();
        }
        for (Playlist playlist : ListenerController.getListenerController().getListener().getListenerPlaylists()) {
            if (playlist != null) {
                playlistListView.getItems().add(playlist);
            } else playlistListView.getItems().clear();
        }
    }
}
