package br.com.renutrir.servicos;

import br.com.renutrir.model.Evento;
import br.com.renutrir.renutrir.HelloController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControladorEventos {

private HelloController hc;  

ControladorEventos(HelloController hc){

  this.hc = hc;
}

@FXML
    public Button eventosBotao;

@FXML
    private Button editarEventoBotao;

  public void botaoEventos(ActionEvent actionEvent) {
    realizarTrocaDeTela("/br/com/renutrir/16-proximos-eventos.fxml", "ReNutrir - Eventos");       
  }


@FXML
    private TextField endEventoField;
  
@FXML
    private TextField dataEventoField;  

@FXML
    private TextField tipoEventoField;

@FXML
    public void botaoCriarEvento() {
        realizarTrocaDeTela("/br/com/renutrir/20-criar-eventos.fxml", "ReNutrir - Criar Evento");
    }  
@FXML  
   public Button criarEventoBotao; 

@FXML
   private TextArea listaEventosArea;

@FXML
    private Button criarEventosBotao;
  
@FXML
    private TextField horarioEventoField;  

 private void limparCamposEvento() {
        nomeEventoField.clear();
        dataEventoField.clear();
        horarioEventoField.clear();
        endEventoField.clear();
        tipoEventoField.clear();
        descricaoEventoField.clear();
    }  

 @FXML
    public void botaoCriarNovoEvento() {
        realizarTrocaDeTela("/br/com/renutrir/20-1-detalhes-eventos.fxml", "ReNutrir - Criar Novo Evento");
    }  

@FXML
   private Button criarNovoEventoBotao;
  
@FXML
   private void atualizarListaEventos() {
        if (listaEventosArea == null) {
            System.err.println("Erro: listaEventosArea não foi inicializada.");
            return;
        }  
     
private void salvarEventoNoArquivo(Evento evento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eventos.txt", true))) {
            writer.write(evento.getNome() + ";" + evento.getData() + ";" + evento.getHorario() + ";" + evento.getLocal() + ";" + evento.getTipo() + ";" + evento.getDescricao());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }     

 @FXML
    private Button botaoSalvarCriarEvento;

@FXML
    void salvarCriarEventoBotao(ActionEvent event) {
        try {
            String nome = nomeEventoField.getText();
            String dataStr = dataEventoField.getText();
            String horarioStr = horarioEventoField.getText();
            String local = endEventoField.getText();
            String tipo = tipoEventoField.getText();
            String descricao = descricaoEventoField.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataStr + " " + horarioStr, formatter);

            Evento evento = new Evento(nome, dataHora.toLocalDate(), local, dataHora.toLocalTime(), tipo, descricao);
            salvarEventoNoArquivo(evento);
            limparCamposEvento();

            //evento criado com sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evento Criado");
            alert.setHeaderText(null);
            alert.setContentText("O evento '" + nome + "' foi criado com sucesso!");
            alert.showAndWait();

        } catch (DateTimeParseException e) {
            //mostrar erro de formatação na data e hr
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Formato");
            alert.setHeaderText("Formato de Data/Hora Inválido");
            alert.setContentText("Por favor, insira a data e hora no formato 'dd/MM/yyyy HH:mm'.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

   
 @FXML
    private Button listaEventosCriadosBotao;

@FXML
    void fieldEndEvento(ActionEvent event) {

    }
@FXML
    void fieldDataEvento(ActionEvent event) {

    }
@FXML
    void fieldNomeEvento(ActionEvent event) {

    }  
@FXML
    void fieldTipoEvento(ActionEvent event) {
    }  
  
@FXML
    private TextField nomeEventoField;
  
@FXML
    public void botaoListaEventosCriadosBotao(ActionEvent actionEvent) {
        realizarTrocaDeTela("/br/com/renutrir/20-2-lista-eventos.fxml","ReNutrir - Lista de Eventos");

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> atualizarListaEventos());
        pause.play();

        List<Evento> eventos = carregarEventosDoArquivo();
        Instituicao instituicaoLogada = SessaoInstituicao.getInstancia().getInstituicaoLogada();

        StringBuilder eventosTexto = new StringBuilder(); //cada evento está relacionado com a inst que o criou
        for (Evento evento : eventos) {
            if (evento.getNome().equals(instituicaoLogada.getNome())) {
                eventosTexto.append(evento.toString()).append("\n");
            }
        }
        listaEventosArea.setText(eventosTexto.toString());
    }  
  
@FXML
    private Label labelEventosLista;
@FXML
    void fieldHorarioEvento(ActionEvent event) {

    }
  
@FXML
    void botaoEditarEvento(ActionEvent event) {
        realizarTrocaDeTela("/br/com/renutrir/20-3-editar-eventos.fxml","ReNutrir - Lista de Eventos");
        String nomeEventoSelecionado = nomeEventoField.getText();

        List<Evento> eventos = carregarEventosDoArquivo();
        Evento eventoParaEditar = eventos.stream()
                .filter(e -> e.getNome().equals(nomeEventoSelecionado))
                .findFirst()
                .orElse(null);

        if (eventoParaEditar != null) {
            eventoParaEditar.setNome(nomeEventoField.getText());
            eventoParaEditar.setData(LocalDate.parse(dataEventoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            eventoParaEditar.setHorario(LocalTime.parse(horarioEventoField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            eventoParaEditar.setLocal(endEventoField.getText());
            eventoParaEditar.setTipo(tipoEventoField.getText());
            eventoParaEditar.setDescricao(descricaoEventoField.getText());

            salvarEventoNoArquivo((Evento) eventos); //salva as mudanças

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evento Editado");
            alert.setHeaderText(null);
            alert.setContentText("O evento '" + nomeEventoParaEditar + "' foi editado com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Evento não encontrado.");
            alert.showAndWait();
        }
    }  
  
@FXML
    private TextArea descricaoEventoField;
@FXML
    void fieldDescricaoEvento(ActionEvent event) {

    }
@FXML  
private String nomeEventoParaEditar;  

private List<Evento> carregarEventosDoArquivo() {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String nome = partes[0];
                LocalDate data = LocalDate.parse(partes[1]);
                String horario = partes[2];
                String local = partes[3];
                String tipo = partes[4];
                String descricao = partes[5];

                Evento evento = new Evento(nome, data, local, LocalTime.parse(horario), tipo, descricao);
                eventos.add(evento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }
  
  
private void removerEventosPassados() {
        List<Evento> eventosAtuais = carregarEventosDoArquivo();
        LocalDateTime agora = LocalDateTime.now();

        eventosAtuais.removeIf(evento -> evento.getData().atTime(evento.getHorario()).isBefore(agora));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eventos.txt"))) {
            for (Evento evento : eventosAtuais) {
                writer.write(evento.getNome() + ";" + evento.getData() + ";" + evento.getHorario() + ";" + evento.getLocal() + ";" + evento.getTipo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
