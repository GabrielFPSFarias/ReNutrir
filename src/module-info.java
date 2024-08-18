module br.com.renutrir.renutrirjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.renutrir.renutrirjavafx to javafx.fxml;
    exports br.com.renutrir.renutrirjavafx;
}