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
	int MaisPar1() {
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
