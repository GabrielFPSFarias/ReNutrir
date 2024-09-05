package br.com.renutrir.renutrir;

import br.com.renutrir.servicos.ControladorIntencaoDeDoacao;
import br.com.renutrir.servicos.ControladorLogin;
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
        FXMLLoader telaInicialLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/01-tela-inicial.fxml"));
        Scene scene = new Scene(telaInicialLoader.load(), 800, 500);
        stage.setTitle("ReNutrir");
        stage.setScene(scene);
        stage.setResizable(false);
        HelloController helloController = telaInicialLoader.getController();

        //Tela 02 - Pre Cadastro
        FXMLLoader preCadastroLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/02-pre-cadastro.fxml"));
        Parent preCadastroRoot = preCadastroLoader.load();
        /*ControladorVoluntario controladorVoluntario = preCadastroLoader.getController();
        controladorVoluntario.setHelloController(helloController)*/

        //Tela 03 - Login
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource( "/br/com/renutrir/03-login.fxml"));
        Parent loginRoot = loginLoader.load();
        /*ControladorLogin controladorLogin = loginLoader.getController();
        controladorLogin.setHelloController(helloController);*/

        //Tela 04 - Menu Doador
        FXMLLoader menuDoadorLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/04-menu-doador.fxml"));
        Parent menuDoadorRoot = menuDoadorLoader.load();
        /*ControladorVoluntario controladorVoluntario = menuDoadorLoader.getController();
        controladorVoluntario.setHelloController(helloController);*/

        //Tela 05 - Intenção Doação
        FXMLLoader intencaoDoacaoLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/05-intencao-doacao.fxml"));
        Parent intencaoDoacaoRoot = intencaoDoacaoLoader.load();
        /*ControladorVoluntario controladorVoluntario = intencaoDoacaoLoader.getController();
        controladorVoluntario.setHelloController(helloController);*/

        //Tela 06 - Doações Solicitadas
        FXMLLoader doacoesSolicitadasLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/06-doacoes-solicitadas.fxml"));
        Parent doacoesSolicitadasRoot = doacoesSolicitadasLoader.load();
        /*ControladorVoluntario controladorVoluntario = doacoesSolicitadasLoader.getController();
        controladorVoluntario.setHelloController(helloController);*/

        //Tela 07 - Confirmar Doação
        FXMLLoader confirmarDoacaoLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-confirmar-doacao.fxml"));
        Parent confirmarDoacaoRoot = confirmarDoacaoLoader.load();
        /*ControladorVoluntario controladorVoluntario = confirmarDoacaoLoader.getController();
        controladorVoluntario.setHelloController(helloController);*/

        //Tela 08 - Menu Voluntário
        FXMLLoader menuVoluntarioLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/08-menu-voluntario.fxml"));
        Parent menuVoluntarioRoot = menuVoluntarioLoader.load();
        /*ControladorVoluntario controladorVoluntario = menuVoluntarioLoader.getController();
        controladorVoluntario.setHelloController(helloController);*/

        //Tela 09 - Seja Voluntário
        FXMLLoader voluntarioLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/09-seja-voluntario.fxml"));
        Parent voluntarioRoot = voluntarioLoader.load();
        ControladorVoluntario controladorVoluntario = voluntarioLoader.getController();
        controladorVoluntario.setHelloController(helloController);

        //Tela 10 - Transportes Doações
        FXMLLoader transportesDoacoesLoader = new FXMLLoader(getClass().getResource( "/br/com/renutrir/10-transportes-doacoes.fxml"));
        Parent transportesDoacesRoot = transportesDoacoesLoader.load();

        //Tela 11 - Transportes Solicitados
        FXMLLoader transportesSolicitadosLoader = new FXMLLoader(getClass().getResource( "/br/com/renutrir/11-transportes-solicitados.fxml"));
        Parent transportesSolicitadosRoot = transportesSolicitadosLoader.load();

        //Tela 12 - Confirmar Transporte
        FXMLLoader confirmarTransporteLoader = new FXMLLoader(getClass().getResource( "/br/com/renutrir/12-confirmar-transporte.fxml"));
        Parent confirmarTransporteRoot = confirmarTransporteLoader.load();

        //Tela 13 - Transportes pendentes
        FXMLLoader transportesPendentesLoader = new FXMLLoader(getClass().getResource( "/br/com/renutrir/13-transportes-pendentes.fxml"));
        Parent transportesPendentesRoot = transportesPendentesLoader.load();

        //Tela 14 - Transporte Concluídos
        FXMLLoader transportesConcluidosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/14-transportes-concluidos.fxml"));
        Parent transportesConcluidosRoot = transportesConcluidosLoader.load();

        //Tela 15 - Pontuações Certificados
        FXMLLoader pontuacoesCertificadosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/15-pontuacoes-certificados.fxml"));
        Parent pontuacoesCertificadosRoot = pontuacoesCertificadosLoader.load();

        //Tela 16 -
        FXMLLoader proximosEventosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/16-proximos-eventos.fxml"));
        Parent proximosEventosRoot = proximosEventosLoader.load();

        //Tela 17 -
        FXMLLoader perfilDoadorLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/17-perfil-doador.fxml"));
        Parent perfilDoadorRoot = perfilDoadorLoader.load();

        //Tela 18 -
        FXMLLoader doacoesPendentesDoadorLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/18-doacoes-pendentes-doador.fxml"));
        Parent doacoesPendentesDoadorRoot = doacoesPendentesDoadorLoader.load();

        //Tela 19 -
        FXMLLoader menuInstituicaoLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/19-menu-instituicao.fxml"));
        Parent menuInstituicaoRoot = menuInstituicaoLoader.load();

        //Tela 20 -
        FXMLLoader criarEventosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/20-criar-eventos.fxml"));
        Parent criarEventosRoot = criarEventosLoader.load();

        //Tela 21 -
        FXMLLoader historicoDoacoesLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/21-historico-doacoes.fxml"));
        Parent historicoDoacoesRoot = historicoDoacoesLoader.load();

        //Tela 22 -
        FXMLLoader solicilitarDoacoesLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-solicitar-doacoes.fxml"));
        Parent solicitarDoacoesRoot = solicilitarDoacoesLoader.load();

        //Tela 23 -
        FXMLLoader perfilInstituicaoLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/23-perfil-instituicao.fxml"));
        Parent perfilInstituicaoRoot = perfilInstituicaoLoader.load();

        //Tela 24 -
        FXMLLoader voluntariosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/24-voluntarios.fxml"));
        Parent voluntariosRoot = voluntariosLoader.load();

        //Tela 25 -
        FXMLLoader solicitarVoluntariosLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/25-solicitar-voluntarios.fxml"));
        Parent solicitarVoluntariosRoot = solicitarVoluntariosLoader.load();

        //Tela 26 -
        FXMLLoader doacoesPendentesInstituicaoLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/26-doacoes-pendentes-inst.fxml"));
        Parent doacoesPendendentesInstituicaoRoot = doacoesPendentesInstituicaoLoader.load();


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