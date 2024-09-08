package br.com.renutrir.renutrir;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalhesInstituicao {

    public static void showDetalhes(String instituicaoNome) {

        Stage detalhesStage = new Stage();
        detalhesStage.setTitle("Detalhes da Instituição");

        Label detalhesLabel = new Label("Informações sobre " + instituicaoNome + ":\n\nAqui vão os detalhes da instituição.");

        VBox layout = new VBox(10, detalhesLabel);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 350, 150);
        detalhesStage.setScene(scene);
        detalhesStage.show();
    }
}