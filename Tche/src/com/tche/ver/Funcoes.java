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

	abstract Retorno Inicio();

	abstract Retorno Q();

	abstract Retorno FuncProt();

	abstract Retorno IProt();

	abstract Retorno IProt1();

	abstract Retorno IVet();

	abstract Retorno IVetDime();

	abstract Retorno IVetDimeLinha();

	abstract Retorno AProt();

	abstract Retorno M();

	abstract int Func();

	abstract int Par();

	abstract int MaisPar();

	abstract int MaisPar1();

	abstract int ParVet();

	abstract int ParVar();

	abstract int FuncRet();

	abstract int IniCod();

	abstract int ICod();

	abstract int ACod();

	abstract int ACod1();

	abstract int Cod();

	abstract int ComandD();

	abstract int ComandD1();

	abstract int ComandD2();

	abstract int ComandD3();

	abstract int ComandC();

	abstract int IniComand();

	abstract int ComandA();

	abstract int ComandALinha();

	abstract int FuncCall();

	abstract int FuncPar();

	abstract int MaisFuncPar();

	abstract int Log();

	abstract int LogLinha();

	abstract int Op1();

	abstract int Op1Linha();

	abstract int Op2();

	abstract int Op2Linha();

	abstract int Op3();

	abstract int Op3Linha();

	abstract int Op4();

	abstract int Op4Linha();

	abstract int Un();

	abstract int P();

	abstract int PLinha();

	abstract int Ident();

	abstract int V();

	abstract int VVet();

	abstract int Vet();

	abstract int VVar();

	abstract int C();

	abstract int T();

	abstract int TVet();

	abstract int TVar();
}
