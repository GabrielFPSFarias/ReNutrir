package br.com.renutrir.servicos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControladorArquivo {
    private String caminho;

    public ControladorArquivo(String caminho) {
        this.caminho = caminho;
    }

    public boolean criarDiretorios() {
        File file = new File(caminho).getParentFile();
        if (file != null && !file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public boolean existe() {
        File file = new File(caminho);
        return file.exists();
    }

    public boolean criarNovoArquivo() throws IOException {
        File file = new File(caminho);
        if (!file.exists()) {
            criarDiretorios();
            return file.createNewFile();
        }
        return false;
    }

    public void escrever(String conteudo) throws IOException {
        if (!existe()) {
            criarNovoArquivo();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(conteudo);
            writer.flush();
        }
    }

    public void novaLinha() throws IOException {
        escrever(System.lineSeparator());
    }
}
