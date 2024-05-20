package view.phase2;

import controller.ListenerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class SearchController {
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
    private ListView<Object> resultList;

    @FXML
    private ImageView searchBtn;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane sideBar;

    @FXML
    void allArtists(ActionEvent event) {
        HomePageLoggedInController.allArtistsSideBar(ctrlStage);
    }

    @FXML
    void allAudios(ActionEvent event) {
        ArtistProfilePageController.allAudiosSideBar(ctrlStage);
    }

    @FXML
    void backAct(ActionEvent event) {
        backMethodLogged(ctrlStage);
    }

    static void backMethodLogged(Stage ctrlStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageLoggedIn.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 450);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ctrlStage.setScene(scene);
    }

    @FXML
    void homeSide(MouseEvent event) {
        AllArtistsPagesController.loadHomeLogged(ctrlStage);
    }

    @FXML
    void lib(MouseEvent event) {
        HomePageLoggedInController.libraryMethod(ctrlStage);
    }

    @FXML
    void logout(ActionEvent event) {
        HomePageLoggedInController.logoutMethod(ctrlStage);
    }

    @FXML
    void searchAct(MouseEvent event) {
        if (ListenerController.getListenerController().search(searchTextField.getText()).size() != 0) {
            for (int i = 0; i < ListenerController.getListenerController().search(searchTextField.getText()).size(); i++) {
                resultList.getItems().clear();
                resultList.getItems().add(ListenerController.getListenerController().search(searchTextField.getText()));

            }
        }
        else resultList.getItems().clear();
    }

}
