package com.tche;

import static com.tche.TipoVariaveis.BOOLEAN;
import static com.tche.TipoVariaveis.CHAR;
import static com.tche.TipoVariaveis.INTEGER;

import java.util.HashMap;

public class TcheGlobal {

	private static HashMap<String, Tipagem>	mapaSimbolos;

	public static HashMap<String, Tipagem> getMapaSimbolos() {
		if (mapaSimbolos == null)
			mapaSimbolos = new HashMap<String, Tipagem>();

		return mapaSimbolos;
	}

	public static void setMapaSimbolos(HashMap<String, Tipagem> maspVariaveis) {
		mapaSimbolos = maspVariaveis;
	}

	private static HashMap<String, TipoVariaveis>	mapaTipagem;

	public static HashMap<String, TipoVariaveis> getMapaTipagem() {
		if (mapaTipagem == null)
			mapaTipagem = new HashMap<String, TipoVariaveis>();

		mapaTipagem.put("PILA", INTEGER);
		mapaTipagem.put("BUENO", BOOLEAN);
		mapaTipagem.put("PIA", CHAR);

		return mapaTipagem;
	}

	public static void setMapaTipagem(HashMap<String, TipoVariaveis> mapaTipagem) {
		TcheGlobal.mapaTipagem = mapaTipagem;
	}

}
