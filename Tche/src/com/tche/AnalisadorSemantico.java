package com.tche;

import static com.tche.TcheGlobal.getMapaSimbolos;

/**
 * @author Tiago Colleoni Zaro, Wagner Almeida
 * 
 *         2014 eh noes
 * 
 * 
 * */
public class AnalisadorSemantico {

	public static boolean addTable(Tipagem tipagem, String nomeVal) {

		// Caso uma v�riavel
		if (tipagem.getTipoEntrada().equals(TipoEntrada.VARIAVEL)) {

			TipoVariaveis tipoVal = TcheGlobal.getMapaTipagem().get(
					tipagem.getDesNomeTipoVal().toUpperCase());

			// Valida seu tipo
			switch (tipoVal) {

			case INTEGER:
				try {
					if (tipagem.getVlrVariavel() != null) {
						tipagem.setVlrVariavel(Integer.valueOf((String) tipagem.getVlrVariavel()));
					}
				} catch (Exception e) {
					return false;
				}
				break;

			case BOOLEAN:
				try {
					if (tipagem.getVlrVariavel() != null) {

						tipagem.setVlrVariavel(new Boolean((Boolean) tipagem
								.getVlrVariavel()));
					}
				} catch (Exception e) {
					return false;
				}

				break;

			case CHAR:
				try {
					if (tipagem.getVlrVariavel() != null) {
						tipagem.setVlrVariavel(new Character(
								(Character) tipagem.getVlrVariavel()));
					}
				} catch (Exception e) {
					return false;
				}
				break;

			default:
				return false;
			}
		} else if (tipagem.getTipoEntrada().equals(TipoEntrada.FUNCAO)) {
			// Caso fun��o
		}

		getMapaSimbolos().put(nomeVal, tipagem);
		return true;
	}

}
