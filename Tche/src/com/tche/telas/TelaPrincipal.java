package com.tche.telas;

import javax.swing.JOptionPane;

import com.tche.DesktopFrameWork;
import com.tche.Retorno;
import com.tche.TcheGlobal;
import com.tche.ver.Lexico;
import com.tche.ver.Sintatico;

public class TelaPrincipal extends TelaPrincipalLay {

	@Override
	public void validarSitaxe() {

	}

	@Override
	public void novoArquivo() {
		txtAreaDesenv.setText("");
		txtAreaLog.setText("");
		txtTokens.setText("");
		txtSintatico.setText("");

		txtAreaDesenv.updateUI();
		txtAreaLog.updateUI();
		txtTokens.updateUI();
		txtSintatico.updateUI();
		this.update(this.getGraphics());
	}

	@Override
	public void compilar() {
		// limparLog();

		TcheGlobal.setMapaSimbolos(null);

		Sintatico mAuxSintatico = new Sintatico();

		Lexico.getInstance().listatokens(txtAreaDesenv.getText());

		// Efetua a analise seintatica
		Retorno retorno;
		try {
			retorno = mAuxSintatico.Inicio();
			if (retorno != null && retorno.getStatus() == 1) {
				DesktopFrameWork.getInstance().addLog("Compilado!");
			} else if (retorno != null && retorno.getStatus() == 0) {

				if (retorno.getDescricaoErro() == null || retorno.getDescricaoErro().trim().isEmpty())
					DesktopFrameWork.getInstance().addLog("Erro desconhecido!");
				else
					DesktopFrameWork.getInstance().addLog(retorno.getDescricaoErro());
			}

			if (retorno != null && !retorno.getDescricaoErro().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, retorno.getDescricaoErro());
			} else {
				JOptionPane.showMessageDialog(null, "Compilado com sucesso!");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void addLog(final String log) {

		if (true)
			return;
		if (log == null | log.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtAreaLog.getText());
		sb.append("\n");
		sb.append(log);

		txtAreaLog.setText(sb.toString());
		txtAreaLog.updateUI();
		this.update(this.getGraphics());
		// this.update(getRootPane().getGraphics());
	}

	private void limparLog() {
		txtAreaLog.setText("");
		txtAreaLog.updateUI();
	}

	@Override
	public void mapaSimbolos() {
		TelaExibiMapaSimbolos.exibirMapaSimbolos();
	}

	public void addSintatico(final String sintatico) {

		if (sintatico == null | sintatico.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtSintatico.getText());
		sb.append("\n");
		sb.append(sintatico);

		txtSintatico.setText(sb.toString());
		txtSintatico.updateUI();
		this.update(this.getGraphics());
	}

	public void addTokens(final String token) {

		if (token == null | token.trim().isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		sb.append(txtTokens.getText());
		sb.append("\n");
		sb.append(token);

		txtTokens.setText(sb.toString());
		txtTokens.updateUI();
		this.update(this.getGraphics());
	}

}
