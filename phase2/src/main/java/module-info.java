module view.phase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens view.phase2 to javafx.fxml;
    exports view.phase2;
}