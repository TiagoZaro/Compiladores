package com.tche.ver;

import com.tche.Retorno;

public class Sintatico extends Funcoes {
	public Lexico lexico;

	@Override
	public Retorno Inicio() {
		Retorno mAuxRetorno = new Retorno();
		mAuxRetorno.setStatus(1);

		if (this.lexico.proximoToken() == tk_querencia) {
			if (this.lexico.proximoToken() == tk_abrechaves) {
				mAuxRetorno = this.Q();

				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() != tk_fechachaves) {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Falta fecha chaves no querencia");
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
					// vazio //TODO
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno FuncProt() {
		/*
		 * if (lexico.proximoToken() == tk_indiada) { if (V() == 1) { if
		 * (lexico.proximoToken() == tk_abreparenteses) { if (Par() == 1) { if
		 * (lexico.proximoToken() == tk_fechaparenteses) { if (FuncRet() == 1) {
		 * if (lexico.proximoToken() == tk_ponto_e_virgula) { return 1; } } } }
		 * } } } return 0;
		 */
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_indiada) {
			mAuxRetorno = this.V();

			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_abreparenteses) {
					mAuxRetorno = this.Par();
					if (mAuxRetorno.getStatus() == 1) {
						if (this.lexico.proximoToken() == tk_fechaparenteses) {
							mAuxRetorno = this.FuncRet();
							if (mAuxRetorno.getStatus() == 1) {
								if (this.lexico.proximoToken() != tk_ponto_e_virgula) {
									mAuxRetorno.setStatus(0);
									mAuxRetorno.setDescricaoErro("Falta ponto e virgula no indiada");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Falta fecha parenteses para a indiada");
						}
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta abre parenteses para a indiada");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IProt() {
		/*
		 * Retorno mAuxRetorno = new Retorno();
		 * 
		 * if (TVar() == 1) { if (V() == 1) { if (IProt1() == 1) { return 1; } }
		 * } return 0;
		 */
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.TVar();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				
//				// Exemplo adiciona na tabela de simbolos
//				Tipagem t = new Tipagem();
//				t.setDesNomeTipoVal("Pila");
//				t.setVlrVariavel(0);
//				t.setTipoEntrada(TipoEntrada.VARIAVEL);
//				String nome = "val";
//
//				addTable(t, nome);
					
				mAuxRetorno = this.IProt1();
			}
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IProt1() {
		/*
		 * if (lexico.proximoToken() == tk_virgula) { if (V() == 1) { if
		 * (IProt1() == 1) { return 1; } } } else if (AProt() == 1) { return 1;
		 * } return 0;
		 */
		// IProt1 -> , V Iprot1| AProt
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_virgula) {
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
				if (this.lexico.proximoToken() == tk_igual) {
					mAuxRetorno = this.IVetDime();
					if (mAuxRetorno.getStatus() == 1) {
						if (this.lexico.proximoToken() != tk_ponto_e_virgula) {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Falta o ponto e virgula no TVet");
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
		/*
		 * if (lexico.proximoToken() == tk_abrecolchetes) { if (C() == 1) { if
		 * (lexico.proximoToken() == tk_fechecolchetes) { if (IVetDimeLinha() ==
		 * 1) { return 1; } } } } return 0;
		 */
		// IVetDime -> [ C ] IvetDime?
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_abrecolchetes) {
			mAuxRetorno = this.C();
			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_fechecolchetes) {
					mAuxRetorno = this.IVetDimeLinha();
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
		/*
		 * if (lexico.proximoToken() == tk_abrecolchetes) { if (C() == 1) { if
		 * (lexico.proximoToken() == tk_fechecolchetes) { return 1; } } } else {
		 * // vazio //TODO } return 0;
		 */
		// IvetDime? -> [ C ] | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_abrecolchetes) {
			mAuxRetorno = this.C();
			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() != tk_fechecolchetes) {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Faltou fecha colchetes IVetDimeLinha");
				}
			}
		} else {
			// vazio //TODO
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
		// AProt -> = C | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_igual) {
			mAuxRetorno = this.C();
		} else {
			// Vazio //TODO
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
		// M -> tche{ IniCod } Func
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_tche) {
			if (this.lexico.proximoToken() == tk_abrechaves) {
				mAuxRetorno = this.IniCod();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() == tk_fechachaves) {
						mAuxRetorno = this.Func();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha chaves do tche");
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
		// Func -> indiada V (Par) FuncRet { IniCod } Func | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_indiada) {
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_abreparenteses) {
					mAuxRetorno = this.Par();
					if (mAuxRetorno.getStatus() == 1) {
						if (this.lexico.proximoToken() == tk_fechaparenteses) {
							mAuxRetorno = this.FuncRet();
							if (mAuxRetorno.getStatus() == 1) {
								if (this.lexico.proximoToken() == tk_abrechaves) {
									mAuxRetorno = this.IniCod();
									if (mAuxRetorno.getStatus() == 1) {
										if (this.lexico.proximoToken() == tk_fechachaves) {
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
		// MaisPar ->, MaisPar1
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_virgula) {
			mAuxRetorno = this.MaisPar1();
		} else {
			mAuxRetorno.setStatus(0);
			mAuxRetorno.setDescricaoErro("Faltou a virgula dos parametros MaisPar");
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
		// MaisPar1 -> ParVet MaisPar | ParVar MaisPar | &
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
	Retorno ParVet() {
		/*
		 * if (TVar() == 1) { if (VVet() == 1) { return 1; } } return 0;
		 */
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
		// FuncRet -> :T | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_dois_pontos) {
			mAuxRetorno = this.T();
		} else {
			// VAZIO //TODO
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IniCod() {
		/*
		 * if (ICod() == 1) { if (IniCod() == 1) { return 1; } } else if (Cod()
		 * == 1) { return 1; } return 0;
		 */
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
		// ICod -> T V ACod;
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.T();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.ACod();
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
		// ACod -> = ACod1 | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_igual) {
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
					if (this.lexico.proximoToken() == tk_ponto_e_virgula) {
						mAuxRetorno = this.Cod();
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou ponto e virgula ComandA");
					}
				} else {
					mAuxRetorno = this.FuncCall();
					if (mAuxRetorno.getStatus() == 1) {
						if (this.lexico.proximoToken() == tk_ponto_e_virgula) {
							mAuxRetorno = this.Cod();
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou ponto e virgula FuncCall");
						}
					} else {
						// VAZIO //TODO
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
		// ComandD -> quetal(Log){Cod} ComandD1 | xispa; | despacho ComandD2;
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_quetal) {
			if (this.lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = this.Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() == tk_fechaparenteses) {
						if (this.lexico.proximoToken() == tk_abrechaves) {
							mAuxRetorno = this.Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (this.lexico.proximoToken() == tk_fechachaves) {
									mAuxRetorno = this.ComandD1();
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno.setDescricaoErro("Faltou fehca chaves no quetal");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou abre chaves no quetal");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou o fecha parenteses no quetal");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses no quetal");
			}
		} else if (this.lexico.proximoToken() == tk_xispa) {
			if (this.lexico.proximoToken() == tk_ponto_e_virgula) {
				mAuxRetorno.setStatus(1);
			}
		} else if (this.lexico.proximoToken() == tk_despacho) {
			mAuxRetorno = this.ComandD2();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ComandD1() {
		/*
		 * if (lexico.proximoToken() == tk_capaz) { if (ComandD3() == 1) {
		 * return 1; } } return 0;
		 */
		// ComandD1 -> capaz ComandD3
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_capaz) {
			mAuxRetorno = this.ComandD3();
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
		// ComandD3 -> {Cod} | (log){Cod} ComandD1 | &
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_abrechaves) {
			mAuxRetorno = this.Cod();
			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() != tk_fechachaves) {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta fecha chaves no capaz ComandD3");
				}
			}
		} else if (this.lexico.proximoToken() == tk_abreparenteses) {
			mAuxRetorno = this.Log();
			if (mAuxRetorno.getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_fechaparenteses) {
					if (this.lexico.proximoToken() == tk_abrechaves) {
						mAuxRetorno = this.Cod();
						if (mAuxRetorno.getStatus() == 1) {
							if (this.lexico.proximoToken() == tk_fechachaves) {
								mAuxRetorno = this.ComandD1();
							} else {
								mAuxRetorno.setStatus(0);
								mAuxRetorno.setDescricaoErro("Faltou fecha chaves no capaz ComandD3");
							}
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou abre chaves no capaz ComandD3");
					}
				} else {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Faltou fecha parentes no capaz ComandD3");
				}
			}
		} else {
			// VAZIO
			mAuxRetorno.setStatus(1);
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
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand)
		// hasta Ident {Cod}
		Retorno mAuxRetorno = new Retorno();

		if (this.lexico.proximoToken() == tk_trova) {
			if (this.lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = this.Ident();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() == tk_fechaparenteses) {
						if (this.lexico.proximoToken() != tk_ponto_e_virgula) {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou ponto e virgula no trova ComandC");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fechar parenteses no trova ComandC");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abrir parenteses no trova ComandC");
			}
		} else if (this.lexico.proximoToken() == tk_voltear) {
			if (this.lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = this.Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() == tk_fechaparenteses) {
						if (this.lexico.proximoToken() == tk_abrechaves) {
							mAuxRetorno = this.Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (this.lexico.proximoToken() != tk_fechachaves) {
									mAuxRetorno.setStatus(0);
									mAuxRetorno.setDescricaoErro("Faltou fecha chaves no voltear");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou abre chaves no voltear");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha parenteses no voltear");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses no voltear");
			}
		} else if (this.lexico.proximoToken() == tk_largatear) {
			if (this.lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = this.IniComand();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() == tk_fechaparenteses) {
						if (this.lexico.proximoToken() == tk_hasta) {
							mAuxRetorno = this.Ident();
							if (this.Ident().getStatus() == 1) {
								if (this.lexico.proximoToken() == tk_abrechaves) {
									mAuxRetorno = this.Cod();
									if (mAuxRetorno.getStatus() == 1) {
										if (this.lexico.proximoToken() != tk_fechachaves) {
											mAuxRetorno.setStatus(0);
											mAuxRetorno.setDescricaoErro("Faltou fecha chaves no largatear");
										}
									}
								} else {
									mAuxRetorno.setStatus(0);
									mAuxRetorno.setDescricaoErro("Faltou abre chaves no largatear");
								}
							}
						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou o hasta no largatear");
						}
					} else {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha parenteses no largatear");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses no largatear");
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IniComand() {
		/*
		 * if (ComandA() == 1) { return 1; } else if (V() == 1) { return 1; }
		 * return 0;
		 */
		// IniComand -> ComandA | V
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ComandA();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = this.V();
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
		// ComandA -> ComandA= ACod1 | aprochegar ComandA | arregar ComandA
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.ComandALinha();
		if (mAuxRetorno.getStatus() == 1) {
			if (this.lexico.proximoToken() == tk_igual) {
				mAuxRetorno = this.ACod1();
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou o sinal de atribuicao");
			}
		} else if (this.lexico.proximoToken() == tk_aprochegar) {
			mAuxRetorno = this.ComandALinha();
		} else if (this.lexico.proximoToken() == tk_arregar) {
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
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() == 1) {
			if (this.lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = this.FuncPar();
				if (mAuxRetorno.getStatus() == 1) {
					if (this.lexico.proximoToken() != tk_fechaparenteses) {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha parenteses na chamada da funcao");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses na chamada da funcao");
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
		Retorno mAuxRetorno = new Retorno();
		Retorno tempRetorno = mAuxRetorno;

		if (this.lexico.proximoToken() == tk_virgula) {
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
		Retorno retorno = new Retorno();
		retorno.setStatus(1); // TODO
		// LogLINHA -> && Op1 LogLINHA | || Op1 LogLINHA | &
		return retorno;
	}

	@Override
	Retorno Op1() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Op2().getStatus() == 1) {
			if (this.Op1Linha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno Op1Linha() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_igual) {
			if (this.lexico.proximoToken() == tk_igual) {
				if (this.Op2().getStatus() == 1) {
					if (this.Op1Linha().getStatus() == 1) {
						retorno.setStatus(1);
					}
				}
			} else {
				retorno.setDescricaoErro("eh esperado um sinal de igual.");
			}
		} else if (this.lexico.proximoToken() == tk_fatorial) {
			if (this.lexico.proximoToken() == tk_igual) {
				if (this.Op2().getStatus() == 1) {
					if (this.Op1Linha().getStatus() == 1) {
						retorno.setStatus(1);
					}
				} else {
					retorno.setDescricaoErro("Esperando operacao.");
				}
			} else {
				retorno.setDescricaoErro("Depois do fatorial tem q vir um simbolo de IGUAL.");
			}
		} else {
			// vazio //TODO
		}
		return retorno;
	}

	@Override
	Retorno Op2() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Op3().getStatus() == 1) {
			if (this.Op2Linha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno Op2Linha() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_maior) {
			if (this.Op3().getStatus() == 1) {
				if (this.Op2Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			} else if (this.lexico.proximoToken() == tk_igual) {
				if (this.Op2Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			} else {
				retorno.setDescricaoErro("eh esperado um sinal de igual ou operando.");
			}
		} else if (this.lexico.proximoToken() == tk_menor) {
			if (this.Op3().getStatus() == 1) {
				if (this.Op2Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			} else if (this.lexico.proximoToken() == tk_igual) {
				if (this.Op2Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			} else {
				retorno.setDescricaoErro("eh esperado um sinal de igual ou operando.");
			}
		} else {
			// VAZIO //TODO
		}
		return retorno;
	}

	@Override
	Retorno Op3() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Op4().getStatus() == 1) {
			if (this.Op3Linha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno Op3Linha() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_adicao) {
			if (this.Op4().getStatus() == 1) {
				if (this.Op3Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			}
		} else if (this.lexico.proximoToken() == tk_subtr) {
			if (this.Op4().getStatus() == 1) {
				if (this.Op3Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			}
		} else {
			// VAZIO //TODO
		}
		return retorno;
	}

	@Override
	Retorno Op4() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Un().getStatus() == 1) {
			if (this.Op4Linha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno Op4Linha() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_mult) {
			if (this.Un().getStatus() == 1) {
				if (this.Op4Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			}
		} else if (this.lexico.proximoToken() == tk_divisao) {
			if (this.Un().getStatus() == 1) {
				if (this.Op4Linha().getStatus() == 1) {
					retorno.setStatus(1);
				}
			}
		}
		return retorno;
	}

	@Override
	Retorno Un() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_subtr) {
			if (this.V().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno P() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.Ident().getStatus() == 1) {
			if (this.PLinha().getStatus() == 1) {
				retorno.setStatus(1);
			}
		} else if (this.lexico.proximoToken() == tk_abreparenteses) {
			if (this.Log().getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_fechaparenteses) {
					retorno.setStatus(1);
				}
			}
		}
		return retorno;
	}

	@Override
	Retorno PLinha() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_abreparenteses) {
			if (this.Log().getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_fechaparenteses) {
					retorno.setStatus(1);
				} else {
					retorno.setDescricaoErro("Faltou fechar parenteses");
				}
			} else {
				retorno.setDescricaoErro("Erro Log()");
			}
		} else {
			retorno.setDescricaoErro("Faltou abrir parenteses");
		}
		return retorno;// TODO aceita vazio VER
	}

	@Override
	Retorno Ident() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.V().getStatus() == 1) {
			retorno.setStatus(1);
		} else if (this.C().getStatus() == 1) {
			retorno.setStatus(1);
		}
		return retorno;
	}

	@Override
	Retorno V() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.VVar().getStatus() == 1) {
			retorno.setStatus(1);
		} else if (this.VVet().getStatus() == 1) {
			retorno.setStatus(1);
		}
		return retorno;
	}

	@Override
	Retorno VVet() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.VVar().getStatus() == 1) {
			if (this.Vet().getStatus() == 1) {
				if (this.Vet().getStatus() == 1) {
					retorno.setStatus(1);
				}
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno Vet() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_abrecolchetes) {
			if (this.Ident().getStatus() == 1) {
				if (this.lexico.proximoToken() == tk_fechecolchetes) {
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
		Retorno retorno = new Retorno();
		// if (lexico.proximoToken() == tk_id) { // TODO
		// return 1;
		// }
		return retorno;
	}

	@Override
	Retorno C() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.lexico.proximoToken() == tk_numero || this.lexico.proximoToken() == tk_apas) {
			retorno.setStatus(1);
		} else {
			retorno.setDescricaoErro("eh esperado um numero ou aspas.");
		}
		return retorno;
	}

	@Override
	Retorno T() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if (this.TVar().getStatus() == 1) {
			if (this.TVet().getStatus() == 1) {
				retorno.setStatus(1);
			}
		}
		return retorno;
	}

	@Override
	Retorno TVet() {
		Retorno retorno = new Retorno();
		retorno.setStatus(0);
		if ((this.lexico.proximoToken() == tk_borracho && this.TVar().getStatus() == 1)
				|| (this.lexico.proximoToken() == tk_bolicho && this.TVar().getStatus() == 1)) {
			retorno.setStatus(1);
		}
		return retorno;
	}

	@Override
	Retorno TVar() {
		Retorno retorno = new Retorno();
		retorno.setStatus(1);
		if (this.lexico.proximoToken() == tk_bueno || this.lexico.proximoToken() == tk_pia || this.lexico.proximoToken() == tk_pila) {

		} else {
			retorno.setStatus(1);
		}
		return retorno;
	}

}
