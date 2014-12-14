package com.tche.telas;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class TelaExibiMapaSimbolosLay extends JFrame{
	
	private JPanel pnlTable;
	protected JTable tbl;
	protected TableModelSimbolos tableModelSimbolos;
	
	public abstract void defaultValues();
	
	
	public TelaExibiMapaSimbolosLay() {
		init();
		
		TelaExibiMapaSimbolosLay.this.defaultValues();
	}

	private void init(){
		this.setTitle("Mapa de Sí­mbolos");
		this.setSize(500, 450);
		
		Container c = getContentPane();
		c.add(getPnlTable());
	}
	
	private JPanel getPnlTable(){
		
		if(pnlTable != null)
			return pnlTable;
		
		pnlTable = new JPanel();
		
		String coluna = "2dlu, pref:grow, 2dlu";
		String linha = "2dlu,pref, 2dlu";
		pnlTable.setLayout(new FormLayout(coluna, linha));
		
		tableModelSimbolos = new TableModelSimbolos();
		
		tbl = new JTable(tableModelSimbolos);
		

		JScrollPane scrollPanel = new JScrollPane(tbl);

		CellConstraints cc = new CellConstraints();
		pnlTable.add(scrollPanel, cc.xy(2, 2));

		return pnlTable;
	}
}
