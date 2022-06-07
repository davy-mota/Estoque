package model;

public class Produto {
	
	private int codigo;
	private String descricao;
	private String marca;
	private float precoCompra;
	private float precoVenda;
	private int tipoproduto;
	private int unidadeMedida;

	public int getTipoproduto() {
		return tipoproduto;
	}
	public void setTipoproduto(int tipoproduto) {
		this.tipoproduto = tipoproduto;
	}
	public int getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(int unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
	}
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	
	
	

}
