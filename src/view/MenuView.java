package view;

import model.Usuario;

import java.util.Scanner;

public class MenuView {
    public void menu() {

        Scanner entrada = new Scanner(System.in);
        UsuarioView usuario = new UsuarioView();

        System.out.println("**************MENU*************");
        System.out.println("1-Cadastrar Usuário");
        System.out.println("2-Cadastrar Fornecedor");
        System.out.println("3-Cadastrar Tipo Pessoa");
        System.out.println("4-Cadastrar Tipo Cliente");
        System.out.println("5-Cadastrar Cliente");
        System.out.println("6-Cadastrar Produto");
        System.out.println("9-Sair");

        int opcao = entrada.nextInt();

        switch (opcao) {

            case 1:
                UsuarioView userView = new UsuarioView();
                userView.menuUsuario();
                break;
            case 9:
                System.out.println("Saindo...");
                System.exit(0);
                break;

            default:
                System.out.println("Digite uma opção válida!!!\n");
                menu();
        }


    }
}
