package br.com.renutrir.renutrir;

import br.com.renutrir.repositorio.RepositorioContas;
import br.com.renutrir.model.Instituicao;

public class DetalhesInstituicao {

    // Simulando o repositório ou banco de dados que contém os detalhes da instituição
     RepositorioContas repositorioInstituicoes = new RepositorioContas();

    public String getDetalhes(String nomeUsuario) {

        // Buscar a instituição pelo nome no repositório
        Instituicao instituicao = repositorioInstituicoes.buscarInstituicaoPorEmailOuUsuario(nomeUsuario);

        // Verificar se a instituição foi encontrada
        if (instituicao != null) {
            // Montar uma string com os detalhes da instituição
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Nome: ").append(instituicao.getNome()).append("\n");
            detalhes.append("Endereço: ").append(instituicao.getEndereco()).append("\n");
           // detalhes.append("Contato: ").append(instituicao.getContato()).append("\n");
            //detalhes.append("Descrição: ").append(instituicao.getDescricao()).append("\n");

            return detalhes.toString();
        } else {
            return "Detalhes da instituição não encontrados.";
        }
    }
}