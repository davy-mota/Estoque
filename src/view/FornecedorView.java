package view;

import controller.FornecedorController;
import model.Fornecedor;

import java.util.ArrayList;
import java.util.Scanner;

public class FornecedorView {

    MenuView menu = new MenuView();
    Scanner entrada = new Scanner(System.in);


    public void menuFornecedor() {


        System.out.println("**************MENU DO FORNECEDOR*************");
        System.out.println("1-Cadastrar");
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
                menuFornecedor();
                break;
        }
    }


    public void menuCadastro() {

        Fornecedor fornecedor = new Fornecedor();

        System.out.println("**************CADASTRO FORNECEDOR*************");
        System.out.println("INFORME O NOME DO FORNECEDOR: ");
        fornecedor.setFornecedorName(entrada.next());
        System.out.println("INFORME O PASSWORD: ");
        fornecedor.setCnpj(entrada.next());

        FornecedorController fornecedorControl = new FornecedorController();
        System.out.println(fornecedorControl.cadastrar(fornecedor));
        menuFornecedor();


    }

    public void menuListar() {
        FornecedorController fornecedorController = new FornecedorController();
        ArrayList<Fornecedor> fornecedorList = fornecedorController.listar();


        if (fornecedorList.isEmpty()) {
            System.out.println("Não possui fornecedor cadastrado");
        } else {
            System.out.println("**************LISTA FORNECEDOR*************\n\n");
            for (int cont = 0; cont < fornecedorList.size(); cont++) {
                System.out.println(fornecedorList.get(cont).toString());
            }
        }
        menuFornecedor();
    }

    public void menuAtualizar() {
        Fornecedor fornecedor = new Fornecedor();
        System.out.println("**************ATUALIZAR FORNECEDOR*************");
        System.out.println("INFORME O NOME DO FORNECEDOR: ");
        fornecedor.setFornecedorName(entrada.next());
        System.out.println("INFORME O CNPJ: ");
        fornecedor.setCnpj(entrada.next());

        FornecedorController fornecedorController = new FornecedorController();
        if (!fornecedorController.atualizar(fornecedor)) {
            System.out.println("Fornecedor não cadastrado");
        } else {
            System.out.println("CADASTRO ATUALIZADO!!!");
        }
        menuFornecedor();
    }

    public void menuDeletar() {
        Fornecedor fornecedor = new Fornecedor();
        System.out.println("**************EXCLUIR FORNECEDOR*************");
        System.out.println("INFORME O NOME DO FORNECEDOR: ");
        fornecedor.setFornecedorName(entrada.next());

        FornecedorController fornecedorController = new FornecedorController();
        if (!fornecedorController.deletar(fornecedor)) {
            System.out.println("Fornecedor não encontrado");
        } else {
            System.out.println("CADASTRO EXCLUÍDO!!!");
        }
        menuFornecedor();
    }
}
