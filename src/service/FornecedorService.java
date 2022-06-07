package service;

import model.Fornecedor;

import java.io.*;
import java.util.ArrayList;

public class FornecedorService {

    private final String DIR_FORNECEDOR_DB = "src/database/fornecedor.txt";
    private FileReader arquivoLeitura;
    private BufferedReader memoriaLeitura;
    private File arquivo;
    private FileWriter escreverArquivo;
    private BufferedWriter memoriaEscrita;

    public FornecedorService() {
        try {
            arquivoLeitura = new FileReader(DIR_FORNECEDOR_DB);
            memoriaLeitura = new BufferedReader(arquivoLeitura);
            arquivo = new File(DIR_FORNECEDOR_DB);
            escreverArquivo = new FileWriter(DIR_FORNECEDOR_DB);
            memoriaEscrita = new BufferedWriter(escreverArquivo);

        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
    }

    public boolean escrever(Fornecedor fornecedor) {
        try {
            if (existeArquivo()) {

                String linha = null;
                int contadorLinha = 1;
                while ((linha = memoriaLeitura.readLine()) != null) {
                    contadorLinha += 1;
                }
                String dadoEscrever = contadorLinha + ";" + fornecedor.getFornecedorName() + ";" + fornecedor.getCnpj() + "\n";
                escreverArquivo = new FileWriter(arquivo, true);
                escreverArquivo.write(dadoEscrever);
                memoriaEscrita.newLine();
                escreverArquivo.close();

                return true;
            } else {
                criaArquivo();
                escrever(fornecedor);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return false;
    }

    public boolean ler(Fornecedor fornecedor) {
        try {
            if (existeArquivo()) {
                String linha = null;


                while ((linha = memoriaLeitura.readLine()) != null) {
                    String[] linha_split = linha.split(";");
                    if (fornecedor.getFornecedorName().equals(linha_split[1])) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Fornecedor> ler() {
        ArrayList<Fornecedor> fornecedorList = new ArrayList<>();

        try {
            if (existeArquivo()) {
                String linha = null;

                while ((linha = memoriaLeitura.readLine()) != null) {
                    String[] linha_split = linha.split(";");
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(Integer.parseInt(linha_split[0]));
                    fornecedor.setFornecedorName(linha_split[1]);
                    fornecedor.setCnpj(linha_split[2]);
                    fornecedorList.add(fornecedor);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }

        return fornecedorList;
    }

    public boolean deletar(Fornecedor fornecedor) {
        Boolean excluiFornecedor = false;

        try {
            if (existeArquivo()) {
                ArrayList<String> fornecedorListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine()) != null) {
                    String[] linha_split = linha.split(";");


                    if (!fornecedor.getFornecedorName().equals(linha_split[1])) {
                        fornecedorListGravar.add(linha);
                    }
                }

                arquivoLeitura.close();
                memoriaLeitura.close();


                escreverArquivo = new FileWriter(DIR_FORNECEDOR_DB);
                BufferedWriter memoriaEscrita = new BufferedWriter(escreverArquivo);

                for (String novaLinha : fornecedorListGravar) {
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }
                memoriaEscrita.close();
                return true;
            } else {
                return false;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }


        return excluiFornecedor;
    }

    public boolean atualizar(Fornecedor fornecedor) {
        Boolean atualizarFornecedor = false;

        try {
            if (existeArquivo()) {
                ArrayList<String> fornecedorListGravar = new ArrayList<>();
                String linha = null;

                while ((linha = memoriaLeitura.readLine()) != null) {
                    String[] linha_split = linha.split(";");

                    if (fornecedor.getFornecedorName().equals(linha_split[1])) {
                        String novaLinha = linha_split[0] + ";" + linha_split[1] + ";" + fornecedor.getCnpj();
                        fornecedorListGravar.add(novaLinha);
                        atualizarFornecedor = true;
                    } else {
                        fornecedorListGravar.add(linha);
                    }
                }
                arquivoLeitura.close();
                memoriaLeitura.close();

                escreverArquivo = new FileWriter(DIR_FORNECEDOR_DB);

                for (String novaLinha : fornecedorListGravar) {
                    memoriaEscrita.write(novaLinha);
                    memoriaEscrita.newLine();
                }
                memoriaEscrita.close();
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível abrir o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo.");
            System.out.println("O erro gerado é: " + e.getMessage());
        }
        return atualizarFornecedor;

    }

    private boolean existeArquivo() {
        File arquivo = new File(DIR_FORNECEDOR_DB);
        return arquivo.exists();
    }

    private boolean criaArquivo() {
        try {
            File arquivo = new File(DIR_FORNECEDOR_DB);
            return arquivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo!!!");
            System.out.println("O erro gerado é: " + e.getMessage());
            return false;
        }
    }
}


