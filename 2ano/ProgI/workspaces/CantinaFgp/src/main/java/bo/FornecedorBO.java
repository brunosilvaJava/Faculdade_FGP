package bo;

import java.util.List;

import vo.FornecedorVO;
import daoimpl.DaoFactory;
import daoservice.IFornecedorDAO;

public class FornecedorBO {
	
	private IFornecedorDAO fornDao;
	
	{
		fornDao = DaoFactory.getIFornecedorDAO();
	}
	
	public List<FornecedorVO> buscarFornecedorPorCodigoENome(String codigo, String nome){
				
		return fornDao.buscarPorCodigoENome(codigo, nome);
		
	}

}
