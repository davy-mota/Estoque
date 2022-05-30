package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.UsuarioController;
import model.Usuario;

public class UsuarioView {

    MenuView menu = new MenuView();
    Scanner entrada = new Scanner(System.in);




    public void menuUsuario() {



        System.out.println("**************MENU DO USUÁRIO*************");
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
                menuUsuario();
                break;
        }
    }


    public void menuCadastro() {

        Usuario user = new Usuario();

        System.out.println("**************CADASTRO USUÁRIO*************");
        System.out.println("INFORME O USERNAME: ");
        user.setUsername(entrada.next());
        System.out.println("INFORME O PASSWORD: ");
        user.setPassword(entrada.next());

        UsuarioController userControl = new UsuarioController();
        System.out.println(userControl.cadastrar(user));
        menuUsuario();


    }

    public void menuListar() {
        UsuarioController usuarioController = new UsuarioController();
        ArrayList<Usuario> userList = usuarioController.listar();


        if(userList.isEmpty()){
            System.out.println("Não possui usuário cadastrado");
        }else{
            System.out.println("**************LISTA USUÁRIO*************\n\n");
            for (int cont = 0; cont < userList.size(); cont++){
                System.out.println(userList.get(cont).toString());
            }
        }
        menuUsuario();
    }

    public void menuAtualizar() {
        Usuario user = new Usuario();
        System.out.println("**************ATUALIZAR USUÁRIO*************");
        System.out.println("INFORME O USERNAME: ");
        user.setUsername(entrada.next());
        System.out.println("INFORME O PASSWORD: ");
        user.setPassword(entrada.next());

        UsuarioController usuarioController = new UsuarioController();
        if (!usuarioController.atualizar(user)) {
            System.out.println("Usuário não cadastrado");
        } else {
            System.out.println("CADASTRO ATUALIZADO!!!");
        }
        menuUsuario();
    }
    public void menuDeletar() {
        Usuario user = new Usuario();
        System.out.println("**************EXCLUIR USUÁRIO*************");
        System.out.println("INFORME O USERNAME: ");
        user.setUsername(entrada.next());

        UsuarioController usuarioController = new UsuarioController();
        if (!usuarioController.deletar(user)) {
            System.out.println("Usuário não encontrado");
        } else {
            System.out.println("CADASTRO EXCLUÍDO!!!");
        }
        menuUsuario();
    }
}
