package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FormaPgtoVO;
import daoservice.IFormaPgtoDAO;

public class FormaPgtoDAO implements IFormaPgtoDAO {
	
	private ConnectionFactory fabrica;
	private Connection conexao;
	private ResultSet rs;
	private PreparedStatement pstm;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
	}

	@Override
	public FormaPgtoVO incluir(FormaPgtoVO formaPgto) {
		
		return null;
	}

	@Override
	public boolean alterar(FormaPgtoVO formaPgto) {
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		
		return true;
	}

	@Override
	public List<FormaPgtoVO> consultar() {
		
		List<FormaPgtoVO> listaFormaPagamento = new ArrayList<FormaPgtoVO>();
		
		try {
			
			conexao = fabrica.getConexao();
			
			String sql = "select id_forma_pgto, descricao from forma_pgto";
			
			pstm = conexao.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			FormaPgtoVO formaPgto = null;
			
			while(rs.next()){
				
				formaPgto = new FormaPgtoVO();
				
				formaPgto.setIdFormaPgtoVenda(rs.getLong("id_forma_pgto"));
				formaPgto.setDescricao(rs.getString("descricao"));
				
				listaFormaPagamento.add(formaPgto);
				
			}
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		return listaFormaPagamento;
		
	}

	@Override
	public FormaPgtoVO consultarPorId(Long id) {
		
		return null;
	}

}
