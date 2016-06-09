package daoimpl;

import daoservice.ICompraDAO;
import daoservice.IFormaPgtoDAO;
import daoservice.IFornecedorDAO;
import daoservice.IFuncionarioCantinaDAO;
import daoservice.ILoginDAO;
import daoservice.IMateriaPrimaDAO;
import daoservice.IOrdemProducaoDAO;
import daoservice.IProdutoMateriaPrimaDAO;
import daoservice.IProdutoVendaDAO;
import daoservice.IStatusDAO;
import daoservice.IUnidadeProdutoDAO;

public class DaoFactory {

	public static IUnidadeProdutoDAO getIUnidadeProdutoDAO(){
		return new UnidadeProdutoDAO();
	}

	public static IStatusDAO getIStatusDAO(){
		return new StatusDAO();
	}

	public static IProdutoVendaDAO getIProdutoVendaDAO(){
		return new ProdutoVendaDAO();
	}

	public static IProdutoMateriaPrimaDAO getIProdutoMateriaPrimaDAO(){
		return new ProdutoMateriaPrimaDAO();
	}

	public static IOrdemProducaoDAO getIOrdemProducaoDAO(){
		return new OrdemProducaoDAO();
	}

	public static ICompraDAO getICompraDAO(){
		return new CompraDAO();
	}
	
	public static IFormaPgtoDAO getIFormaPgtoDAO(){
		return new FormaPgtoDAO();
	}
	
	public static IFornecedorDAO getIFornecedorDAO(){
		return new FornecedorDAO();
	}
	
	public static ILoginDAO getILoginDAO(){
		return new LoginDAO();
	}
	
	public static IMateriaPrimaDAO getIMateriaPrimaDAO(){
		return new MateriaPrimaDAO();
	}
	
	public static IFuncionarioCantinaDAO getIFuncionarioCantinaDAO(){
		return new FuncionarioCantinaDAO();
	}
	
}
