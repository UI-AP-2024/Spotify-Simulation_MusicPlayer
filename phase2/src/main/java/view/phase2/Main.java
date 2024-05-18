package view.phase2;

import controller.AdminController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Admin;

import java.time.LocalDate;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi there :)");
        // comment
        AdminController adminController = new AdminController();
        adminController.setAdmin(Admin.getAdmin("admin01", "adminPass", "Admin", "admin01@gmail.com","09121112233",LocalDate.of(2005,7,10)));
//        view.phase2.View.getView().showMenu();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePageNotLoggedIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        stage.setTitle("view.phase2.Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
