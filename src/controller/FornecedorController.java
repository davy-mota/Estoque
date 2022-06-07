package controller;

import model.Fornecedor;

import java.util.ArrayList;


public class FornecedorController extends Controller{

        public String cadastrar(Fornecedor fornecedor){
            if(fornecedorService.ler(fornecedor)){
                return "Fornecedor já cadastrado!!!";
            }else{
                if(fornecedorService.escrever(fornecedor)) {
                    return "Fornecedor cadastrado!!!";
                }else{
                    return "Tente novamente!!!";
                }

            }

        }
        public ArrayList<Fornecedor> listar(){

            return fornecedorService.ler();

        }

        public boolean atualizar(Fornecedor fornecedor) {
            return fornecedorService.atualizar(fornecedor);
        }


        public boolean deletar(Fornecedor fornecedor){
            return fornecedorService.deletar(fornecedor);
        }
}


