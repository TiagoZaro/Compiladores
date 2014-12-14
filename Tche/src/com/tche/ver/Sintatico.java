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
		if (TVar() == 1) {
			if (VVar() == 1) {

			}
		}
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
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand)
		// hasta Ident {Cod}
		return 0;
	}

	@Override
	int IniComand() {
		// IniComand -> ComandA | V
		return 0;
	}

	@Override
	int ComandA() {
		// ComandA -> ComandALINHA = ACod1 | aprochegar ComandALINHA | arregar
		// ComandALINHA
		return 0;
	}

	@Override
	int ComandALinha() {
		// ComandALINHA -> V | Vet
		return 0;
	}

	@Override
	int FuncCall() {
		// FuncCall -> V(FuncPar);
		return 0;
	}

	@Override
	int FuncPar() {
		// FuncPar-> Ident MaisFuncPar
		return 0;
	}

	@Override
	int MaisFuncPar() {
		// MaisFuncPar -> , Ident MaisFuncPar | &
		return 0;
	}

	@Override
	int Log() {
		// Log -> Op1 LogLINHA
		return 0;
	}

	@Override
	int LogLinha() {
		// LogLINHA -> && Op1 LogLINHA | || Op1 LogLINHA | &
		return 0;
	}

	@Override
	int Op1() {
		// Op1 -> Op2 Op1LINHA
		return 0;
	}

	@Override
	int Op1Linha() {
		// Op1LINHA -> == Op2 Op1LINHA | != Op2 Op1LINHA | &
		return 0;
	}

	@Override
	int Op2() {
		// Op2 -> Op3 Op2LINHA
		return 0;
	}

	@Override
	int Op2Linha() {
		// Op2LINHA -> > Op3 Op2LINHA | < Op3 Op2LINHA | >= Op3 Op2LINHA | <=
		// Op3 Op2LINHA | &
		return 0;
	}

	@Override
	int Op3() {
		// Op3 -> Op4 Op3LINHA
		return 0;
	}

	@Override
	int Op3Linha() {
		// Op3LINHA -> + Op4 Op3LINHA | - Op4 Op3LINHA | &
		return 0;
	}

	@Override
	int Op4() {
		// Op4 -> Un Op4LINHA
		return 0;
	}

	@Override
	int Op4Linha() {
		// Op4LINHA-> * Un Op4LINHA | / Un Op4LINHA | &
		return 0;
	}

	@Override
	int Un() {
		if (lexico.proximoToken() == tk_subtr) {
			if (V() == 1) {

			}
		}
		return 0;
	}

	@Override
	int P() {
		if (Ident() == 1) {
			if (PLinha() == 1) {
				return 1;
			}
		} else if (lexico.proximoToken() == tk_abreparenteses) {
			if (Log() == 1) {
				if (lexico.proximoToken() == tk_fechaparenteses) {
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	int PLinha() {
		if (lexico.proximoToken() == tk_abreparenteses) {
			if (Log() == 1) {
				if (lexico.proximoToken() == tk_fechaparenteses) {
					return 1;
				}
			}
		}
		return 0;// TODO aceita vazio VER
	}

	@Override
	int Ident() {
		if (V() == 1) {
			return 1;
		} else if (C() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int V() {
		if (VVar() == 1) {
			return 1;
		} else if (VVet() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int VVet() {
		if (VVar() == 1) {
			if (Vet() == 1) {
				if (Vet() == 1) {
					return 1;
				}
				return 1;
			}
		}
		return 0;
	}

	@Override
	int Vet() {
		if (lexico.proximoToken() == tk_abrecolchetes) {
			if (Ident() == 1) {
				if (lexico.proximoToken() == tk_fechecolchetes) {
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	int VVar() {
		// if (lexico.proximoToken() == tk_id) { // TODO
		// return 1;
		// }
		return 0;
	}

	@Override
	int C() {
		if (lexico.proximoToken() == tk_numero || lexico.proximoToken() == tk_apas) {
			return 1;
		}
		return 0;
	}

	@Override
	int T() {
		if (TVar() == 1) {
			if (TVet() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int TVet() {
		if ((lexico.proximoToken() == tk_borracho && TVar() == 1) || (lexico.proximoToken() == tk_bolicho && TVar() == 1)) {
			return 1;
		}
		return 0;
	}

	@Override
	int TVar() {
		if (lexico.proximoToken() == tk_bueno || lexico.proximoToken() == tk_pia || lexico.proximoToken() == tk_pila) {
			return 1;
		}
		return 0;
	}

}
