package bo;

import java.util.ArrayList;
import java.util.List;

import utils.UtilFuncoes;
import vo.ProdutoVO;
import daoservice.IProdutoDAO;
import enumeradores.TipoProduto;

public abstract class ProdutoBO<P extends ProdutoVO, D extends IProdutoDAO<P>> {
	
	private D produtoDao;
	
	public ProdutoBO(D produtoDao) {
		this.produtoDao = produtoDao;
	}
	
	public P incluir(P produto){
		
		produto.setQtdeEstoque(0d);
		
		return produtoDao.incluir(produto);
		
	}
	
	public boolean alterar(P produto){
		
		return produtoDao.alterar(produto);
		
	}
	
	public boolean deletar(P produto){
		
		return produtoDao.deletar(produto.getIdProduto());
		
	}
	
	public boolean entradaEstoque(Long idProduto, Double qtd){
		
		return produtoDao.entradaEstoque(idProduto, qtd);
		
	}
	
	public boolean retiradaEstoque(Long idProduto, Double qtd){
		
		return produtoDao.retiradaEstoque(idProduto, qtd);
		
	}

	public Boolean verificaDescricaoVazio(String descricao){
		
		return UtilFuncoes.isCampoVazio(descricao);
		
	}
	
	public List<P> buscarProdutoPorCodigoENome(String cod, String nome){
		
		return produtoDao.buscarPorCodigoENome(cod, nome);
		
	}
	
	public List<P> consultarTodosProdutos(){
		
		return produtoDao.consultar();
	}
	
	public P consultarProdutoPorId(Long id){
		
		return (P) produtoDao.consultarPorId(id);
	}
	
	public List<TipoProduto> consultarTiposProduto(){
		
		List<TipoProduto> tiposProduto = new ArrayList<TipoProduto>();
		TipoProduto[] tp = TipoProduto.values();
		
		for (TipoProduto tipoProduto : tp) {
			tiposProduto.add(tipoProduto);
		}
		
		return tiposProduto;
		
	}
	
	public boolean isCampoVazio(String campo){
		return UtilFuncoes.isCampoVazio(campo);
	}
		
}
