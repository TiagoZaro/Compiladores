package com.tche;

public class Tipagem {

	private String desNomeTipoVal;
	private String nomeVar;
	private TipoEntrada tipoEntrada;
	private Object vlrVariavel;

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

}
