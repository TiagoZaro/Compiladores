package com.tche.telas;

import com.tche.ver.Lexico;
import com.tche.ver.Sintatico;


public class TelaPrincipal extends TelaPrincipalLay {

	@Override
	public void validarSitaxe() {

	}

	@Override
	public void novoArquivo() {

	}

	@Override
	public void compilar() {
		Sintatico mAuxSintatico = new Sintatico();
		mAuxSintatico.lexico = new Lexico();
		mAuxSintatico.lexico.listatokens(txtAreaDesenv.getText());
	}

	public void addLog(String log) {

		if (log == null | log.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtAreaLog.getText());
		sb.append(log);

		txtAreaLog.setText(sb.toString());
		txtAreaLog.updateUI();
	}

	@Override
	public void mapaSimbolos() {
		TelaExibiMapaSimbolos.exibirMapaSimbolos();
	}

}
