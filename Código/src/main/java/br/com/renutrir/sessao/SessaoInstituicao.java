package br.com.renutrir.sessao;

import br.com.renutrir.model.Instituicao;

public class SessaoInstituicao {
    private static SessaoInstituicao instancia;
    private Instituicao instituicaoLogada;

    private SessaoInstituicao() {}

    public static SessaoInstituicao getInstancia() {
        if (instancia == null) {
            instancia = new SessaoInstituicao();
        }
        return instancia;
    }

    public void setInstituicaoLogada(Instituicao instituicao) {
        this.instituicaoLogada = instituicao;
    }

    public Instituicao getInstituicaoLogada() {
        return instituicaoLogada;
    }

    public void limparSessao() {
        instituicaoLogada = null;
    }
}
