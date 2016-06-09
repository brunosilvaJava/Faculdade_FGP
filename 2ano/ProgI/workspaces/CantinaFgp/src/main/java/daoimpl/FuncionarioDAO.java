package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import vo.FuncionarioVO;
import daoservice.IFuncionarioDAO;

public class FuncionarioDAO implements IFuncionarioDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}
	
	@Override
	public FuncionarioVO incluir(FuncionarioVO funcionario) {
		return null;
	}

	@Override
	public boolean alterar(FuncionarioVO funcionario) {
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		return false;
	}

	@Override
	public List<FuncionarioVO> consultar() {
		return null;
	}

	@Override
	public FuncionarioVO consultarPorId(Long id) {
		return null;
	}


}
