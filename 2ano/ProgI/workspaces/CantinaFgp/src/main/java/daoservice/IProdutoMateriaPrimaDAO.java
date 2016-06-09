package daoservice;

import java.util.List;

import vo.ProdutoMateriaPrimaVO;

public interface IProdutoMateriaPrimaDAO extends IDAO<ProdutoMateriaPrimaVO> {

	List<ProdutoMateriaPrimaVO> consultarReceitaPorIdProduto(Long id);
	
}

