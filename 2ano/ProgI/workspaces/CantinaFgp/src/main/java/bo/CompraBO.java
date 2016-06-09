package bo;

import java.util.Date;
import java.util.List;

import utils.UtilFuncoes;
import vo.CompraVO;
import vo.FornecedorVO;
import vo.ItemCompraVO;
import vo.MateriaPrimaVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import daoimpl.DaoFactory;
import daoservice.ICompraDAO;

public class CompraBO {

	private ICompraDAO compraDao;
	private ProdutoVendaBO prodVendaBo;
	private MateriaPrimaBO matPrimaBo;

	{
		compraDao = DaoFactory.getICompraDAO();
		prodVendaBo = new ProdutoVendaBO();
		matPrimaBo = new MateriaPrimaBO();		
	}
	
	// Métodos
	
	public boolean isDataValida(Date data){
		
		if(new Date().before(data)){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isAlteracaoPermitida(CompraVO compra){
		
		if(compra.getStatus().getDescricao().equals("Concluída")){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isCampoVazio(String campo){
		
		return UtilFuncoes.isCampoVazio(campo);
		
	}
	
	public boolean isValorValido(String campo){
		
		return UtilFuncoes.isValorNumerico(campo) && Double.parseDouble(campo) >= 0;
		
	}
	
	public boolean isQtdeValida(String campo){
		
		return UtilFuncoes.isValorNumerico(campo) && Double.parseDouble(campo) > 0;
		
	}
	
	public boolean isFornecedorValido(FornecedorVO fornecedor){
		
		return !(fornecedor == null || fornecedor.getIdFornecedor() == null || fornecedor.getIdFornecedor().equals(""));
		
	}
	
	public boolean isListaItensCompraValida(List<ItemCompraVO> listaItensCompra){
		
		return !(listaItensCompra == null || listaItensCompra.size() == 0);
		
	}
	
	

	// CRUD

	public CompraVO incluir(CompraVO compra) {

		return compraDao.incluir(compra);

	}

	public boolean alterar(CompraVO compra) {
			
	boolean alterado = false;
	
	if(compraDao.alterar(compra)){
		
		alterado = true;
		
		String status = compra.getStatus().getDescricao();
	
		if(status.equals("Concluída")){
			
			List<ItemCompraVO> itensCompra = compra.getItensCompra();
			
			for (ItemCompraVO itemCompra : itensCompra) {
				
				ProdutoVO produto = itemCompra.getProduto();
				
				if(produto instanceof ProdutoVendaVO){
					if(!prodVendaBo.entradaEstoque(produto.getIdProduto(), itemCompra.getQtde())){
						alterado = false;
					}
				}
				else if(produto instanceof MateriaPrimaVO){
					if(!matPrimaBo.entradaEstoque(produto.getIdProduto(), itemCompra.getQtde())){
						alterado = false;
					}
				}
				
			}
			
		}
	
	}
		
		return alterado;

	}

	public boolean deletar(CompraVO compra) {

		if(compra.getStatus().getDescricao().equals("Concluída")){
			return false;
		}
		
		return compraDao.deletarCompra(compra);

	}

	public List<CompraVO> consultar() {

		return compraDao.consultar();

	}

}
