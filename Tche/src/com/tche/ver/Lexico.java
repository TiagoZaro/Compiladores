package com.tche.ver;

import java.util.ArrayList;
import java.util.List;

import com.tche.DesktopFrameWork;

public class Lexico {

	static final int tk_EOF = -1;
	static final int tk_naoreconhecido = 0;
	static final int tk_adicao = 1;
	static final int tk_subtr = 2;
	static final int tk_mult = 3;
	static final int tk_divisao = 4;
	static final int tk_potencia = 6;
	static final int tk_abreparenteses = 7;
	static final int tk_fechaparenteses = 8;
	static final int tk_maior = 9;
	static final int tk_menor = 11;
	static final int tk_diferente = 13;
	static final int tk_igual = 14;
	static final int tk_fatorial = 17;
	static final int tk_numero = 100;
	static final int tk_abrechaves = 56;
	static final int tk_fechachaves = 57;
	static final int tk_abrecolchetes = 58;
	static final int tk_fechecolchetes = 59;
	static final int tk_ponto = 60;
	static final int tk_ponto_e_virgula = 61;
	static final int tk_true = 62;
	static final int tk_false = 63;
	static final int tk_apas = 64;
	static final int tk_barras = 65;
	static final int tk_e_comm = 66;
	static final int tk_querencia = 67;
	static final int tk_tche = 68;
	static final int tk_pila = 69;
	static final int tk_pia = 70;
	static final int tk_borracho = 71;
	static final int tk_bueno = 72;
	static final int tk_bolicho = 73;
	static final int tk_indiada = 74;
	static final int tk_despacho = 75;
	static final int tk_xispa = 76;
	static final int tk_quetal = 77;
	static final int tk_capaz = 78;
	static final int tk_trova = 79;
	static final int tk_voltear = 80;
	static final int tk_aprochegar = 81;
	static final int tk_arregar = 82;
	static final int tk_largatear = 83;
	static final int tk_hasta = 84;
	static final int tk_dois_pontos = 85;
	static final int tk_virgula = 86;
	static final int tk_variavel = 87;

	String strt; // string com a sentenca
	int post, tamt; // posicao atual e tamanho da sentenca
	char car_atual; // ultimo cararter lido
	int token; // codigo do token identificado
	// char lexema[] = new char[400];

	// lexema identificado
	private StringBuilder sbLexema = new StringBuilder();

	public List<Integer> lstTokens = new ArrayList<Integer>();
	public List<String> lstLexema = new ArrayList<String>();

	private Lexico() {
	}

	private static Lexico instance;

	public static Lexico getInstance() {
		if (instance == null)
			instance = new Lexico();

		return instance;
	}

	public int proximoToken() {
		if (lstTokens.size() == 0)
			return -1;

		DesktopFrameWork.getInstance().addLog(
				"Próximo token:" + lstTokens.get(0));
		return lstTokens.get(0);
	}

	public String proximoLexema() {

		if (lstLexema.size() == 0)
			return "-1";

		DesktopFrameWork.getInstance().addLog(
				"Próximo lexema:" + lstLexema.get(0));

		return lstLexema.get(0);
	}

	public void consumirLexema() {

		if (lstLexema.size() <= 0)
			return;

		DesktopFrameWork.getInstance().addLog(
				"Consumiu o lexema:" + lstLexema.get(0));

		lstLexema.remove(0);
	}

	public void consumirToken() {

		if (lstTokens.size() <= 0)
			return;

		DesktopFrameWork.getInstance().addLog(
				"Consumiu o token:" + lstTokens.get(0));
		lstTokens.remove(0);
	}

	public char lecar() {
		if (this.post >= this.tamt) {
			return String.valueOf('z').charAt(0);
		} else {
			return this.strt.charAt(this.post++);
		}
	}

	public void iniciageradortokens(String s) {
		this.lstTokens.clear();
		this.lstTokens.clear();
		
		this.strt = s;
		this.post = 0;
		this.tamt = this.strt.length();
		this.car_atual = this.lecar();		
	}

	public void proximotoken() {
		int estado = 0, fim = 0, p = 0;

		while (fim != 1) {
			switch (estado) {
			case -2:
				this.token = this.tk_naoreconhecido;
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case -1:
				this.token = this.tk_EOF;
				fim = 1;
				break;

			case 0:
				p = 0;
				switch (this.car_atual) {
				case 'z':
					estado = -1;
					break;
				case '+':
					estado = 1;
					break;
				case '-':
					estado = 2;
					break;
				case '*':
					estado = 3;
					break;
				case '^':
					estado = 6;
					break;
				case '(':
					estado = 7;
					break;//
				case ')':
					estado = 8;
					break;
				case '!':
					estado = 17;
					break;//
				case '=':
					estado = 14;
					break;
				case ':':
					estado = 23;
					break;
				case '{':
					estado = 20;
					break;
				case '}':
					estado = 21;
					break;
				case ';':
					estado = 22;
					break;
				case '[':
					estado = 24;
					break;
				case ']':
					estado = 25;
					break;
				case ',':
					estado = 26;
					break;
				default:
					if (this.car_atual >= '0' && this.car_atual <= '9') {
						estado = 100;
					} else

					if (this.car_atual == 'a') {
						if (this.strt.substring(this.post, this.post + 10)
								.equals("prochegar")) {
							token = this.tk_aprochegar;
							fim = 1;
							post += 10;
							car_atual = lecar();
							sbLexema.append("aprochegar");
						} else if (this.strt
								.substring(this.post, this.post + 6).equals(
										"rregar")) {
							token = this.tk_arregar;
							fim = 1;
							post += 6;
							car_atual = lecar();
							sbLexema.append("arregar");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'b') {
						if (this.strt.substring(this.post, this.post + 6)
								.equals("olicho")) {
							token = this.tk_bolicho;
							fim = 1;
							post += 6;
							car_atual = lecar();
							sbLexema.append("bolicho");
						} else if (this.strt
								.substring(this.post, this.post + 7).equals(
										"orracho")) {
							token = this.tk_borracho;
							fim = 1;
							post += 7;
							car_atual = lecar();
							sbLexema.append("borracho");
						} else if (this.strt
								.substring(this.post, this.post + 4).equals(
										"ueno")) {
							token = this.tk_bueno;
							fim = 1;
							post += 4;
							car_atual = lecar();
							sbLexema.append("bueno");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'c') {
						if (this.strt.substring(this.post, this.post + 4)
								.equals("apaz")) {
							token = this.tk_capaz;
							fim = 1;
							post += 4;
							car_atual = lecar();
							sbLexema.append("capaz");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'd') {
						if (this.strt.substring(this.post, this.post + 7)
								.equals("espacho")) {
							token = this.tk_despacho;
							fim = 1;
							post += 7;
							car_atual = lecar();
							sbLexema.append("despacho");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'h') {
						if (this.strt.substring(this.post, this.post + 4)
								.equals("asta")) {
							token = this.tk_hasta;
							fim = 1;
							post += 4;
							car_atual = lecar();
							sbLexema.append("hasta");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'i') {
						if (this.strt.substring(this.post, this.post + 6)
								.equals("ndiada")) {
							token = this.tk_indiada;
							fim = 1;
							post += 6;
							car_atual = lecar();
							sbLexema.append("indiada");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'l') {
						if (this.strt.substring(this.post, this.post + 8)
								.equals("argatear")) {
							token = this.tk_largatear;
							fim = 1;
							post += 8;
							car_atual = lecar();
							sbLexema.append("lagartear");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'p') {
						if (this.strt.substring(this.post, this.post + 2)
								.equals("ia")) {
							token = this.tk_pia;
							fim = 1;
							post += 2;
							car_atual = lecar();
							sbLexema.append("pia");
						} else if (this.strt
								.substring(this.post, this.post + 3).equals(
										"ila")) {
							token = this.tk_pila;
							fim = 1;
							post += 3;
							car_atual = lecar();
							sbLexema.append("pila");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'q') {
						if (this.strt.substring(this.post, this.post + 8)
								.equals("uerencia")) {
							token = this.tk_querencia;
							fim = 1;
							post += 8;
							car_atual = lecar();
							sbLexema.append("querenceia");
						} else if (this.strt
								.substring(this.post, this.post + 4).equals(
										"etal")) {
							token = this.tk_quetal;
							fim = 1;
							post += 4;
							car_atual = lecar();
							sbLexema.append("quetal");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 't') {
						if (this.strt.substring(this.post, this.post + 3)
								.equals("che")) {
							token = this.tk_tche;
							fim = 1;
							post += 3;
							car_atual = lecar();
							sbLexema.append("tche");
						} else if (this.strt
								.substring(this.post, this.post + 4).equals(
										"rova")) {
							token = this.tk_trova;
							fim = 1;
							post += 4;
							car_atual = lecar();
							sbLexema.append("trova");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'v') {
						if (this.strt.substring(this.post, this.post + 6)
								.equals("oltear")) {
							token = this.tk_voltear;
							fim = 1;
							post += 6;
							car_atual = lecar();
							sbLexema.append("voltear");
						} else {
							// erro
							estado = 101;
						}

					} else if (this.car_atual == 'x') {
						if (this.strt.substring(this.post, this.post + 5)
								.equals("ispa")) {
							token = this.tk_xispa;
							fim = 1;
							post += 5;
							car_atual = lecar();
							sbLexema.append("xispa");
						} else {
							// erro
							estado = 101;
						}
					}

					else {
						estado = -2;
					}
					break;
				}
				break;
			case 1:
				this.token = this.tk_adicao;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 2:
				this.token = this.tk_subtr;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 3:
				this.token = this.tk_mult;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 6:
				this.token = this.tk_potencia;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 7:
				this.token = this.tk_abreparenteses;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 20:
				this.token = tk_abrechaves;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 21:
				this.token = tk_fechachaves;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 22:
				this.token = tk_ponto_e_virgula;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 23:
				this.token = tk_dois_pontos;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 24:
				this.token = tk_abrecolchetes;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 25:
				this.token = tk_fechecolchetes;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 26:
				this.token = tk_virgula;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 17:
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				this.token = this.tk_fatorial;
				fim = 1;
				break;
			case 14:
				this.token = this.tk_igual;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 8:
				this.token = this.tk_fechaparenteses;
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 100:
				sbLexema.append(this.car_atual);
				this.car_atual = this.lecar();
				if (this.car_atual < '0' || this.car_atual > '9') {
					fim = 1;
					this.token = this.tk_numero;
				}
				break;
			case 101:
				sbLexema.append(car_atual);
				car_atual = lecar();
				if (!((car_atual >= 'a' && car_atual <= 'z')
						|| (car_atual >= 'A' && car_atual <= 'Z') || (car_atual >= '0' && car_atual <= '9'))) {
					token = tk_variavel;
					fim = 1;
				}
				break;
			}
		}

	}

	public void listatokens(String s) {
		this.iniciageradortokens(s);
		this.proximotoken();

		while (this.token != this.tk_EOF) {
			try {
				if (token == 0)
					continue;

				DesktopFrameWork.getInstance().addLog(
						String.format("token=%s lexema='%s'\n", this.token,
								sbLexema.toString()));

				lstTokens.add(token);
				lstLexema.add(sbLexema.toString());
				
				sbLexema.setLength(0);
			} finally {
				this.proximotoken();
			}
		}
	}
}
