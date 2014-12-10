package com.tche;

import static com.tche.TcheGlobal.getMapaSimbolos;

/**
 * @author Tiago Colleoni Zaro, Wagner Almeida
 * 
 *         2014
 * 
 * */
public class AnalisadorSemantico {

	public static void main(String[] args) {

		// Exemplo adiciona na tabela de simbolos
		Tipagem t = new Tipagem();
		t.setDesNomeTipoVal("Pila");
		t.setVlrVariavel(0);
		t.setTipoEntrada(TipoEntrada.VARIAVEL);
		String nome = "val";

		addTable(t, nome);
	}

	public static boolean addTable(Tipagem tipagem, String nomeVal) {

		// Caso uma váriavel
		if (tipagem.getTipoEntrada().equals(TipoEntrada.VARIAVEL)) {

			TipoVariaveis tipoVal = TcheGlobal.getMapaTipagem().get(
					tipagem.getDesNomeTipoVal().toUpperCase());

			// Valida seu tipo
			switch (tipoVal) {

			case INTEGER:
				try {
					if (tipagem.getVlrVariavel() != null) {
						tipagem.setVlrVariavel(new Integer((Integer) tipagem
								.getVlrVariavel()));
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
			// Caso função
		}

		getMapaSimbolos().put(nomeVal, tipagem);
		return true;
	}

}
