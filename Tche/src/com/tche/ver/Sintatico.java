package com.tche.ver;

import static com.tche.ver.Lexico.getInstance;

import com.tche.AnalisadorSemantico;
import com.tche.DesktopFrameWork;
import com.tche.Retorno;
import com.tche.TcheGlobal;
import com.tche.Tipagem;
import com.tche.TipoEntrada;

public class Sintatico extends Funcoes {

	Tipagem tipoVar = null;
	Tipagem tipoFunc = null;

	@Override
	public Retorno Inicio() {
		DesktopFrameWork.getInstance().addSintatico("Inicio");
		Retorno mAuxRetorno = new Retorno();
		mAuxRetorno.setStatus(1);

		if (getInstance().proximoToken() == tk_querencia) {
			getInstance().consumirToken();
			getInstance().consumirLexema();

			if (getInstance().proximoToken() == tk_abrechaves) {
				getInstance().consumirToken();
				getInstance().consumirLexema();

				mAuxRetorno = this.Q();

				if (mAuxRetorno.getStatus() == 1) {
					if (getInstance().proximoToken() == tk_fechachaves) {
						getInstance().consumirToken();
						getInstance().consumirLexema();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Falta fecha chaves no querencia");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Falta abre chaves no querencia");
			}

		}

		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.M();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Q() {		
		DesktopFrameWork.getInstance().addSintatico("Q");
		// Q -> IVet Q | IProt Q | FuncProt Q | &
		Retorno mAuxRetorno = null;

		/*
		 * if (IVet() == 1) { if (Q() == 1) { return 1; } } else if (IProt() ==
		 * 1) { if (Q() == 1) { return 1; } } else if (FuncProt() == 1) { if
		 * (Q() == 1) { return 1; } } else { // vazio //TODO }
		 * 
		 * return 0;
		 */

		mAuxRetorno = this.IVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.Q();
		} else {
			mAuxRetorno = this.IProt();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.Q();
			} else {
				mAuxRetorno = this.FuncProt();
				if (mAuxRetorno.getStatus() == 1) {
					mAuxRetorno = this.Q();
				} else {
					// vazio
					mAuxRetorno.setStatus(1);
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno FuncProt() {
		DesktopFrameWork.getInstance().addSintatico("FuncProt");
		/*
		 * if (lexico.proximoToken() == tk_indiada) { if (V() == 1) { if
		 * (lexico.proximoToken() == tk_abreparenteses) { if (Par() == 1) { if
		 * (lexico.proximoToken() == tk_fechaparenteses) { if (FuncRet() == 1) {
		 * if (lexico.proximoToken() == tk_ponto_e_virgula) { return 1; } } } }
		 * } } } return 0;
		 */
		// FuncProt -> indiada V (Par) FuncRet;
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_indiada) {
			getInstance().consumirToken();
			getInstance().consumirLexema();
			mAuxRetorno = this.V();

			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					mAuxRetorno = this.Par();
					if (mAuxRetorno.getStatus() == 1) {
						if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.FuncRet();
							if (mAuxRetorno.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
									getInstance().consumirLexema();
									getInstance().consumirToken();
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno
											.setDescricaoErro("Falta ponto e virgula no indiada");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Falta fecha parenteses para a indiada");
						}
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno
							.setDescricaoErro("Falta abre parenteses para a indiada");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IProt() {
		DesktopFrameWork.getInstance().addSintatico("IProt");
		/*
		 * Retorno mAuxRetorno = new Retorno();
		 * 
		 * if (TVar() == 1) { if (V() == 1) { if (IProt1() == 1) { return 1; } }
		 * } return 0;
		 */
		// IProt -> TVar V IProt1;
		Retorno mAuxRetorno = new Retorno();

		Retorno mAuxRetornoTVar = this.TVar();
		if (mAuxRetornoTVar.getStatus() == 1) {
			Retorno mAuxRetornoV = this.V();
			if (mAuxRetornoV.getStatus() == 1) {
				
				mAuxRetorno.setTipagem(mAuxRetornoTVar.getTipagem());
				mAuxRetorno.getTipagem().setNomeVar(mAuxRetornoV.getTipagem().getNomeVar());
				
				AnalisadorSemantico.addTable(mAuxRetorno.getTipagem(), mAuxRetorno.getTipagem().getNomeVar());
				mAuxRetorno = this.IProt1();

				if (mAuxRetorno.getStatus() == 1) {
					if (getInstance().proximoToken() == tk_ponto_e_virgula) {
						consumirTudo();

						// // Exemplo adiciona na tabela de simbolos
						// Tipagem t = new Tipagem();
						// t.setDesNomeTipoVal("Pila");
						// t.setVlrVariavel(0);
						// t.setTipoEntrada(TipoEntrada.VARIAVEL);
						// String nome = "val";
						//
						// addTable(t, nome);
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou o ponto e virgula na inicialização da variavel");
					}
				}
			}
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IProt1() {
		DesktopFrameWork.getInstance().addSintatico("IProt1");
		/*
		 * if (lexico.proximoToken() == tk_virgula) { if (V() == 1) { if
		 * (IProt1() == 1) { return 1; } } } else if (AProt() == 1) { return 1;
		 * } return 0;
		 */
		// IProt1 -> , V Iprot1| AProt
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_virgula) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.V();

			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.IProt1();
			}
		} else {
			mAuxRetorno = this.AProt();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IVet() {
		DesktopFrameWork.getInstance().addSintatico("IVet");
		/*
		 * if (TVet() == 1) { if (V() == 1) { if (lexico.proximoToken() ==
		 * tk_igual) { if (IVetDime() == 1) { if (lexico.proximoToken() ==
		 * tk_ponto_e_virgula) { return 1; } } } } } return 0;
		 */
		// IVet -> TVet V = IVetDime;
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.TVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.V();

			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_igual) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					mAuxRetorno = this.IVetDime();
					if (mAuxRetorno.getStatus() == 1) {
						if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
							getInstance().consumirToken();
							getInstance().consumirLexema();
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Falta o ponto e virgula no TVet");
						}
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta o sinal de igual TVet");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IVetDime() {
		DesktopFrameWork.getInstance().addSintatico("IVetDime");
		/*
		 * if (lexico.proximoToken() == tk_abrecolchetes) { if (C() == 1) { if
		 * (lexico.proximoToken() == tk_fechecolchetes) { if (IVetDimeLinha() ==
		 * 1) { return 1; } } } } return 0;
		 */
		// IVetDime -> [ C ] IvetDime?
		// IVetDime -> [ C ] IvetDime’
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_abrecolchetes) {
			getInstance().consumirToken();
			getInstance().consumirLexema();
//			mAuxRetorno = this.C();
			mAuxRetorno = this.Ident();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechecolchetes) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					mAuxRetorno = this.IVetDimeLinha();
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno
							.setDescricaoErro("Faltou fecha colchetes IVetDime");
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
		DesktopFrameWork.getInstance().addSintatico("IVetDimeLinha");
		/*
		 * if (lexico.proximoToken() == tk_abrecolchetes) { if (C() == 1) { if
		 * (lexico.proximoToken() == tk_fechecolchetes) { return 1; } } } else {
		 * // vazio //TODO } return 0;
		 */
		// IvetDime? -> [ C ] | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_abrecolchetes) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
//			mAuxRetorno = this.C();
			mAuxRetorno = this.Ident();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechecolchetes) {					
					getInstance().consumirLexema();
					getInstance().consumirToken();
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno
							.setDescricaoErro("Faltou fecha colchetes IVetDimeLinha");
				}
			}
		} else {
			// vazio
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno AProt() {
		/*
		 * if (lexico.proximoToken() == tk_igual) { if (C() == 1) { return 1; }
		 * } else { // Vazio //TODO } // TODO Auto-generated method stub return
		 * 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("AProt");
		// AProt -> = C | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_igual) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.C();
		} else {
			// Vazio
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno M() {
		/*
		 * if (lexico.proximoToken() == tk_tche) { if (lexico.proximoToken() ==
		 * tk_abrechaves) { if (IniCod() == 1) { if (lexico.proximoToken() ==
		 * tk_fechachaves) { if (Func() == 1) { return 1; } } } } } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("M");
		// M -> tche{ IniCod } Func
		Retorno mAuxRetorno = new Retorno();

		if (getInstance().proximoToken() == tk_tche) {
			getInstance().consumirLexema();
			getInstance().consumirToken();

			if (getInstance().proximoToken() == tk_abrechaves) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.IniCod();
				if (mAuxRetorno.getStatus() == 1) { // TODO 1) VERIFICAR PQ NAO
													// RETORNA 1
					if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						mAuxRetorno = this.Func();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou fecha chaves do tche");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre chaves do tche");
			}
		} else {
			mAuxRetorno.setStatus(0);
			mAuxRetorno.setDescricaoErro("Faltou o Tche!!!");
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Func() {
		/*
		 * if (lexico.proximoToken() == tk_indiada) { if (V() == 1) { if
		 * (lexico.proximoToken() == tk_abreparenteses) { if (Par() == 1) { if
		 * (lexico.proximoToken() == tk_fechaparenteses) { if (FuncRet() == 1) {
		 * if (lexico.proximoToken() == tk_abrechaves) { if (IniCod() == 1) { if
		 * (lexico.proximoToken() == tk_fechachaves) { if (Func() == 1) { return
		 * 1; } } } } } } } } } } else { // VAZIO //TODO } // TODO
		 * Auto-generated method stub return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("Func");
		// Func -> indiada V (Par) FuncRet { IniCod } Func | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_indiada) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					mAuxRetorno = this.Par();
					if (mAuxRetorno.getStatus() == 1) {
						if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.FuncRet();
							if (mAuxRetorno.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
									getInstance().consumirLexema();
									getInstance().consumirToken();
									mAuxRetorno = this.IniCod();
									if (mAuxRetorno.getStatus() == 1) {
										if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
											getInstance().consumirLexema();
											getInstance().consumirToken();
											mAuxRetorno = this.Func();
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Par() {
		/*
		 * if (ParVet() == 1) { if (MaisPar() == 1) { return 1; } } else if
		 * (ParVar() == 1) { if (MaisPar() == 1) { return 1; } } else { // VAZIO
		 * //TODO } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("Par");
		// Par-> ParVet MaisPar | ParVar MaisPar
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ParVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.MaisPar();
		} else {
			mAuxRetorno = this.ParVar();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.MaisPar();
			} else {
				// VAZIO //TODO
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno MaisPar() {
		/*
		 * if (lexico.proximoToken() == tk_virgula) { if (MaisPar1() == 1) {
		 * return 1; } } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("MaisPar");
		// MaisPar ->, MaisPar1 | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_virgula) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.MaisPar1();
		} else {
			// VAZIO
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno MaisPar1() {
		/*
		 * if (ParVet() == 1) { if (MaisPar() == 1) { return 1; } } else if
		 * (ParVar() == 1) { if (MaisPar() == 1) { return 1; } } else { // VAZIO
		 * //TODO } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("MaisPar1");
		// MaisPar1 -> ParVet MaisPar | ParVar MaisPar
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ParVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.MaisPar();
		} else {
			mAuxRetorno = this.ParVar();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.MaisPar();
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ParVet() {
		/*
		 * if (TVar() == 1) { if (VVet() == 1) { return 1; } } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ParVet");
		// ParVet -> TVet VVet
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.TVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.VVet();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ParVar() {
		/*
		 * if (TVar() == 1) { if (VVar() == 1) { return 1; } } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ParVar");
		// ParVar -> TVar VVar
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.TVar();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.VVar();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno FuncRet() {
		/*
		 * if (lexico.proximoToken() == tk_dois_pontos) { if (T() == 1) { return
		 * 1; } } else { // VAZIO //TODO } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("FuncRet");
		// FuncRet -> :T | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_dois_pontos) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.T();
		} else {
			// VAZIO
			mAuxRetorno.setStatus(1);
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IniCod() {
		/*
		 * if (ICod() == 1) { if (IniCod() == 1) { return 1; } } else if (Cod()
		 * == 1) { return 1; } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("IniCod");
		// IniCod -> ICod IniCod | Cod
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ICod();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.IniCod();
		} else {
			mAuxRetorno = this.Cod();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ICod() {
		/*
		 * if (T() == 1) { if (V() == 1) { if (ACod1() == 1) { return 1; } } }
		 * return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ICod");
		// ICod -> T V ACod;
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.T();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.ACod();
				if (mAuxRetorno.getStatus() == 1) {
					if (getInstance().proximoToken() == tk_ponto_e_virgula) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou o ponto e vírgula na inicialização da variavel");
					}
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ACod() {
		/*
		 * if (lexico.proximoToken() == tk_igual) { if (ACod1() == 1) { return
		 * 1; } } else { // VAZIO //TODO } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ACod");
		// ACod -> = ACod1 | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_igual) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.ACod1();
		} else {
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ACod1() {
		/*
		 * if (Ident() == 1) { return 1; } else if (Op3() == 1) { return 1; }
		 * else if (FuncCall() == 1) { return 1; } return 0;
		 */

		// ACod1 -> Ident | Op3 | FuncCall
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.Ident();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = this.Op3();
			if (mAuxRetorno.getStatus() != 1) {
				mAuxRetorno = this.FuncCall();
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Cod() {
		/*
		 * if (ComandC() == 1) { if (Cod() == 1) { return 1; } } else if
		 * (ComandD() == 1) { if (Cod() == 1) { return 1; } } else if (ComandA()
		 * == 1) { if (lexico.proximoToken() == tk_ponto_e_virgula) { if (Cod()
		 * == 1) { return 1; } } } else if (FuncCall() == 1) { if
		 * (lexico.proximoToken() == tk_ponto_e_virgula) { if (Cod() == 1) {
		 * return 1; } } } else { // VAZIO //TODO }
		 * 
		 * return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("Cod");
		// Cod -> ComandC Cod | ComandD Cod | ComandA; Cod | FuncCall; Cod | &
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ComandC();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.Cod();
		} else {
			mAuxRetorno = this.ComandD();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.Cod();
			} else {
				mAuxRetorno = this.ComandA();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						mAuxRetorno = this.Cod();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou ponto e virgula ComandA");
					}
				} else {
					mAuxRetorno = this.FuncCall();
					if (mAuxRetorno.getStatus() == 1) {
						if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.Cod();
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Faltou ponto e virgula FuncCall");
						}
					} else {
						mAuxRetorno.setStatus(1);
					}
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandD() {
		/*
		 * if (lexico.proximoToken() == tk_quetal) { if (lexico.proximoToken()
		 * == tk_abreparenteses) { if (Log() == 1) { if (lexico.proximoToken()
		 * == tk_fechaparenteses) { if (lexico.proximoToken() == tk_abrechaves)
		 * { if (Cod() == 1) { if (lexico.proximoToken() == tk_fechachaves) { if
		 * (ComandD1() == 1) { return 1; } } } } } } } } else if
		 * (lexico.proximoToken() == tk_xispa) { if (lexico.proximoToken() ==
		 * tk_ponto_e_virgula) { return 1; } } else if (lexico.proximoToken() ==
		 * tk_despacho) { if (ComandD2() == 1) { return 1; } } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ComandD");
		// ComandD -> quetal(Log){Cod} ComandD1 | xispa; | despacho ComandD2;
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_quetal) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
									getInstance().consumirLexema();
									getInstance().consumirToken();
									mAuxRetorno = this.ComandD1();
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno
											.setDescricaoErro("Faltou fehca chaves no quetal");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Faltou abre chaves no quetal");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou o fecha parenteses no quetal");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno
						.setDescricaoErro("Faltou abre parenteses no quetal");
			}
		} else if (getInstance().proximoToken() == tk_xispa) {
			consumirTudo();
			if (getInstance().proximoToken() == tk_ponto_e_virgula) {
				consumirTudo();
				mAuxRetorno.setStatus(1);
			}
		} else if (Lexico.getInstance().proximoToken() == tk_despacho) {
			consumirTudo();
			mAuxRetorno = this.ComandD2();
			if (mAuxRetorno.getStatus() == 1){
				if (getInstance().proximoToken() == tk_ponto_e_virgula){
					consumirTudo();
					mAuxRetorno.setStatus(1);
				} else{
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Faltou o ponto e virgula no despacho");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandD1() {
		/*
		 * if (lexico.proximoToken() == tk_capaz) { if (ComandD3() == 1) {
		 * return 1; } } return 0;
		 */
		// ComandD1 -> capaz ComandD3 | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_capaz) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.ComandD3();
		} else{
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandD2() {
		/*
		 * if (Log() == 1) { return 1; } else { // VAZIO //TODO } return 0;
		 */
		// ComandD2 -> log | &
		Retorno mAuxRetorno = new Retorno();
		mAuxRetorno = this.Log();
		if (mAuxRetorno.getStatus() != 1) {
			// REPRESENTA O VAZIO
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandD3() {
		/*
		 * if (lexico.proximoToken() == tk_abrechaves) { if (Cod() == 1) { if
		 * (lexico.proximoToken() == tk_fechachaves) { return 1; } } } else if
		 * (lexico.proximoToken() == tk_abreparenteses) { if (Log() == 1) { if
		 * (lexico.proximoToken() == tk_fechaparenteses) { if
		 * (lexico.proximoToken() == tk_abreparenteses) { if (Cod() == 1) { if
		 * (lexico.proximoToken() == tk_fechaparenteses) { if (ComandD1() == 1)
		 * { return 1; } } } } } } } else { // VAZIO //TODO } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ComandD3");
		// ComandD3 -> {Cod} | (log){Cod} ComandD1
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.Cod();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
					getInstance().consumirLexema();
					getInstance().consumirToken();					
				} else{
					mAuxRetorno.setStatus(0);
					mAuxRetorno
							.setDescricaoErro("Falta fecha chaves no capaz ComandD3");
				}
			}
		} else if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.Log();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						mAuxRetorno = this.Cod();
						if (mAuxRetorno.getStatus() == 1) {
							if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
								getInstance().consumirLexema();
								getInstance().consumirToken();
								mAuxRetorno = this.ComandD1();
							} else {
								mAuxRetorno.setStatus(0);
								mAuxRetorno
										.setDescricaoErro("Faltou fecha chaves no capaz ComandD3");
							}
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou abre chaves no capaz ComandD3");
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno
							.setDescricaoErro("Faltou fecha parentes no capaz ComandD3");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandC() {
		/*
		 * if (lexico.proximoToken() == tk_trova) { if (lexico.proximoToken() ==
		 * tk_abreparenteses) { if (Ident() == 1) { if (lexico.proximoToken() ==
		 * tk_fechaparenteses) { if (lexico.proximoToken() ==
		 * tk_ponto_e_virgula) { return 1; } } } } } else if
		 * (lexico.proximoToken() == tk_voltear) { if (lexico.proximoToken() ==
		 * tk_abreparenteses) { if (Log() == 1) { if (lexico.proximoToken() ==
		 * tk_fechaparenteses) { if (lexico.proximoToken() == tk_abrechaves) {
		 * if (Cod() == 1) { if (lexico.proximoToken() == tk_fechachaves) {
		 * return 1; } } } } } } } else if (lexico.proximoToken() ==
		 * tk_largatear) { if (lexico.proximoToken() == tk_abreparenteses) { if
		 * (IniComand() == 1) { if (lexico.proximoToken() == tk_fechaparenteses)
		 * { if (lexico.proximoToken() == tk_hasta) { if (Ident() == 1) { if
		 * (lexico.proximoToken() == tk_abrechaves) { if (Cod() == 1) { if
		 * (lexico.proximoToken() == tk_fechachaves) { return 1; } } } } } } } }
		 * } return 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("ComandC");
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand)
		// hasta Ident {Cod}
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_trova) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.Ident();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
							consumirTudo();							
						} else{
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou ponto e virgula no trova ComandC");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou fechar parenteses no trova ComandC");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno
						.setDescricaoErro("Faltou abrir parenteses no trova ComandC");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_voltear) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
									getInstance().consumirLexema();
									getInstance().consumirToken();
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno
											.setDescricaoErro("Faltou fecha chaves no voltear");									
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Faltou abre chaves no voltear");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou fecha parenteses no voltear");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno
						.setDescricaoErro("Faltou abre parenteses no voltear");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_largatear) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.IniComand();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						if (Lexico.getInstance().proximoToken() == tk_hasta) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.Ident();
							if (mAuxRetorno.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
									getInstance().consumirLexema();
									getInstance().consumirToken();
									mAuxRetorno = this.Cod();
									if (mAuxRetorno.getStatus() == 1) {
										if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
											getInstance().consumirLexema();
											getInstance().consumirToken();
										} else {
											mAuxRetorno.setStatus(0);
											mAuxRetorno
													.setDescricaoErro("Faltou fecha chaves no largatear");											
										}
									}
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno
											.setDescricaoErro("Faltou abre chaves no largatear");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno
									.setDescricaoErro("Faltou o hasta no largatear");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno
								.setDescricaoErro("Faltou fecha parenteses no largatear");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno
						.setDescricaoErro("Faltou abre parenteses no largatear");
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IniComand() {
		/*
		 * if (ComandA() == 1) { return 1; } else if (V() == 1) { return 1; }
		 * return 0;
		 * /
		// IniComand -> ComandA | V
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ComandA();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = this.V();
		}

		return mAuxRetorno; */
		// IniComand -> V IniComand’
		DesktopFrameWork.getInstance().addSintatico("IniComand");
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.IniComandLinha();
		}

		return mAuxRetorno;
	}
	
	@Override
	Retorno IniComandLinha() {
		// IniComand’ -> = ACod1 | &
		DesktopFrameWork.getInstance().addSintatico("IniComandLinha");
		
		Retorno mAuxRetorno = new Retorno();
		
		if (getInstance().proximoToken() == tk_igual){
			consumirTudo();
			
			mAuxRetorno = ACod1();
		} else{
//			VAZIO
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandA() {
		/*
		 * if (ComandALinha() == 1) { if (lexico.proximoToken() == tk_igual) {
		 * if (ACod1() == 1) { return 1; } } } else if (lexico.proximoToken() ==
		 * tk_aprochegar) { if (ComandALinha() == 1) { return 1; } } else if
		 * (lexico.proximoToken() == tk_arregar) { if (ComandALinha() == 1) {
		 * return 1; } } return 0;
		 */
		// ComandA -> ComandA’ = ACod1 | aprochegar ComandA’ | arregar ComandA’
		DesktopFrameWork.getInstance().addSintatico("ComandA");
		Retorno mAuxRetorno = new Retorno();

//		mAuxRetorno = this.ComandALinha();
		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() == 1) {
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.ACod1();
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou o sinal de atribuicao");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_aprochegar) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.ComandALinha();
		} else if (Lexico.getInstance().proximoToken() == tk_arregar) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.ComandALinha();
		}
		return mAuxRetorno;
	}

	@Override
	Retorno ComandALinha() {
		/*
		 * if (V() == 1) { return 1; } else if (Vet() == 1) { return 1; } return
		 * 0;
		 */
		// ComandA -> V | Vet
		DesktopFrameWork.getInstance().addSintatico("ComandALinha");
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = this.Vet();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno FuncCall() {
		/*
		 * if (V() == 1) { if (lexico.proximoToken() == tk_abreparenteses) { if
		 * (FuncPar() == 1) { if (lexico.proximoToken() == tk_fechaparenteses) {
		 * return 1; } } } } return 0;
		 */
		// FuncCall -> V(FuncPar);
		DesktopFrameWork.getInstance().addSintatico("FuncCall");
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() == 1) {
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				mAuxRetorno = this.FuncPar();
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {						
						getInstance().consumirToken();
						getInstance().consumirLexema();
					} else {
						
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha parenteses na chamada da funcao");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno
						.setDescricaoErro("Faltou abre parenteses na chamada da funcao");
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno FuncPar() {
		/*
		 * if (Ident() == 1) { if (MaisFuncPar() == 1) { return 1; } } return 0;
		 */
		// FuncPar-> Ident MaisFuncPar
		DesktopFrameWork.getInstance().addSintatico("FuncPar");
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.Ident();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.MaisFuncPar();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno MaisFuncPar() {
		/*
		 * if (lexico.proximoToken() == tk_virgula) { if (Ident() == 1) { if
		 * (MaisFuncPar() == 1) { return 1; } } } else { // VAZIO //TODO }
		 * return 0;
		 */
		// MaisFuncPar -> , Ident MaisFuncPar | &
		DesktopFrameWork.getInstance().addSintatico("MaisFuncPar");
		Retorno mAuxRetorno = new Retorno();
		Retorno tempRetorno = mAuxRetorno;

		if (Lexico.getInstance().proximoToken() == tk_virgula) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			mAuxRetorno = this.Ident();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.MaisFuncPar();
			}
		} else {
			// VAZIO
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Log() {
		DesktopFrameWork.getInstance().addSintatico("Log");
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Op1().getStatus() == 1) {
			if (this.LogLinha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno LogLinha() {
		DesktopFrameWork.getInstance().addSintatico("LogLinha");
		// LogLINHA -> && Op1 LogLINHA | || Op1 LogLINHA | &
		Retorno retorno = new Retorno();		

		if (getInstance().proximoToken() == tk_e_comm){
			retorno = Op1();
			if (retorno.getStatus() == 1){
				retorno = LogLinha();
			}
		} else if (getInstance().proximoToken() == tk_barras){
			retorno = Op1();
			if (retorno.getStatus() == 1){
				retorno = LogLinha();
			}
		} else{		
//			VAZIO
			retorno.setStatus(1);
		}
		
		return retorno;
	}

	@Override
	Retorno Op1() {
		DesktopFrameWork.getInstance().addSintatico("Op1");
		// Op1 -> Op2 Op1’
		Retorno retorno = new Retorno();
		
		retorno = this.Op2(); 
		if (retorno.getStatus() == 1) {
			retorno = this.Op1Linha();
		}
		
		return retorno;
	}

	@Override
	Retorno Op1Linha() {
		DesktopFrameWork.getInstance().addSintatico("Op1Linha");
		// Op1’ -> == Op2 Op1’ | != Op2 Op1’ | &
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_igual) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				retorno = this.Op2(); 				
				if (retorno.getStatus() == 1) {
					retorno = this.Op1Linha();
				}
			} else {
				retorno.setDescricaoErro("eh esperado um sinal de igual.");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_fatorial) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				getInstance().consumirLexema();
				getInstance().consumirToken();
				retorno = this.Op2(); 
				if (retorno.getStatus() == 1) {
					retorno = this.Op1Linha();
				}
			} else {
				retorno.setDescricaoErro("Depois do fatorial tem q vir um simbolo de IGUAL.");
			}
		} else {
			// vazio
			retorno.setStatus(1);
		}
		
		return retorno;
	}

	@Override
	Retorno Op2() {
		DesktopFrameWork.getInstance().addSintatico("Op2");
		// Op2 -> Op3 Op2’
		Retorno retorno = new Retorno();
		
		retorno = this.Op3(); 
		if (retorno.getStatus() == 1) {
			retorno = this.Op2Linha();
		}
		
		return retorno;
	}

	@Override
	Retorno Op2Linha() {
		DesktopFrameWork.getInstance().addSintatico("Op2Linha");
		// Op2’ -> > Op3 Op2’ | < Op3 Op2’ | >= Op3 Op2’ | <= Op3 Op2’ | &
		Retorno retorno = new Retorno();
		
//		VAZIO
		retorno.setStatus(1); 

		if (getInstance().lookAhead() == tk_igual){
			if (Lexico.getInstance().proximoToken() == tk_maior) {
				consumirTudo();
				consumirTudo(); // consome o igual
				retorno = this.Op3(); 
				if (retorno.getStatus() == 1) {
					retorno = this.Op2Linha();
				} 
			} else if (Lexico.getInstance().proximoToken() == tk_menor) {
				consumirTudo();
				consumirTudo(); // consome o igual
				retorno = this.Op3(); 
				if (retorno.getStatus() == 1) {
					retorno = this.Op2Linha();
				} 
			}
		} else{
			if (Lexico.getInstance().proximoToken() == tk_maior) {
				consumirTudo();
				retorno = this.Op3(); 
				if (retorno.getStatus() == 1) {
					retorno = this.Op2Linha();
				} 
			} else if (Lexico.getInstance().proximoToken() == tk_menor) {
				consumirTudo();
				retorno = this.Op3(); 
				if (retorno.getStatus() == 1) {
					retorno = this.Op2Linha();
				} 
			}
		}
		
		return retorno;
	}

	@Override
	Retorno Op3() {
		DesktopFrameWork.getInstance().addSintatico("Op3");
		// Op3 -> Op4 Op3’
		Retorno retorno = new Retorno();
		
		retorno = this.Op4();
		
		if (retorno.getStatus() == 1) {
			retorno = this.Op3Linha();
		}
		
		return retorno;
	}

	@Override
	Retorno Op3Linha() {
		DesktopFrameWork.getInstance().addSintatico("Op3Linha");
//		Op3’ -> + Op4 Op3’ | - Op4 Op3’ | &
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_adicao) {
			consumirTudo();
			retorno = this.Op4(); 
			if (retorno.getStatus() == 1) {
				retorno = this.Op3Linha();
			}
		} else if (Lexico.getInstance().proximoToken() == tk_subtr) {
			consumirTudo();
			retorno = this.Op4(); 
			if (retorno.getStatus() == 1) {
				retorno = this.Op3Linha();						
			}
		} else {
			// VAZIO
			retorno.setStatus(1);
		}
		return retorno;
	}

	@Override
	Retorno Op4() {
		DesktopFrameWork.getInstance().addSintatico("Op4");
//		Op4 -> Un Op4’
		Retorno retorno = new Retorno();
		
		retorno = Un();
		if (retorno.getStatus() == 1) {
			retorno = Op4Linha();
		}
		
		return retorno;
	}

	@Override
	Retorno Op4Linha() {
		DesktopFrameWork.getInstance().addSintatico("Op4Linha");
//		Op4’-> * Un Op4’ | / Un Op4’ | &
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_mult) {
			consumirTudo();
			retorno = this.Un();
			if (retorno.getStatus() == 1) {
				retorno = this.Op4Linha();
			}
		} else if (Lexico.getInstance().proximoToken() == tk_divisao) {
			consumirTudo();
			retorno = this.Un(); 
			if (retorno.getStatus() == 1) {
				retorno = this.Op4Linha();
			}
		} else{
//			VAZIO
			retorno.setStatus(1);
		}			
		
		return retorno;
	}

	@Override
	Retorno Un() {
		DesktopFrameWork.getInstance().addSintatico("Un");
		// Un -> -V | +V | !V | true | false | P
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_subtr) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (this.V().getStatus() == 1) {
				retorno.setStatus(1);
			}
		} else if (getInstance().proximoToken() == tk_adicao){
			consumirTudo();
			retorno = V();
		} else if (getInstance().proximoToken() == tk_fatorial){
			consumirTudo();
			retorno = V();
		} else if (getInstance().proximoToken() == tk_true){
			consumirTudo();
			retorno.setStatus(1);
		} else if (getInstance().proximoToken() == tk_false){
			consumirTudo();
			retorno.setStatus(1);
		} else {
			retorno = P();
		}
		
		return retorno;
	}

	@Override
	Retorno P() {
		DesktopFrameWork.getInstance().addSintatico("P");
//		P -> Ident P’ | (Log)
		Retorno retorno = new Retorno();
		
		if (getInstance().proximoToken() == tk_abreparenteses) {
			consumirTudo();
			retorno = this.Log();
			if (retorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					consumirTudo();
					retorno.setStatus(1);
				} else{
					retorno.setStatus(0);
					retorno.setDescricaoErro("Faltou fechar o parentese");
				}
			}
		} else{
			retorno = this.Ident(); 
			if (retorno.getStatus() == 1) {
				retorno = this.PLinha();
			}
		}
		
		return retorno;
	}

	@Override
	Retorno PLinha() {
		DesktopFrameWork.getInstance().addSintatico("PLinha");
//		P’ -> (Log) | &
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
			consumirTudo();
			retorno = this.Log(); 
			if (retorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					consumirTudo();
					retorno.setStatus(1);
				} else {
					retorno.setDescricaoErro("Faltou fechar parenteses");
				}
			}
		} else {
//			VAZIO
			retorno.setStatus(1);
		}

		return retorno;
	}

	@Override
	Retorno Ident() {
		DesktopFrameWork.getInstance().addSintatico("Ident");
		// Ident -> V | C 
		Retorno retorno = new Retorno();
		
		retorno = this.V();
		if (retorno.getStatus() == 1) {
			
		} else{
			retorno = this.C();
		}
	
		return retorno;
	}

	@Override
	Retorno V() {
		DesktopFrameWork.getInstance().addSintatico("V");
		// V -> VVar | VVet
/*		Retorno retorno = new Retorno();

		retorno = this.VVar();
		if (retorno.getStatus() == 1) {
			retorno.setStatus(1);
		} else {
			retorno = this.VVet();
		}

		return retorno;*/
		
		Retorno retorno = new Retorno();

		if (getInstance().lookAhead() == tk_abrecolchetes){
			retorno = this.VVet();			
		} else{
			retorno = this.VVar();
		}
		
		return retorno;
	}

	@Override
	Retorno VVet() {
		DesktopFrameWork.getInstance().addSintatico("VVet");
		// VVet -> VVar Vet | VVar Vet Vet		
		Retorno retorno = new Retorno();
		
		/*retorno = this.VVar(); 
		if (retorno.getStatus() == 1) {
			retorno = this.Vet(); //ERRADO
			
			if (retorno.getStatus() == 1) {
				if (this.Vet().getStatus() == 1) {
					retorno.setStatus(1);
				}
				retorno.setStatus(1);
			}
		}
		return retorno;*/
		
		// VVet -> VVar IVetDime
		retorno = this.VVar(); 
		
		if (retorno.getStatus() == 1) {
			retorno = this.IVetDime();
		}
		
		return retorno;
	}

	@Override
	Retorno Vet() {
		DesktopFrameWork.getInstance().addSintatico("Vet");
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (Lexico.getInstance().proximoToken() == tk_abrecolchetes) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			if (this.Ident().getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechecolchetes) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					retorno.setStatus(1);
				} else {
					retorno.setDescricaoErro("Faltou fechar colchetes");
				}
			} else {
				retorno.setDescricaoErro("Faltou identificador");
			}
		}
		return retorno;
	}

	@Override
	Retorno VVar() {
		DesktopFrameWork.getInstance().addSintatico("VVar");
		Retorno retorno = new Retorno();
		if (getInstance().proximoToken() == tk_variavel) {

//			if (tipoVar != null) {
//				tipoVar.setNomeVar(getInstance().proximoLexema());
//			}
			
			Tipagem mAuxTipagem = new Tipagem();
			mAuxTipagem.setNomeVar(getInstance().proximoLexema());	
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno.setTipagem(mAuxTipagem);
			retorno.setStatus(1);
		}
		return retorno;
	}

	@Override
	Retorno C() {
		DesktopFrameWork.getInstance().addSintatico("C");
		// C-> num | “ ”
		Retorno retorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_numero) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setVlrVariavel(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);

			retorno.setStatus(1);

			consumirTudo();
		} else if (Lexico.getInstance().proximoToken() == tk_apas) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setVlrVariavel(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);
			retorno.setStatus(1);

			consumirTudo();
		} else {
			retorno.setStatus(0);
			retorno.setDescricaoErro("eh esperado um numero ou aspas.");
		}

		return retorno;
	}

	@Override
	Retorno T() {
		DesktopFrameWork.getInstance().addSintatico("T");
		// T -> TVar | TVet
		Retorno retorno = TVar();

		// nome val vvar
		// valor dela no c
		// se for variavel no tvar

		boolean isVar = retorno.getStatus() == 1;
		boolean isVetor = false;

		if (isVar) {
			retorno = TVet();
			if (retorno.getStatus() == 1) {
				isVetor = true;
			}
		}

		if (!isVar && !isVetor) {
			retorno.setStatus(0);
			retorno.setDescricaoErro("Não é variável nem vetor");
		} else {
			retorno.setStatus(1);
		}

		if (isVar || isVetor) {
			if (tipoVar != null)
				tipoVar.setTipoEntrada(TipoEntrada.VARIAVEL);
		}

		return retorno;
	}

	@Override
	Retorno TVet() {
		DesktopFrameWork.getInstance().addSintatico("TVet");
		// TVet -> borracho TVar | bolicho TVar
		Retorno retorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_borracho) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setTipoArray(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno = this.TVar();

		} else if (getInstance().proximoToken() == tk_bolicho) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setTipoArray(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno = this.TVar();
		}

		return retorno;
	}

	@Override
	Retorno TVar() {
		DesktopFrameWork.getInstance().addSintatico("TVar");
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (Lexico.getInstance().proximoToken() == tk_bueno) {

			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno.setStatus(1);
			Tipagem tipagem = new Tipagem();
			tipagem.setDesNomeTipoVal("bueno");	
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
		}
		if (Lexico.getInstance().proximoToken() == tk_pia) {
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno.setStatus(1);

			Tipagem tipagem = new Tipagem();
			tipagem.setDesNomeTipoVal("pia");
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
		}

		if (Lexico.getInstance().proximoToken() == tk_pila) {

			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno.setStatus(1);

			Tipagem tipagem = new Tipagem();
			tipagem.setDesNomeTipoVal("pila");
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
		}

		return retorno;
	}

	private void consumirTudo() {
		getInstance().consumirToken();
		getInstance().consumirLexema();
	}

}
