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


	public abstract Retorno Inicio();

	abstract Retorno Q();

	abstract Retorno FuncProt();

	abstract Retorno IProt();

	abstract Retorno IProt1();

	abstract Retorno IVet();

	abstract Retorno IVetDime();

	abstract Retorno IVetDimeLinha();

	abstract Retorno AProt();

	abstract Retorno M();

	abstract Retorno Func();

	abstract Retorno Par();

	abstract Retorno MaisPar();

	abstract Retorno MaisPar1();

	abstract Retorno ParVet();

	abstract Retorno ParVar();

	abstract Retorno FuncRet();

	abstract Retorno IniCod();

	abstract Retorno ICod();

	abstract Retorno ACod();

	abstract Retorno ACod1();

	abstract Retorno Cod();

	abstract Retorno ComandD();

	abstract Retorno ComandD1();

	abstract Retorno ComandD2();

	abstract Retorno ComandD3();

	abstract Retorno ComandC();

	abstract Retorno IniComand();
	
	abstract Retorno IniComandLinha();

	abstract Retorno ComandA();

	abstract Retorno ComandALinha();

	abstract Retorno FuncCall();

	abstract Retorno FuncPar();

	abstract Retorno MaisFuncPar();

	abstract Retorno Log();

	abstract Retorno LogLinha();

	abstract Retorno Op1();

	abstract Retorno Op1Linha();

	abstract Retorno Op2();

	abstract Retorno Op2Linha();

	abstract Retorno Op3();

	abstract Retorno Op3Linha();

	abstract Retorno Op4();

	abstract Retorno Op4Linha();

	abstract Retorno Un();

	abstract Retorno P();

	abstract Retorno PLinha();

	abstract Retorno Ident();

	abstract Retorno V();

	abstract Retorno VVet();

	abstract Retorno Vet();

	abstract Retorno VVar();

	abstract Retorno C();

	abstract Retorno T();

	abstract Retorno TVet();

	abstract Retorno TVar();
}
