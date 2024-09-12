package br.com.renutrir.servicos;

import br.com.renutrir.model.Endereco;
import br.com.renutrir.repositorio.RepositorioInstituicao;
import  br.com.renutrir.model.Instituicao;

public class ResultadoDistancia {

    private String enderecoInstituicao;
    private long distanciaEmMetros;

    public ResultadoDistancia(String enderecoInstituicao, long distanciaEmMetros) {
        this.enderecoInstituicao = enderecoInstituicao;
        this.distanciaEmMetros = distanciaEmMetros;
    }



    public String getEnderecoInstituicao() {
        return enderecoInstituicao;
    }

    public long getDistanciaEmMetros() {
        return distanciaEmMetros;
    }

    @Override
    public String toString() {
        return "Instituição: " + enderecoInstituicao + " - Distância: " + distanciaEmMetros + " metros";
    }
}