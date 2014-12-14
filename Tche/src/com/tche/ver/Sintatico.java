package com.tche.ver;

import com.tche.Retorno;

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
	Retorno Inicio() {
		Retorno mAuxRetorno = new Retorno();
		mAuxRetorno.setStatus(1);
		
		if (lexico.proximoToken() == tk_querencia) {
			if (lexico.proximoToken() == tk_abrechaves) {
				mAuxRetorno = Q();
			
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() != tk_fechachaves) {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Falta fecha chaves no querencia");					
					}
				}
			}
			else{ 
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Falta abre chaves no querencia");
			}
				
		}
		
		if (mAuxRetorno.getStatus() == 1){
			mAuxRetorno = M();
		}
			
		return mAuxRetorno;
	}

	@Override
	Retorno Q() {
		Retorno mAuxRetorno = null;
		
		/*if (IVet() == 1) {
			if (Q() == 1) {
				return 1;
			}
		} else if (IProt() == 1) {
			if (Q() == 1) {
				return 1;
			}
		} else if (FuncProt() == 1) {
			if (Q() == 1) {
				return 1;
			}
		} else {
			// vazio //TODO
		}		
		
		return 0;*/
		
		mAuxRetorno = IVet();
		if (mAuxRetorno.getStatus() == 1){
			mAuxRetorno = Q();					
		}
		else{
			mAuxRetorno = IProt();
			if (mAuxRetorno.getStatus() == 1){
				mAuxRetorno = Q();
			}
			else {
				mAuxRetorno = FuncProt();
				if (mAuxRetorno.getStatus() == 1){
					mAuxRetorno = Q();
				}
				else{
					// vazio //TODO
				}					
			}
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno FuncProt() {
		/*if (lexico.proximoToken() == tk_indiada) {
			if (V() == 1) {
				if (lexico.proximoToken() == tk_abreparenteses) {
					if (Par() == 1) {
						if (lexico.proximoToken() == tk_fechaparenteses) {
							if (FuncRet() == 1) {
								if (lexico.proximoToken() == tk_ponto_e_virgula) {
									return 1;
								}
							}
						}
					}
				}
			}
		}
		return 0;*/
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_indiada) {
			mAuxRetorno = V();
			
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() == tk_abreparenteses) {
					mAuxRetorno = Par();
					if (mAuxRetorno.getStatus() == 1) {
						if (lexico.proximoToken() == tk_fechaparenteses) {
							mAuxRetorno = FuncRet();
							if (mAuxRetorno.getStatus() == 1) {
								if (lexico.proximoToken() != tk_ponto_e_virgula) {
									mAuxRetorno.setStatus(0);
									mAuxRetorno.setDescricaoErro("Falta ponto e virgula no indiada");
								}
							}
						}
						else{
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Falta fecha parenteses para a indiada");
						}
					}
				}
				else{
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta abre parenteses para a indiada");
				}					
			}
		}
		
		return mAuxRetorno;		
	}

	@Override
	Retorno IProt() {
		/*Retorno mAuxRetorno = new Retorno();
		
		if (TVar() == 1) {
			if (V() == 1) {
				if (IProt1() == 1) {
					return 1;
				}
			}
		}
		return 0;*/
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = TVar();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = V();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = IProt1();
			}
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IProt1() {
		/*if (lexico.proximoToken() == tk_virgula) {
			if (V() == 1) {
				if (IProt1() == 1) {
					return 1;
				}
			}
		} else if (AProt() == 1) {
			return 1;
		}
		return 0;*/
		// IProt1 -> , V Iprot1| AProt
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_virgula) {
			mAuxRetorno = V();
			
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = IProt1();
			}
		} else{
			mAuxRetorno = AProt();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno IVet() {
		/*if (TVet() == 1) {
			if (V() == 1) {
				if (lexico.proximoToken() == tk_igual) {
					if (IVetDime() == 1) {
						if (lexico.proximoToken() == tk_ponto_e_virgula) {
							return 1;
						}
					}
				}
			}
		}
		return 0;*/
		//IVet -> TVet V = IVetDime;
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = TVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = V();
			
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() == tk_igual) {
					mAuxRetorno = IVetDime();
					if (mAuxRetorno.getStatus() == 1) {
						if (lexico.proximoToken() != tk_ponto_e_virgula) {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Falta o ponto e virgula no TVet");
						}
					}
				} else{
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta o sinal de igual TVet");
				}					
			}
		}
		
		return mAuxRetorno;		
	}

	@Override
	Retorno IVetDime() {
		/*if (lexico.proximoToken() == tk_abrecolchetes) {
			if (C() == 1) {
				if (lexico.proximoToken() == tk_fechecolchetes) {
					if (IVetDimeLinha() == 1) {
						return 1;
					}
				}
			}
		}
		return 0;*/
		// IVetDime -> [ C ] IvetDime’
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_abrecolchetes) {
			mAuxRetorno = C();
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() == tk_fechecolchetes) {
					if (IVetDimeLinha() == 1) {
						return 1;
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Faltou fecha colchetes IVetDime");
				}
			}
		} else {
			mAuxRetorno.setStatus(0);
			mAuxRetorno.setDescricaoErro("Faltou abre colchetes IVetDime");
		}
		
		
		return mAuxRetorno;
	}

	@Override
	Retorno IVetDimeLinha() {
		if (lexico.proximoToken() == tk_abrecolchetes) {
			if (C() == 1) {
				if (lexico.proximoToken() == tk_fechecolchetes) {
					return 1;
				}
			}
		} else {
			// vazio //TODO
		}
		return 0;
	}

	@Override
	Retorno AProt() {
		if (lexico.proximoToken() == tk_igual) {
			if (C() == 1) {
				return 1;
			}
		} else {
			// Vazio //TODO
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	Retorno M() {
		if (lexico.proximoToken() == tk_tche) {
			if (lexico.proximoToken() == tk_abrechaves) {
				if (IniCod() == 1) {
					if (lexico.proximoToken() == tk_fechachaves) {
						if (Func() == 1) {
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	@Override
	int Func() {
		if (lexico.proximoToken() == tk_indiada) {
			if (V() == 1) {
				if (lexico.proximoToken() == tk_abreparenteses) {
					if (Par() == 1) {
						if (lexico.proximoToken() == tk_fechaparenteses) {
							if (FuncRet() == 1) {
								if (lexico.proximoToken() == tk_abrechaves) {
									if (IniCod() == 1) {
										if (lexico.proximoToken() == tk_fechachaves) {
											if (Func() == 1) {
												return 1;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			// VAZIO //TODO
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int Par() {
		if (ParVet() == 1) {
			if (MaisPar() == 1) {
				return 1;
			}
		} else if (ParVar() == 1) {
			if (MaisPar() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int MaisPar() {
		if (lexico.proximoToken() == tk_virgula) {
			if (MaisPar1() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int MaisPar1() {
		if (ParVet() == 1) {
			if (MaisPar() == 1) {
				return 1;
			}
		} else if (ParVar() == 1) {
			if (MaisPar() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int ParVet() {
		if (TVar() == 1) {
			if (VVet() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int ParVar() {
		if (TVar() == 1) {
			if (VVar() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int FuncRet() {
		if (lexico.proximoToken() == tk_dois_pontos) {
			if (T() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int IniCod() {
		if (ICod() == 1) {
			if (IniCod() == 1) {
				return 1;
			}
		} else if (Cod() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int ICod() {
		if (T() == 1) {
			if (V() == 1) {
				if (ACod1() == 1) {
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	int ACod() {
		if (lexico.proximoToken() == tk_igual) {
			if (ACod1() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int ACod1() {
		if (Ident() == 1) {
			return 1;
		} else if (Op3() == 1) {
			return 1;
		} else if (FuncCall() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int Cod() {
		if (ComandC() == 1) {
			if (Cod() == 1) {
				return 1;
			}
		} else if (ComandD() == 1) {
			if (Cod() == 1) {
				return 1;
			}
		} else if (ComandA() == 1) {
			if (lexico.proximoToken() == tk_ponto_e_virgula) {
				if (Cod() == 1) {
					return 1;
				}
			}
		} else if (FuncCall() == 1) {
			if (lexico.proximoToken() == tk_ponto_e_virgula) {
				if (Cod() == 1) {
					return 1;
				}
			}
		} else {
			// VAZIO //TODO
		}

		return 0;
	}

	@Override
	int ComandD() {
		if (lexico.proximoToken() == tk_quetal) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (Log() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_abrechaves) {
							if (Cod() == 1) {
								if (lexico.proximoToken() == tk_fechachaves) {
									if (ComandD1() == 1) {
										return 1;
									}
								}
							}
						}
					}
				}
			}
		} else if (lexico.proximoToken() == tk_xispa) {
			if (lexico.proximoToken() == tk_ponto_e_virgula) {
				return 1;
			}
		} else if (lexico.proximoToken() == tk_despacho) {
			if (ComandD2() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int ComandD1() {
		if (lexico.proximoToken() == tk_capaz) {
			if (ComandD3() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int ComandD2() {
		if (Log() == 1) {
			return 1;
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int ComandD3() {
		if (lexico.proximoToken() == tk_abrechaves) {
			if (Cod() == 1) {
				if (lexico.proximoToken() == tk_fechachaves) {
					return 1;
				}
			}
		} else if (lexico.proximoToken() == tk_abreparenteses) {
			if (Log() == 1) {
				if (lexico.proximoToken() == tk_fechaparenteses) {
					if (lexico.proximoToken() == tk_abreparenteses) {
						if (Cod() == 1) {
							if (lexico.proximoToken() == tk_fechaparenteses) {
								if (ComandD1() == 1) {
									return 1;
								}
							}
						}
					}
				}
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int ComandC() {
		if (lexico.proximoToken() == tk_trova) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (Ident() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_ponto_e_virgula) {
							return 1;
						}
					}
				}
			}
		} else if (lexico.proximoToken() == tk_voltear) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (Log() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_abrechaves) {
							if (Cod() == 1) {
								if (lexico.proximoToken() == tk_fechachaves) {
									return 1;
								}
							}
						}
					}
				}
			}
		} else if (lexico.proximoToken() == tk_largatear) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (IniComand() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_hasta) {
							if (Ident() == 1) {
								if (lexico.proximoToken() == tk_abrechaves) {
									if (Cod() == 1) {
										if (lexico.proximoToken() == tk_fechachaves) {
											return 1;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	@Override
	int IniComand() {
		if (ComandA() == 1) {
			return 1;
		} else if (V() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int ComandA() {
		if (ComandALinha() == 1) {
			if (lexico.proximoToken() == tk_igual) {
				if (ACod1() == 1) {
					return 1;
				}
			}
		} else if (lexico.proximoToken() == tk_aprochegar) {
			if (ComandALinha() == 1) {
				return 1;
			}
		} else if (lexico.proximoToken() == tk_arregar) {
			if (ComandALinha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int ComandALinha() {
		if (V() == 1) {
			return 1;
		} else if (Vet() == 1) {
			return 1;
		}
		return 0;
	}

	@Override
	int FuncCall() {
		if (V() == 1) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (FuncPar() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						return 1;
					}
				}
			}
		}
		return 0;
	}

	@Override
	int FuncPar() {
		if (Ident() == 1) {
			if (MaisFuncPar() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int MaisFuncPar() {
		if (lexico.proximoToken() == tk_virgula) {
			if (Ident() == 1) {
				if (MaisFuncPar() == 1) {
					return 1;
				}
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int Log() {
		if (Op1() == 1) {
			if (LogLinha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int LogLinha() {
		// LogLINHA -> && Op1 LogLINHA | || Op1 LogLINHA | &
		return 0;
	}

	@Override
	int Op1() {
		if (Op2() == 1) {
			if (Op1Linha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int Op1Linha() {
		if (lexico.proximoToken() == tk_igual) {
			if (lexico.proximoToken() == tk_igual) {
				if (Op2() == 1) {
					if (Op1Linha() == 1) {
						return 1;
					}
				}
			}
		} else if (lexico.proximoToken() == tk_fatorial) {
			if (lexico.proximoToken() == tk_igual) {
				if (Op2() == 1) {
					if (Op1Linha() == 1) {
						return 1;
					}
				}
			}
		} else {
			// vazio //TODO
		}
		return 0;
	}

	@Override
	int Op2() {
		if (Op3() == 1) {
			if (Op2Linha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int Op2Linha() {
		if (lexico.proximoToken() == tk_maior) {
			if (Op3() == 1) {
				if (Op2Linha() == 1) {
					return 1;
				}
			} else if (lexico.proximoToken() == tk_igual) {
				if (Op2Linha() == 1) {
					return 1;
				}
			}
		} else if (lexico.proximoToken() == tk_menor) {
			if (Op3() == 1) {
				if (Op2Linha() == 1) {
					return 1;
				}
			} else if (lexico.proximoToken() == tk_igual) {
				if (Op2Linha() == 1) {
					return 1;
				}
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int Op3() {
		if (Op4() == 1) {
			if (Op3Linha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int Op3Linha() {

		if (lexico.proximoToken() == tk_adicao) {
			if (Op4() == 1) {
				if (Op3Linha() == 1) {
					return 1;
				}
			}
		} else if (lexico.proximoToken() == tk_subtr) {
			if (Op4() == 1) {
				if (Op3Linha() == 1) {
					return 1;
				}
			}
		} else {
			// VAZIO //TODO
		}
		return 0;
	}

	@Override
	int Op4() {
		if (Un() == 1) {
			if (Op4Linha() == 1) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	int Op4Linha() {
		if (lexico.proximoToken() == tk_mult) {
			if (Un() == 1) {
				if (Op4Linha() == 1) {
					return 1;
				}
			}
		} else if (lexico.proximoToken() == tk_divisao) {
			if (Un() == 1) {
				if (Op4Linha() == 1) {
					return 1;
				}
			}
		}
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
