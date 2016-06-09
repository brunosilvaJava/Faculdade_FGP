package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CantinaVO;
import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.UsuarioVO;
import daoservice.IFuncionarioCantinaDAO;

public class FuncionarioCantinaDAO implements IFuncionarioCantinaDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	public List<FuncionarioCantinaVO> buscarPorCodigoENome(String cod, String nome){
		
		List<FuncionarioCantinaVO> listaFuncionarios = new ArrayList<FuncionarioCantinaVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pf.id_pessoa_funcionario, pf.cod_funcionario, p.nome, p.cpf, p.data_nascimento, u.id_usuario, "
					+ "fc.id_funcionario_cantina, fc.cargo, fc.data_contratacao, fc.data_saida, fc.ativo, fc.id_cantina, "
					+ "c.id_cantina, c.nome "
					+ "from pessoa_funcionario pf "
					+ "inner join pessoa p on p.id_pessoa = pf.id_pessoa_funcionario "
					+ "inner join usuario u on u.id_usuario = pf.id_usuario "
					+ "inner join funcionario_cantina fc on pf.id_pessoa_funcionario = fc.id_pessoa_funcionario "
					+ "inner join cantina c on c.id_cantina = fc.id_cantina "
					+ "where fc.ativo = 1 and p.nome like ? and pf.cod_funcionario like ?");
			
			pstm.setString(1, "%" + nome + "%");
			pstm.setString(2, "%" + cod + "%");
			
			rs = pstm.executeQuery();
			
			FuncionarioVO funcionario = null;
			FuncionarioCantinaVO funcionarioCantina = null;
			CantinaVO cantina = null;
			
			while(rs.next()){
				
				funcionarioCantina = new FuncionarioCantinaVO();
				funcionarioCantina.setAtivo(rs.getBoolean("ativo"));
				funcionarioCantina.setCargo(rs.getString("cargo"));
				funcionarioCantina.setDataContratacao(rs.getDate("data_contratacao"));
				funcionarioCantina.setDataSaida(rs.getDate("data_saida"));
				funcionarioCantina.setIdFuncionarioCantina(rs.getLong("id_funcionario_cantina"));
				
				funcionario = new FuncionarioVO();
				funcionario.setCodPessoa(rs.getString("cod_funcionario"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(rs.getDate("data_nascimento"));
				funcionario.setIdPessoa(rs.getLong("id_pessoa_funcionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(new UsuarioVO());
				funcionario.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
				
				funcionarioCantina.setFuncionario(funcionario);
				
				cantina = new CantinaVO();
				cantina.setIdCantina(rs.getLong("id_cantina"));
				cantina.setNome("nome_cantina");
				
				funcionarioCantina.setCantina(cantina);
				
				listaFuncionarios.add(funcionarioCantina);
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
		
		return listaFuncionarios;
		
	}

	@Override
	public FuncionarioCantinaVO incluir(FuncionarioCantinaVO objeto) {
		
		return null;
	}

	@Override
	public boolean alterar(FuncionarioCantinaVO objeto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Override
	public List<FuncionarioCantinaVO> consultar() {
		
		return null;
	}

	@Override
	public FuncionarioCantinaVO consultarPorId(Long id) {
		
		FuncionarioCantinaVO funcionarioCantina = null;
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select pf.id_pessoa_funcionario, pf.cod_funcionario, p.nome, p.cpf, p.data_nascimento, u.id_usuario, "
					+ "fc.id_funcionario_cantina, fc.cargo, fc.data_contratacao, fc.data_saida, fc.ativo, fc.id_cantina, "
					+ "c.id_cantina, c.nome "
					+ "from pessoa_funcionario pf "
					+ "inner join pessoa p on p.id_pessoa = pf.id_pessoa_funcionario "
					+ "inner join usuario u on u.id_usuario = pf.id_usuario "
					+ "inner join funcionario_cantina fc on pf.id_pessoa_funcionario = fc.id_pessoa_funcionario "
					+ "inner join cantina c on c.id_cantina = fc.id_cantina "
					+ "where fc.ativo = 1 and fc.id_funcionario_cantina = ?");
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
			
			funcionarioCantina = null;
			CantinaVO cantina = null;
			FuncionarioVO funcionario = null;
			
			while(rs.next()){
				
				funcionarioCantina = new FuncionarioCantinaVO();
				funcionarioCantina.setAtivo(rs.getBoolean("ativo"));
				funcionarioCantina.setCargo(rs.getString("cargo"));
				funcionarioCantina.setDataContratacao(rs.getDate("data_contratacao"));
				funcionarioCantina.setDataSaida(rs.getDate("data_saida"));
				funcionarioCantina.setIdFuncionarioCantina(rs.getLong("id_funcionario_cantina"));
				
				funcionario = new FuncionarioVO();
				funcionario.setCodPessoa(rs.getString("cod_funcionario"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataNascimento(rs.getDate("data_nascimento"));
				funcionario.setIdPessoa(rs.getLong("id_pessoa_funcionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(new UsuarioVO());
				funcionario.getUsuario().setIdUsuario(rs.getLong("id_usuario"));
				
				funcionarioCantina.setFuncionario(funcionario);
				
				cantina = new CantinaVO();
				cantina.setIdCantina(rs.getLong("id_cantina"));
				cantina.setNome("nome_cantina");
				
				funcionarioCantina.setCantina(cantina);
				
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
		
		return funcionarioCantina;
		
	}

	
}
