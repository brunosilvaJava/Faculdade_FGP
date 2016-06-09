package bo;

import java.util.List;

import vo.ProdutoMateriaPrimaVO;
import daoimpl.DaoFactory;
import daoservice.IProdutoMateriaPrimaDAO;

public class ProdutoMateriaPrimaBO {
	
	private IProdutoMateriaPrimaDAO receitaDao;

	
	{
		receitaDao = DaoFactory.getIProdutoMateriaPrimaDAO();
	
	}
	
	
	public List<ProdutoMateriaPrimaVO> consultarReceitaPorIdProduto(Long idProduto){
		return receitaDao.consultarReceitaPorIdProduto(idProduto);
		
	}
	
	
}
