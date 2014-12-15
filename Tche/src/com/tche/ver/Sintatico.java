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
					mAuxRetorno = IVetDimeLinha();
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
		/*if (lexico.proximoToken() == tk_abrecolchetes) {
			if (C() == 1) {
				if (lexico.proximoToken() == tk_fechecolchetes) {
					return 1;
				}
			}
		} else {
			// vazio //TODO
		}
		return 0;*/
		// IvetDime’ -> [ C ] | &
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_abrecolchetes) {
			mAuxRetorno = C();
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() != tk_fechecolchetes) {
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
		/*if (lexico.proximoToken() == tk_igual) {
			if (C() == 1) {
				return 1;
			}
		} else {
			// Vazio //TODO
		}
		// TODO Auto-generated method stub
		return 0;*/
		// AProt -> = C | &
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_igual) {
			mAuxRetorno = C();			
		} else {
			// Vazio //TODO
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno M() {
		/*if (lexico.proximoToken() == tk_tche) {
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
		return 0;*/
		// M -> tche{ IniCod } Func
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_tche) {
			if (lexico.proximoToken() == tk_abrechaves) {
				mAuxRetorno = IniCod();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() == tk_fechachaves) {
						mAuxRetorno = Func();
					} else{
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha chaves do tche");	
					}					
				}
			} else{
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre chaves do tche");
			}			
		} else{
			mAuxRetorno.setStatus(0);
			mAuxRetorno.setDescricaoErro("Faltou o Tche!!!");
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno Func() {
		/*if (lexico.proximoToken() == tk_indiada) {
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
		return 0;*/
		// Func -> indiada V (Par) FuncRet { IniCod } Func | &
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
								if (lexico.proximoToken() == tk_abrechaves) {
									mAuxRetorno = IniCod();
									if (mAuxRetorno.getStatus() == 1) {
										if (lexico.proximoToken() == tk_fechachaves) {
											mAuxRetorno = Func();
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
		/*if (ParVet() == 1) {
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
		return 0;*/
		// Par-> ParVet MaisPar | ParVar MaisPar
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ParVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = MaisPar();
		} else{
			mAuxRetorno = ParVar();
			if (mAuxRetorno.getStatus() == 1){
				mAuxRetorno = MaisPar();	
			}
			else {
				// VAZIO //TODO
			}
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno MaisPar() {
		/*if (lexico.proximoToken() == tk_virgula) {
			if (MaisPar1() == 1) {
				return 1;
			}
		}
		return 0;*/
		// MaisPar ->, MaisPar1
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_virgula) {
			mAuxRetorno = MaisPar1();
		} else{
			mAuxRetorno.setStatus(0);
			mAuxRetorno.setDescricaoErro("Faltou a virgula dos parametros MaisPar");
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno MaisPar1() {
		/*if (ParVet() == 1) {
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
		return 0;*/
		// MaisPar1 -> ParVet MaisPar | ParVar MaisPar | &
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ParVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = MaisPar();
		} else{
			mAuxRetorno = ParVar();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = MaisPar();
			} else {
				// VAZIO //TODO
			}			
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ParVet() {
		/*if (TVar() == 1) {
			if (VVet() == 1) {
				return 1;
			}
		}
		return 0;*/
		// ParVet -> TVet VVet
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = TVet();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = VVet();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ParVar() {
		/*if (TVar() == 1) {
			if (VVar() == 1) {
				return 1;
			}
		}
		return 0;*/
		// ParVar -> TVar VVar
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = TVar();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = VVar();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno FuncRet() {
		/*if (lexico.proximoToken() == tk_dois_pontos) {
			if (T() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;*/
		// FuncRet -> :T | &
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_dois_pontos) {
			mAuxRetorno = T();
		} else {
			// VAZIO //TODO
		}
		return mAuxRetorno;
	}

	@Override
	Retorno IniCod() {
		/*if (ICod() == 1) {
			if (IniCod() == 1) {
				return 1;
			}
		} else if (Cod() == 1) {
			return 1;
		}
		return 0;*/
		// IniCod -> ICod IniCod | Cod
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ICod();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = IniCod();
		} else{ 
			mAuxRetorno = Cod();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ICod() {
		/*if (T() == 1) {
			if (V() == 1) {
				if (ACod1() == 1) {
					return 1;
				}
			}
		}
		return 0;*/
		// ICod -> T V ACod;
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = T();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = V();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = ACod();
			}
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ACod() {
		/*if (lexico.proximoToken() == tk_igual) {
			if (ACod1() == 1) {
				return 1;
			}
		} else {
			// VAZIO //TODO
		}
		return 0;*/
		// ACod -> = ACod1 | &
		Retorno mAuxRetorno = new Retorno();
				
		if (lexico.proximoToken() == tk_igual) {
			mAuxRetorno = ACod1();
		} else {
			mAuxRetorno.setStatus(1);
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ACod1() {
		/*if (Ident() == 1) {
			return 1;
		} else if (Op3() == 1) {
			return 1;
		} else if (FuncCall() == 1) {
			return 1;
		}
		return 0;*/
		
		// ACod1 -> Ident | Op3 | FuncCall
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = Ident();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = Op3();
			if (mAuxRetorno != 1){
				mAuxRetorno = FuncCall();
			}
		} 
		
		return mAuxRetorno;
	}

	@Override
	Retorno Cod() {
		/*if (ComandC() == 1) {
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

		return 0;*/
		// Cod -> ComandC Cod | ComandD Cod | ComandA; Cod | FuncCall; Cod | &
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ComandC();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = Cod();
		} else{
			mAuxRetorno = ComandD();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = Cod();
			}
			else{
				mAuxRetorno = ComandA();
				if (mAuxRetorno.getStatus() == 1){
					if (lexico.proximoToken() == tk_ponto_e_virgula) {
						mAuxRetorno = Cod();
					} else{
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou ponto e virgula ComandA");
					}					
				} else{
					mAuxRetorno = FuncCall();
					if (mAuxRetorno.getStatus() == 1){
						if (lexico.proximoToken() == tk_ponto_e_virgula) {
							mAuxRetorno = Cod();
						} else{
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
		/*if (lexico.proximoToken() == tk_quetal) {
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
		return 0;*/
		// ComandD -> quetal(Log){Cod} ComandD1 | xispa; | despacho ComandD2;
		Retorno mAuxRetorno = new Retorno();
				
		if (lexico.proximoToken() == tk_quetal) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_abrechaves) {
							mAuxRetorno = Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (lexico.proximoToken() == tk_fechachaves) {
									mAuxRetorno = ComandD1();
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
			} else{
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses no quetal");
			}
		} else if (lexico.proximoToken() == tk_xispa) {
			if (lexico.proximoToken() == tk_ponto_e_virgula) {
				mAuxRetorno.setStatus(1);
			}
		} else if (lexico.proximoToken() == tk_despacho) {
			mAuxRetorno = ComandD2();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD1() {
		/*if (lexico.proximoToken() == tk_capaz) {
			if (ComandD3() == 1) {
				return 1;
			}
		}
		return 0;*/
		// ComandD1 -> capaz ComandD3
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_capaz) {
			mAuxRetorno = ComandD3();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD2() {
		/*if (Log() == 1) {
			return 1;
		} else {
			// VAZIO //TODO
		}
		return 0;*/
		// ComandD2 -> log | &
		Retorno mAuxRetorno = new Retorno();
		mAuxRetorno = Log();
		if (mAuxRetorno.getStatus() != 1) {
			// REPRESENTA O VAZIO
			mAuxRetorno.setStatus(1);
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ComandD3() {
		/*if (lexico.proximoToken() == tk_abrechaves) {
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
		return 0;*/
		// ComandD3 -> {Cod} | (log){Cod} ComandD1 | &
		Retorno mAuxRetorno = new Retorno();
				
		if (lexico.proximoToken() == tk_abrechaves) {
			mAuxRetorno = Cod();
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() != tk_fechachaves) {
					mAuxRetorno.setStatus(0);
					mAuxRetorno.setDescricaoErro("Falta fecha chaves no capaz ComandD3");
				}
			}
		} else if (lexico.proximoToken() == tk_abreparenteses) {
			mAuxRetorno = Log();
			if (mAuxRetorno.getStatus() == 1) {
				if (lexico.proximoToken() == tk_fechaparenteses) {
					if (lexico.proximoToken() == tk_abrechaves) {
						mAuxRetorno = Cod();
						if (mAuxRetorno.getStatus() == 1) {
							if (lexico.proximoToken() == tk_fechachaves) {
								mAuxRetorno = ComandD1();
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
		/*if (lexico.proximoToken() == tk_trova) {
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
		return 0;*/
		// ComandC -> trova(Ident); | voltear(Log){Cod} | largatear (IniComand) hasta Ident {Cod}
		Retorno mAuxRetorno = new Retorno();
		
		if (lexico.proximoToken() == tk_trova) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = Ident();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() != tk_ponto_e_virgula) {
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
		} else if (lexico.proximoToken() == tk_voltear) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = Log();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_abrechaves) {
							mAuxRetorno = Cod();
							if (mAuxRetorno.getStatus() == 1) {
								if (lexico.proximoToken() != tk_fechachaves) {
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
		} else if (lexico.proximoToken() == tk_largatear) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = IniComand();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						if (lexico.proximoToken() == tk_hasta) {
							mAuxRetorno = Ident();
							if (Ident() == 1) {
								if (lexico.proximoToken() == tk_abrechaves) {
									mAuxRetorno = Cod();
									if (mAuxRetorno.getStatus() == 1) {
										if (lexico.proximoToken() != tk_fechachaves) {
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
		/*if (ComandA() == 1) {
			return 1;
		} else if (V() == 1) {
			return 1;
		}
		return 0;*/
		// IniComand -> ComandA | V
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ComandA();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = V();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno ComandA() {
		/*if (ComandALinha() == 1) {
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
		return 0;*/
		// ComandA -> ComandA’ = ACod1 | aprochegar ComandA’ | arregar ComandA’
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = ComandALinha();
		if (mAuxRetorno.getStatus() == 1) {
			if (lexico.proximoToken() == tk_igual) {
				mAuxRetorno = ACod1();
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou o sinal de atribuição");
			}
		} else if (lexico.proximoToken() == tk_aprochegar) {
			mAuxRetorno = ComandALinha();
		} else if (lexico.proximoToken() == tk_arregar) {
			mAuxRetorno = ComandALinha();
		}
		return mAuxRetorno;
	}

	@Override
	Retorno ComandALinha() {
		/*if (V() == 1) {
			return 1;
		} else if (Vet() == 1) {
			return 1;
		}
		return 0;*/
		// ComandA’ -> V | Vet
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = V();
		if (mAuxRetorno.getStatus() != 1) {
			mAuxRetorno = Vet();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno FuncCall() {
		/*if (V() == 1) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				if (FuncPar() == 1) {
					if (lexico.proximoToken() == tk_fechaparenteses) {
						return 1;
					}
				}
			}
		}
		return 0;*/
		// FuncCall -> V(FuncPar);
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = V();		
		if (mAuxRetorno.getStatus() == 1) {
			if (lexico.proximoToken() == tk_abreparenteses) {
				mAuxRetorno = FuncPar();
				if (mAuxRetorno.getStatus() == 1) {
					if (lexico.proximoToken() != tk_fechaparenteses) {
						mAuxRetorno.setStatus(0);
						mAuxRetorno.setDescricaoErro("Faltou fecha parenteses na chamada da função");
					}
				}
			} else {
				mAuxRetorno.setStatus(0);
				mAuxRetorno.setDescricaoErro("Faltou abre parenteses na chamada da função");
			}
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno FuncPar() {
		/*if (Ident() == 1) {
			if (MaisFuncPar() == 1) {
				return 1;
			}
		}
		return 0;*/
		// FuncPar-> Ident MaisFuncPar
		Retorno mAuxRetorno = new Retorno();
		
		mAuxRetorno = Ident();
		if (mAuxRetorno.getStatus() == 1) {
			mAuxRetorno = MaisFuncPar();
		}
		
		return mAuxRetorno;
	}

	@Override
	Retorno MaisFuncPar() {
		/*if (lexico.proximoToken() == tk_virgula) {
			if (Ident() == 1) {
				if (MaisFuncPar() == 1) {
					return 1;
				}
			}
		} else {
			// VAZIO //TODO
		}
		return 0;*/
		// MaisFuncPar -> , Ident MaisFuncPar | &
		Retorno mAuxRetorno = new Retorno();
		Retorno tempRetorno = mAuxRetorno;
		
		if (lexico.proximoToken() == tk_virgula) {
			mAuxRetorno = Ident();
			if (mAuxRetorno.getStatus() == 1) {
				mAuxRetorno = MaisFuncPar();
			}
		} else {
			// VAZIO 
			mAuxRetorno.setStatus(1);
		}
		
		return mAuxRetorno;		
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
