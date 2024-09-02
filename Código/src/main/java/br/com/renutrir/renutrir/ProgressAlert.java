package br.com.renutrir.renutrir;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressAlert extends Application {

    private Stage stage;
    private ProgressBar progressBar;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);
        progressBar.setStyle("-fx-accent: green;");

        StackPane root = new StackPane(progressBar);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 100);
        stage.setScene(scene);
    }

    public void showProgress(double progress) {
        progressBar.setProgress(progress);
        stage.show();
    }

    public void hideProgress() {
        stage.hide();
    }
}
