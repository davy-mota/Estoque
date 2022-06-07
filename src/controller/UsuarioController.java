package controller;

import model.Fornecedor;
import model.Usuario;
import service.UsuarioService;

import java.util.ArrayList;


public class UsuarioController extends Controller {


    public String cadastrar(Usuario user) {
        if (UsuarioService.ler(user)) {
            return "Usuário já cadastrado!!!";
        } else {
            if (UsuarioService.escrever(user)) {
                return "Usuário cadastrado!!!";
            } else {
                return "Tente novamente!!!";
            }

        }

    }

    public ArrayList<Usuario> listar() {

        return UsuarioService.ler();

    }

    public boolean atualizar(Usuario usuario) {
        return usuarioService.atualizar(usuario);
    }


    public boolean deletar(Usuario usuario){
        return usuarioService.deletar(usuario);
    }
}