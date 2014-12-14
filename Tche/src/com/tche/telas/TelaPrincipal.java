package com.tche.telas;


public class TelaPrincipal extends TelaPrincipalLay {

	@Override
	public void validarSitaxe() {

	}

	@Override
	public void novoArquivo() {

	}

	@Override
	public void compilar() {
		// TODO
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
