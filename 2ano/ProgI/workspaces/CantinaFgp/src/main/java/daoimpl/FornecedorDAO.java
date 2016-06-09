package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FornecedorVO;
import daoservice.IFornecedorDAO;

public class FornecedorDAO implements IFornecedorDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	@Override
	public FornecedorVO incluir(FornecedorVO objeto) {
		
		return null;
	}

	@Override
	public boolean alterar(FornecedorVO objeto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<FornecedorVO> consultar() {
		
		List<FornecedorVO> listaFornecedores = new ArrayList<FornecedorVO>();
		
		try {

			conexao = fabrica.getConexao();

			pstm = conexao.prepareStatement("select id_fornecedor, nome, contato from fornecedor where ativo = 1");
			
			rs = pstm.executeQuery();

			FornecedorVO fornecedor = null;

			while (rs.next()) {
				
				fornecedor = new FornecedorVO();
				
				fornecedor.setIdFornecedor(rs.getLong("id_fornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setContato(rs.getString("contato"));
				fornecedor.setCodFornecedor(fornecedor.getIdFornecedor().toString());

				listaFornecedores.add(fornecedor);
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
		
		return listaFornecedores;
	}

	@Override
	public FornecedorVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<FornecedorVO> buscarPorCodigoENome(String cod, String nome) {
		
		List<FornecedorVO> listaFornecedores = new ArrayList<FornecedorVO>();
		
		Integer codigo = 0;
		if(!cod.equals("")){
			codigo = Integer.parseInt(cod);
		}
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select id_fornecedor, nome, contato from fornecedor where id_fornecedor = ? or nome like ? and ativo = 1");
			
			
			
			pstm.setInt(1, codigo);
			pstm.setString(2, "%" + nome + "%");
		
			rs = pstm.executeQuery();
			
			FornecedorVO fornecedor = null;
			
			while(rs.next()){
				
				fornecedor = new FornecedorVO();
				fornecedor.setIdFornecedor(rs.getLong("id_fornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setContato(rs.getString("contato"));
				fornecedor.setCodFornecedor(fornecedor.getIdFornecedor().toString());
				
				listaFornecedores.add(fornecedor);
				
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
		
		return listaFornecedores;
		
	}

}
