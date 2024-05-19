package view.phase2;

import controller.ListenerController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Genre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GenrePickingController {
    public static Stage ctrlStage;

    @FXML
    private Button applyBtn;

    @FXML
    private Rectangle countryBtn;

    @FXML
    private Button genresBack;

    @FXML
    private Rectangle hiphopBtn;

    @FXML
    private Rectangle historyBtn;

    @FXML
    private Rectangle interviewBtn;

    @FXML
    private Rectangle jazzBtn;

    @FXML
    private Rectangle popBtn;

    @FXML
    private Rectangle rockBtn;

    @FXML
    private Rectangle societyBtn;

    @FXML
    private Rectangle trueCrimeBtn;

    @FXML
    void backAction(ActionEvent event) {
        SignUpPageController.backMethod(ctrlStage);
    }


    private boolean isChosen = false;

    private void chooseGenre(Rectangle choice, Genre genre) {
        if (!isChosen) {
            choice.setArcHeight(130);
            choice.setArcWidth(130);
            ListenerController.getListenerController().addToFavGenres(genre);
            Set<Genre> set = new HashSet<>();
            ArrayList<Genre> unique = new ArrayList<>();
            for (Genre g : ListenerController.getListenerController().getListener().getFavGenres()) {
                if (set.add(g)) {
                    unique.add(g);
                }
            }
            ListenerController.getListenerController().getListener().getFavGenres().clear();
            ListenerController.getListenerController().getListener().getFavGenres().addAll(unique);
            isChosen = true;
        } else {
            choice.setArcHeight(0.1);
            choice.setArcWidth(0.1);
            ListenerController.getListenerController().getListener().getFavGenres().remove(genre);
            isChosen = false;
        }
    }

    @FXML
    void countryAct(MouseEvent event) {
        chooseGenre(countryBtn, Genre.COUNTRY);
    }

    @FXML
    void crimeAct(MouseEvent event) {
        chooseGenre(trueCrimeBtn, Genre.TRUE_CRIME);
    }

    @FXML
    void hiphopAct(MouseEvent event) {
        chooseGenre(hiphopBtn, Genre.HIPHOP);
    }

    @FXML
    void historyAct(MouseEvent event) {
        chooseGenre(historyBtn, Genre.HISTORY);
    }

    @FXML
    void interviewAct(MouseEvent event) {
        chooseGenre(interviewBtn, Genre.INTERVIEW);
    }

    @FXML
    void jazzAct(MouseEvent event) {
        chooseGenre(jazzBtn, Genre.JAZZ);
    }

    @FXML
    void popAct(MouseEvent event) {
        chooseGenre(popBtn, Genre.POP);
    }

    @FXML
    void rockAct(MouseEvent event) {
        chooseGenre(rockBtn, Genre.ROCK);
    }

    @FXML
    void societyAct(MouseEvent event) {
        chooseGenre(societyBtn, Genre.SOCIETY);
    }

    @FXML
    void applyAct(ActionEvent event) throws IOException {
        if (ListenerController.getListenerController().getListener().getFavGenres().size() > 3) {
            ErrorController.textMsg = "Please Choose 3 Genres or Less.";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            Scene scene;
            try {
                Stage secondStage = new Stage();
                scene = new Scene(fxmlLoader.load(), 350, 150);
                secondStage.initModality(Modality.APPLICATION_MODAL);
                secondStage.setScene(scene);
                secondStage.setTitle("Error!");
                secondStage.show();
            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
        }
        ErrorController.textMsg = "Successful";
        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("successPage.fxml"));
        Scene scene;
        try {
            Stage secondStage = new Stage();
            scene = new Scene(fxmlLoader2.load(), 300, 120);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(scene);
            secondStage.show();
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 700, 450);
        ctrlStage.setScene(scene2);
        ctrlStage.show();
    }


}
