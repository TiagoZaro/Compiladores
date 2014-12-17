package com.tche;

public class Retorno {

	private int status;
	private String descricaoErro;
	private Tipagem tipagem;

	public Retorno() {
		status = 0;
		descricaoErro = "";

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

	public Tipagem getTipagem() {
		return tipagem;
	}

	public void setTipagem(Tipagem tipagem) {
		this.tipagem = tipagem;
	}

}
