package view.phase2;

import controller.UserAccountController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Database;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AllAudiosPageController implements Initializable {

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
    private GridPane gridpane;

    @FXML
    private Button logoutBtn;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane sideBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                VBox mainVBox = new VBox();
                String path = Paths.get(Database.getDatabase().getAllAudios().get(counter).getCover()).toAbsolutePath().toString();
                Image image = new Image(path);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);
                imageView.setTranslateX(25);
                imageView.setTranslateY(10);
                Label label = new Label(Database.getDatabase().getAllAudios().get(counter).getAudioTitle());
                label.setTranslateY(20);
                label.setTranslateX(25);
                counter++;
                mainVBox.getChildren().addAll(imageView, label);
                gridpane.add(mainVBox, j, i);
            }
        }
    }
}
