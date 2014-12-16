package com.tche.telas;

import com.tche.DesktopFrameWork;
import com.tche.Retorno;
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
		//limparLog();
		
		Sintatico mAuxSintatico = new Sintatico();
		Lexico.getInstance().listatokens(txtAreaDesenv.getText());

		// Efetua a analise seintatica
		Retorno retorno = mAuxSintatico.Inicio();
		if (retorno != null && retorno.getStatus() == 1) {
			DesktopFrameWork.getInstance().addLog("Compilado!");
		} else if (retorno != null && retorno.getStatus() == 0) {

			if (retorno.getDescricaoErro() == null
					|| retorno.getDescricaoErro().trim().isEmpty())
				DesktopFrameWork.getInstance().addLog("Erro desconhecido!");
			else
				DesktopFrameWork.getInstance().addLog(
						retorno.getDescricaoErro());
		}

	}

	public void addLog(final String log) {

		if (log == null | log.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtAreaLog.getText());
		sb.append("\n");
		sb.append(log);

		txtAreaLog.setText(sb.toString());
		txtAreaLog.updateUI();
		//this.update(getRootPane().getGraphics());
	}
	private void limparLog(){
		txtAreaLog.setText("");
		txtAreaLog.updateUI();
	}

	@Override
	public void mapaSimbolos() {
		TelaExibiMapaSimbolos.exibirMapaSimbolos();
	}

}
