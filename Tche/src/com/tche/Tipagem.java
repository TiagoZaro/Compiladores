package com.tche;

public class Tipagem {

	private String desNomeTipoVal;
	private String nomeVar;
	private TipoEntrada tipoEntrada;
	private Object vlrVariavel;
	private Integer dimensao;
	private String TipoArray;
	
	public Tipagem(){
		this.desNomeTipoVal = "";
		this.nomeVar		= "";
		this.tipoEntrada	= TipoEntrada.NAO_DEFINIDO;
		this.vlrVariavel 	= "0";
		this.dimensao		= 0;
		this.TipoArray		= "";
	}

	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(TipoEntrada tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public Object getVlrVariavel() {
		return vlrVariavel;
	}

	public void setVlrVariavel(Object vlrVariavel) {
		this.vlrVariavel = vlrVariavel;
	}

	public String getDesNomeTipoVal() {
		return desNomeTipoVal;
	}

	public void setDesNomeTipoVal(String desNomeTipoVal) {
		this.desNomeTipoVal = desNomeTipoVal;
	}

	public String getNomeVar() {
		return nomeVar;
	}

	public void setNomeVar(String nomeVar) {
		this.nomeVar = nomeVar;
	}

	public Integer getDimensao() {
		return dimensao;
	}

	public void setDimensao(Integer dimensao) {
		this.dimensao = dimensao;
	}

	public String getTipoArray() {
		return TipoArray;
	}

	public void setTipoArray(String tipoArray) {
		TipoArray = tipoArray;
	}
}
