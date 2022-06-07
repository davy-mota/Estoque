package service;

import controller.TipoPessoaController;
import model.TipoPessoa;
import view.MenuView;

import java.util.ArrayList;
import java.util.Scanner;

public class TipoPessoaService {

    MenuView menu = new MenuView();
    Scanner entrada = new Scanner(System.in);




    public void menuTipoPessoa() {



        System.out.println("**************MENU DO TIPO PESSOA*************");
        System.out.println("1-Cadastrar Usuário");
        System.out.println("2-Listar");
        System.out.println("3-Atualizar");
        System.out.println("4-Deletar");
        System.out.println("5-Voltar Para Menu");

        int opcao = entrada.nextInt();

        switch (opcao) {

            case 1:
                menuCadastro();
                break;

            case 2:
                menuListar();
                break;
            case 3:
                menuAtualizar();
                break;
            case 4:
                menuDeletar();
                break;
            case 5:
                menu.menu();
                break;

            default:
                System.out.println("Digite uma opção válida!!!\n");
                menuTipoPessoa();
                break;
        }
    }


    public void menuCadastro() {

        TipoPessoa tipoPessoa = new TipoPessoa();

        System.out.println("**************CADASTRO TIPO PESSOA*************");
        System.out.println("INFORME O USERNAME: ");
        tipoPessoa.setDescricao(entrada.next());
        TipoPessoaController tipoPessoaController = new TipoPessoaController();
        System.out.println(tipoPessoaController.cadastrar(tipoPessoa));
        menuTipoPessoa();


    }

    public void menuListar() {
        TipoPessoaController tipoPessoaController = new TipoPessoaController();
        ArrayList<TipoPessoa> tipoPessoaList = tipoPessoaController.listar();


        if(tipoPessoaList.isEmpty()){
            System.out.println("Não possui usuário cadastrado");
        }else{
            System.out.println("**************LISTA USUÁRIO*************\n\n");
            for (int cont = 0; cont < tipoPessoaList.size(); cont++){
                System.out.println(tipoPessoaList.get(cont).toString());
            }
        }
        menuTipoPessoa();
    }

    public void menuAtualizar() {
        TipoPessoa tipoPessoa = new TipoPessoa();
        System.out.println("**************ATUALIZAR TIPO PESSOA*************");
        System.out.println("INFORME O USERNAME: ");
        tipoPessoa.setDescricao(entrada.next());

        TipoPessoaController tipoPessoaController = new TipoPessoaController();
        if (!tipoPessoaController.atualizar(tipoPessoa)) {
            System.out.println("Tipo Pessoa não cadastrado");
        } else {
            System.out.println("CADASTRO ATUALIZADO!!!");
        }
        menuTipoPessoa();
    }
    public void menuDeletar() {
        TipoPessoa tipoPessoa = new TipoPessoa();
        System.out.println("**************EXCLUIR TIPO PESSOA*************");
        System.out.println("INFORME A DESCRIÇÃO: ");
        tipoPessoa.setDescricao(entrada.next());

        TipoPessoaController tipoPessoaController = new TipoPessoaController();
        if (!tipoPessoaController.deletar(tipoPessoa)) {
            System.out.println("Tipo Pessoa não encontrado");
        } else {
            System.out.println("CADASTRO EXCLUÍDO!!!");
        }
        menuTipoPessoa();
    }
}
