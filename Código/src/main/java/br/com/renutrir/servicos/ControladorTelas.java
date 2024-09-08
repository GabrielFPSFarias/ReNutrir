package br.com.renutrir.servicos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.renutrir.renutrir.HelloController;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorTelas {
    private static ControladorTelas instance;
    private Stage primaryStage;

    private Map<String, Scene> cenas = new HashMap<>();
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void fecharTelaAtual() {
        if (stage != null) {
            stage.close();
        }
    }

    //controlador geral
    private Scene mainScene;
    private HelloController helloController;

    //controlador para todas as telas individualmente
    private Scene cadastroDoadorScene;
    private ControladorCadastro controladorCadastroDoador;

    private Scene cadastroInstituicaoScene;
    private ControladorCadastro controladorCadastroInstituicao;

    private Scene preCadastroScene;
    private ControladorCadastro controladorPreCadastro;

    private Scene loginScene;
    private ControladorLogin controladorLogin;

    private Scene menuDoadorScene;
    private ControladorPerfil controladorMenuDoador;

    private Scene intencaoDoacaoScene;
    private ControladorIntencaoDeDoacao controladorIntencaoDeDoacao;

    private Scene doacoesSolicitadasScene;
    private ControladorSolicitacaoDoacao controladorDoacoesSolicitadas;

    private Scene pixDetalhesScene;
    private ControladorTransacaoPix controladorPixDetalhes;

    private Scene pixScene;
    private ControladorTransacaoPix controladorPix;

    private Scene cartaoCreditoScene;
    private ControladorTransacaoCartao controladorCartaoCredito;

    private Scene cartaoDebitoScene;
    private ControladorTransacaoCartao controladorCartaoDebito;

    private Scene transacaoCartaoScene;
    private ControladorTransacaoCartao controladorTransacaoCartao;

    private Scene cartaoScene;
    private ControladorTransacaoCartao controladorCartao;

    private Scene alimentosScene;
    private ControladorIntencaoDeDoacao controladorAlimentos;

    private Scene bebidasScene;
    private ControladorIntencaoDeDoacao controladorBebidas;

    private Scene roupasScene;
    private ControladorIntencaoDeDoacao controladorRoupas;

    private Scene produtosLimpezaScene;
    private ControladorIntencaoDeDoacao controladorProdutosLimpeza;

    private Scene moveisScene;
    private ControladorIntencaoDeDoacao controladorMoveis;

    private Scene higienePessoalScene;
    private ControladorIntencaoDeDoacao controladorHigienePessoal;

    private Scene doacaoConcluidaScene;
    private ControladorDoacaoConcluida controladorDoacaoConcluida;

    private Scene confirmarDoacaoScene;
    private ControladorIntencaoDeDoacao controladorConfirmarDoacao;

    private Scene preVoluntarioScene;
    private ControladorVoluntario controladorPreVoluntario;

    private Scene menuVoluntarioScene;
    private ControladorVoluntario controladorMenuVoluntario;

    private Scene sejaVoluntarioScene;
    private ControladorVoluntario controladorSejaVoluntario;

    private Scene transportesDoacoesScene;
    private ControladorTransporte controladorTransportesDoacoes;

    private Scene transportesSolicitadosScene;
    private ControladorTransporte controladorTransportesSolicitados;

    private Scene confirmarTransporteScene;
    private ControladorTransporte controladorConfirmarTransporte;

    private Scene transportesPendentesScene;
    private ControladorTransporte controladorTransportesPendentes;

    private Scene transportesConcluidosScene;
    private ControladorTransporte controladorTransportesConcluidos;

    private Scene pontuacoesCertificadosScene;
    private ControladorCertificado controladorPontuacoesCertificados;

    private Scene proximosEventosScene;
    private ControladorEventos controladorProximosEventos;

    private Scene perfilDoadorScene;
    private ControladorPerfil controladorPerfilDoador;

    private Scene doacoesPendentesDoadorScene;
    private ControladorDoacoesPendentes controladorDoacoesPendentesDoador;

    private Scene menuInstituicaoScene;
    private ControladorPerfil controladorMenuInstituicao;

    private Scene detalhesEventosScene;
    private ControladorEventos controladorDetalhesEventos;

    private Scene listaEventosScene;
    private ControladorEventos controladorListaEventos;

    private Scene editarEventosScene;
    private ControladorEventos controladorEditarEventos;

    private Scene criarEventosScene;
    private ControladorEventos controladorCriarEventos;

    private Scene historicoDoacoesScene;
    private ControladorDoacaoConcluida controladorHistoricoDoacoes;

    private Scene valoresScene;
    private ControladorSolicitacaoDoacao controladorValores;

    private Scene alimentos22Scene;
    private ControladorSolicitacaoDoacao controladorAlimentos22;

    private Scene roupas22Scene;
    private ControladorSolicitacaoDoacao controladorRoupas22;

    private Scene moveis22Scene;
    private ControladorSolicitacaoDoacao controladorMoveis22;

    private Scene bebidas22Scene;
    private ControladorSolicitacaoDoacao controladorBebidas22;

    private Scene produtosLimpeza22Scene;
    private ControladorSolicitacaoDoacao controladorProdutosLimpeza22;

    private Scene higienePessoal22Scene;
    private ControladorSolicitacaoDoacao controladorHigienePessoal22;

    private Scene solicitarDoacoesScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoes;

    private Scene perfilInstituicaoScene;
    private ControladorPerfil controladorPerfilInstituicao;

    private Scene voluntariosScene;
    private ControladorVoluntario controladorVoluntarios;

    private Scene solicitarVoluntariosScene;
    private ControladorVoluntario controladorSolicitarVoluntarios;

    private Scene validarIntencaoScene;
    private ControladorIntencaoDeDoacao controladorValidarIntencao;

    private Scene doacoesPendentesInstScene;
    private ControladorDoacoesPendentes controladorDoacoesPendentesInst;

    private Scene intencaoConcluidaScene;
    private ControladorIntencaoDeDoacao controladorIntencaoConcluida;

    private Scene solicitarDoacoesGeralScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesGeral;

    private Scene solicitarDoacoesValoresScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesValores;

    private Scene solicitarDoacoesAlimentosScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesAlimentos;

    private Scene solicitarDoacoesRoupasScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesRoupas;

    private Scene solicitarDoacoesMoveisScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesMoveis;

    private Scene solicitarDoacoesBebidasScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesBebidas;

    private Scene solicitarDoacoesProdutosLimpezaScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesProdutosLimpeza;

    private Scene solicitarDoacoesHigienePessoalScene;
    private ControladorSolicitacaoDoacao controladorSolicitarDoacoesHigienePessoal;

    private Scene listaInstituicoesScene;
    private ControladorListaInstituicoes controladorListaInstituicoes;

    private ControladorTelas() {
        this.initialize();
    }

    public static ControladorTelas getInstance() {
        if (instance == null) {
            instance = new ControladorTelas();
        }
        return instance;
    }

    private void initialize() {
        try {
            //01 - Tela inicial
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane mainPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/01-tela-inicial.fxml").openStream());
            this.mainScene = new Scene(mainPane);
            this.helloController = fxmlLoader.getController();

            //02 - Pré-cadastro
            fxmlLoader = new FXMLLoader();
            Pane preCadastroPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/02-pre-cadastro.fxml").openStream());
            this.preCadastroScene = new Scene(preCadastroPane);
            this.controladorPreCadastro = fxmlLoader.getController();

            //02 - Cadastro Doador
            fxmlLoader = new FXMLLoader();
            Pane cadastroDoadorPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/02-cadastro-doador.fxml").openStream());
            this.cadastroDoadorScene = new Scene(cadastroDoadorPane);
            this.controladorCadastroDoador = fxmlLoader.getController();

            //02 - Cadastro Instituição
            fxmlLoader = new FXMLLoader();
            Pane cadastroInstituicaoPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/02-cadastro-instituicao.fxml").openStream());
            this.cadastroInstituicaoScene = new Scene(cadastroInstituicaoPane);
            this.controladorCadastroInstituicao = fxmlLoader.getController();

            //03 - Login
            fxmlLoader = new FXMLLoader();
            Pane loginPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/03-login.fxml").openStream());
            this.loginScene = new Scene(loginPane);
            this.controladorLogin = fxmlLoader.getController();

            //04 - Menu Doador
            fxmlLoader = new FXMLLoader();
            Pane menuDoadorPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/04-menu-doador.fxml").openStream());
            this.menuDoadorScene = new Scene(menuDoadorPane);
            this.controladorMenuDoador = fxmlLoader.getController();

            //05 - Intenção de Doação
            fxmlLoader = new FXMLLoader();
            Pane intencaoDoacaoPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/05-intencao-doacao.fxml").openStream());
            this.intencaoDoacaoScene = new Scene(intencaoDoacaoPane);
            this.controladorIntencaoDeDoacao = fxmlLoader.getController();

            //05.5 - Lista de Instituições
            fxmlLoader = new FXMLLoader();
            Pane listaInstituicoesPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/05.5-lista-instituicoes1.fxml").openStream());
            this.listaInstituicoesScene = new Scene(listaInstituicoesPane);
            this.controladorListaInstituicoes = fxmlLoader.getController();

            //06 - Doações Solicitadas
            fxmlLoader = new FXMLLoader();
            Pane doacoesSolicitadasPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/06-doacoes-solicitadas.fxml").openStream());
            this.doacoesSolicitadasScene = new Scene(doacoesSolicitadasPane);
            this.controladorDoacoesSolicitadas = fxmlLoader.getController();

            //07.1.1 - PIX Detalhes
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-1-1-pix-detalhes.fxml"));
            Pane pixDetalhesPane = fxmlLoader.load();
            this.pixDetalhesScene = new Scene(pixDetalhesPane);
            this.controladorPixDetalhes = fxmlLoader.getController();

            //07.1 - PIX
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-1-pix.fxml"));
            Pane pixPane = fxmlLoader.load();
            this.pixScene = new Scene(pixPane);
            this.controladorPix = fxmlLoader.getController();

            //07.2.1 - Cartão de Crédito
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-1-c-credito.fxml"));
            Pane cartaoCreditoPane = fxmlLoader.load();
            this.cartaoCreditoScene = new Scene(cartaoCreditoPane);
            this.controladorCartaoCredito = fxmlLoader.getController();

            //07.2.2 - Cartão de Débito
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-2-c-debito.fxml"));
            Pane cartaoDebitoPane = fxmlLoader.load();
            this.cartaoDebitoScene = new Scene(cartaoDebitoPane);
            this.controladorCartaoDebito = fxmlLoader.getController();

            //07.2.3 - Transação Cartão
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-3-transacao-cartao.fxml"));
            Pane transacaoCartaoPane = fxmlLoader.load();
            this.transacaoCartaoScene = new Scene(transacaoCartaoPane);
            this.controladorTransacaoCartao = fxmlLoader.getController();

            //07.2 - Cartão
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-2-cartao.fxml"));
            Pane cartaoPane = fxmlLoader.load();
            this.cartaoScene = new Scene(cartaoPane);
            this.controladorCartao = fxmlLoader.getController();

            //07.3 - Alimentos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-3-alimentos.fxml"));
            Pane alimentosPane = fxmlLoader.load();
            this.alimentosScene = new Scene(alimentosPane);
            this.controladorAlimentos = fxmlLoader.getController();

            //07.4 - Bebidas
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-4-bebidas.fxml"));
            Pane bebidasPane = fxmlLoader.load();
            this.bebidasScene = new Scene(bebidasPane);
            this.controladorBebidas = fxmlLoader.getController();

            //07.5 - Roupas
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-5-roupas.fxml"));
            Pane roupasPane = fxmlLoader.load();
            this.roupasScene = new Scene(roupasPane);
            this.controladorRoupas = fxmlLoader.getController();

            //07.6 - Produtos de Limpeza
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-6-produtos-limpeza.fxml"));
            Pane produtosLimpezaPane = fxmlLoader.load();
            this.produtosLimpezaScene = new Scene(produtosLimpezaPane);
            this.controladorProdutosLimpeza = fxmlLoader.getController();

            //07.7 - Móveis
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-7-moveis.fxml"));
            Pane moveisPane = fxmlLoader.load();
            this.moveisScene = new Scene(moveisPane);
            this.controladorMoveis = fxmlLoader.getController();

            //07.8 - Higiene Pessoal
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-8-higiene-pessoal.fxml"));
            Pane higienePessoalPane = fxmlLoader.load();
            this.higienePessoalScene = new Scene(higienePessoalPane);
            this.controladorHigienePessoal = fxmlLoader.getController();

            //07.9 - Doação Concluída (intenção)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-9-doacao-concluida-1.fxml"));
            Pane doacaoConcluida1Pane = fxmlLoader.load();
            this.intencaoConcluidaScene = new Scene(doacaoConcluida1Pane);
            this.controladorIntencaoConcluida = fxmlLoader.getController();

            //07.10 - Doação Concluída
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-10-doacao-concluida.fxml"));
            Pane doacaoConcluidaPane = fxmlLoader.load();
            this.doacaoConcluidaScene = new Scene(doacaoConcluidaPane);
            this.controladorDoacaoConcluida = fxmlLoader.getController();

            //07 - Confirmar Doação
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/07-confirmar-doacao.fxml"));
            Pane confirmarDoacaoPane = fxmlLoader.load();
            this.confirmarDoacaoScene = new Scene(confirmarDoacaoPane);
            this.controladorConfirmarDoacao = fxmlLoader.getController();

            //08 - Menu Voluntário
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/08-menu-voluntario.fxml"));
            Pane menuVoluntarioPane = fxmlLoader.load();
            this.menuVoluntarioScene = new Scene(menuVoluntarioPane);
            this.controladorMenuVoluntario = fxmlLoader.getController();

            //08.1 - Pré-Voluntário
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/08-1-pre-voluntario.fxml"));
            Pane preVoluntarioPane = fxmlLoader.load();
            this.preVoluntarioScene = new Scene(preVoluntarioPane);
            this.controladorPreVoluntario = fxmlLoader.getController();

            //09 - Seja Voluntário
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/09-seja-voluntario.fxml"));
            Pane sejaVoluntarioPane = fxmlLoader.load();
            this.sejaVoluntarioScene = new Scene(sejaVoluntarioPane);
            this.controladorSejaVoluntario = fxmlLoader.getController();

            //10 - Transportes Doações
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/10-transportes-doacoes.fxml"));
            Pane transportesDoacoesPane = fxmlLoader.load();
            this.transportesDoacoesScene = new Scene(transportesDoacoesPane);
            this.controladorTransportesDoacoes = fxmlLoader.getController();

            //11 - Transportes Solicitados
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/11-transportes-solicitados.fxml"));
            Pane transportesSolicitadosPane = fxmlLoader.load();
            this.transportesSolicitadosScene = new Scene(transportesSolicitadosPane);
            this.controladorTransportesSolicitados = fxmlLoader.getController();

            //12 - Confirmar Transporte
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/12-confirmar-transporte.fxml"));
            Pane confirmarTransportePane = fxmlLoader.load();
            this.confirmarTransporteScene = new Scene(confirmarTransportePane);
            this.controladorConfirmarTransporte = fxmlLoader.getController();

            //13 - Transportes Pendentes
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/13-transportes-pendentes.fxml"));
            Pane transportesPendentesPane = fxmlLoader.load();
            this.transportesPendentesScene = new Scene(transportesPendentesPane);
            this.controladorTransportesPendentes = fxmlLoader.getController();

            //14 - Transportes Concluídos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/14-transportes-concluidos.fxml"));
            Pane transportesConcluidosPane = fxmlLoader.load();
            this.transportesConcluidosScene = new Scene(transportesConcluidosPane);
            this.controladorTransportesConcluidos = fxmlLoader.getController();

            //15 - Pontuações Certificados
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/15-pontuacoes-certificados.fxml"));
            Pane pontuacoesCertificadosPane = fxmlLoader.load();
            this.pontuacoesCertificadosScene = new Scene(pontuacoesCertificadosPane);
            this.controladorPontuacoesCertificados = fxmlLoader.getController();

            //16 - Próximos Eventos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/16-proximos-eventos.fxml"));
            Pane proximosEventosPane = fxmlLoader.load();
            this.proximosEventosScene = new Scene(proximosEventosPane);
            this.controladorProximosEventos = fxmlLoader.getController();

            //17 - Perfil Doador
            fxmlLoader = new FXMLLoader();
            Pane perfilDoadorPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/17-perfil-doador.fxml").openStream());
            this.perfilDoadorScene = new Scene(perfilDoadorPane);
            this.controladorPerfilDoador = fxmlLoader.getController();

            //18 - Doações Pendentes Doador
            fxmlLoader = new FXMLLoader();
            Pane doacoesPendentesDoadorPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/18-doacoes-pendentes-doador.fxml").openStream());
            this.doacoesPendentesDoadorScene = new Scene(doacoesPendentesDoadorPane);
            this.controladorDoacoesPendentesDoador = fxmlLoader.getController();

            //19 - Menu Instituição
            fxmlLoader = new FXMLLoader();
            Pane menuInstituicaoPane = fxmlLoader.load(getClass().getResource("/br/com/renutrir/19-menu-instituicao.fxml").openStream());
            this.menuInstituicaoScene = new Scene(menuInstituicaoPane);
            this.controladorMenuInstituicao = fxmlLoader.getController();

            //20 - Criar Eventos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/20-criar-eventos.fxml"));
            Pane criarEventosPane = fxmlLoader.load();
            this.criarEventosScene = new Scene(criarEventosPane);
            this.controladorCriarEventos = fxmlLoader.getController();

            //20.1 - Detalhes Eventos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/20-1-detalhes-eventos.fxml"));
            Pane detalhesEventosPane = fxmlLoader.load();
            this.detalhesEventosScene = new Scene(detalhesEventosPane);
            this.controladorDetalhesEventos = fxmlLoader.getController();

            //20.2 - Lista de Eventos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/20-2-lista-eventos.fxml"));
            Pane listaEventosPane = fxmlLoader.load();
            this.listaEventosScene = new Scene(listaEventosPane);
            this.controladorListaEventos = fxmlLoader.getController();

            //20.3 - Editar Eventos
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/20-3-editar-eventos.fxml"));
            Pane editarEventosPane = fxmlLoader.load();
            this.editarEventosScene = new Scene(editarEventosPane);
            this.controladorEditarEventos = fxmlLoader.getController();

            //21 - Histórico de Doações
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/21-historico-doacoes.fxml"));
            Pane historicoDoacoesPane = fxmlLoader.load();
            this.historicoDoacoesScene = new Scene(historicoDoacoesPane);
            this.controladorHistoricoDoacoes = fxmlLoader.getController();

            //22 - Solicitar Doações
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-solicitar-doacoes.fxml"));
            Pane solicitarDoacoesGeralPane = fxmlLoader.load();
            this.solicitarDoacoesGeralScene = new Scene(solicitarDoacoesGeralPane);
            this.controladorSolicitarDoacoesGeral = fxmlLoader.getController();

            //22.1 - Solicitar Doações (Valores)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-1-valores.fxml"));
            Pane solicitarDoacoesValoresPane = fxmlLoader.load();
            this.solicitarDoacoesValoresScene = new Scene(solicitarDoacoesValoresPane);
            this.controladorSolicitarDoacoesValores = fxmlLoader.getController();

            //22.2 - Solicitar Doações (Alimentos)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-2-alimentos.fxml"));
            Pane solicitarDoacoesAlimentosPane = fxmlLoader.load();
            this.solicitarDoacoesAlimentosScene = new Scene(solicitarDoacoesAlimentosPane);
            this.controladorSolicitarDoacoesAlimentos = fxmlLoader.getController();

            //22.3 - Solicitar Doações (Roupas)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-3-roupas.fxml"));
            Pane solicitarDoacoesRoupasPane = fxmlLoader.load();
            this.solicitarDoacoesRoupasScene = new Scene(solicitarDoacoesRoupasPane);
            this.controladorSolicitarDoacoesRoupas = fxmlLoader.getController();

            //22.4 - Solicitar Doações (Móveis)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-4-moveis.fxml"));
            Pane solicitarDoacoesMoveisPane = fxmlLoader.load();
            this.solicitarDoacoesMoveisScene = new Scene(solicitarDoacoesMoveisPane);
            this.controladorSolicitarDoacoesMoveis = fxmlLoader.getController();

            //22.5 - Solicitar Doações (Bebidas)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-5-bebidas.fxml"));
            Pane solicitarDoacoesBebidasPane = fxmlLoader.load();
            this.solicitarDoacoesBebidasScene = new Scene(solicitarDoacoesBebidasPane);
            this.controladorSolicitarDoacoesBebidas = fxmlLoader.getController();

            //22.6 - Solicitar Doações (Produtos de Limpeza)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-6-prod-limpeza.fxml"));
            Pane solicitarDoacoesProdutosLimpezaPane = fxmlLoader.load();
            this.solicitarDoacoesProdutosLimpezaScene = new Scene(solicitarDoacoesProdutosLimpezaPane);
            this.controladorSolicitarDoacoesProdutosLimpeza = fxmlLoader.getController();

            //22.7 - Solicitar Doações (Higiene Pessoal)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/22-7-higiene-pessoal.fxml"));
            Pane solicitarDoacoesHigienePessoalPane = fxmlLoader.load();
            this.solicitarDoacoesHigienePessoalScene = new Scene(solicitarDoacoesHigienePessoalPane);
            this.controladorSolicitarDoacoesHigienePessoal = fxmlLoader.getController();

            //23 - Perfil Instituição
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/23-perfil-instituicao.fxml"));
            Pane perfilInstituicaoPane = fxmlLoader.load();
            this.perfilInstituicaoScene = new Scene(perfilInstituicaoPane);
            this.controladorPerfilInstituicao = fxmlLoader.getController();

            //24 - Voluntários (inst)
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/24-voluntarios.fxml"));
            Pane voluntariosPane = fxmlLoader.load();
            this.voluntariosScene = new Scene(voluntariosPane);
            this.controladorVoluntarios = fxmlLoader.getController();

            //25 - Solicitar Voluntários
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/25-solicitar-voluntarios.fxml"));
            Pane solicitarVoluntariosPane = fxmlLoader.load();
            this.solicitarVoluntariosScene = new Scene(solicitarVoluntariosPane);
            this.controladorSolicitarVoluntarios = fxmlLoader.getController();

            //26 - Doações Pendentes Instituição
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/26-doacoes-pendentes-inst.fxml"));
            Pane doacoesPendentesInstPane = fxmlLoader.load();
            this.doacoesPendentesInstScene = new Scene(doacoesPendentesInstPane);
            this.controladorDoacoesPendentesInst = fxmlLoader.getController();

            //26.1 - Validar Intenção de Doação
            fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/renutrir/26-1-validar-intencao.fxml"));
            Pane validarIntencaoPane = fxmlLoader.load();
            this.validarIntencaoScene = new Scene(validarIntencaoPane);
            this.controladorValidarIntencao = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //métodos pra acessar as cenas entre telas e controladores

    public Scene getCadastroDoadorScene() {
        return cadastroDoadorScene;
    }

    public ControladorCadastro getControladorCadastroDoador() {
        return controladorCadastroDoador;
    }

    public Scene getCadastroInstituicaoScene() {
        return cadastroInstituicaoScene;
    }

    public ControladorCadastro getControladorCadastroInstituicao() {
        return controladorCadastroInstituicao;
    }

    public Scene getPreCadastroScene() {
        return preCadastroScene;
    }

    public ControladorCadastro getControladorPreCadastro() {
        return controladorPreCadastro;
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public ControladorLogin getControladorLogin() {
        return controladorLogin;
    }

    public Scene getMenuDoadorScene() {
        return menuDoadorScene;
    }

    public ControladorPerfil getControladorMenuDoador() {
        return controladorMenuDoador;
    }

    public Scene getIntencaoDoacaoScene() {
        return intencaoDoacaoScene;
    }

    public ControladorIntencaoDeDoacao getControladorIntencaoDeDoacao() {
        return controladorIntencaoDeDoacao;
    }

    public Scene getDoacoesSolicitadasScene() {
        return doacoesSolicitadasScene;
    }

    public ControladorSolicitacaoDoacao getControladorDoacoesSolicitadas() {
        return controladorDoacoesSolicitadas;
    }

    public Scene getPixDetalhesScene() {
        return pixDetalhesScene;
    }

    public ControladorTransacaoPix getControladorPixDetalhes() {
        return controladorPixDetalhes;
    }

    public Scene getPixScene() {
        return pixScene;
    }

    public ControladorTransacaoPix getControladorPix() {
        return controladorPix;
    }

    public Scene getCartaoCreditoScene() {
        return cartaoCreditoScene;
    }

    public ControladorTransacaoCartao getControladorCartaoCredito() {
        return controladorCartaoCredito;
    }

    public Scene getCartaoDebitoScene() {
        return cartaoDebitoScene;
    }

    public ControladorTransacaoCartao getControladorCartaoDebito() {
        return controladorCartaoDebito;
    }

    public Scene getTransacaoCartaoScene() {
        return transacaoCartaoScene;
    }

    public ControladorTransacaoCartao getControladorTransacaoCartao() {
        return controladorTransacaoCartao;
    }

    public Scene getCartaoScene() {
        return cartaoScene;
    }

    public ControladorTransacaoCartao getControladorCartao() {
        return controladorCartao;
    }

    public Scene getAlimentosScene() {
        return alimentosScene;
    }

    public ControladorIntencaoDeDoacao getControladorAlimentos() {
        return controladorAlimentos;
    }

    public Scene getBebidasScene() {
        return bebidasScene;
    }

    public ControladorIntencaoDeDoacao getControladorBebidas() {
        return controladorBebidas;
    }

    public Scene getRoupasScene() {
        return roupasScene;
    }

    public ControladorIntencaoDeDoacao getControladorRoupas() {
        return controladorRoupas;
    }

    public Scene getProdutosLimpezaScene() {
        return produtosLimpezaScene;
    }

    public ControladorIntencaoDeDoacao getControladorProdutosLimpeza() {
        return controladorProdutosLimpeza;
    }

    public Scene getMoveisScene() {
        return moveisScene;
    }

    public ControladorIntencaoDeDoacao getControladorMoveis() {
        return controladorMoveis;
    }

    public Scene getHigienePessoalScene() {
        return higienePessoalScene;
    }

    public ControladorIntencaoDeDoacao getControladorHigienePessoal() {
        return controladorHigienePessoal;
    }

    public Scene getDoacaoConcluidaScene() {
        return doacaoConcluidaScene;
    }

    public ControladorDoacaoConcluida getControladorDoacaoConcluida() {
        return controladorDoacaoConcluida;
    }

    public Scene getConfirmarDoacaoScene() {
        return confirmarDoacaoScene;
    }

    public ControladorIntencaoDeDoacao getControladorConfirmarDoacao() {
        return controladorConfirmarDoacao;
    }

    public Scene getPreVoluntarioScene() {
        return preVoluntarioScene;
    }

    public ControladorVoluntario getControladorPreVoluntario() {
        return controladorPreVoluntario;
    }

    public Scene getMenuVoluntarioScene() {
        return menuVoluntarioScene;
    }

    public ControladorVoluntario getControladorMenuVoluntario() {
        return controladorMenuVoluntario;
    }

    public Scene getSejaVoluntarioScene() {
        return sejaVoluntarioScene;
    }

    public ControladorVoluntario getControladorSejaVoluntario() {
        return controladorSejaVoluntario;
    }

    public Scene getTransportesDoacoesScene() {
        return transportesDoacoesScene;
    }

    public ControladorTransporte getControladorTransportesDoacoes() {
        return controladorTransportesDoacoes;
    }

    public Scene getTransportesSolicitadosScene() {
        return transportesSolicitadosScene;
    }

    public ControladorTransporte getControladorTransportesSolicitados() {
        return controladorTransportesSolicitados;
    }

    public Scene getConfirmarTransporteScene() {
        return confirmarTransporteScene;
    }

    public ControladorTransporte getControladorConfirmarTransporte() {
        return controladorConfirmarTransporte;
    }

    public Scene getTransportesPendentesScene() {
        return transportesPendentesScene;
    }

    public ControladorTransporte getControladorTransportesPendentes() {
        return controladorTransportesPendentes;
    }

    public Scene getTransportesConcluidosScene() {
        return transportesConcluidosScene;
    }

    public ControladorTransporte getControladorTransportesConcluidos() {
        return controladorTransportesConcluidos;
    }

    public Scene getPontuacoesCertificadosScene() {
        return pontuacoesCertificadosScene;
    }

    public ControladorCertificado getControladorPontuacoesCertificados() {
        return controladorPontuacoesCertificados;
    }

    public Scene getProximosEventosScene() {
        return proximosEventosScene;
    }

    public ControladorEventos getControladorProximosEventos() {
        return controladorProximosEventos;
    }

    public Scene getPerfilDoadorScene() {
        return perfilDoadorScene;
    }

    public ControladorPerfil getControladorPerfilDoador() {
        return controladorPerfilDoador;
    }

    public Scene getDoacoesPendentesDoadorScene() {
        return doacoesPendentesDoadorScene;
    }

    public ControladorDoacoesPendentes getControladorDoacoesPendentesDoador() {
        return controladorDoacoesPendentesDoador;
    }

    public Scene getMenuInstituicaoScene() {
        return menuInstituicaoScene;
    }

    public ControladorPerfil getControladorMenuInstituicao() {
        return controladorMenuInstituicao;
    }

    public Scene getDetalhesEventosScene() {
        return detalhesEventosScene;
    }

    public ControladorEventos getControladorDetalhesEventos() {
        return controladorDetalhesEventos;
    }

    public Scene getListaEventosScene() {
        return listaEventosScene;
    }

    public ControladorEventos getControladorListaEventos() {
        return controladorListaEventos;
    }

    public Scene getEditarEventosScene() {
        return editarEventosScene;
    }

    public ControladorEventos getControladorEditarEventos() {
        return controladorEditarEventos;
    }

    public Scene getCriarEventosScene() {
        return criarEventosScene;
    }

    public ControladorEventos getControladorCriarEventos() {
        return controladorCriarEventos;
    }

    public Scene getHistoricoDoacoesScene() {
        return historicoDoacoesScene;
    }

    public ControladorDoacaoConcluida getControladorHistoricoDoacoes() {
        return controladorHistoricoDoacoes;
    }

    public Scene getValoresScene() {
        return valoresScene;
    }

    public ControladorSolicitacaoDoacao getControladorValores() {
        return controladorValores;
    }

    public Scene getAlimentos22Scene() {
        return alimentos22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorAlimentos22() {
        return controladorAlimentos22;
    }

    public Scene getRoupas22Scene() {
        return roupas22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorRoupas22() {
        return controladorRoupas22;
    }

    public Scene getMoveis22Scene() {
        return moveis22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorMoveis22() {
        return controladorMoveis22;
    }

    public Scene getBebidas22Scene() {
        return bebidas22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorBebidas22() {
        return controladorBebidas22;
    }

    public Scene getProdutosLimpeza22Scene() {
        return produtosLimpeza22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorProdutosLimpeza22() {
        return controladorProdutosLimpeza22;
    }

    public Scene getHigienePessoal22Scene() {
        return higienePessoal22Scene;
    }

    public ControladorSolicitacaoDoacao getControladorHigienePessoal22() {
        return controladorHigienePessoal22;
    }

    public Scene getSolicitarDoacoesScene() {
        return solicitarDoacoesScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoes() {
        return controladorSolicitarDoacoes;
    }

    public Scene getPerfilInstituicaoScene() {
        return perfilInstituicaoScene;
    }

    public ControladorPerfil getControladorPerfilInstituicao() {
        return controladorPerfilInstituicao;
    }

    public Scene getVoluntariosScene() {
        return voluntariosScene;
    }

    public ControladorVoluntario getControladorVoluntarios() {
        return controladorVoluntarios;
    }

    public Scene getSolicitarVoluntariosScene() {
        return solicitarVoluntariosScene;
    }

    public ControladorVoluntario getControladorSolicitarVoluntarios() {
        return controladorSolicitarVoluntarios;
    }

    public Scene getValidarIntencaoScene() {
        return validarIntencaoScene;
    }

    public ControladorIntencaoDeDoacao getControladorValidarIntencao() {
        return controladorValidarIntencao;
    }

    public Scene getDoacoesPendentesInstScene() {
        return doacoesPendentesInstScene;
    }

    public ControladorDoacoesPendentes getControladorDoacoesPendentesInst() {
        return controladorDoacoesPendentesInst;
    }

    public Scene getIntencaoConcluidaScene() {
        return intencaoConcluidaScene;
    }

    public ControladorIntencaoDeDoacao getControladorIntencaoConcluida() {
        return controladorIntencaoConcluida;
    }

    //solicitar doações

    public Scene getSolicitarDoacoesGeralScene() {
        return solicitarDoacoesGeralScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesGeral() {
        return controladorSolicitarDoacoesGeral;
    }

    public Scene getSolicitarDoacoesValoresScene() {
        return solicitarDoacoesValoresScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesValores() {
        return controladorSolicitarDoacoesValores;
    }

    public Scene getSolicitarDoacoesAlimentosScene() {
        return solicitarDoacoesAlimentosScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesAlimentos() {
        return controladorSolicitarDoacoesAlimentos;
    }

    public Scene getSolicitarDoacoesRoupasScene() {
        return solicitarDoacoesRoupasScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesRoupas() {
        return controladorSolicitarDoacoesRoupas;
    }

    public Scene getSolicitarDoacoesMoveisScene() {
        return solicitarDoacoesMoveisScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesMoveis() {
        return controladorSolicitarDoacoesMoveis;
    }

    public Scene getSolicitarDoacoesBebidasScene() {
        return solicitarDoacoesBebidasScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesBebidas() {
        return controladorSolicitarDoacoesBebidas;
    }

    public Scene getSolicitarDoacoesProdutosLimpezaScene() {
        return solicitarDoacoesProdutosLimpezaScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesProdutosLimpeza() {
        return controladorSolicitarDoacoesProdutosLimpeza;
    }

    public Scene getSolicitarDoacoesHigienePessoalScene() {
        return solicitarDoacoesHigienePessoalScene;
    }

    public ControladorSolicitacaoDoacao getControladorSolicitarDoacoesHigienePessoal() {
        return controladorSolicitarDoacoesHigienePessoal;
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void trocarTela(Scene novaCena) {
        primaryStage.setScene(novaCena);
        primaryStage.show();
    }

}
