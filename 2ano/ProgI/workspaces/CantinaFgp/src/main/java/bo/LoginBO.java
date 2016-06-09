package bo;

import vo.UsuarioVO;
import daoimpl.DaoFactory;
import daoservice.ILoginDAO;

public class LoginBO {
	
	private ILoginDAO loginDao;
	
	{
		loginDao = DaoFactory.getILoginDAO();
	}

	public UsuarioVO logarUsuario(UsuarioVO usuario) {
		return loginDao.logarUsuario(usuario);
	}

}
