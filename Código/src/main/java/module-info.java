module br.com.renutrir.renutrir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens br.com.renutrir.renutrir to javafx.graphics, javafx.fxml;
    opens br.com.renutrir.servicos to javafx.graphics, javafx.fxml;
    exports br.com.renutrir.servicos to javafx.graphics, javafx.fxml;
    exports br.com.renutrir.renutrir;
}

