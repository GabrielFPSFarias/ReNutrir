/*
package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIntencaoDoacao {

    public List<IntencaoDoacao> getIntencoes() {
        return intencoes;
    }

    private List<IntencaoDoacao> intencoes;

    public boolean removerIntencao(IntencaoDoacao intencao){
        return intencoes.remove(intencao);
    }

    public void atualizarIntencao(IntencaoDoacao intencao) {
        for (int i = 0; i < intencoes.size(); i++) {
            if (intencoes.get(i).equals(intencao)) {
                intencoes.set(i, intencao);
                break;
            }
        }
    }

    public IntencaoDoacao buscarIntencao(Doador doador, LocalDateTime data) {
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getDoador().equals(doador)) {
                if (intencao.getData().equals(data)) {
                    return intencao;
                }
            }
        }
        return null;
    }

    public ArrayList<IntencaoDoacao> intencoesPorInst(Instituicao instituicao){
        ArrayList<IntencaoDoacao> intencoes = new ArrayList<>();
        for(IntencaoDoacao intencao : this.intencoes){
            if(intencao.getInstituicao().equals(instituicao)){
                intencoes.add(intencao);
            }
        }
        return intencoes;
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes); //Retorna lista das intençoes
    }

    public RepositorioIntencaoDoacao() {
        this.intencoes = new ArrayList<>();
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void adicionarIntencao(IntencaoDoacao intencao) {
        try {
            salvarIntencaoNoArquivo(intencao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarIntencaoNoArquivo(IntencaoDoacao intencao) throws IOException {
        String caminhoArquivo = "src/dados/intencoes.dat";

        File arquivo = new File(caminhoArquivo);

        File diretorio = arquivo.getParentFile();
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        try (FileWriter writer = new FileWriter(arquivo, true)) {
            writer.write(String.format(
                    "%s,%s,%d,%s,%s,%s,%s,%n",
                    intencao.getNomeUsuarioDoador(),
                    intencao.getNomeUsuarioInstituicao(),
                    intencao.getQuantidade(),
                    intencao.getItem(),
                    intencao.getStatus(),
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    intencao.getInstituicao().getEndereco()
            ));
        }
    }


    public boolean instRecebe(Instituicao instituicao) {
        for(IntencaoDoacao intencao : this.intencoes){
            if(intencao.getInstituicao().equals(instituicao)){
                return true;
            }
        }
        return false;
    }

    private static Instituicao instituicaoSelecionada;

    public static Instituicao getInstituicaoSelecionada() {
        return instituicaoSelecionada;
    }

    public static void setInstituicaoSelecionada(Instituicao instituicao) {
        instituicaoSelecionada = instituicao;
    }
}
*/
//Repositório Novo

package br.com.renutrir.repositorio;

import br.com.renutrir.model.Doador;
import br.com.renutrir.model.Instituicao;
import br.com.renutrir.model.IntencaoDoacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioIntencaoDoacao {

    private final String arquivoIntencao = "src/dados/intencoes-de-doacao.dat";
    private List<IntencaoDoacao> intencoes;

    public RepositorioIntencaoDoacao() {
        intencoes = carregarIntencoes();

    }

    private List<IntencaoDoacao> carregarIntencoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoIntencao))) {
            return (List<IntencaoDoacao>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarIntencoes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoIntencao))) {
            oos.writeObject(intencoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarIntencao(IntencaoDoacao intencao) {
        intencoes.add(intencao);
        salvarIntencoes();
    }

    public boolean removerIntencao(IntencaoDoacao intencao) {
        boolean removido = intencoes.remove(intencao);
        if (removido) {
            salvarIntencoes();
        }
        return removido;
    }

public boolean removerIntencaoDataNome(IntencaoDoacao intencao) {
        boolean removido = false;
        Iterator<IntencaoDoacao> iterator = this.intencoes.iterator();
        while (iterator.hasNext()) {
            IntencaoDoacao intencoes = iterator.next();
            if (intencao.getData().equals(intencoes.getData()) &&
                    intencao.getNomeUsuarioDoador().equals(intencoes.getNomeUsuarioDoador())) {
                iterator.remove();
                salvarIntencoes();
                removido = true;
            }
        }
        return removido;
    }

    public void atualizarIntencao(IntencaoDoacao intencao) {
        for (int i = 0; i < intencoes.size(); i++) {
            if (intencoes.get(i).equals(intencao)) {
                intencoes.set(i, intencao);
                salvarIntencoes();
                break;
            }
        }
    }

    public IntencaoDoacao buscarIntencao(Doador doador, LocalDateTime data) {
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getDoador().equals(doador) && intencao.getData().equals(data)) {
                return intencao;
            }
        }
        return null;
    }

    public ArrayList<IntencaoDoacao> intencoesPorInst(Instituicao instituicao) {
        ArrayList<IntencaoDoacao> intencoesInst = new ArrayList<>();
        for (IntencaoDoacao intencao : intencoes) {
            if (intencao.getInstituicao().equals(instituicao)) {
                intencoesInst.add(intencao);
            }
        }
        return intencoesInst;
    }

    public List<IntencaoDoacao> listarIntencoes() {
        return new ArrayList<>(intencoes);
    }

    public boolean instRecebe(Instituicao instituicao) {
        for(IntencaoDoacao intencao : this.intencoes){
            if(intencao.getInstituicao().getNomeUsuario().equals(instituicao.getNomeUsuario())){
                return true;
            }
        }
        return false;
    }

    private static Instituicao instituicaoSelecionada;

    public static Instituicao getInstituicaoSelecionada() {
        return instituicaoSelecionada;
    }

    public static void setInstituicaoSelecionada(Instituicao instituicao) {
        instituicaoSelecionada = instituicao;
    }

    public List<IntencaoDoacao> getIntencoes() {
        return intencoes;
    }

}