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
import daoservice.ILoginDAO;

public class LoginDAO implements ILoginDAO {
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	private List<FuncionarioCantinaVO> buscarCantinasPorFuncionario(FuncionarioVO funcionario){
		
		List<FuncionarioCantinaVO> funcionarioCantinas = null;
		FuncionarioCantinaVO funcionarioCantina = null;
				
		try {
						
			pstm = conexao.prepareStatement(
					"select fc.id_funcionario_cantina, fc.cargo, fc.data_contratacao, fc.data_saida, fc.id_cantina "
					+ "from funcionario_cantina fc "
					+ "inner join pessoa_funcionario pf on pf.id_pessoa_funcionario = fc.id_pessoa_funcionario "
					+ "where fc.id_pessoa_funcionario = ? and fc.ativo = 1");
			
			pstm.setLong(1, funcionario.getIdPessoa());
			
			rs = pstm.executeQuery();
			
			funcionarioCantinas = new ArrayList<FuncionarioCantinaVO>();
			
			while(rs.next()){
				
				funcionarioCantina = new FuncionarioCantinaVO();
				funcionarioCantina.setIdFuncionarioCantina(rs.getLong("id_funcionario_cantina"));
				funcionarioCantina.setCargo(rs.getString("cargo"));
				funcionarioCantina.setDataContratacao(rs.getDate("data_contratacao"));
				funcionarioCantina.setDataSaida(rs.getDate("data_saida"));
				funcionarioCantina.setCantina(new CantinaVO());
				funcionarioCantina.getCantina().setIdCantina(rs.getLong("id_cantina"));
				funcionarioCantina.setFuncionario(funcionario);
				
				funcionarioCantinas.add(funcionarioCantina);
				
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstm.close();
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return funcionarioCantinas;
	}

	@Override
	public UsuarioVO logarUsuario(UsuarioVO usuario) {
		
		UsuarioVO usuarioLogado = null;
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select u.id_usuario, u.senha, u.login_usuario, "
					+ "f.cod_funcionario, "
					+ "p.id_pessoa, p.nome, p.cpf, p.rg, p.email, p.telefone, p.data_nascimento, "
					+ "fc.id_funcionario_cantina "
					+ "from usuario u "
					+ "inner join pessoa_funcionario f on f.id_usuario = u.id_usuario "
					+ "inner join funcionario_cantina fc on fc.id_pessoa_funcionario = f.id_pessoa_funcionario "
					+ "inner join pessoa p on p.id_pessoa = f.id_pessoa_funcionario "
					+ "where u.login_usuario = ? and u.senha = ? and u.ativo = 1");
			
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			
			rs = pstm.executeQuery();
						
			if(rs.next()){
				usuarioLogado = new UsuarioVO();
				usuarioLogado.setIdUsuario(rs.getLong("id_usuario"));
				usuarioLogado.setSenha(rs.getString("senha"));
				usuarioLogado.setLogin(rs.getString("login_usuario"));
				usuarioLogado.setFuncionario(new FuncionarioVO());
				usuarioLogado.getFuncionario().setCodPessoa(rs.getString("cod_funcionario"));
				usuarioLogado.getFuncionario().setIdPessoa(rs.getLong("id_pessoa"));
				usuarioLogado.getFuncionario().setNome(rs.getString("nome"));
				usuarioLogado.getFuncionario().setRg(rs.getString("rg"));
				usuarioLogado.getFuncionario().setCpf(rs.getString("cpf"));
				usuarioLogado.getFuncionario().setEmail(rs.getString("email"));
				usuarioLogado.getFuncionario().setTelefone(rs.getString("telefone"));
				usuarioLogado.getFuncionario().setDataNascimento(rs.getDate("data_nascimento"));
			}
			
			if(usuarioLogado != null){
				usuarioLogado.getFuncionario().setFuncionarioCantinas(buscarCantinasPorFuncionario(usuarioLogado.getFuncionario()));
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
		
		return usuarioLogado;
	}

	
	
}
