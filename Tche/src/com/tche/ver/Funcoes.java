package com.tche.ver;

import com.tche.Retorno;

public abstract class Funcoes {
	static final int tk_EOF = -1;
	static final int tk_naoreconhecido = 0;
	static final int tk_adicao = 1;
	static final int tk_subtr = 2;
	static final int tk_mult = 3;
	static final int tk_divisao = 4;
	static final int tk_potencia = 6;
	static final int tk_abreparenteses = 7;
	static final int tk_fechaparenteses = 8;
	static final int tk_maior = 9;
	static final int tk_menor = 11;
	static final int tk_diferente = 13;
	static final int tk_igual = 14;
	static final int tk_fatorial = 17;
	static final int tk_numero = 100;
	static final int tk_abrechaves = 56;
	static final int tk_fechachaves = 57;
	static final int tk_abrecolchetes = 58;
	static final int tk_fechecolchetes = 59;
	static final int tk_ponto = 60;
	static final int tk_ponto_e_virgula = 61;
	static final int tk_true = 62;
	static final int tk_false = 63;
	static final int tk_apas = 64;
	static final int tk_barras = 65;
	static final int tk_e_comm = 66;
	static final int tk_querencia = 67;
	static final int tk_tche = 68;
	static final int tk_pila = 69;
	static final int tk_pia = 70;
	static final int tk_borracho = 71;
	static final int tk_bueno = 72;
	static final int tk_bolicho = 73;
	static final int tk_indiada = 74;
	static final int tk_despacho = 75;
	static final int tk_xispa = 76;
	static final int tk_quetal = 77;
	static final int tk_capaz = 78;
	static final int tk_trova = 79;
	static final int tk_voltear = 80;
	static final int tk_aprochegar = 81;
	static final int tk_arregar = 82;
	static final int tk_largatear = 83;
	static final int tk_hasta = 84;
	static final int tk_dois_pontos = 85;
	static final int tk_virgula = 86;
	static final int tk_variavel = 87;

	public abstract Retorno Inicio() throws Exception;

	abstract Retorno Q() throws Exception;

	abstract Retorno FuncProt() throws Exception;

	abstract Retorno IProt() throws Exception;

	abstract Retorno IProt1(String pTipoVariavel) throws Exception;

	abstract Retorno IVet() throws Exception;

	abstract Retorno IVetDime() throws Exception;

	abstract Retorno IVetDimeLinha() throws Exception;

	abstract Retorno AProt() throws Exception;

	abstract Retorno M() throws Exception;

	abstract Retorno Func() throws Exception;

	abstract Retorno Par() throws Exception;

	abstract Retorno MaisPar() throws Exception;

	abstract Retorno MaisPar1() throws Exception;

	abstract Retorno ParVet() throws Exception;

	abstract Retorno ParVar() throws Exception;

	abstract Retorno FuncRet() throws Exception;

	abstract Retorno IniCod() throws Exception;

	abstract Retorno ICod() throws Exception;

	abstract Retorno ACod(String pVariavel) throws Exception;

	abstract Retorno ACod1() throws Exception;

	abstract Retorno Cod(String pNext) throws Exception;

	abstract Retorno ComandD(String pNext) throws Exception;

	abstract Retorno ComandD1(String pNext) throws Exception;

	abstract Retorno ComandD2(String pNext) throws Exception;

	abstract Retorno ComandD3(String pNext) throws Exception;

	abstract Retorno ComandC() throws Exception;

	abstract Retorno IniComand() throws Exception;

	abstract Retorno IniComandLinha() throws Exception;

	abstract Retorno ComandA() throws Exception;

	abstract Retorno ComandALinha() throws Exception;

	abstract Retorno FuncCall() throws Exception;

	abstract Retorno FuncPar() throws Exception;

	abstract Retorno MaisFuncPar() throws Exception;

	abstract Retorno Log(String pFalse) throws Exception;

	abstract Retorno LogLinha(String pFalse, String pTrue) throws Exception;

	abstract Retorno Op1(String pFalse, String pTrue) throws Exception;

	abstract Retorno Op1Linha(String pFalse, String pTrue, String pVariavel) throws Exception;

	abstract Retorno Op2(String pFalse, String pTrue) throws Exception;

	abstract Retorno Op2Linha(String pFalse, String pTrue, String pVariavel) throws Exception;

	abstract Retorno Op3() throws Exception;

	abstract Retorno Op3Linha(String pVariavel) throws Exception;

	abstract Retorno Op4() throws Exception;

	abstract Retorno Op4Linha(String pVariavel) throws Exception;

	abstract Retorno Un() throws Exception;

	abstract Retorno P() throws Exception;

	abstract Retorno PLinha() throws Exception;

	abstract Retorno Ident() throws Exception;

	abstract Retorno V() throws Exception;

	abstract Retorno VVet() throws Exception;

	abstract Retorno Vet() throws Exception;

	abstract Retorno VVar() throws Exception;

	abstract Retorno C() throws Exception;

	abstract Retorno T() throws Exception;

	abstract Retorno TVet() throws Exception;

	abstract Retorno TVar() throws Exception;
}
