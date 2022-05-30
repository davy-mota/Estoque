package controller;

import model.Usuario;
import service.UsuarioService;

import java.util.ArrayList;


public class UsuarioController extends Controller{


    public String cadastrar(Usuario user){
        if(usuarioService.ler(user)){
            return "Usuário já cadastrado!!!";
        }else{
            if(usuarioService.escrever(user)) {
                return "Usuário cadastrado!!!";
            }else{
                return "Tente novamente!!!";
            }

        }

    }
    public ArrayList<Usuario> listar(){

        return usuarioService.ler();

    }

    public boolean atualizar(Usuario user) {
        return usuarioService.atualizar(user);
    }


    public boolean deletar(Usuario user){
        return usuarioService.deletar(user);
    }
}
