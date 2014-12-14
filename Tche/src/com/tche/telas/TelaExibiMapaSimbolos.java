package com.tche.telas;

import static com.tche.TcheGlobal.getMapaSimbolos;

import java.util.Set;

import com.tche.Tipagem;
import com.tche.TipoEntrada;


public class TelaExibiMapaSimbolos extends TelaExibiMapaSimbolosLay{
	
	
	public static void exibirMapaSimbolos(){
		
		TelaExibiMapaSimbolos bb = new TelaExibiMapaSimbolos();
		bb.setVisible(true);
	}

	@Override
	public void defaultValues() {
		
		tableModelSimbolos.clear();
		
		Set<String> lstKeys =  getMapaSimbolos().keySet();
		
		for (String k : lstKeys) {
			ItemTableSimbolos item = new ItemTableSimbolos();
			
			Tipagem tipagem = getMapaSimbolos().get(k);
			
			item.setNome(k);
			item.setNomeTipoVal(tipagem.getDesNomeTipoVal().toUpperCase());
			item.setValorVal(tipagem.getVlrVariavel().toString());
				
			if(tipagem.getTipoEntrada().equals(TipoEntrada.FUNCAO)){
				item.setTipoEntrada("FUNCÃO");
			}else{
				item.setTipoEntrada("VARIÁVEL");
			}
			
			tableModelSimbolos.addBean(item);
		}
		
	}

}
