package daoservice;

import vo.ProdutoVO;

public interface IProdutoDAO<T extends ProdutoVO> extends IDAO<T>, IBuscaDAO<T>{

	boolean entradaEstoque(Long idProduto, Double qtd);
	boolean retiradaEstoque(Long idProduto, Double qtd);
	
	
}
