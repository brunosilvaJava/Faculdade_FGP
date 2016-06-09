package bo;

import java.util.ArrayList;
import java.util.List;

import vo.FornecedorVO;
import vo.ProdutoVendaVO;
import daoimpl.DaoFactory;
import daoimpl.FornecedorDAO;
import daoservice.IProdutoVendaDAO;
import enumeradores.TipoProduto;

public class ProdutoVendaBO extends ProdutoBO<ProdutoVendaVO, IProdutoVendaDAO> {
	
	public static IProdutoVendaDAO produtoVendaDao;
	public FornecedorDAO fornecedorDao;
	
	static{
		produtoVendaDao = DaoFactory.getIProdutoVendaDAO();
	}
	
	{
		fornecedorDao = new FornecedorDAO();
	}

	public ProdutoVendaBO() {
		super(produtoVendaDao);
	}
	
	@Override
	public ProdutoVendaVO incluir(ProdutoVendaVO produto) {

		Long id = produtoVendaDao.getUltimoIdGerado();
		
		if(id == null){
			id = 0l;
		}
		
		Long proximoId = id+1;

		produto.setCodProduto(proximoId.toString());
		
		return super.incluir(produto);
		
	}
	
	public List<ProdutoVendaVO> buscarProdutoFabricadoPorCodigoENome(String nome, String cod){
		
		List<ProdutoVendaVO> produtosVenda = buscarProdutoPorCodigoENome(nome, cod);
		List<ProdutoVendaVO> produtosFabricados = new ArrayList<ProdutoVendaVO>();
		
		for (ProdutoVendaVO produtoVenda : produtosVenda) {
			
			if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
				
				produtosFabricados.add(produtoVenda);
				
			}
			
		}
		
		return produtosFabricados;
		
	}
	
	public List<ProdutoVendaVO> filtrarProdutoRevendaPorCodigoENome(String nome, String cod){
		
		List<ProdutoVendaVO> produtosVenda = buscarProdutoPorCodigoENome(nome, cod);
		List<ProdutoVendaVO> produtosRevenda = new ArrayList<ProdutoVendaVO>();
		
		for (ProdutoVendaVO produtoVenda : produtosVenda) {
			
			if(produtoVenda.getTipo().equals(TipoProduto.REVENDA)){
				
				produtosRevenda.add(produtoVenda);
				
			}
			
		}
		
		return produtosRevenda;
		
	}
	
	public boolean deletar(ProdutoVendaVO produto){
		
		return produtoVendaDao.deletarProduto(produto);
	}
	
	public List<FornecedorVO> consultarTodosFornecedores(){
		
		return fornecedorDao.consultar();
	}

	

}
