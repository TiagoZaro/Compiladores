package com.tche.telas;

import com.tche.TcheGlobal;

public class TelaPrincipal extends TelaPrincipalLay {

	@Override
	public void validarSitaxe() {

	}

	@Override
	public void novoArquivo() {

	}

	@Override
	public void compilar() {
		// TODO Auto-generated method stub
		
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		System.out.println(TcheGlobal.criarLabel());
		
		System.out.println(TcheGlobal.criarTmp());
		System.out.println(TcheGlobal.criarTmp());
		System.out.println(TcheGlobal.criarTmp());
		System.out.println(TcheGlobal.criarTmp());
		System.out.println(TcheGlobal.criarTmp());
		System.out.println(TcheGlobal.criarTmp());

	}

	public void gerarC3E(String log) {

		if (log == null | log.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtAreaLog.getText());
		sb.append(log);

		txtAreaLog.setText(sb.toString());
		txtAreaLog.updateUI();
	}

}
