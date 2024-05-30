package view.phase2;

import controller.ListenerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Database;
import model.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AudioPageController implements Initializable {

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
    private ImageView addBtn;

    @FXML
    private Text artistNameText;

    @FXML
    private Text audioTitleText;

    @FXML
    private Button audiosSidebar;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView coverImageView;

    @FXML
    private ImageView forBtn;

    @FXML
    private ImageView likeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text lyrics;

    @FXML
    private ImageView playPauseBtn;

    @FXML
    private TextField playlistNameText;

    @FXML
    private ImageView prevBtn;

    @FXML
    private AnchorPane sideBar;

    @FXML
    void back(ActionEvent event) {
        SearchController.backMethodLogged(ctrlStage);
        mediaPlayer.pause();
    }


    @FXML
    void allArtistsAct(MouseEvent event) {
        HomePageLoggedInController.allArtistsSideBar(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void allAudiosAct(MouseEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void libAct(MouseEvent event) {
        HomePageLoggedInController.libraryMethod(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void logoutAct(ActionEvent event) {
        HomePageLoggedInController.logoutMethod(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void searchAct(MouseEvent event) {
        AllArtistsPagesController.searchActionSide(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void homeSideAct(MouseEvent event) {
        AllArtistsPagesController.loadHomeLogged(ctrlStage);
        mediaPlayer.pause();
    }

    @FXML
    void addAct(MouseEvent event) {
        try {
            ListenerController.getListenerController().addAudioToPlaylist(playlistNameText.getText(), AllAudiosPageController.chosenAudio.getAudioID());
            playlistNameText.clear();
            SuccessMsgController.textMsg = "audio added to wanted playlist successfully.";
            ArtistProfilePageController.loadSuccessMsg();
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }

    private boolean isLiked = false;

    @FXML
    void likeAct(MouseEvent event) {
        if (!isLiked) {
            String path = Paths.get("src/main/resources/view/phase2/Images/liked.png").toAbsolutePath().toString();
            Image liked = new Image(path);
            likeBtn.setImage(liked);
            AllAudiosPageController.chosenAudio.numOfLikes++;
            ListenerController.getListenerController().getListener().getGenresSuggestion().add(AllAudiosPageController.chosenAudio.getAudioGenre());
            isLiked = true;
        } else if (isLiked) {
            String path = Paths.get("src/main/resources/view/phase2/Images/like.png").toAbsolutePath().toString();
            Image notLiked = new Image(path);
            likeBtn.setImage(notLiked);
            AllAudiosPageController.chosenAudio.numOfLikes--;
            isLiked = false;
        }
    }

    private boolean isPlaying;

    Media music;
    MediaPlayer mediaPlayer;
    int musicCounter = Database.getDatabase().getAllAudios().indexOf(AllAudiosPageController.chosenAudio);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String coverPath = Paths.get(AllAudiosPageController.chosenAudio.getCover()).toAbsolutePath().toString();
        Image cover = new Image(coverPath);
        coverImageView.setImage(cover);
        artistNameText.setText(AllAudiosPageController.chosenAudio.getArtistName());
        audioTitleText.setText(AllAudiosPageController.chosenAudio.getAudioTitle());
        lyrics.setText(((Music) AllAudiosPageController.chosenAudio).getLyrics());
        isPlaying = false;
        String path = Paths.get("src/main/resources/view/phase2/Images/play1.png").toAbsolutePath().toString();
        String path1 = Paths.get("src/main/resources/view/phase2/Images/pause1.png").toAbsolutePath().toString();
        Image playImage = new Image(path);
        Image pauseImage = new Image(path1);
        playPauseBtn.setImage(playImage);
        music = new Media(AllAudiosPageController.chosenAudio.getAudioLink());
        mediaPlayer = new MediaPlayer(music);
        playPauseBtn.setOnMouseClicked(e -> {
            if (!isPlaying) {
                playPauseBtn.setImage(pauseImage);
                mediaPlayer.play();
                isPlaying = true;
            } else if (isPlaying) {
                playPauseBtn.setImage(playImage);
                mediaPlayer.pause();
                isPlaying = false;
            }
        });
        forBtn.setOnMouseClicked(e -> {
            mediaPlayer.pause();
            isPlaying = false;
            playPauseBtn.setImage(playImage);
            if (musicCounter == Database.getDatabase().getAllAudios().size() - 1) {
                musicCounter = 0;
            } else musicCounter++;
            mediaPlayer = new MediaPlayer(new Media(Database.getDatabase().getAllAudios().get(musicCounter).getAudioLink()));
            String coverPath1 = Paths.get(Database.getDatabase().getAllAudios().get(musicCounter).getCover()).toAbsolutePath().toString();
            Image cover1 = new Image(coverPath1);
            coverImageView.setImage(cover1);
            artistNameText.setText(Database.getDatabase().getAllAudios().get(musicCounter).getArtistName());
            audioTitleText.setText(Database.getDatabase().getAllAudios().get(musicCounter).getAudioTitle());
            lyrics.setText(((Music) Database.getDatabase().getAllAudios().get(musicCounter)).getLyrics());
            mediaPlayer.play();
            isPlaying = true;
            playPauseBtn.setImage(pauseImage);
        });
        prevBtn.setOnMouseClicked(e -> {
            mediaPlayer.pause();
            isPlaying = false;
            playPauseBtn.setImage(playImage);
            if (musicCounter == 0) {
                musicCounter = Database.getDatabase().getAllAudios().size() - 1;
            } else musicCounter--;
            mediaPlayer = new MediaPlayer(new Media(Database.getDatabase().getAllAudios().get(musicCounter).getAudioLink()));
            String coverPath1 = Paths.get(Database.getDatabase().getAllAudios().get(musicCounter).getCover()).toAbsolutePath().toString();
            Image cover1 = new Image(coverPath1);
            coverImageView.setImage(cover1);
            artistNameText.setText(Database.getDatabase().getAllAudios().get(musicCounter).getArtistName());
            audioTitleText.setText(Database.getDatabase().getAllAudios().get(musicCounter).getAudioTitle());
            lyrics.setText(((Music) Database.getDatabase().getAllAudios().get(musicCounter)).getLyrics());
            mediaPlayer.play();
            isPlaying = true;
            playPauseBtn.setImage(pauseImage);
        });
    }
}
