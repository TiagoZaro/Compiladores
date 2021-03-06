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

	public static void atualizarValorTable(Tipagem tipagem, String nomeVal)
			throws Exception {
		try {

			// Verifica se j� existe
			Tipagem tip = TcheGlobal.getMapaSimbolos().get(nomeVal);

			if (tip != null) {
				tip.setVlrVariavel(tipagem.getVlrVariavel());

				getMapaSimbolos().put(nomeVal, tipagem);
			}

		} catch (Exception e) {
			throw new Exception("Erro ao atualizar tabela de s�mbolos!");
		}
	}

	public static Retorno addTable(Tipagem tipagem, String nomeVal)
			throws Exception {

		Retorno retorno = new Retorno();

		// Verifica se j� existe
		Tipagem tip = TcheGlobal.getMapaSimbolos().get(nomeVal);

		if (tip != null)
			throw new Exception("Vari�vel j� existe!");

		// Caso uma v�riavel
		if (tipagem.getTipoEntrada().equals(TipoEntrada.VARIAVEL)) {

			TipoVariaveis tipoVal = TcheGlobal.getMapaTipagem().get(
					tipagem.getDesNomeTipoVal().toUpperCase());

			// Valida seu tipo
			switch (tipoVal) {

			case INTEGER:
				try {
					if (tipagem.getVlrVariavel() != null) {
						String STR = String.valueOf(tipagem.getVlrVariavel());
						Integer vlr = Integer.valueOf(STR);
						tipagem.setVlrVariavel((Object) vlr);
					}
				} catch (Exception e) {
					retorno.setStatus(0);
					retorno.setDescricaoErro("Erro ao adicionar inteiro!");
					return retorno;
				}
				break;

			case BOOLEAN:
				try {
					if (tipagem.getVlrVariavel() != null) {

						if (tipagem.getVlrVariavel().toString().toUpperCase()
								.equals("TRUE")) {
							tipagem.setVlrVariavel(true);
						} else if (tipagem.getVlrVariavel().toString()
								.toUpperCase().equals("FALSE")) {
							tipagem.setVlrVariavel(false);
						} else
							tipagem.setVlrVariavel(null);
					}
				} catch (Exception e) {
					retorno.setStatus(0);
					retorno.setDescricaoErro("Erro ao adicionar boleano!");
					return retorno;
				}

				break;

			case CHAR:
				try {
					if (tipagem.getVlrVariavel() != null) {
						tipagem.setVlrVariavel(Character.valueOf(tipagem
								.getVlrVariavel().toString().charAt(0)));
					}
				} catch (Exception e) {
					retorno.setStatus(0);
					retorno.setDescricaoErro("Erro ao adicionar char!");
					return retorno;
				}
				break;

			default:
				retorno.setStatus(0);
				retorno.setDescricaoErro("Erro desconhecido!");
				return retorno;
			}
		} else if (tipagem.getTipoEntrada().equals(TipoEntrada.FUNCAO)) {
			// Caso fun��o
		}

		getMapaSimbolos().put(nomeVal, tipagem);
		retorno.setStatus(1);
		retorno.setTipagem(tipagem);

		return retorno;
	}

}
