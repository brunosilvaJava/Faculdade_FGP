package bo;

import java.util.List;

import vo.UnidadeProdutoVO;
import daoimpl.DaoFactory;
import daoservice.IUnidadeProdutoDAO;

public class UnidadeProdutoBO {

	private IUnidadeProdutoDAO unidadeDao;
	
	{
		unidadeDao = DaoFactory.getIUnidadeProdutoDAO();
	}
	
	public List<UnidadeProdutoVO> consultar(){
		
		return unidadeDao.consultar();
		
	}
	
}
