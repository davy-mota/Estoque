package controller;

import service.*;

public abstract class Controller {

    public UsuarioService usuarioService;
    public FornecedorService fornecedorService;
    public ProdutoService produtoService;
    public TipoPessoaService tipoPessoaService;
    public TipoProdutoService tipoProdutoService;

    public Controller(){
        usuarioService = new UsuarioService();
        fornecedorService = new FornecedorService();
        produtoService = new ProdutoService();
        tipoPessoaService = new TipoPessoaService();
        tipoProdutoService = new TipoProdutoService();
    }

}
