package com.tche;

public class Tipagem {

	private String		desNomeTipoVal;
	private TipoEntrada	tipoEntrada;
	private Object		vlrVariavel;

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

}
