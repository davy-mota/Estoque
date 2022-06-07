package service;

import model.Produto;

import java.io.*;
import java.util.ArrayList;

public class ProdutoService {

    private final String DIR_produto_DB = "src/database/produto.txt";
    private FileReader arquivoLeitura;
    private BufferedReader memoriaLeitura;
    private File arquivo;
    private FileWriter escreverArquivo;
    private BufferedWriter memoriaEscrita;

    public ProdutoService(){
        try {
            arquivoLeitura = new FileReader(DIR_produto_DB);
            memoriaLeitura = new BufferedReader(arquivoLeitura);
            arquivo = new File(DIR_produto_DB);
            escreverArquivo = new FileWriter(DIR_produto_DB);
            memoriaEscrita = new BufferedWriter(escreverArquivo);

        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
    }

    public boolean escrever(Produto produto) {
        try{
            if(existeArquivo()){

                String linha = null;
                int contadorLinha = 1;
                while ((linha = memoriaLeitura.readLine()) != null){
                    contadorLinha += 1;
                }
                String dadoEscrever = contadorLinha + ";" + produto.getDescricao() + produto.getPrecoVenda() + ";" +
                        produto.getPrecoCompra() + produto.getMarca() + produto.getUnidadeMedida() + "\n";
                escreverArquivo = new FileWriter(arquivo, true);
                escreverArquivo.write(dadoEscrever);
                memoriaEscrita.newLine();
                escreverArquivo.close();

                return true;
            }else{
                criaArquivo();
                escrever(produto);
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
    public boolean ler(Produto produto){
        try{
            if(existeArquivo()){
                String linha = null;


                while ((linha = memoriaLeitura.readLine()) != null){
                    String[] linha_split = linha.split(";");
                    if(produto.getDescricao().equals(linha_split[1])) {
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
    public ArrayList<Produto> ler(){
        ArrayList<Produto> produtoList = new ArrayList<>();

        try{
            if(existeArquivo()){
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");
                    Produto produto = new Produto();
                    produto.setCodigo(Integer.parseInt(linha_split[0]));
                    produto.setDescricao(linha_split[1]);
                    produto.setPrecoVenda(Float.parseFloat(linha_split[2]));
                    produto.setPrecoCompra(Float.parseFloat(linha_split[3]));
                    produto.setPrecoVenda(Float.parseFloat(linha_split[2]));
                    produtoList.add(produto);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }catch (Exception e){
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }

        return produtoList;
    }
    public boolean deletar(Produto produto){
        Boolean excluiproduto = false;

        try{
            if(existeArquivo()){
                ArrayList<String> produtoListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null){
                    String[] linha_split = linha.split(";");


                    if (!produto.getDescricao().equals(linha_split[1])){
                        produtoListGravar.add(linha);
                    }
                }

                arquivoLeitura.close();
                memoriaLeitura.close();


                escreverArquivo = new FileWriter(DIR_produto_DB);
                BufferedWriter memoriaEscrita = new BufferedWriter(escreverArquivo);

                for (String novaLinha : produtoListGravar) {
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


        return excluiproduto;
    }

    public boolean atualizar(Produto produto){
        Boolean atualizarproduto = false;

        try {
            if(existeArquivo()){
                ArrayList<String> produtoListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine())!=null) {
                    String[] linha_split = linha.split(";");

                    if (produto.getDescricao().equals(linha_split[1])) {
                        String novaLinha = linha_split[0] + ";" + linha_split[1] + ";" + produto.getPrecoVenda();
                        produtoListGravar.add(novaLinha);
                        atualizarproduto = true;
                    }else{
                        produtoListGravar.add(linha);
                    }
                }
                arquivoLeitura.close();
                memoriaLeitura.close();

                escreverArquivo = new FileWriter(DIR_produto_DB);

                for (String novaLinha : produtoListGravar) {
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
        return atualizarproduto;

    }
    private boolean existeArquivo(){
        File arquivo = new File(DIR_produto_DB);
        return arquivo.exists();
    }
    private boolean criaArquivo(){
        try {
            File arquivo = new File(DIR_produto_DB);
            return arquivo.createNewFile();
        }catch (IOException e){
            System.out.println("Ocorreu um erro ao criar o arquivo!!!");
            System.out.println("O erro gerado é: " + e.getMessage());
            return false;
        }
    }
}


