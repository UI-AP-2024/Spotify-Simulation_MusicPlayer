module view.phase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens view.phase2 to javafx.fxml;
    exports view.phase2;
}