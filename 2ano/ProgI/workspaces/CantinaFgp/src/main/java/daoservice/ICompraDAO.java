package daoservice;

import vo.CompraVO;

public interface ICompraDAO extends IDAO<CompraVO>{
	
	boolean deletarCompra(CompraVO compra);

}
