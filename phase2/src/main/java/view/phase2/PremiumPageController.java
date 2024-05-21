package view.phase2;

import controller.ListenerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PremiumSubscription;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PremiumPageController implements Initializable {
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
    private Text creditText;

    @FXML
    private Button get180;

    @FXML
    private Button get30;

    @FXML
    private Button get60;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane sideBar;
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

    @FXML
    void get180Act(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(PremiumSubscription.ONE_HUNDRED_EIGHTY_DAYS);
            creditText.setText(String.valueOf(ListenerController.getListenerController().getListener().getListenerCredit()) + "$");
            SuccessMsgController.textMsg = "Premium Package purchase was successful";
            ArtistProfilePageController.loadSuccessMsg();
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }

    @FXML
    void get30Act(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(PremiumSubscription.THIRTY_DAYS);
            creditText.setText(String.valueOf(ListenerController.getListenerController().getListener().getListenerCredit()) + "$");
            SuccessMsgController.textMsg = "Premium Package purchase was successful";
            ArtistProfilePageController.loadSuccessMsg();
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }

    @FXML
    void get60Act(ActionEvent event) {
        try {
            ListenerController.getListenerController().getPremium(PremiumSubscription.SIXTY_DAYS);
            creditText.setText(String.valueOf(ListenerController.getListenerController().getListener().getListenerCredit()) + "$");
            SuccessMsgController.textMsg = "Premium Package purchase was successful";
            ArtistProfilePageController.loadSuccessMsg();
        } catch (Exception e) {
            ErrorController.textMsg = e.getMessage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("errorPage.fxml"));
            SignUpPageController.errorMethod(e, fxmlLoader);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creditText.setText(String.valueOf(ListenerController.getListenerController().getListener().getListenerCredit()) + "$");
    }
}
