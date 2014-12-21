package com.tche;

public class Retorno {

	private int status;
	private String descricaoErro;
	private Tipagem tipagem;
	private String codigo;

	public Retorno() {
		status 			= 0;
		descricaoErro 	= "";
		codigo 			= "";
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
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = this.codigo + codigo;
	}

	
	public Retorno clone(){
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno.setStatus(this.status);
		mAuxRetorno.setDescricaoErro(this.descricaoErro);
		mAuxRetorno.setTipagem(this.tipagem);
//		mAuxRetorno.setCodigo(this.codigo);
		
		return mAuxRetorno;
	}
}
