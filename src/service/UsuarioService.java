package service;

import model.Usuario;

import java.io.*;
import java.util.ArrayList;

public class UsuarioService {

    private final String DIR_USER_DB = "src/database/usuario.txt";
    private FileReader arquivoLeitura;
    private BufferedReader memoriaLeitura;
    private File arquivo;
    private FileWriter escreverArquivo;
    private BufferedWriter memoriaEscrita;

    public UsuarioService(){
        try {
            arquivoLeitura = new FileReader(DIR_USER_DB);
            memoriaLeitura = new BufferedReader(arquivoLeitura);
            arquivo = new File(DIR_USER_DB);
            escreverArquivo = new FileWriter(DIR_USER_DB);
            memoriaEscrita = new BufferedWriter(escreverArquivo);

        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
    }

    public boolean escrever(Usuario user) {
        try{
            if(existeArquivo()){

                String linha = null;
                int contadorLinha = 1;
                while ((linha = memoriaLeitura.readLine()) != null){
                    contadorLinha += 1;
                }
                String dadoEscrever = contadorLinha + ";" + user.getUsername() + ";" + user.getPassword() + "\n";
                escreverArquivo = new FileWriter(arquivo, true);
                escreverArquivo.write(dadoEscrever);
                memoriaEscrita.newLine();
                escreverArquivo.close();

                return true;
            }else{
                criaArquivo();
                escrever(user);
            }

        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return false;
    }
    public boolean ler(Usuario user){
        try{
            if(existeArquivo()){
                String linha = null;


                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");
                    if(user.getUsername().equals(linha_split[1])) {
                        return true;
                    }
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return false;
    }
    public ArrayList<Usuario> ler(){
        ArrayList<Usuario> userList = new ArrayList<>();

        try{
            if(existeArquivo()){
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");
                    Usuario user = new Usuario();
                    user.setId(Integer.parseInt(linha_split[0]));
                    user.setUsername(linha_split[1]);
                    user.setPassword(linha_split[2]);
                    userList.add(user);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }

        return userList;
    }
    public boolean deletar(Usuario user){
        Boolean excluiUser = false;

        try{
            if(existeArquivo()){
                ArrayList<String> userListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");


                    if (!user.getUsername().equals(linha_split[1])){
                        userListGravar.add(linha);
                    }
                }

                arquivoLeitura.close();
                memoriaLeitura.close();


                escreverArquivo = new FileWriter(DIR_USER_DB);
                BufferedWriter memoriaEscrita = new BufferedWriter(escreverArquivo);

                for (String novaLinha : userListGravar) {
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }
                memoriaEscrita.close();
                return true;
            }else{
                return false;
            }

        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }


        return excluiUser;
    }

    public boolean atualizar(Usuario user){
        Boolean atualizarUser = false;

        try {
            if(existeArquivo()){
                ArrayList<String> userListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null) {
                    String[] linha_split = linha.split(";");

                    if (user.getUsername().equals(linha_split[1])) {
                        String novaLinha = linha_split[0] + ";" + linha_split[1] + ";" + user.getPassword();
                        userListGravar.add(novaLinha);
                        atualizarUser = true;
                    }else{
                        userListGravar.add(linha);
                    }
                }
                arquivoLeitura.close();
                memoriaLeitura.close();

                escreverArquivo = new FileWriter(DIR_USER_DB);

                for (String novaLinha : userListGravar) {
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }
                memoriaEscrita.close();
            }else {
                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return atualizarUser;

    }
    private boolean existeArquivo(){
        File arquivo = new File(DIR_USER_DB);
        return arquivo.exists();
    }
    private boolean criaArquivo(){
        try {
            File arquivo = new File(DIR_USER_DB);
            return arquivo.createNewFile();
        }catch (IOException e){
            System.out.println("Ocorreu um erro ao criar o arquivo!!!");
            System.out.println("O erro gerado é: " + e.getMessage());
            return false;
        }
    }
}
