package com.tche;

import static com.tche.TipoVariaveis.BOOLEAN;
import static com.tche.TipoVariaveis.CHAR;
import static com.tche.TipoVariaveis.INTEGER;

import java.util.HashMap;
import java.util.HashSet;

public class TcheGlobal {

	private static HashMap<String, Tipagem>	mapaSimbolos;
	
	private static HashSet<Integer> lstLabels = new HashSet<Integer>();
	
	private static Integer controlaLabels = 0;
	
	private static HashSet<Integer> lstVarTmps = new HashSet<Integer>();
	
	private static Integer controlaVarTmps = 0;
	

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

	
	public static String criarTmp() {
		while(lstVarTmps.contains(controlaVarTmps)){
			controlaVarTmps++;
		}
		
		lstVarTmps.add(controlaVarTmps);
		
		// iniciando criacao de novo labal
		StringBuilder sb = new StringBuilder();
		sb.append("VAR_TMP_").append(controlaVarTmps);
		
		return sb.toString();
	}
	
	public static String criarLabel() {
		while(lstLabels.contains(controlaLabels)){
			controlaLabels++;
		}
		
		lstLabels.add(controlaLabels);
		
		// iniciando criacao de novo labal
		StringBuilder sb = new StringBuilder();
		sb.append("LABEL_").append(controlaLabels);
		
		return sb.toString();
	}
}
