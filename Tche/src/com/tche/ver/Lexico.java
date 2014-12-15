package com.tche.ver;

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

	String strt; // string com a sentenca
	int post, tamt; // posicao atual e tamanho da sentenca
	char car_atual; // ultimo cararter lido
	int token; // codigo do token identificado
	char lexema[] = new char[400]; // lexema identificado

	private static Lexico instance;

	public static Lexico GetInstance() {
		if (instance == null)
			instance = new Lexico();

		return instance;
	}

	public int proximoToken() {
		return 1;
	}

	public char lecar() {
		if (this.post >= this.tamt) {
			return String.valueOf('z').charAt(0);
		} else {
			return this.strt.charAt(this.post++);
		}
	}

	public void iniciageradortokens(String s) {
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
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case -1:
				this.token = this.tk_EOF;
				this.lexema[p] = '\0';
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
					estado = this.tk_dois_pontos;
					break;

				default:
					if (this.car_atual >= '0' && this.car_atual <= '9') {
						estado = 100;
					} else

					if (this.car_atual == 'a') {
						if (this.strt.substring(this.post, this.post + 11).equals("aprochegar")) {
							estado = this.tk_aprochegar;
						} else if (this.strt.substring(this.post, this.post + 7).equals("arregar")) {
							estado = this.tk_arregar;
						} else {
							// erro
						}

					} else if (this.car_atual == 'b') {
						if (this.strt.substring(this.post, this.post + 7).equals("bolicho")) {
							estado = this.tk_bolicho;
						} else if (this.strt.substring(this.post, this.post + 8).equals("borracho")) {
							estado = this.tk_borracho;
						} else if (this.strt.substring(this.post, this.post + 5).equals("bueno")) {
							estado = this.tk_bueno;
						} else {
							// erro
						}

					} else if (this.car_atual == 'c') {
						if (this.strt.substring(this.post, this.post + 5).equals("capaz")) {
							estado = this.tk_capaz;
						} else {
							// erro
						}

					} else if (this.car_atual == 'd') {
						if (this.strt.substring(this.post, this.post + 8).equals("despacho")) {
							estado = this.tk_despacho;
						} else {
							// erro
						}

					} else if (this.car_atual == 'h') {
						if (this.strt.substring(this.post, this.post + 5).equals("hasta")) {
							estado = this.tk_hasta;
						} else {
							// erro
						}

					} else if (this.car_atual == 'i') {
						if (this.strt.substring(this.post, this.post + 7).equals("indiada")) {
							estado = this.tk_indiada;
						} else {
							// erro
						}

					} else if (this.car_atual == 'l') {
						if (this.strt.substring(this.post, this.post + 9).equals("largatear")) {
							estado = this.tk_largatear;
						} else {
							// erro
						}

					} else if (this.car_atual == 'p') {
						if (this.strt.substring(this.post, this.post + 3).equals("pia")) {
							estado = this.tk_pia;
						} else if (this.strt.substring(this.post, this.post + 4).equals("pila")) {
							estado = this.tk_pila;
						} else {
							// erro
						}

					} else if (this.car_atual == 'q') {
						if (this.strt.substring(this.post, this.post + 9).equals("querencia")) {
							estado = this.tk_querencia;
						} else if (this.strt.substring(this.post, this.post + 6).equals("quetal")) {
							estado = this.tk_quetal;
						} else {
							// erro
						}

					} else if (this.car_atual == 't') {
						if (this.strt.substring(this.post, this.post + 4).equals("tche")) {
							estado = this.tk_tche;
						} else if (this.strt.substring(this.post, this.post + 5).equals("trova")) {
							estado = this.tk_trova;
						} else {
							// erro
						}

					} else if (this.car_atual == 'v') {
						if (this.strt.substring(this.post, this.post + 7).equals("voltear")) {
							estado = this.tk_voltear;
						} else {
							// erro
						}

					} else if (this.car_atual == 'x') {
						if (this.strt.substring(this.post, this.post + 5).equals("xispa")) {
							estado = this.tk_xispa;
						} else {
							// erro
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
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 2:
				this.token = this.tk_subtr;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 3:
				this.token = this.tk_mult;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 6:
				this.token = this.tk_potencia;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 7:
				this.token = this.tk_abreparenteses;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 17:
				this.lexema[p++] = this.car_atual;
				this.car_atual = this.lecar();
				this.token = this.tk_fatorial;
				fim = 1;
				break;
			case 14:
				this.token = this.tk_igual;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 8:
				this.token = this.tk_fechaparenteses;
				this.lexema[p++] = this.car_atual;
				this.lexema[p] = '\0';
				this.car_atual = this.lecar();
				fim = 1;
				break;
			case 100:
				this.lexema[p++] = this.car_atual;
				this.car_atual = this.lecar();
				if (this.car_atual < '0' || this.car_atual > '9') {
					this.lexema[p] = '\0';
					fim = 1;
					this.token = this.tk_numero;
				}
				break;
			}
		}

	}

	public void listatokens(String s) {
		this.iniciageradortokens(s);
		this.proximotoken();
		while (this.token != this.tk_EOF) {
			System.out.println(String.format("token=%s lexema='%s'\n", this.token, this.lexema[0]));
			this.proximotoken();
		}
	}

	public static void main(String[] args) {
		String s = "(48+5)";
		new Lexico().listatokens(s);
	}

}