package controller;

import java.util.ArrayList;

public class TipoProdutoController {
    public String cadastrar(Tipoproduto tipoproduto){
        if(tipoprodutoService.ler(Tipoproduto)){
            return "Usuário já cadastrado!!!";
        }else{
            if(TipoprodutoService.escrever(Tipoproduto)) {
                return "Usuário cadastrado!!!";
            }else{
                return "Tente novamente!!!";
            }

        }

    }
    public ArrayList<Tipoproduto> listar(){

        return tipoprodutoService.ler();

    }

    public boolean atualizar(Tipoproduto Tipoproduto) {
        return TipoprodutoService.atualizar(Tipoproduto);
    }


    public boolean deletar(Tipoproduto Tipoproduto){
        return TipoprodutoService.deletar(Tipoproduto);
    }
}
