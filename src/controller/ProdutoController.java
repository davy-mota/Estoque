package controller;

import model.Produto;
import service.ProdutoService;

import java.util.ArrayList;

public class ProdutoController {

    public String cadastrar(Produto produto){
        if(ProdutoService.ler(produto)){
            return "Usuário já cadastrado!!!";
        }else{
            if(ProdutoService.escrever(produto)) {
                return "Usuário cadastrado!!!";
            }else{
                return "Tente novamente!!!";
            }

        }

    }
    public ArrayList<Produto> listar(){

        return ProdutoService.ler();

    }

    public boolean atualizar(Produto produto) {
        return ProdutoService.atualizar(produto);
    }


    public boolean deletar(Produto produto){
        return ProdutoService.deletar(produto);
    }
}
