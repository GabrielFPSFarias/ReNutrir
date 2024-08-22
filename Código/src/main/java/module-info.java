module br.com.renutrir.renutrir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens br.com.renutrir.renutrir to javafx.graphics, javafx.fxml;
    exports br.com.renutrir.renutrir;
}
