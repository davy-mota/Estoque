package model;

public class Fornecedor {
	private int id;
	private String fornecedorName;
	private String cnpj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedorName() {
		return fornecedorName;
	}
	public void setFornecedorName(String fornecedorName) {
		this.fornecedorName = fornecedorName;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "ID: " + Integer.toString(getId()) +" | "+ " NOME DO FORNECEDOR: " + getFornecedorName() +" | "+ " PASSWORD: " + getCnpj();
	}
}
