package daoservice;

import java.util.List;

import enumeradores.TipoStatus;
import vo.StatusVO;

public interface IStatusDAO extends IDAO<StatusVO> {

	List<StatusVO> consultar(TipoStatus tipo);
	
}
