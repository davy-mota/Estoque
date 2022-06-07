package service;

import model.TipoPessoa;
import model.Usuario;

import java.io.*;
import java.util.ArrayList;

public class TipoPessoaService {

    private final String DIR_TP_DB = "src/database/tipoPessoa.txt";
    private FileReader arquivoLeitura;
    private BufferedReader memoriaLeitura;
    private File arquivo;
    private FileWriter escreverArquivo;
    private BufferedWriter memoriaEscrita;

    public TipoPessoaService(){
        try {
            arquivoLeitura = new FileReader(DIR_TP_DB);
            memoriaLeitura = new BufferedReader(arquivoLeitura);
            arquivo = new File(DIR_TP_DB);
            escreverArquivo = new FileWriter(DIR_TP_DB);
            memoriaEscrita = new BufferedWriter(escreverArquivo);

        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
    }

    public boolean escrever(TipoPessoa tipoPessoa) {
        try{
            if(existeArquivo()){

                String linha = null;
                int contadorLinha = 1;
                while ((linha = memoriaLeitura.readLine()) != null){
                    contadorLinha += 1;
                }
                String dadoEscrever = contadorLinha + ";" + tipoPessoa.getDescricao() + "\n";
                escreverArquivo = new FileWriter(arquivo, true);
                escreverArquivo.write(dadoEscrever);
                memoriaEscrita.newLine();
                escreverArquivo.close();

                return true;
            }else{
                criaArquivo();
                escrever(tipoPessoa);
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
    public boolean ler(TipoPessoa tipoPessoa){
        try{
            if(existeArquivo()){
                String linha = null;


                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");
                    if(tipoPessoa.getDescricao().equals(linha_split[1])) {
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
    public ArrayList<TipoPessoa> ler(){
        ArrayList<TipoPessoa> tipoPessoaList = new ArrayList<>();

        try{
            if(existeArquivo()){
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");
                    TipoPessoa tipoPessoa = new TipoPessoa();
                    tipoPessoa.setId(Integer.parseInt(linha_split[0]));
                    tipoPessoa.setDescricao(linha_split[1]);
                    tipoPessoaList.add(tipoPessoa);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }

        return tipoPessoaList;
    }
    public boolean deletar(Usuario tipoPessoa){
        Boolean excluiUser = false;

        try{
            if(existeArquivo()){
                ArrayList<String> tipoPessoaListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");


                    if (!tipoPessoa.getUsername().equals(linha_split[1])){
                        tipoPessoaListGravar.add(linha);
                    }
                }

                arquivoLeitura.close();
                memoriaLeitura.close();


                escreverArquivo = new FileWriter(DIR_TP_DB);
                BufferedWriter memoriaEscrita = new BufferedWriter(escreverArquivo);

                for (String novaLinha : tipoPessoaListGravar) {
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
        Boolean atualizarTipoPessoa = false;

        try {
            if(existeArquivo()){
                ArrayList<String> tipoPessoaListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null) {
                    String[] linha_split = linha.split(";");

                    if (user.getUsername().equals(linha_split[1])) {
                        String novaLinha = linha_split[0] + ";" + linha_split[1] + ";" + user.getPassword();
                        tipoPessoaListGravar.add(novaLinha);
                        atualizarTipoPessoa = true;
                    }else{
                        tipoPessoaListGravar.add(linha);
                    }
                }
                arquivoLeitura.close();
                memoriaLeitura.close();

                escreverArquivo = new FileWriter(DIR_TP_DB);

                for (String novaLinha : tipoPessoaListGravar) {
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
        return atualizarTipoPessoa;

    }
    private boolean existeArquivo(){
        File arquivo = new File(DIR_TP_DB);
        return arquivo.exists();
    }
    private boolean criaArquivo(){
        try {
            File arquivo = new File(DIR_TP_DB);
            return arquivo.createNewFile();
        }catch (IOException e){
            System.out.println("Ocorreu um erro ao criar o arquivo!!!");
            System.out.println("O erro gerado é: " + e.getMessage());
            return false;
        }
    }
}
