package com.tche.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public abstract class TelaPrincipalLay extends JFrame {

	private JMenuBar	menuBar;

	private JMenu		menuArquivos;
	private JMenu		menuUtil;

	private JMenuItem	menuItemSalvar;
	private JMenuItem	menuItemAbrir;
	private JMenuItem	menuItemSair;
	private JMenuItem	menuItemNovo;
	private JMenuItem	menuItemCompilar;

	public JTextPane	textPanel;

	public abstract void validarSitaxe();

	public abstract void novoArquivo();

	public abstract void compilar();

	public TelaPrincipalLay() {
		init();
		addEvents();
		validarSitaxe();
	}

	private void init() {
		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		this.setSize(toolkit.getScreenSize());

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

		Container c = getContentPane();
		c.add(new JScrollPane(getPnlTxtArea()), BorderLayout.CENTER);

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

		menuArquivos.add(menuItemNovo);
		menuArquivos.add(menuItemAbrir);
		menuArquivos.add(menuItemSalvar);
		menuArquivos.add(menuItemSair);

	}

	private JTextPane getPnlTxtArea() {

		if (textPanel == null)
			textPanel = new JTextPane();

		SimpleAttributeSet aset = new SimpleAttributeSet();
		StyleConstants.setForeground(aset, Color.BLACK);

		return textPanel;
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

		menuItemNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipalLay.this.novoArquivo();
			}
		});
	}

}
