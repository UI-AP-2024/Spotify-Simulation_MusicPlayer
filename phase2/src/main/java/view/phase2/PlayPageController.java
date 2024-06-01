package view.phase2;

import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import model.Audio;
import model.Database;
import model.Music;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class PlayPageController implements Initializable {

    @FXML
    private Text artistText;

    @FXML
    private ImageView cover;

    @FXML
    private ImageView forBtn;

    @FXML
    private ImageView likeBtn;

    @FXML
    private ImageView playPauseBtn;

    @FXML
    private ImageView prevBtn;

    @FXML
    private Text titleText;
    public static Audio chosenAudio;
    public static List<Audio> audioList;

    private boolean isLiked = false;

    @FXML
    void likeAct(MouseEvent event) {
        if (!isLiked) {
            String path = Paths.get("src/main/resources/view/phase2/Images/liked.png").toAbsolutePath().toString();
            Image liked = new Image(path);
            likeBtn.setImage(liked);
            AllAudiosPageController.chosenAudio.numOfLikes++;
            ListenerController.getListenerController().getListener().getLikedAudios().add(chosenAudio);
            ListenerController.getListenerController().getListener().getGenresSuggestion().add(chosenAudio.getAudioGenre());
            isLiked = true;
        } else if (isLiked) {
            String path = Paths.get("src/main/resources/view/phase2/Images/like.png").toAbsolutePath().toString();
            Image notLiked = new Image(path);
            likeBtn.setImage(notLiked);
            AllAudiosPageController.chosenAudio.numOfLikes--;
            ListenerController.getListenerController().getListener().getLikedAudios().remove(chosenAudio);
            isLiked = false;
        }
    }


    private boolean isPlaying;

    Media music;
    static MediaPlayer mediaPlayer;
    int musicCounter = audioList.indexOf(chosenAudio);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String coverPath = Paths.get(chosenAudio.getCover()).toAbsolutePath().toString();
        Image coverOfAudio = new Image(coverPath);
        cover.setImage(coverOfAudio);
        artistText.setText(chosenAudio.getArtistName());
        titleText.setText(chosenAudio.getAudioTitle());
        isPlaying = false;
        String path = Paths.get("src/main/resources/view/phase2/Images/play1.png").toAbsolutePath().toString();
        String path1 = Paths.get("src/main/resources/view/phase2/Images/pause1.png").toAbsolutePath().toString();
        Image playImage = new Image(path);
        Image pauseImage = new Image(path1);
        playPauseBtn.setImage(playImage);
        music = new Media(chosenAudio.getAudioLink());
        mediaPlayer = new MediaPlayer(music);
        for (Audio likedAudios : ListenerController.getListenerController().getListener().getLikedAudios()) {
            if (chosenAudio.equals(likedAudios)) {
                String path2 = Paths.get("src/main/resources/view/phase2/Images/liked.png").toAbsolutePath().toString();
                Image liked = new Image(path2);
                likeBtn.setImage(liked);
                break;
            } else {
                String path3 = Paths.get("src/main/resources/view/phase2/Images/like.png").toAbsolutePath().toString();
                Image notLiked = new Image(path3);
                likeBtn.setImage(notLiked);
                break;
            }
        }
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
            if (musicCounter == audioList.size() - 1) {
                musicCounter = 0;
            } else musicCounter++;
            chosenAudio = audioList.get(musicCounter);
            mediaPlayer = new MediaPlayer(new Media(chosenAudio.getAudioLink()));
            String coverPath1 = Paths.get(chosenAudio.getCover()).toAbsolutePath().toString();
            Image cover1 = new Image(coverPath1);
            cover.setImage(cover1);
            artistText.setText(chosenAudio.getArtistName());
            titleText.setText(chosenAudio.getAudioTitle());
            mediaPlayer.play();
            for (Audio likedAudios : ListenerController.getListenerController().getListener().getLikedAudios()) {
                if (chosenAudio.equals(likedAudios)) {
                    String path2 = Paths.get("src/main/resources/view/phase2/Images/liked.png").toAbsolutePath().toString();
                    Image liked = new Image(path2);
                    likeBtn.setImage(liked);
                    break;
                } else {
                    String path3 = Paths.get("src/main/resources/view/phase2/Images/like.png").toAbsolutePath().toString();
                    Image notLiked = new Image(path3);
                    likeBtn.setImage(notLiked);
                    break;
                }
            }
            isPlaying = true;
            playPauseBtn.setImage(pauseImage);
        });
        prevBtn.setOnMouseClicked(e -> {
            mediaPlayer.pause();
            isPlaying = false;
            playPauseBtn.setImage(playImage);
            if (musicCounter == 0) {
                musicCounter = audioList.size() - 1;
            } else musicCounter--;
            chosenAudio = audioList.get(musicCounter);
            mediaPlayer = new MediaPlayer(new Media(chosenAudio.getAudioLink()));
            String coverPath1 = Paths.get(chosenAudio.getCover()).toAbsolutePath().toString();
            Image cover1 = new Image(coverPath1);
            cover.setImage(cover1);
            artistText.setText(chosenAudio.getArtistName());
            titleText.setText(chosenAudio.getAudioTitle());
            mediaPlayer.play();
            for (Audio likedAudios : ListenerController.getListenerController().getListener().getLikedAudios()) {
                if (chosenAudio.equals(likedAudios)) {
                    String path2 = Paths.get("src/main/resources/view/phase2/Images/liked.png").toAbsolutePath().toString();
                    Image liked = new Image(path2);
                    likeBtn.setImage(liked);
                    break;
                } else {
                    String path3 = Paths.get("src/main/resources/view/phase2/Images/like.png").toAbsolutePath().toString();
                    Image notLiked = new Image(path3);
                    likeBtn.setImage(notLiked);
                    break;
                }
            }
            isPlaying = true;
            playPauseBtn.setImage(pauseImage);
        });
    }
}

