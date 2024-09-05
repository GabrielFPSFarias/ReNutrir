package br.com.renutrir.renutrir;

import br.com.renutrir.servicos.ControladorVoluntario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javax.imageio.IIOParam;
import java.io.IOException;

public class HelloApplication extends Application {

    private Stage progressStage;
    private ProgressBar progressBar;
    private static HelloApplication instance;
    private ProgressAlert progressAlert;

    public static HelloApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;

        //Tela inicial
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/01-tela-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("ReNutrir");
        stage.setScene(scene);
        stage.setResizable(false);
        HelloController helloController = fxmlLoader.getController();

        //Tela 09 - Seja Voluntário
        FXMLLoader voluntarioLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/09-seja-voluntario.fxml"));
        Parent voluntarioRoot = voluntarioLoader.load();
        ControladorVoluntario controladorVoluntario = voluntarioLoader.getController();
        controladorVoluntario.setHelloController(helloController);

        //define a tela que começa
        stage.setScene(scene);
        stage.show();
    }


    public void showAlertComProgresso() {
        if (progressStage == null) {
            initProgressWindow();
        }
        progressStage.show();

        new Thread(() -> {
            for (double progress = 0; progress <= 1.0; progress += 0.01) {
                try {
                    Thread.sleep(30); // Atualiza a cada 30 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final double currentProgress = progress;
                Platform.runLater(() -> progressBar.setProgress(currentProgress));
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(this::hideProgress);
        }).start();
    }


    private void initProgressWindow() {
        progressStage = new Stage();
        progressStage.setTitle("Progresso da Doação");

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        StackPane root = new StackPane(progressBar);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 80);
        progressStage.setScene(scene);
    }

    public void showProgress(double progress) {
        Platform.runLater(() -> {
            if (progressStage != null) {
                progressBar.setProgress(progress);
                progressStage.show();
            }
        });
    }

    public void hideProgress() {
        Platform.runLater(() -> {
            if (progressStage != null) {
                progressStage.hide();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}



/*
package br.com.renutrir.renutrir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/01-tela-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("ReNutrir");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
*/