package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.UnidadeProdutoVO;
import daoservice.IMateriaPrimaDAO;
import enumeradores.TipoProduto;

public class MateriaPrimaDAO implements IMateriaPrimaDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		
	}

	@Override
	public MateriaPrimaVO incluir(MateriaPrimaVO materiaPrima) {
		
		
		
		return null;
		
	}

	@Override
	public boolean alterar(MateriaPrimaVO materiaPrima) {
		
		
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<MateriaPrimaVO> consultar() {
		
		List<MateriaPrimaVO> listaMateriasPrimas = new ArrayList<MateriaPrimaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select mp.id_materia_prima, mp.cod_materia_prima, mp.descricao, mp.qtd_estoque, mp.ativo, mp.preco_custo, mp.lote, mp.id_unidade, "
					+ "u.descricao, u.ativo, "
					+ "mpc.estoque, mpc.qtde_maxima, mpc.qtde_minima "
					+ "from materia_prima mp "
					+ "inner join unidade u on u.id_unidade = mp.id_unidade "
					+ "inner join materia_prima_cantina mpc on mpc.id_materia_prima = mp.id_materia_prima ");
			
			rs = pstm.executeQuery();
			
			MateriaPrimaVO materiaPrima = null;
			
			while(rs.next()){
				
				materiaPrima = new MateriaPrimaVO();
				materiaPrima.setTipo(TipoProduto.MATERIA_PRIMA);
				materiaPrima.setIdProduto(rs.getLong("id_materia_prima"));
				materiaPrima.setCodProduto(rs.getString("id_materia_prima"));
				materiaPrima.setDescricao(rs.getString("descricao"));
				materiaPrima.setLote(rs.getBoolean("lote"));
				materiaPrima.setPrecoCusto(rs.getDouble("preco_custo"));
				materiaPrima.setAtivo(rs.getBoolean("ativo"));
				
				materiaPrima.setUnidade(new UnidadeProdutoVO());
				materiaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				materiaPrima.getUnidade().setDescricao(rs.getString("descricao"));
				materiaPrima.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				materiaPrima.setEstoque(new ProdutoCantinaVO());
				materiaPrima.getEstoque().setQtdeAtual(rs.getDouble("estoque"));
				materiaPrima.getEstoque().setQtdeMaxima(rs.getDouble("qtde_maxima"));
				materiaPrima.getEstoque().setQtdeMinima(rs.getDouble("qtde_minima"));
								
				listaMateriasPrimas.add(materiaPrima);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			}
		}
		
		return listaMateriasPrimas;
	}

	@Override
	public MateriaPrimaVO consultarPorId(Long id) {
		
		MateriaPrimaVO materiaPrima = null;
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select mp.id_materia_prima, mp.cod_materia_prima, mp.descricao, mp.ativo as mp_ativo, mp.preco_custo, "
					+ "mp.lote, mp.id_unidade, u.descricao, u.ativo as u_ativo "
					+ "from materia_prima mp "
					+ "inner join unidade u on u.id_unidade = mp.id_unidade "
					+ "where id_materia_prima = ?");
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
		
			if(rs.next()){
				
				materiaPrima = new MateriaPrimaVO();
				materiaPrima.setIdProduto(rs.getLong("id_materia_prima"));
				materiaPrima.setCodProduto(rs.getString("cod_materia_prima"));
				materiaPrima.setDescricao(rs.getString("descricao"));
				materiaPrima.setTipo(TipoProduto.MATERIA_PRIMA);
				materiaPrima.setLote(rs.getBoolean("lote"));
				materiaPrima.setPrecoCusto(rs.getDouble("preco_custo"));
				materiaPrima.setAtivo(rs.getBoolean("mp_ativo"));
				materiaPrima.setUnidade(new UnidadeProdutoVO());
				materiaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				materiaPrima.getUnidade().setDescricao(rs.getString("descricao"));
				materiaPrima.getUnidade().setStatus(rs.getBoolean("u_ativo"));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			}
		}
		
		return materiaPrima;
	}

	@Override
	public List<MateriaPrimaVO> buscarPorCodigoENome(String cod, String nome) {
		
		List<MateriaPrimaVO> listaMateriasPrimas = new ArrayList<MateriaPrimaVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select mp.id_materia_prima, mp.cod_materia_prima, mp.descricao, "
					+ "mp.qtd_estoque, mp.ativo, mp.lote, mp.id_unidade, mp.preco_custo, "
					+ "u.descricao, u.ativo "
					+ "from materia_prima mp "
					+ "left join unidade u on u.id_unidade = mp.id_unidade "
					+ "where mp.cod_materia_prima like ? or mp.descricao like ?");
			
			pstm.setString(1, "%" + cod + "%");
			pstm.setString(2, "%" + nome + "%");
		
			rs = pstm.executeQuery();
			
			MateriaPrimaVO materiaPrima = null;
			
			while(rs.next()){
				
				materiaPrima = new MateriaPrimaVO();
				materiaPrima.setIdProduto(rs.getLong("id_materia_prima"));
				materiaPrima.setCodProduto(rs.getString("cod_materia_prima"));
				materiaPrima.setDescricao(rs.getString("descricao"));
				materiaPrima.setQtdeEstoque(rs.getDouble("qtd_estoque"));
				materiaPrima.setAtivo(rs.getBoolean("ativo"));
				materiaPrima.setLote(rs.getBoolean("lote"));
				materiaPrima.setTipo(TipoProduto.MATERIA_PRIMA);
				materiaPrima.setPrecoCusto(rs.getDouble("preco_custo"));
				materiaPrima.setUnidade(new UnidadeProdutoVO());
				materiaPrima.getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				materiaPrima.getUnidade().setDescricao(rs.getString("descricao"));
				materiaPrima.getUnidade().setStatus(rs.getBoolean("ativo"));
				
				listaMateriasPrimas.add(materiaPrima);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
			}
		}
		
		return listaMateriasPrimas;
		
	}

	@Override
	public boolean entradaEstoque(Long idProduto, Double qtd) {
		
		ResultSet rs = null;
		
		try{
			
			conexao = fabrica.getConexao();

			pstm = conexao.prepareStatement("select estoque from materia_prima_cantina where id_materia_prima = ?");
			
			pstm.setLong(1, idProduto);
			
			rs = pstm.executeQuery();
			
			Integer qtdAtual = 0;
			
			if(rs.next()){
				
				qtdAtual = rs.getInt("estoque");
				
			}
			
			pstm = conexao.prepareStatement("update materia_prima_cantina set estoque = ? where id_materia_prima = ?");

			pstm.setDouble(1, qtdAtual+qtd);
			pstm.setLong(2, idProduto);
			
			if(pstm.executeUpdate() == 0){
				conexao.rollback();
				return false;
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
				return false;
				} catch (SQLException e1) {}
		} finally {			
			try {
				conexao.close();
				pstm.close();
				if(rs!=null){
					rs.close();	
				}
			} catch (SQLException e) {
				return false;
			}
			
		}
		
		return true;
		
	}

	@Override
	public boolean retiradaEstoque(Long idProduto, Double qtd) {
		
		try{
			
			conexao = fabrica.getConexao();

			pstm = conexao.prepareStatement("select estoque from materia_prima_cantina where id_materia_prima = ?");
			
			pstm.setLong(1, idProduto);
			
			rs = pstm.executeQuery();
			
			Integer qtdAtual = 0;
			
			if(rs.next()){
				
				qtdAtual = rs.getInt("estoque");
				
			}
			
			pstm = conexao.prepareStatement("update materia_prima_cantina set estoque = ? where id_materia_prima = ?");
			
			pstm.setDouble(1, qtdAtual-qtd);
			pstm.setLong(2, idProduto);
			
			if(pstm.executeUpdate() == 0){
				conexao.rollback();
				return false;
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
				return false;
				} catch (SQLException e1) {}
		} finally {			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
