package bo;

import java.util.List;

import utils.UtilFuncoes;
import vo.OrdemProducaoVO;
import vo.ProdutoMateriaPrimaVO;
import daoimpl.DaoFactory;
import daoservice.IOrdemProducaoDAO;

public class OrdemProducaoBO {
	
	private IOrdemProducaoDAO ordemProdDao;
	private ProdutoVendaBO prodVendaBo;
	private MateriaPrimaBO matPrimaBo;
	
	{
		
		ordemProdDao = DaoFactory.getIOrdemProducaoDAO();
		prodVendaBo = new ProdutoVendaBO();
		matPrimaBo = new MateriaPrimaBO();
		
	}
	
	public boolean isQtdNecessaria(OrdemProducaoVO ordemProducao){
		
		for (ProdutoMateriaPrimaVO itemReceita : ordemProducao.getProdutoVenda().getReceita()) {
			
			if(itemReceita.getQtde() * ordemProducao.getQtde() > itemReceita.getMateriaPrima().getEstoque().getQtdeAtual()){
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	public boolean isAlteracaoPermitida(OrdemProducaoVO ordemProducao){
		
		if(ordemProducao.getStatus().getDescricao().equals("Em Aberto")){
			return true;
		}
		
		return false;
		
	}
	
	public boolean isCampoFuncionarioVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoCodigoFuncionarioVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoProdutoVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoCodigoProdutoVazio(String campo){
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoQtdVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
	}
	
	public boolean isCampoQtdNegativo(String campo){
		
		return UtilFuncoes.stringToInteger(campo) <= 0;
	}
	
	public boolean isCampoQuantidadeNumerico(String campo){
		
		return UtilFuncoes.isValorNumerico(campo);
	}
	
	public Integer stringToInteger(String campo){
		return UtilFuncoes.stringToInteger(campo);
	}

	// CRUD
	
	public OrdemProducaoVO incluir(OrdemProducaoVO ordemProducao){
		
		return ordemProdDao.incluir(ordemProducao);
		
	}
	
	public List<OrdemProducaoVO> consultar(){
		
		return ordemProdDao.consultar();
		
	}
		
	public boolean alterar(OrdemProducaoVO ordemProducao){
				
		boolean alterado = false;
		
		if(ordemProdDao.alterar(ordemProducao)){
			
			alterado = true;
			
			String status = ordemProducao.getStatus().getDescricao();
		
			if(status.equals("Concluída")){
								
				if(!prodVendaBo.entradaEstoque(ordemProducao.getProdutoVenda().getIdProduto(), ordemProducao.getQtde().doubleValue())){
					alterado = false;
				}
				
			}
			else if(status.equals("Em Fabricação")){
				
				List<ProdutoMateriaPrimaVO> receita = ordemProducao.getProdutoVenda().getReceita();
				
				for (ProdutoMateriaPrimaVO itemReceita : receita) {
					
					if(!matPrimaBo.retiradaEstoque(itemReceita.getMateriaPrima().getIdProduto(), itemReceita.getQtde()*ordemProducao.getQtde())){
						alterado = false;
					}
					
				}
								
			}
			
		}
		
		return alterado;
		
	}
	
	public boolean deletar(OrdemProducaoVO ordemProducao){
				
		return ordemProdDao.deletar(ordemProducao.getIdOrdemProducao());
		
	}

}
