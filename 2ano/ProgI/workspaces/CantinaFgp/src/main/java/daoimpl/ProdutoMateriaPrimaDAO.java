package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;
import daoservice.IProdutoMateriaPrimaDAO;
import enumeradores.TipoProduto;

public class ProdutoMateriaPrimaDAO implements IProdutoMateriaPrimaDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;

	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}
	
	@Override
	public ProdutoMateriaPrimaVO incluir(ProdutoMateriaPrimaVO produtoMateriaPrima) {
		return null;
	}

	@Override
	public boolean alterar(ProdutoMateriaPrimaVO objeto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<ProdutoMateriaPrimaVO> consultar() {
		
		return null;
	}

	@Override
	public ProdutoMateriaPrimaVO consultarPorId(Long id) {
		
		return null;
	}
	
	public List<ProdutoMateriaPrimaVO> consultarReceitaPorIdProduto(Long id) {
		
		List<ProdutoMateriaPrimaVO> listaMateriasPrimaProduto = null;
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pv.id_produto_venda, mp.cod_materia_prima, mp.descricao, mp.id_materia_prima, mp.lote, "
					+"mp.ativo, mp.preco_custo, u.id_unidade, u.abreviatura, u.descricao as descricao_unidade, "
					+"mpc.id_estoque_mat_prima, mpc.qtde_maxima, mpc.qtde_minima, mpc.estoque, r.qtde " 
					+"from produto_venda pv "
					+"inner join receita r on pv.id_produto_venda = r.id_produto "
					+"inner join materia_prima mp on r.id_materia_prima = mp.id_materia_prima "
					+"inner join materia_prima_cantina mpc on mpc.id_materia_prima = mp.id_materia_prima "
					+"left join unidade u on u.id_unidade = r.id_unidade "
					+"where pv.id_produto_venda = ? and pv.ativo = 1 and mp.ativo = 1 and u.ativo = 1");
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
			
			ProdutoMateriaPrimaVO produtoMateriaPrima = null;
			listaMateriasPrimaProduto = new ArrayList<ProdutoMateriaPrimaVO>();
			
			while(rs.next()){
				
				produtoMateriaPrima = new ProdutoMateriaPrimaVO();
				
				produtoMateriaPrima.setQtde(rs.getDouble("qtde"));
				
				produtoMateriaPrima.setMateriaPrima(new MateriaPrimaVO());
				produtoMateriaPrima.getMateriaPrima().setAtivo(rs.getBoolean("ativo"));
				produtoMateriaPrima.getMateriaPrima().setCodProduto(rs.getString("cod_materia_prima"));
				produtoMateriaPrima.getMateriaPrima().setDescricao(rs.getString("descricao"));
				produtoMateriaPrima.getMateriaPrima().setIdProduto(rs.getLong("id_materia_prima"));
				produtoMateriaPrima.getMateriaPrima().setPrecoCusto(rs.getDouble("preco_custo"));
				produtoMateriaPrima.getMateriaPrima().setLote(rs.getBoolean("lote"));
				produtoMateriaPrima.getMateriaPrima().setTipo(TipoProduto.MATERIA_PRIMA);
				
				produtoMateriaPrima.setUnidade(new UnidadeProdutoVO());
				produtoMateriaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				produtoMateriaPrima.getUnidade().setDescricao(rs.getString("descricao_unidade"));
				produtoMateriaPrima.getUnidade().setAbreviatura(rs.getString("abreviatura"));
				
				produtoMateriaPrima.getMateriaPrima().setEstoque(new ProdutoCantinaVO());
				produtoMateriaPrima.getMateriaPrima().getEstoque().setIdEstoque(rs.getLong("id_estoque_mat_prima"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeAtual(rs.getDouble("estoque"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeMaxima(rs.getDouble("qtde_maxima"));
				produtoMateriaPrima.getMateriaPrima().getEstoque().setQtdeMinima(rs.getDouble("qtde_minima"));
				
				produtoMateriaPrima.setProdutoFabricado(new ProdutoVendaVO());
				produtoMateriaPrima.getProdutoFabricado().setIdProduto(rs.getLong("id_produto_venda"));
				
				listaMateriasPrimaProduto.add(produtoMateriaPrima);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
	
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				conexao.close();
				pstm.close();
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		

		return listaMateriasPrimaProduto;
		
	}

}
