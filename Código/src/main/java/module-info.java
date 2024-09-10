module br.com.renutrir.renutrir {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;
    requires org.json;
    requires google.maps.services;

    opens br.com.renutrir.model to javafx.base;
    opens br.com.renutrir.servicos to javafx.fxml;
    opens br.com.renutrir.renutrir to javafx.fxml;

    exports br.com.renutrir.model;
    exports br.com.renutrir.servicos;
    exports br.com.renutrir.renutrir;
}