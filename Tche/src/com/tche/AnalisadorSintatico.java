package com.tche;

import com.tche.ver.Lexico;


public class AnalisadorSintatico extends Gramatica{
	
	// DesktopFrameWork.getInstance().addLog("FDFDFD");

	@Override
	String Inicio() {
		String mAuxMensagemRetorno = "";
		
		if (Lexico.GetInstance().proximoToken() == Tokens.tk_querencia){
			if (Lexico.GetInstance().proximoToken() == Tokens.tk_abrechaves){
				mAuxMensagemRetorno = Q();
				
				if (mAuxMensagemRetorno.equals(""))
					if (Lexico.GetInstance().proximoToken() != Tokens.tk_fechachaves)
						mAuxMensagemRetorno =  "Falta fecha chaves para a Querência";
			}
			else 
				mAuxMensagemRetorno = "Falta abre chaves para a Querência";
		}
		else if (true){
			
		}
			
		return mAuxMensagemRetorno;
	}

	@Override
	String Q() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	int FuncProt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int IProt() {
		// TODO Auto-generated method stub
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
	int ComandD3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int ComandC() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
