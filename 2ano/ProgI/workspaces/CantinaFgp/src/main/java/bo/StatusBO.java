package bo;

import java.util.List;

import vo.StatusVO;
import daoimpl.DaoFactory;
import daoservice.IStatusDAO;
import enumeradores.TipoStatus;

public class StatusBO {
	
	private IStatusDAO statusDao;
	
	{
		
		statusDao = DaoFactory.getIStatusDAO();
	
	}
	
	public List<StatusVO> consultarTodosStatus(TipoStatus tipo){
		
		return statusDao.consultar(tipo);
		
	}

}
