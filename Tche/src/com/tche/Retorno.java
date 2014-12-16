package com.tche;

public class Retorno {
	
	private int status;
	private String descricaoErro;
	
	private String valor;
	private String tipo;
	
	public Retorno(){
		status 			= 0;
		descricaoErro 	= "";
		
		valor  			= "";
		tipo			= "";
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	
	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
	public String getDescricaoErro() {
		return descricaoErro;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValor() {
		return valor;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
}
