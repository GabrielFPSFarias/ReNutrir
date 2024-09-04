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

    public void showProgress() {
        stage.show();
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                double progress = i / 100.0;
                Platform.runLater(() -> progressBar.setProgress(progress));

                try {
                    Thread.sleep(10); // Atualiza o progresso a cada 10 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> stage.hide());
        }).start();
    }

    public void hideProgress() {
        stage.hide();
    }
}
