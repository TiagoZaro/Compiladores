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
	public Retorno Inicio() throws Exception {
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
						throw new Exception("Falta fecha chaves no querencia");
					}
				}
			} else {
				throw new Exception("Falta abre chaves no querencia");

			}

		}

		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.M();
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno Q() throws Exception {		
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
				mAuxRetorno = this.Q(); // TODO chama ele mesmo e perde a referencia
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
	Retorno FuncProt() throws Exception{
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
	Retorno IProt() throws Exception {
		
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
				
				mAuxRetorno = AnalisadorSemantico.addTable(mAuxRetorno.getTipagem(), mAuxRetorno.getTipagem().getNomeVar());
				
				if (mAuxRetorno.getStatus() == 1){
					Retorno mAuxRetornoIProt1 = this.IProt1(mAuxRetorno.getTipagem().getDesNomeTipoVal());

					if (mAuxRetornoIProt1.getStatus() == 1) {
						mAuxRetorno.getTipagem().setVlrVariavel(mAuxRetornoIProt1.getTipagem().getVlrVariavel());
						AnalisadorSemantico.atualizarValorTable(mAuxRetorno.getTipagem(), mAuxRetorno.getTipagem().getNomeVar());
						
						if (getInstance().proximoToken() == tk_ponto_e_virgula) {
							consumirTudo();

						} else {
							mAuxRetorno.setStatus(0);
							mAuxRetorno.setDescricaoErro("Faltou o ponto e virgula na inicialização da variavel");
						}
					} else{
						mAuxRetorno = mAuxRetornoIProt1;
					}
				} else{
					mAuxRetorno.setStatus(0);
				}
			}
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IProt1(String pTipoVariavel) throws Exception {
		DesktopFrameWork.getInstance().addSintatico("IProt1");
		/*
		 * if (lexico.proximoToken() == tk_virgula) { if (V() == 1) { if
		 * (IProt1() == 1) { return 1; } } } else if (AProt() == 1) { return 1;
		 * } return 0;
		 */
		// IProt1 -> , V Iprot1| AProt
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_virgula) {
			consumirTudo();
			Retorno mAuxRetornoV = this.V();

			if (mAuxRetornoV.getStatus() == 1) {
				
				mAuxRetorno = mAuxRetornoV;
				mAuxRetorno.getTipagem().setDesNomeTipoVal(pTipoVariavel);
				
				mAuxRetorno = AnalisadorSemantico.addTable(mAuxRetorno.getTipagem(), mAuxRetorno.getTipagem().getNomeVar());
				
				Retorno mAuxRetornoIProt1 = this.IProt1(pTipoVariavel);
				
				if (mAuxRetornoIProt1.getStatus() == 1){
					mAuxRetorno.getTipagem().setVlrVariavel(mAuxRetornoIProt1.getTipagem().getVlrVariavel());
					AnalisadorSemantico.atualizarValorTable(mAuxRetorno.getTipagem(), mAuxRetorno.getTipagem().getNomeVar());
				} else{
					mAuxRetorno = mAuxRetornoIProt1;
				}
			}
		} else {
			mAuxRetorno = this.AProt();
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IVet() throws Exception{
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
							throw new Exception("Falta o ponto e virgula no TVet");
						}
					}
				} else {
					throw new Exception("Falta o sinal de igual TVet");
				}
			}
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IVetDime() throws Exception {
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
			
			try {
				Tipagem tipagem = new Tipagem();
				tipagem.setDimensao(Integer.valueOf(getInstance().proximoLexema()));
				
				getInstance().consumirToken();
				getInstance().consumirLexema();
				
				mAuxRetorno.setStatus(1);
				mAuxRetorno.setTipagem(tipagem);
			} catch (Exception e) {
				// mAuxRetorno = this.C(); o valor númerico já é tratado na excecao
				throw new Exception("Dimensão deve ser numérica!");
			}
			
//			mAuxRetorno = this.Ident();
			if (mAuxRetorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechecolchetes) {
					getInstance().consumirLexema();
					getInstance().consumirToken();
					System.out.println(getInstance().proximoLexema());
					mAuxRetorno = this.IVetDimeLinha();
					
					
				} else {
					throw new Exception("Faltou fecha colchetes IVetDime");
				}
			}
		} else {
			throw new Exception("Faltou abre colchetes IVetDime");
		}

		return mAuxRetorno;
	}

	@Override
	Retorno IVetDimeLinha() throws Exception{
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
			mAuxRetorno = this.C();
//			mAuxRetorno = this.Ident();
			
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
	Retorno AProt() throws Exception{
		/*
		 * if (lexico.proximoToken() == tk_igual) { if (C() == 1) { return 1; }
		 * } else { // Vazio //TODO } // TODO Auto-generated method stub return
		 * 0;
		 */
		DesktopFrameWork.getInstance().addSintatico("AProt");
		// AProt -> = C | &
		Retorno mAuxRetorno = new Retorno();		

		if (Lexico.getInstance().proximoToken() == tk_igual) {
			consumirTudo();
			mAuxRetorno = this.C();
			
//			Tipagem tip = new Tipagem();
//			tip.setVlrVariavel(mAuxRetorno.getTipagem().getVlrVariavel());
//			mAuxRetorno.setTipagem(tip);
		} else {
			// Vazio
			mAuxRetorno.setStatus(1);
			mAuxRetorno.setTipagem(new Tipagem());
		}

		return mAuxRetorno;
	}

	@Override
	Retorno M() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("M");
		// M -> tche{ IniCod } Func
		Retorno mAuxRetorno = new Retorno();

		if (getInstance().proximoToken() == tk_tche) {
			consumirTudo();

			if (getInstance().proximoToken() == tk_abrechaves) {
				consumirTudo();
				Retorno mAuxRetornoIniCod = this.IniCod();
				if (mAuxRetornoIniCod.getStatus() == 1) { 
					if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
						consumirTudo();
						Retorno mAuxRetornoFunc = this.Func();
						if (mAuxRetornoFunc.getStatus() == 1){
							mAuxRetorno = mAuxRetornoFunc.clone();
							mAuxRetorno.setCodigo(mAuxRetornoIniCod.getCodigo() + mAuxRetornoFunc.getCodigo());
						} else{
							mAuxRetorno = mAuxRetornoFunc;
						}
					} else {
						throw new Exception("Faltou fecha chaves do tche");
					}
				} else{
					mAuxRetorno = mAuxRetornoIniCod;
				}
			} else {
				throw new Exception("Faltou abre chaves do tche");
			}
		} else {
			throw new Exception("Faltou o Tche!!!");
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno Func() throws Exception{
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
	Retorno Par() throws Exception{
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
	Retorno MaisPar() throws Exception {
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
	Retorno MaisPar1() throws Exception{
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
	Retorno ParVet() throws Exception{
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
	Retorno ParVar() throws Exception {
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
	Retorno FuncRet() throws Exception{
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
	Retorno IniCod() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("IniCod");
		// IniCod -> ICod IniCod | Cod
		Retorno mAuxRetorno = new Retorno();

		Retorno mAuxRetornoICod = this.ICod();
		if (mAuxRetornoICod.getStatus() == 1) {
			Retorno mAuxRetornoIniCod = this.IniCod();
			
			if (mAuxRetornoIniCod.getStatus() == 1){
				mAuxRetorno = mAuxRetornoIniCod.clone();
				mAuxRetorno.setCodigo(mAuxRetornoICod.getCodigo() + mAuxRetornoIniCod.getCodigo());
			} else{
				mAuxRetorno = mAuxRetornoIniCod;
			}
		} else {
			mAuxRetorno = this.Cod("");
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ICod() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ICod");
		// ICod -> T V ACod;
		Retorno mAuxRetorno = new Retorno();

		mAuxRetorno = this.T();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = this.V();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = this.ACod(mAuxRetorno.getTipagem().getNomeVar());
				if (mAuxRetorno.getStatus() == 1) {
					if (getInstance().proximoToken() == tk_ponto_e_virgula) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
					} else {
						throw new Exception("Faltou o ponto e vírgula na inicialização da variavel");
					}
				}
			}
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ACod(String pVariavel) throws Exception{ 
		DesktopFrameWork.getInstance().addSintatico("ACod");
		// ACod -> = ACod1 | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_igual) {
			consumirTudo();
			Retorno mAuxRetornoACod1 = this.ACod1();
			if (mAuxRetornoACod1.getStatus() == 1){
				String mAuxC3E 		= pVariavel + " := " + mAuxRetornoACod1.getTipagem().getNomeVar()+"\n";
				String mAuxCodigo 	= mAuxRetornoACod1.getCodigo() + mAuxC3E;
				
//				DesktopFrameWork.getInstance().addC3E(mAuxC3E);
				mAuxRetorno = mAuxRetornoACod1.clone();
				mAuxRetorno.setCodigo(mAuxCodigo);
				DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
			}
		} else {
			mAuxRetorno.setStatus(1);
		}

		return mAuxRetorno;
	}

	@Override
	Retorno ACod1() throws Exception {
		DesktopFrameWork.getInstance().addSintatico("ACod1");
		// ACod1 -> Op3 | FuncCall
		Retorno mAuxRetorno = new Retorno();
		
		Retorno mAuxRetornoOp3 = Op3();
		if (mAuxRetornoOp3.getStatus() == 1){
			mAuxRetorno = mAuxRetornoOp3.clone();
			mAuxRetorno.setCodigo(mAuxRetornoOp3.getCodigo());
		} else{
			Retorno mAuxRetornoFuncCall = FuncCall();
			mAuxRetorno = mAuxRetornoFuncCall.clone();
			mAuxRetorno.setCodigo(mAuxRetornoFuncCall.getCodigo());
		}

		return mAuxRetorno;
	}

	@Override
	Retorno Cod(String pNext) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Cod");
		// Cod -> ComandC Cod | ComandD Cod | ComandA; Cod | FuncCall; Cod | &
		Retorno mAuxRetorno = new Retorno();

		Retorno mAuxRetornoComandC = this.ComandC();
		if (mAuxRetornoComandC.getStatus() == 1) {
			Retorno mAuxRetornoCod = this.Cod("");
			if (mAuxRetornoCod.getStatus() == 1){
				mAuxRetorno = mAuxRetornoCod.clone();
				mAuxRetorno.setCodigo(mAuxRetornoComandC.getCodigo() + mAuxRetornoCod.getCodigo());
			} else{
				mAuxRetorno = mAuxRetornoCod;
			}
		} else {				
			Retorno mAuxRetornoComandA = this.ComandA();
			if (mAuxRetornoComandA.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
					consumirTudo();
					Retorno mAuxRetornoCod = this.Cod("");
					if (mAuxRetornoCod.getStatus() == 1){
						mAuxRetorno = mAuxRetornoCod.clone();
						mAuxRetorno.setCodigo(mAuxRetornoComandA.getCodigo() + mAuxRetornoCod.getCodigo());		
					} else{
						mAuxRetorno = mAuxRetornoCod;
					}
				} else {
					throw new Exception("Faltou ponto e virgula ComandA");
				}
			} else {
				Retorno mAuxRetornoFuncCall = this.FuncCall();
				if (mAuxRetornoFuncCall.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_ponto_e_virgula) {
						consumirTudo();
						Retorno mAuxRetornoCod = this.Cod("");
						if (mAuxRetornoCod.getStatus() == 1){
							mAuxRetorno = mAuxRetornoCod.clone();
							mAuxRetorno.setCodigo(mAuxRetornoFuncCall.getCodigo() + mAuxRetornoCod.getCodigo());	
						} else{
							mAuxRetorno = mAuxRetornoCod;
						}
					} else {
						throw new Exception("Faltou ponto e virgula FuncCall");
					}
				} else {
					String mAuxNext = TcheGlobal.criarLabel();
					Retorno mAuxRetornoComandD = this.ComandD(mAuxNext);
					if (mAuxRetornoComandD.getStatus() == 1) {
						Retorno mAuxRetornoCod = this.Cod(mAuxNext);
						
						if (mAuxRetornoCod.getStatus() == 1){
							String mAuxC3E = mAuxNext + ":\n";	
							
							mAuxRetorno = mAuxRetornoCod.clone();
							mAuxRetorno.setCodigo(mAuxRetornoComandD.getCodigo() + mAuxC3E + mAuxRetornoCod.getCodigo());
						} else{
							mAuxRetorno = mAuxRetornoCod;
						}				
					} else {
						mAuxRetorno.setStatus(1);
					}
				}
			}
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD(String pNext) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ComandD");
		// ComandD -> quetal(Log){Cod} ComandD1 | xispa; | despacho ComandD2;
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_quetal) {
			consumirTudo();
			if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
				consumirTudo();
				
				String mAuxFalse = TcheGlobal.criarLabel();				
				Retorno mAuxRetornoLog = this.Log(mAuxFalse);
				
				if (mAuxRetornoLog.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						consumirTudo();
						if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
							consumirTudo();
							
							Retorno mAuxRetornoCod = this.Cod(pNext);
							if (mAuxRetornoCod.getStatus() == 1) {
								if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
									consumirTudo();
									
									Retorno mAuxRetornoComandD1 = this.ComandD1(pNext);
									if (mAuxRetornoComandD1.getStatus() == 1){
										
										String mAuxCodigo = mAuxRetornoLog.getCodigo() 	+ "\n" + 
															mAuxRetornoCod.getCodigo()	+ "\n";
										String mAuxC3E = "goto " + pNext;
//										DesktopFrameWork.getInstance().addC3E(mAuxC3E);
										
										mAuxCodigo += mAuxC3E + "\n";
										mAuxC3E = mAuxFalse + ":";
//										DesktopFrameWork.getInstance().addC3E(mAuxC3E);
										
										mAuxCodigo += 	mAuxC3E 						+ "\n" + 
														mAuxRetornoComandD1.getCodigo() + "\n";	
										
										mAuxRetorno = mAuxRetornoComandD1.clone();
										mAuxRetorno.setCodigo(mAuxCodigo);
									} else{
										mAuxRetorno = mAuxRetornoComandD1;
									}									
								} else {
									throw new Exception("Faltou fehca chaves no quetal");
								}
							} else{
								mAuxRetorno = mAuxRetornoCod;
							}
						} else {
							throw new Exception("Faltou abre chaves no quetal");
						}
					} else {
						throw new Exception("Faltou o fecha parenteses no quetal");
					}
				} else{
					mAuxRetorno = mAuxRetornoLog;
				}					
			} else {
				throw new Exception("Faltou abre parenteses no quetal");
			}
		} else if (getInstance().proximoToken() == tk_xispa) {
			consumirTudo();
			if (getInstance().proximoToken() == tk_ponto_e_virgula) {
				consumirTudo();
				mAuxRetorno.setStatus(1);
			}
		} else if (Lexico.getInstance().proximoToken() == tk_despacho) {
			consumirTudo();
			mAuxRetorno = this.ComandD2(pNext);
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

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD1(String pNext) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ComandD1");
		// ComandD1 -> capaz ComandD3 | &
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_capaz) {
			consumirTudo();
			mAuxRetorno = this.ComandD3(pNext);
		} else{
			mAuxRetorno.setStatus(1);
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD2(String pNext) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ComandD2");
		// ComandD2 -> log | &
		Retorno mAuxRetorno = new Retorno();
		String mAuxFalse = TcheGlobal.criarLabel();
		
		Retorno mAuxRetornoLog = this.Log(mAuxFalse);
		if (mAuxRetornoLog.getStatus() == 1) {			
			String mAuxCodigo = mAuxRetornoLog.getCodigo()	+
								"goto " + pNext + "\n"		+
								mAuxFalse + ":\n";
			
			mAuxRetorno = mAuxRetornoLog.clone();
			mAuxRetorno.setCodigo(mAuxCodigo);
		} else{
			// REPRESENTA O VAZIO
			mAuxRetorno.setStatus(1);
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD3(String pNext) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ComandD3");
		// ComandD3 -> {Cod} | (log){Cod} ComandD1
		Retorno mAuxRetorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
			consumirTudo();
			Retorno mAuxRetornoCod = this.Cod(pNext);
			if (mAuxRetornoCod.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
					consumirTudo();		
					
					mAuxRetorno = mAuxRetornoCod.clone();
					mAuxRetorno.setCodigo(mAuxRetornoCod.getCodigo());
				} else{
					throw new Exception("Falta fecha chaves no capaz ComandD3");
				}
			} else{
				mAuxRetorno = mAuxRetornoCod;
			}
		} else if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
			consumirTudo();
			String mAuxFalse = TcheGlobal.criarLabel();			
			Retorno mAuxRetornoLog = this.Log(mAuxFalse);
			
			if (mAuxRetornoLog.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					consumirTudo();
					if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
						consumirTudo();
						
						Retorno mAuxRetornoCod = this.Cod(pNext);
						if (mAuxRetornoCod.getStatus() == 1) {
							if (Lexico.getInstance().proximoToken() == tk_fechachaves) {
								consumirTudo();
								Retorno mAuxRetornoComandD1 = this.ComandD1(pNext);
								if (mAuxRetornoComandD1.getStatus() == 1){
									String mAuxCodigo = mAuxRetornoLog.getCodigo() 	+ 
														mAuxRetornoCod.getCodigo() 	+
														"goto " + pNext + "\n"		+
														mAuxFalse + ":" + "\n"		+
														mAuxRetornoComandD1.getCodigo();
									
									mAuxRetorno = mAuxRetornoComandD1.clone();
									mAuxRetorno.setCodigo(mAuxCodigo);
								} else{
									mAuxRetorno = mAuxRetornoComandD1;
								}
							} else {
								throw new Exception("Faltou fecha chaves no capaz ComandD3");
							}
						} else{
							mAuxRetorno = mAuxRetornoCod;
						}
					} else {
						throw new Exception("Faltou abre chaves no capaz ComandD3");
					} 
				} else {
					throw new Exception("Faltou fecha parentes no capaz ComandD3");
				}
			} else{
				mAuxRetorno = mAuxRetornoLog;
			}
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ComandC() throws Exception{
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
				mAuxRetorno = this.Log("");
				if (mAuxRetorno.getStatus() == 1) {
					if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
						getInstance().consumirLexema();
						getInstance().consumirToken();
						if (Lexico.getInstance().proximoToken() == tk_abrechaves) {
							getInstance().consumirLexema();
							getInstance().consumirToken();
							mAuxRetorno = this.Cod("");
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
									mAuxRetorno = this.Cod("");
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
						throw new Exception("Faltou fecha parenteses no largatear");
					}
				}
			} else {
				throw new Exception("Faltou abre parenteses no largatear");
			}
		}

		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno IniComand() throws Exception{
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
	Retorno IniComandLinha() throws Exception{
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
	Retorno ComandA() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("ComandA");
		// ComandA -> V = ACod1 | aprochegar ComandA’ | arregar ComandA’
		Retorno mAuxRetorno = new Retorno();

//		mAuxRetorno = this.ComandALinha();
		mAuxRetorno = this.V();
		if (mAuxRetorno.getStatus() == 1) {	
			Tipagem mAuxTipagem = TcheGlobal.getMapaSimbolos().get(mAuxRetorno.getTipagem().getNomeVar());
			if (mAuxTipagem == null)
				throw new Exception("Variavel " + mAuxRetorno.getTipagem().getNomeVar() +" Não Declarada!");			
			
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				consumirTudo();
				Retorno mAuxRetornoACod1 = this.ACod1();
				
				if (mAuxRetornoACod1.getStatus() == 1){
					String mAuxC3E 		= mAuxTipagem.getNomeVar() + " := " + mAuxRetornoACod1.getTipagem().getNomeVar() + "\n";
					String mAuxCodigo 	= mAuxRetornoACod1.getCodigo() + mAuxC3E;
					
//					DesktopFrameWork.getInstance().addC3E(mAuxC3E);
					
					mAuxRetorno = mAuxRetornoACod1.clone();
					mAuxRetorno.setCodigo(mAuxCodigo);
				} else{				
					mAuxRetorno = mAuxRetornoACod1;
				}
			} else {
				throw new Exception("Faltou o sinal de atribuicao");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_aprochegar) {
			consumirTudo();
			mAuxRetorno = this.ComandALinha();
		} else if (Lexico.getInstance().proximoToken() == tk_arregar) {
			consumirTudo();
			mAuxRetorno = this.ComandALinha();
		}
		
		DesktopFrameWork.getInstance().addC3E(mAuxRetorno.getCodigo());
		return mAuxRetorno;
	}

	@Override
	Retorno ComandALinha() throws Exception{
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
	Retorno FuncCall() throws Exception{
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
						throw new Exception("Faltou fecha parenteses na chamada da funcao");
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
	Retorno FuncPar() throws Exception{
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
	Retorno MaisFuncPar() throws Exception{
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
	Retorno Log(String pFalse) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Log");
//		Log -> Op1 Log’
		Retorno retorno = new Retorno();		
		String mAuxTrue = TcheGlobal.criarLabel();
		
		Retorno mAuxRetornoOp1 = this.Op1(pFalse, mAuxTrue); 
		if (mAuxRetornoOp1.getStatus() == 1) {			
			Retorno mAuxRetornoLogLinha = this.LogLinha(pFalse, mAuxTrue);
			if (mAuxRetornoLogLinha.getStatus() == 1) {
//				String mAuxC3E 		= mAuxTrue + ":\n";			
				String mAuxC3E 		= "";
//				DesktopFrameWork.getInstance().addC3E(mAuxC3E);
				String mAuxCodigo 	= 	mAuxRetornoOp1.getCodigo() + mAuxC3E + mAuxRetornoLogLinha.getCodigo();
				
				retorno = mAuxRetornoLogLinha.clone();
				retorno.setCodigo(mAuxCodigo);
			} else{
				retorno = mAuxRetornoLogLinha;
			}
		} else{
			retorno = mAuxRetornoOp1;
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno LogLinha(String pFalse, String pTrue) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("LogLinha");
		// LogLINHA -> && Op1 LogLINHA | || Op1 LogLINHA | &
		Retorno retorno = new Retorno();		

		if (getInstance().proximoToken() == tk_e_comm){
			consumirTudo();
			String mAuxTrue = TcheGlobal.criarLabel();			
			Retorno mAuxRetornoOp1 = Op1(pFalse, mAuxTrue);
			if (mAuxRetornoOp1.getStatus() == 1){
				Retorno mAuxRetornoLogLinha = LogLinha(pFalse, mAuxTrue);
				if (mAuxRetornoLogLinha.getStatus() == 1){
					String mAuxC3E = mAuxTrue + ":\n";
					mAuxC3E = "";
					
					retorno = mAuxRetornoOp1.clone();
					retorno.setCodigo(mAuxC3E + mAuxRetornoOp1.getCodigo() + mAuxRetornoLogLinha.getCodigo());
				} else{
					retorno = mAuxRetornoLogLinha;
				}
			} else{
				retorno = mAuxRetornoOp1;
			}
		} else if (getInstance().proximoToken() == tk_barras){
			consumirTudo();
			String mAuxFalse = TcheGlobal.criarLabel();			
			Retorno mAuxRetornoOp1 = Op1(mAuxFalse, pTrue);
			if (mAuxRetornoOp1.getStatus() == 1){
				Retorno mAuxRetornoLogLinha = LogLinha(mAuxFalse, pTrue);
				if (mAuxRetornoLogLinha.getStatus() == 1){
					String mAuxC3E = mAuxFalse + ":\n";
//					mAuxC3E = "";
					
					retorno = mAuxRetornoOp1.clone();
					retorno.setCodigo(mAuxRetornoOp1.getCodigo() + mAuxC3E + mAuxRetornoLogLinha.getCodigo());
				} else{
					retorno = mAuxRetornoLogLinha;
				}
			} else{
				retorno = mAuxRetornoOp1;
			}
		} else{		
//			VAZIO
			retorno.setStatus(1);
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op1(String pFalse, String pTrue) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op1");
		// Op1 -> Op2 Op1’
		Retorno retorno = new Retorno();
		
		Retorno retornoOp2 = this.Op2(pFalse, pTrue); 
		if (retornoOp2.getStatus() == 1) {			
			Retorno retornoOp1Linha = this.Op1Linha(pFalse, pTrue, retornoOp2.getTipagem().getNomeVar());
			if (retornoOp1Linha.getStatus() == 1){
				retorno = retornoOp1Linha.clone();
				retorno.setCodigo(retornoOp2.getCodigo() + retornoOp1Linha.getCodigo());
			} else{
				retorno = retornoOp1Linha;
			}
		} else{
			retorno = retornoOp2;
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op1Linha(String pFalse, String pTrue, String pVariavel) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op1Linha");
		// Op1’ -> == Op2 Op1’ | != Op2 Op1’ | &
		Retorno retorno 	= new Retorno();
		Tipagem mAuxTipagem = new Tipagem();
		
		if (Lexico.getInstance().proximoToken() == tk_igual) {
			consumirTudo();
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				consumirTudo();
				Retorno retornoOp2 = this.Op2(pFalse, pTrue); 				
				if (retornoOp2.getStatus() == 1) {	
					String mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = "if " + pVariavel + " == " + retornoOp2.getTipagem().getNomeVar() + "goto " + mAuxTrue + "\n" +
										"goto " + pFalse + "\n" +
										mAuxTrue + ":\n";
					
					Retorno retornoOp1Linha = this.Op1Linha(pFalse, pTrue, retornoOp2.getTipagem().getNomeVar());
					
					if (retornoOp1Linha.getStatus() == 1){
						mAuxCodigo += retornoOp1Linha.getCodigo();
						
						retorno = retornoOp1Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp1Linha;
					}
				} else{
					retorno = retornoOp2;
				}
			} else {
				throw new Exception("eh esperado um sinal de igual.");
			}
		} else if (Lexico.getInstance().proximoToken() == tk_fatorial) {
			consumirTudo();
			if (Lexico.getInstance().proximoToken() == tk_igual) {
				consumirTudo();
				Retorno retornoOp2 = this.Op2(pFalse, pTrue); 				
				if (retornoOp2.getStatus() == 1) {	
					String mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = "if " + pVariavel + " != " + retornoOp2.getTipagem().getNomeVar() + "goto " + mAuxTrue + "\n" +
										"goto " + pFalse + "\n" +
										mAuxTrue + ":\n";
					
					Retorno retornoOp1Linha = this.Op1Linha(pFalse, pTrue, retornoOp2.getTipagem().getNomeVar());
					
					if (retornoOp1Linha.getStatus() == 1){
						mAuxCodigo += retornoOp1Linha.getCodigo();
						
						retorno = retornoOp1Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp1Linha;
					}
				} else{
					retorno = retornoOp2;
				}
			} else {
				throw new Exception("Depois do fatorial tem q vir um simbolo de IGUAL.");
			}
		} else {
			// vazio
			mAuxTipagem.setNomeVar(pVariavel);
			retorno.setTipagem(mAuxTipagem);
			retorno.setStatus(1);
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op2(String pFalse, String pTrue) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op2");
		// Op2 -> Op3 Op2’
		Retorno retorno = new Retorno();
		
		Retorno retornoOp3 = this.Op3(); 
		if (retornoOp3.getStatus() == 1) {
			Retorno retornoOp2Linha = this.Op2Linha(pFalse, pTrue, retornoOp3.getTipagem().getNomeVar());
			if(retornoOp2Linha.getStatus() == 1){ 
				retorno = retornoOp2Linha.clone();
				retorno.setCodigo(retornoOp3.getCodigo() + retornoOp2Linha.getCodigo());
			}
		} else{
			retorno = retornoOp3;
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op2Linha(String pFalse, String pTrue, String pVariavel) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op2Linha");
		// Op2’ -> > Op3 Op2’ | < Op3 Op2’ | >= Op3 Op2’ | <= Op3 Op2’ | &
		Retorno retorno 	= new Retorno();
		Tipagem mAuxTipagem = new Tipagem();
		String mAuxTrue 	= "";
		
//		VAZIO
		mAuxTipagem.setNomeVar(pVariavel);
		retorno.setTipagem(mAuxTipagem);
		retorno.setStatus(1); 

		if (getInstance().lookAhead() == tk_igual){
			if (Lexico.getInstance().proximoToken() == tk_maior) {
				consumirTudo();
				consumirTudo(); // consome o igual
				Retorno retornoOp3 = this.Op3(); 
				if (retornoOp3.getStatus() == 1) {
					mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = "if " + pVariavel + " >= " + retornoOp3.getTipagem().getNomeVar() + " goto " + mAuxTrue + "\n" +					
										"goto " 	+ pFalse 	+ "\n" +					
										mAuxTrue 	+ ":" 		+ "\n";
					
					Retorno retornoOp2Linha = this.Op2Linha(pFalse, pTrue, retornoOp3.getTipagem().getNomeVar());
					
					if (retornoOp2Linha.getStatus() == 1){
						mAuxCodigo += retornoOp2Linha.getCodigo();
						
						retorno = retornoOp2Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp2Linha;
					}
				}  
			} else if (Lexico.getInstance().proximoToken() == tk_menor) {
				consumirTudo();
				consumirTudo(); // consome o igual
				Retorno retornoOp3 = this.Op3(); 
				if (retornoOp3.getStatus() == 1) {
					mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = "if " + pVariavel + " <= " + retornoOp3.getTipagem().getNomeVar() + " goto " + mAuxTrue + "\n" +					
										"goto " 	+ pFalse 	+ "\n" +					
										mAuxTrue 	+ ":" 		+ "\n";
					
					Retorno retornoOp2Linha = this.Op2Linha(pFalse, pTrue, retornoOp3.getTipagem().getNomeVar());
					
					if (retornoOp2Linha.getStatus() == 1){
						mAuxCodigo += retornoOp2Linha.getCodigo();
						
						retorno = retornoOp2Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp2Linha;
					}
				}  
			}
		} else{
			if (Lexico.getInstance().proximoToken() == tk_maior) {
				consumirTudo();
				Retorno retornoOp3 = this.Op3(); 
				if (retornoOp3.getStatus() == 1) {
//					mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = "";
					String mAuxC3E = "if " + pVariavel + " > " + retornoOp3.getTipagem().getNomeVar() + " goto " + mAuxTrue;		
					mAuxCodigo += mAuxC3E + "\n";
					
					mAuxC3E = "goto " + pFalse;				
					mAuxCodigo += mAuxC3E + "\n";
					
					mAuxC3E = mAuxTrue + ":";
					mAuxCodigo += mAuxC3E + "\n";
					
					Retorno retornoOp2Linha = this.Op2Linha(pFalse, pTrue, retornoOp3.getTipagem().getNomeVar());
					
					if (retornoOp2Linha.getStatus() == 1){
						mAuxCodigo += retornoOp2Linha.getCodigo();
						
						retorno = retornoOp2Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp2Linha;
					}
				} 
			} else if (Lexico.getInstance().proximoToken() == tk_menor) {
				consumirTudo();
				Retorno retornoOp3 = this.Op3(); 
				if (retornoOp3.getStatus() == 1) {
					mAuxTrue = TcheGlobal.criarLabel();
					mAuxTrue = pTrue;
					String mAuxCodigo = retornoOp3.getCodigo() +
										"if " + pVariavel + " < " + retornoOp3.getTipagem().getNomeVar() + " goto " + mAuxTrue + "\n" +					
										"goto " 	+ pFalse 	+ "\n";					
//										mAuxTrue 	+ ":" 		+ "\n";
					
					Retorno retornoOp2Linha = this.Op2Linha(pFalse, pTrue, retornoOp3.getTipagem().getNomeVar());
					
					if (retornoOp2Linha.getStatus() == 1){
						mAuxCodigo += retornoOp2Linha.getCodigo();
						
						retorno = retornoOp2Linha.clone();
						retorno.setCodigo(mAuxCodigo);
					} else{
						retorno = retornoOp2Linha;
					}
				}  
			}
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op3() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op3");
		// Op3 -> Op4 Op3’
		Retorno retorno = new Retorno();
		
		Retorno retornoOp4 = this.Op4();
		
		if (retornoOp4.getStatus() == 1) {
			Retorno retornoOp3Linha = this.Op3Linha(retornoOp4.getTipagem().getNomeVar());
			if (retornoOp3Linha.getStatus() == 1){
				String mAuxCodigo = retornoOp4.getCodigo() + retornoOp3Linha.getCodigo();
				
				retorno = retornoOp3Linha.clone();
				retorno.setCodigo(mAuxCodigo);
			} else{
				retorno = retornoOp3Linha;
			}
		} else{
			retorno = retornoOp4;
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op3Linha(String pVariavel) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op3Linha");
//		Op3’ -> + Op4 Op3’ | - Op4 Op3’ | &
		Retorno retorno 	= new Retorno();
		Tipagem mAuxTipagem = new Tipagem();
		
		if (Lexico.getInstance().proximoToken() == tk_adicao) {
			consumirTudo();
			Retorno retornoOp4 = this.Op4(); 
			
			if (retornoOp4.getStatus() == 1) {
				String mAuxTemp = TcheGlobal.criarTmp();
				Retorno retornoOp3Linha = this.Op3Linha(mAuxTemp);	
				
				if (retornoOp3Linha.getStatus() == 1){
					String mAuxCodigo = mAuxTemp + " := " + pVariavel + " + " + retornoOp4.getTipagem().getNomeVar() + "\n" +
										retornoOp4.getCodigo() +
										retornoOp3Linha.getCodigo();
					retorno.setTipagem(retornoOp3Linha.getTipagem());
					retorno.setCodigo(mAuxCodigo);
					retorno.setStatus(1);
				} else{
					retorno = retornoOp3Linha;
				}				
			} else{
				retorno = retornoOp4;
			}
		} else if (Lexico.getInstance().proximoToken() == tk_subtr) {
			consumirTudo();
			Retorno retornoOp4 = this.Op4(); 
			
			if (retornoOp4.getStatus() == 1) {
				String mAuxTemp = TcheGlobal.criarTmp();
				Retorno retornoOp3Linha = this.Op3Linha(mAuxTemp);	
				
				if (retornoOp3Linha.getStatus() == 1){
					String mAuxCodigo = mAuxTemp + " := " + pVariavel + " - " + retornoOp4.getTipagem().getNomeVar() + "\n" +
										retornoOp4.getCodigo() +
										retornoOp3Linha.getCodigo();
					
					retorno.setTipagem(retornoOp3Linha.getTipagem());
					retorno.setCodigo(mAuxCodigo);
					retorno.setStatus(1);
				} else{
					retorno = retornoOp3Linha;
				}				
			} else{
				retorno = retornoOp4;
			}
		} else {
			// VAZIO
			mAuxTipagem.setNomeVar(pVariavel);
			retorno.setTipagem(mAuxTipagem);
			retorno.setStatus(1);
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op4() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op4");
//		Op4 -> Un Op4’
		Retorno retorno = new Retorno();
		
		Retorno retornoUn = Un();
		if (retornoUn.getStatus() == 1) {
			Retorno retornoOp4Linha = Op4Linha(retornoUn.getTipagem().getNomeVar());
			
			if (retornoOp4Linha.getStatus() == 1){
				String mAuxCodigoAtual = retornoUn.getCodigo() + retornoOp4Linha.getCodigo();
				retorno = retornoOp4Linha.clone();
				retorno.setCodigo(mAuxCodigoAtual);
			} else{
				retorno = retornoOp4Linha;
			}
		} else{
			retorno = retornoUn;
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Op4Linha(String pVariavel) throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Op4Linha");
//		Op4’-> * Un Op4’ | / Un Op4’ | &
		Retorno retorno 	= new Retorno();
		Tipagem mAuxTipagem = new Tipagem();
		
		if (Lexico.getInstance().proximoToken() == tk_mult) {
			consumirTudo();
			Retorno retornoUn = this.Un();
			
			if (retornoUn.getStatus() == 1) {
				String mAuxTemp = TcheGlobal.criarTmp();
				Retorno mAuxRetornoOp4Linha = this.Op4Linha(mAuxTemp);
				
				if (mAuxRetornoOp4Linha.getStatus() == 1){
					String mAuxCodigo = mAuxTemp + " := " + pVariavel + " * " + retornoUn.getTipagem().getNomeVar() + "\n";
//					DesktopFrameWork.getInstance().addC3E(mAuxCodigo);
					retorno.setTipagem(mAuxRetornoOp4Linha.getTipagem());
					retorno.setCodigo(mAuxCodigo);
					retorno.setStatus(1);
				} else{
					retorno = mAuxRetornoOp4Linha;
				}
			} else{
				retorno = retornoUn;
			}
		} else if (Lexico.getInstance().proximoToken() == tk_divisao) {
			consumirTudo();
			Retorno retornoUn = this.Un();
			
			if (retornoUn.getStatus() == 1) {
				String mAuxTemp = TcheGlobal.criarTmp();
				Retorno mAuxRetornoOp4Linha = this.Op4Linha(mAuxTemp);
				
				if (mAuxRetornoOp4Linha.getStatus() == 1){
					String mAuxCodigo = mAuxTemp + " := " + pVariavel + " / " + retornoUn.getTipagem().getNomeVar() + "\n";
//					DesktopFrameWork.getInstance().addC3E(mAuxCodigo);
					retorno.setTipagem(mAuxRetornoOp4Linha.getTipagem());
					retorno.setCodigo(mAuxCodigo);
					retorno.setStatus(1);
				} else{
					retorno = mAuxRetornoOp4Linha;
				}
			} else{
				retorno = retornoUn;
			}
		} else{
//			VAZIO			
			mAuxTipagem.setNomeVar(pVariavel);
			retorno.setTipagem(mAuxTipagem);
			retorno.setStatus(1);
		}			
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Un() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Un");
		// Un -> -V | +V | !V | true | false | P
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_subtr) {
			consumirTudo();
			Retorno retornoV = V();
			
			if (retornoV.getStatus() == 1){
				Tipagem mAuxTipagem = new Tipagem();
				mAuxTipagem.setNomeVar(TcheGlobal.criarTmp());
				String mAuxCodigo = mAuxTipagem.getNomeVar() + " := minus " + retornoV.getTipagem().getNomeVar() + "\n";
//				DesktopFrameWork.getInstance().addC3E(mAuxCodigo);
				
				retorno.setTipagem(mAuxTipagem);
				retorno.setCodigo(mAuxCodigo);
				retorno.setStatus(1);
			}
		} else if (getInstance().proximoToken() == tk_adicao){
			consumirTudo();
			Retorno retornoV = V();
			
			if (retornoV.getStatus() == 1){
				Tipagem mAuxTipagem = new Tipagem();
				mAuxTipagem.setNomeVar(TcheGlobal.criarTmp());
				String mAuxCodigo = mAuxTipagem.getNomeVar() + " := plus " + retornoV.getTipagem().getNomeVar() + "\n";
//				DesktopFrameWork.getInstance().addC3E(mAuxCodigo);
				
				retorno.setTipagem(mAuxTipagem);
				retorno.setCodigo(mAuxCodigo);
				retorno.setStatus(1);
			}
		} else if (getInstance().proximoToken() == tk_fatorial){
			consumirTudo();
			Retorno retornoV = V();
			
			if (retornoV.getStatus() == 1){
				Tipagem mAuxTipagem = new Tipagem();
				mAuxTipagem.setNomeVar(TcheGlobal.criarTmp());
				String mAuxCodigo = mAuxTipagem.getNomeVar() + " := invert " + retornoV.getTipagem().getNomeVar() + "\n";
//				DesktopFrameWork.getInstance().addC3E(mAuxCodigo);
				
				retorno.setTipagem(mAuxTipagem);
				retorno.setCodigo(mAuxCodigo);
				retorno.setStatus(1);
			}
		} else if (getInstance().proximoToken() == tk_true){
			consumirTudo();			
			Tipagem mAuxTipagem = new Tipagem();
			mAuxTipagem.setVlrVariavel("true");
			retorno.setStatus(1);
		} else if (getInstance().proximoToken() == tk_false){
			consumirTudo();
			Tipagem mAuxTipagem = new Tipagem();
			mAuxTipagem.setVlrVariavel("false");
			retorno.setStatus(1);
		} else {
			retorno = P();
		}
		
		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno P() throws Exception {
		DesktopFrameWork.getInstance().addSintatico("P");
//		P -> Ident P’ | (Log)
		Retorno retorno = new Retorno();
		
		if (getInstance().proximoToken() == tk_abreparenteses) {
			consumirTudo();
			retorno = this.Log("");
			if (retorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					consumirTudo();
					retorno.setStatus(1);
				} else{
					throw new Exception("Faltou fechar o parentese");
				}
			}
		} else{
			retorno = this.Ident(); 
			if (retorno.getStatus() == 1) {
				Retorno retornoPLinha = this.PLinha();
				
				if (retornoPLinha.getStatus() == 1){
					retorno = retornoPLinha;
				}
			}
		}
		
		return retorno;
	}

	@Override
	Retorno PLinha() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("PLinha");
//		P’ -> (Log) | &
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_abreparenteses) {
			consumirTudo();
			retorno = this.Log(""); 
			if (retorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechaparenteses) {
					consumirTudo();
					retorno.setStatus(1);
				} else {
					throw new Exception("Faltou fechar parenteses");
				}
			}
		} else {
//			VAZIO
//			retorno.setStatus(1);
		}

		DesktopFrameWork.getInstance().addC3E(retorno.getCodigo());
		return retorno;
	}

	@Override
	Retorno Ident() throws Exception {
		DesktopFrameWork.getInstance().addSintatico("Ident");
		// Ident -> V | C 
		Retorno retorno = new Retorno();
		
		retorno = this.V();
		if (retorno.getStatus() != 1){
			retorno = this.C();
		}
	
		return retorno;
	}

	@Override
	Retorno V() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("V");
		// V -> VVar | VVet
		
		Retorno retorno = new Retorno();

		if (getInstance().lookAhead() == tk_abrecolchetes){
			retorno = this.VVet();			
		} else{
			retorno = this.VVar();
		}
		
		return retorno;
	}

	@Override
	Retorno VVet() throws Exception {
		DesktopFrameWork.getInstance().addSintatico("VVet");
		// VVet -> VVar IVetDime
		Retorno retorno = new Retorno();

		retorno = this.VVar(); 
		
		if (retorno.getStatus() == 1) {
			retorno = this.IVetDime();
		}
		
		return retorno;
	}

	@Override
	Retorno Vet() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("Vet");
//		Vet -> [Ident]
		Retorno retorno = new Retorno();
		
		if (Lexico.getInstance().proximoToken() == tk_abrecolchetes) {
			consumirTudo();
			
			retorno = this.Ident();
			if (retorno.getStatus() == 1) {
				if (Lexico.getInstance().proximoToken() == tk_fechecolchetes) {
					consumirTudo();
					retorno.setStatus(1);
				} else {
					throw new Exception("Faltou fechar colchetes");
				}
			} else {
				throw new Exception("Faltou identificador para o vetor");
			}
		} else{
			throw new Exception("Faltou Abrir o Colchetes");
		}
		
		return retorno;
	}

	@Override
	Retorno VVar() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("VVar");
//		VVar -> id
		Retorno retorno = new Retorno();
		
		if (getInstance().proximoToken() == tk_variavel) {			
			Tipagem mAuxTipagem = new Tipagem();
			mAuxTipagem.setNomeVar(getInstance().proximoLexema());
			retorno.setTipagem(mAuxTipagem);
			retorno.getTipagem().setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setStatus(1);
			
			consumirTudo();
		}
		return retorno;
	}

	@Override
	Retorno C() throws Exception {
		DesktopFrameWork.getInstance().addSintatico("C");
		// C-> num | “ ”
		Retorno retorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_numero) {
			
			Tipagem tipagem = new Tipagem();
//			tipagem.setVlrVariavel(getInstance().proximoLexema());
			tipagem.setNomeVar(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);
			retorno.setStatus(1);

			consumirTudo();
		} else if (Lexico.getInstance().proximoToken() == tk_apas) {
			
			Tipagem tipagem = new Tipagem();
//			tipagem.setVlrVariavel(getInstance().proximoLexema());
			tipagem.setNomeVar(getInstance().proximoLexema());
			retorno.setTipagem(tipagem);
			retorno.setStatus(1);

			consumirTudo();
		} else {
			throw new Exception("Eh esperado um número ou aspas.");
		}

		return retorno;
	}

	@Override
	Retorno T() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("T");
		// T -> TVar | TVet
		Retorno retorno = TVar();

		if (retorno.getStatus() != 1)
			retorno = TVet();				
		
		return retorno;
	}

	@Override
	Retorno TVet() throws Exception{
		DesktopFrameWork.getInstance().addSintatico("TVet");
		// TVet -> borracho TVar | bolicho TVar
		Retorno retorno = new Retorno();

		if (Lexico.getInstance().proximoToken() == tk_borracho) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setTipoArray(getInstance().proximoLexema());
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno = this.TVar();

		} else if (getInstance().proximoToken() == tk_bolicho) {
			
			Tipagem tipagem = new Tipagem();
			tipagem.setTipoArray(getInstance().proximoLexema());
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno = this.TVar();
		}

		return retorno;
	}

	@Override
	Retorno TVar() throws Exception{
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
			
			Tipagem tipagem = new Tipagem();
			tipagem.setDesNomeTipoVal("pia");
			tipagem.setTipoEntrada(TipoEntrada.VARIAVEL);
			retorno.setTipagem(tipagem);
			
			getInstance().consumirLexema();
			getInstance().consumirToken();
			retorno.setStatus(1);
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
