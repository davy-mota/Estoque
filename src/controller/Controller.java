package controller;

import service.UsuarioService;

public abstract class Controller {

    public UsuarioService usuarioService;

    public Controller(){
        usuarioService = new UsuarioService();
    }
}
