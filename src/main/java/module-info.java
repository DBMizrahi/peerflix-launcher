module co.mizrahi.launcher {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens co.mizrahi.launcher to javafx.fxml;
    exports co.mizrahi.launcher;
    exports co.mizrahi.launcher.constants;
}
