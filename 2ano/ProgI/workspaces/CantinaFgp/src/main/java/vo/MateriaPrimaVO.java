package vo;

import java.util.List;

public class MateriaPrimaVO extends ProdutoVO {
	
 	private List<ProdutoMateriaPrimaVO> listaProdutosFabricados;

	public List<ProdutoMateriaPrimaVO> getListaProdutosFabricados() {
		return listaProdutosFabricados;
	}

	public void setListaProdutosFabricados(List<ProdutoMateriaPrimaVO> listaProdutosFabricados) {
		this.listaProdutosFabricados = listaProdutosFabricados;
	}

}
