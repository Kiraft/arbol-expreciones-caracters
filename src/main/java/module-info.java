module com.example.arbolbinariofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.arbolbinariofx to javafx.fxml;
    exports com.example.arbolbinariofx;
    exports com.example.arbolbinariofx.gui.bst;
    opens com.example.arbolbinariofx.gui.bst to javafx.fxml;
}