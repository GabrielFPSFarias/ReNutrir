module br.com.renutrir.renutrir {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens br.com.renutrir.javafx to javafx.fxml;
    exports br.com.renutrir.javafx;
}