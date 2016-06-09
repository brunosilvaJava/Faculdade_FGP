package interfaces;

import java.util.List;

import vo.GenericVO;

public interface ITelaConsultar<T extends GenericVO> {
	
	void deletar(T objeto);
	List<T> consultar();
	void carregarGridItens(List<T> lista);
	
}
