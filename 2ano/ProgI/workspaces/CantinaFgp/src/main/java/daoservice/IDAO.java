package daoservice;

import java.util.List;

import vo.GenericVO;

public interface IDAO<T extends GenericVO> {
	
	T incluir(T objeto);
	boolean alterar(T objeto);
	boolean deletar(Long id);
	List<T> consultar();
	T consultarPorId(Long id);
	
	
}
