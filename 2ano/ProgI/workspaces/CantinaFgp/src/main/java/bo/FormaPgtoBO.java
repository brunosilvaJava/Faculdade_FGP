package bo;

import java.util.List;

import vo.FormaPgtoVO;
import daoimpl.DaoFactory;
import daoservice.IFormaPgtoDAO;

public class FormaPgtoBO {
	
	private IFormaPgtoDAO formaPgtoDao;
	
	{
		
		formaPgtoDao = DaoFactory.getIFormaPgtoDAO();
	
	}
	
	public List<FormaPgtoVO> consultarTodasFormaPgto(){
		
		return formaPgtoDao.consultar();
		
	}
	
}
