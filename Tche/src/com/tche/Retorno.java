package com.tche;

public class Retorno {
	
	private int status;
	private String descricaoErro;
	
	public Retorno(){
		status 			= 0;
		descricaoErro 	= "";
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
	
	
	
}
