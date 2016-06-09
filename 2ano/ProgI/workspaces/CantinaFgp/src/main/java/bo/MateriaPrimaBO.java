package bo;

import vo.MateriaPrimaVO;
import daoimpl.DaoFactory;
import daoservice.IMateriaPrimaDAO;

public class MateriaPrimaBO extends ProdutoBO<MateriaPrimaVO, IMateriaPrimaDAO> {

	private static IMateriaPrimaDAO matPrimaDao;
	
	static {
		matPrimaDao = DaoFactory.getIMateriaPrimaDAO();
	}
	
	public MateriaPrimaBO() {
		super(matPrimaDao);
	}

	
	
}
