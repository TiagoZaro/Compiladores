package com.tche.telas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelSimbolos extends AbstractTableModel {

	/**
	 * Lista de itens
	 */
	private List<ItemTableSimbolos> listaBean;

	/**
	 * Obtém a lista de itens da tabela
	 * 
	 * @return Um {@link ArrayList} com itens
	 */
	public List<ItemTableSimbolos> getListaBean() {
		if (listaBean == null)
			listaBean = new ArrayList<ItemTableSimbolos>(0);

		return listaBean;
	}

	/**
	 * Atualiza a lista de itens
	 * 
	 * @param listaBean
	 *            Um {@link ArrayList} com itens
	 */
	public void setListaBean(List<ItemTableSimbolos> listaBean) {
		this.listaBean = listaBean;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return getListaBean().size();
	}

	@Override
	public String getColumnName(int column) {
		return new String[] { "Entrada", "Tipo", "Nome", "Valor", "Tipo Array",
				"Dimens�o" }[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return new Class<?>[] { String.class, String.class, String.class,
				String.class, String.class, Integer.class }[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (getListaBean().isEmpty())
			return null;

		ItemTableSimbolos bean = getListaBean().get(rowIndex);
		return new Object[] { bean.getTipoEntrada(), bean.getNomeTipoVal(),
				bean.getNome(), bean.getValorVal(), bean.getTipArray(), bean.getDimensao() }[columnIndex];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		ItemTableSimbolos bean = getListaBean().get(rowIndex);

		switch (columnIndex) {
		case 0:
			bean.setTipoEntrada((String) value);
			break;
		case 1:
			bean.setNomeTipoVal((String) value);
			break;
		case 2:
			bean.setNome((String) value);
			break;
		case 3:
			bean.setValorVal((String) value);
			break;
		case 4:
			bean.setTipArray((String) value);
			break;
		case 5:
			bean.setDimensao((Integer) value);
			break;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (getListaBean().isEmpty())
			return false;

		// Verifica se é a primeira coluna
		if (columnIndex == 1)
			return true;

		return false;
	}

	public void addBean(ItemTableSimbolos bean) {
		getListaBean().add(bean); // Adiciona na lista
		fireTableDataChanged(); // Atualiza a tabela
	}

	public void removeBean(int idx) {
		try {
			getListaBean().remove(idx); // Remove o elemento da lista
			fireTableDataChanged(); // Atualiza a tabela
		} catch (Exception e) {
		}
	}

	public void clear() {
		try {
			for (ItemTableSimbolos itemTableSimbolos : listaBean) {
				getListaBean().remove(itemTableSimbolos);
				fireTableDataChanged();
			}
		} catch (Exception e) {
		}
	}

}
