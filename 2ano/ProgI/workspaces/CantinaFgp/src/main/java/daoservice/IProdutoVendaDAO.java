package daoservice;

import vo.ProdutoVendaVO;

public interface IProdutoVendaDAO extends IProdutoDAO<ProdutoVendaVO> {
	
	Long getUltimoIdGerado();
	boolean deletarProduto(ProdutoVendaVO produto);
	
}
