package com.tche.ver;

public class Sintatico extends Funcoes {

	public static void main(String[] args) {
		String s = "aaa";
	}

	Lexico lexico;

	private class Lexico {
		int proximoToken() {
			return 1;
		}
	}

	@Override
	int Inicio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Q() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int FuncProt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IProt() {
		if (lexico.proximoToken() == tk_bolicho) {
			if (V() == 1) {
				if (IProt1() == 1) {
					return 1;
				}
			}
		}

		// if (lexico.proximoToken() == 79) {
		// if (lexio.promoTOken() == paranet) {
		// ident();
		// if (lexico.proximoToken() == paranetresfecha) {
		// if (leximo.promimoToken() == pontovirgula) {
		// return 1;
		// }
		// }
		// }
		// }
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand)
		// hasta Ident {Cod}

		return 0;
	}

	@Override
	int IProt1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IVet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IVetDime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IVetDimeLinha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int AProt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int M() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Func() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Par() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int MaisPar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ParVet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ParVar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int FuncRet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IniCod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ICod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ACod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ACod1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Cod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandD() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandD1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandD2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandC() {
		// if (lexico.proximoToken() == 79) {
		// if (lexio.promoTOken() == paranet) {
		// ident();
		// if (lexico.proximoToken() == paranetresfecha) {
		// if (leximo.promimoToken() == pontovirgula) {
		// return 1;
		// }
		// }
		// }
		// }
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand)
		// hasta Ident {Cod}
		return 0;
	}

	@Override
	int IniComand() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandALinha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int FuncCall() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int FuncPar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int MaisFuncPar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Log() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int LogLinha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op1Linha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op2Linha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op3Linha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op4() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Op4Linha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Un() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int P() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int PLinha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Ident() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int V() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int VVet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Vet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int VVar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int C() {
		if (lexico.proximoToken() == tk_numero || lexico.proximoToken() == tk_apas) {

		}
		return 0;
	}

	@Override
	int T() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int TVet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int TVar() {
		// TODO Auto-generated method stub
		return 0;
	}

}
