package com.tche.telas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class TelaPrincipalLay extends JFrame {

	private JMenuBar menuBar;

	private JMenu menuArquivos;
	private JMenu menuUtil;

	private JMenuItem menuItemSalvar;
	private JMenuItem menuItemAbrir;
	private JMenuItem menuItemSair;
	private JMenuItem menuItemNovo;
	private JMenuItem menuItemCompilar;
	private JMenuItem menuItemMapaSimbolos;

	public JTextArea txtAreaDesenv;
	public JTextArea txtAreaLog;
	public JTextArea txtTokens;
	public JTextArea txtSintatico;

	public JPanel pnlLateralDireita;
	public JPanel pnlLateralEsquerda;
	public JPanel pnlTokens;
	public JPanel pnlSintatico;

	public abstract void validarSitaxe();

	public abstract void novoArquivo();

	public abstract void compilar();

	public abstract void mapaSimbolos();

	public TelaPrincipalLay() {
		init();
		addEvents();
		validarSitaxe();
	}

	private void init() {
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		// this.setSize(toolkit.getScreenSize());
		this.setSize(1200, 800);

		this.setResizable(true);

		this.setLayout(new BorderLayout());

		this.setTitle("Tchê IDE Developer");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(JOptionPane.getRootFrame());

		// Cria a barra de menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		// Cria os menus da barra
		createMenus();

		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new FormLayout("2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu", "2dlu, pref, 2dlu"));

		CellConstraints cc = new CellConstraints();
		pnlPrincipal.add(getPnlDireita(), cc.xy(4, 2));
		pnlPrincipal.add(getPnlEsquerdo(), cc.xy(2, 2));
		pnlPrincipal.add(getPnlTokens(), cc.xy(6, 2));
		pnlPrincipal.add(getPnlSintatico(), cc.xy(8, 2));

		Container c = getContentPane();
		c.add(pnlPrincipal);

	}

	private void createMenus() {

		menuArquivos = new JMenu("Arquivos");
		menuBar.add(menuArquivos);

		menuUtil = new JMenu("Util");
		menuBar.add(menuUtil);

		// Cria menu de arquivos
		createMenuArquivos();
	}

	private void createMenuArquivos() {

		menuItemSalvar = new JMenuItem("Salvar");
		menuItemAbrir = new JMenuItem("Abrir");
		menuItemSair = new JMenuItem("Sair");
		menuItemNovo = new JMenuItem("Novo");

		menuItemCompilar = new JMenuItem("Compilar");
		menuUtil.add(menuItemCompilar);

		menuItemMapaSimbolos = new JMenuItem("Mapa de Símbolos");
		menuUtil.add(menuItemMapaSimbolos);

		menuArquivos.add(menuItemNovo);
		menuArquivos.add(menuItemAbrir);
		menuArquivos.add(menuItemSalvar);
		menuArquivos.add(menuItemSair);

	}

	private void addEvents() {

		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menuItemCompilar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipalLay.this.compilar();
			}
		});

		menuItemMapaSimbolos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipalLay.this.mapaSimbolos();
			}
		});

		menuItemNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipalLay.this.novoArquivo();
			}
		});
	}

	private JPanel getPnlEsquerdo() {
		if (pnlLateralEsquerda != null)
			return pnlLateralEsquerda;

		pnlLateralEsquerda = new JPanel();
		txtAreaDesenv = new JTextArea(40, 25);

		JScrollPane scrool = new JScrollPane(txtAreaDesenv);

		pnlLateralEsquerda.add(scrool);

		return pnlLateralEsquerda;
	}

	private JPanel getPnlDireita() {

		if (pnlLateralDireita != null)
			return pnlLateralDireita;

		pnlLateralDireita = new JPanel();
		txtAreaLog = new JTextArea(40, 20);

		JScrollPane scrool = new JScrollPane(txtAreaLog);

		pnlLateralDireita.add(scrool);

		return pnlLateralDireita;
	}

	private JPanel getPnlTokens() {
		txtTokens = new JTextArea(40, 20);
		JScrollPane scrool = new JScrollPane(txtTokens);

		pnlTokens = new JPanel();
		pnlTokens.add(scrool);
		return pnlTokens;
	}

	private JPanel getPnlSintatico() {
		txtSintatico = new JTextArea(40, 20);
		JScrollPane scrool = new JScrollPane(txtSintatico);

		pnlSintatico = new JPanel();
		pnlSintatico.add(scrool);
		return pnlSintatico;
	}
}
