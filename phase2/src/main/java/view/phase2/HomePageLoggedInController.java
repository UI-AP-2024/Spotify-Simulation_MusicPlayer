package view.phase2;

import controller.ListenerController;
import controller.UserAccountController;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class HomePageLoggedInController implements Initializable {
    public static Stage ctrlStage;

    @FXML
    private Button ArtistsSidebar;

    @FXML
    private ImageView HomeSideBar;

    @FXML
    private ImageView LibSidebar;

    @FXML
    private ImageView dw;

    @FXML
    private ImageView Searchsidebar;

    @FXML
    private Button audiosSidebar;

    @FXML
    private GridPane gridpane;

    @FXML
    private Button logoutBtn;

    @FXML
    private ScrollPane scrollPaneHome;

    @FXML
    private AnchorPane sideBar;

    @FXML
    void dwAct(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dwPage.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }
    @FXML
    void allArtists(ActionEvent event) {
        allArtistsSideBar(ctrlStage);
    }

    static void allArtistsSideBar(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AllArtists.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void allAudios(ActionEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
    }

    @FXML
    void lib(MouseEvent event) {
        libraryMethod(ctrlStage);
    }

    static void libraryMethod(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ListenerPage.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 736, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void logout(ActionEvent event) {
        logoutMethod(ctrlStage);
    }

    static void logoutMethod(Stage ctrlStage) {
        UserAccountController.getUserAccountController().logout();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageNotLoggedIn.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void srch(MouseEvent event) {
        AllArtistsPagesController.searchActionSide(ctrlStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                VBox mainVBox = new VBox();
                Audio audio = ListenerController.getListenerController().suggestAudios().get(counter);
                String path = Paths.get(audio.getCover()).toAbsolutePath().toString();
                Image image = new Image(path);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);
                imageView.setTranslateX(25);
                imageView.setTranslateY(10);
                Label label = new Label(audio.getAudioTitle());
                label.setTranslateY(20);
                label.setTranslateX(25);
                counter++;
                mainVBox.getChildren().addAll(imageView, label);
                gridpane.add(mainVBox, j, i);
            }
        }
    }
}
