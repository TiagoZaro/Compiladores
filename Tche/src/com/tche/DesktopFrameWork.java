package com.tche;

import com.tche.telas.TelaPrincipal;

public final class DesktopFrameWork {

	private static TelaPrincipal	FC	= new TelaPrincipal();

	public static void main(String[] args) {
		FC.setVisible(true);
	}

	public static TelaPrincipal getInstance() {
		return FC;
	}
}
