package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FornecedorVO;
import vo.UnidadeProdutoVO;
import daoservice.IUnidadeProdutoDAO;

public class UnidadeProdutoDAO implements IUnidadeProdutoDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	

	@Override
	public UnidadeProdutoVO incluir(UnidadeProdutoVO unidadeProduto) {
		
		return null;
	}

	@Override
	public boolean alterar(UnidadeProdutoVO unidadeProduto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<UnidadeProdutoVO> consultar() {
		
		List<UnidadeProdutoVO> unidadeProdutoVO = new ArrayList<UnidadeProdutoVO>();
		
		try {

			conexao = fabrica.getConexao();

			pstm = conexao.prepareStatement("select id_unidade, descricao, abreviatura from unidade where ativo = 1");
			
			rs = pstm.executeQuery();

			UnidadeProdutoVO unidade = null;

			while (rs.next()) {
				
				unidade = new UnidadeProdutoVO();
				
				unidade.setIdUnidadeProduto(rs.getLong("id_unidade"));
				unidade.setDescricao(rs.getString("descricao"));
				unidade.setAbreviatura(rs.getString("abreviatura"));

				unidadeProdutoVO.add(unidade);
			}

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				conexao.close();
				pstm.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}
		
		return unidadeProdutoVO;
	}

	@Override
	public UnidadeProdutoVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<UnidadeProdutoVO> buscarPorCodigoENome(String cod, String nome) {
		
		return null;
	}

}
