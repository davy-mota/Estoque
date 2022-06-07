package view;

import java.util.ArrayList;
import java.util.Scanner;
import controller.ProdutoController;
import model.Produto;


public class ProdutoView {

    MenuView menu = new MenuView();
    Scanner entrada = new Scanner(System.in);




    public void menuProduto() {



        System.out.println("**************MENU DO PRODUTO*************");
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
                menuProduto();
                break;
        }
    }


    public void menuCadastro() {

        Produto produto = new Produto();

        System.out.println("**************CADASTRO PRODUTO*************");
        System.out.println("INFORME A DESCRIÇÃO: ");
        produto.setDescricao(entrada.next());
        System.out.println("INFORME O PREÇO DE VENDA: ");
        produto.setPrecoVenda(Float.parseFloat(entrada.next()));
        System.out.println("INFORME O PREÇO DE COMPRA: ");
        produto.setPrecoCompra(Float.parseFloat(entrada.next()));
        System.out.println("INFORME A UNIDADE DE MEDIDA: ");
        produto.setUnidadeMedida(Integer.parseInt(entrada.next()));
        System.out.println("INFORME O TIPO DO PRODUTO: ");
        produto.setTipoproduto(Integer.parseInt(entrada.next()));
        System.out.println("INFORME A MARCA: ");
        produto.setMarca((entrada.next()));

        ProdutoController produtoController = new ProdutoController();
        System.out.println(produtoController.cadastrar(produto));
        menuProduto();


    }

    public void menuListar() {
        ProdutoController produtoController = new ProdutoController();
        ArrayList<Produto> produtoList = produtoController.listar();


        if(produtoList.isEmpty()){
            System.out.println("Não possui produto cadastrado");
        }else{
            System.out.println("**************LISTA PRODUTO*************\n\n");
            for (int cont = 0; cont < produtoList.size(); cont++){
                System.out.println(produtoList.get(cont).toString());
            }
        }
        menuProduto();
    }

    public void menuAtualizar() {
        Produto produto = new Produto();
        System.out.println("**************ATUALIZAR PRODUTO*************");
        System.out.println("INFORME A DESCRIÇÃO: ");
        produto.setDescricao(entrada.next());
        System.out.println("INFORME O PREÇO DE VENDA: ");
        produto.setPrecoVenda(entrada.nextFloat());


        ProdutoController produtoController = new ProdutoController();
        if (!produtoController.atualizar(produto)) {
            System.out.println("Produto não cadastrado");
        } else {
            System.out.println("CADASTRO ATUALIZADO!!!");
        }
        menuProduto();
    }
    public void menuDeletar() {
        Produto produto = new Produto();
        System.out.println("**************EXCLUIR PRODUTO*************");
        System.out.println("INFORME A DESCRIÇÃO: ");
        produto.setDescricao(entrada.next());

        ProdutoController produtoController = new ProdutoController();
        if (!produtoController.deletar(produto)) {
            System.out.println("Produto não encontrado");
        } else {
            System.out.println("CADASTRO EXCLUÍDO!!!");
        }
        menuProduto();
    }
}
