package controller;

import model.TipoPessoa;
import service.TipoPessoaService;

import java.util.ArrayList;

public class TipoPessoaController {

    public String cadastrar(TipoPessoa tipoPessoa) {
        if (TipoPessoaService.ler(tipoPessoa)) {
            return "Usuário já cadastrado!!!";
        } else {
            if (TipoPessoaService.escrever(tipoPessoa)) {
                return "Usuário cadastrado!!!";
            } else {
                return "Tente novamente!!!";
            }

        }

    }

    public ArrayList<TipoPessoa> listar() {

        return TipoPessoaService.ler();

    }

    public boolean atualizar(TipoPessoa TipoPessoa) {
        return TipoPessoaService.atualizar(tipoPessoa);
    }


    public boolean deletar(TipoPessoa TipoPessoa) {
        return TipoPessoaService.deletar(tipoPessoa);
    }
}
