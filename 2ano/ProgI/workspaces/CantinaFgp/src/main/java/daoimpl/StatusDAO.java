package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.StatusVO;
import daoservice.IStatusDAO;
import enumeradores.TipoStatus;

public class StatusDAO implements IStatusDAO{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	{
		fabrica = ConnectionFactory.getInstance();
	}

	@Override
	public StatusVO incluir(StatusVO objeto) {
		
		return null;
	}

	@Override
	public boolean alterar(StatusVO objeto) {
		
		return false;
	}

	@Override
	public boolean deletar(Long id) {
		
		return false;
	}

	@Deprecated
	@Override
	public List<StatusVO> consultar() {
		return null;
	}

	@Override
	public StatusVO consultarPorId(Long id) {
		
		return null;
	}

	@Override
	public List<StatusVO> consultar(TipoStatus tipoStatus) {
		
			List<StatusVO> listaStatus = null;
			String tipo = "";
			
			if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
				tipo = TipoStatus.ORDEM_PRODUCAO.getTipo();
			}
			else if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
				tipo = TipoStatus.ORDEM_COMPRA.getTipo();
			}
			else if(tipoStatus.equals(TipoStatus.VENDA)){
				tipo = TipoStatus.VENDA.getTipo();
			}
	

		try {

			listaStatus = new ArrayList<StatusVO>();

			conexao = fabrica.getConexao();

			pstm = conexao.prepareStatement("select id_status, descricao, tipo from status where tipo = ?");

			pstm.setString(1, tipo);
			
			rs = pstm.executeQuery();

			StatusVO status = null;

			while (rs.next()) {

				status = new StatusVO();
				status.setIdStatus(rs.getLong("id_status"));
				status.setDescricao(rs.getString("descricao"));
				
				if(rs.getString("tipo").equals(TipoStatus.ORDEM_COMPRA.getTipo())){
					
					status.setTipoStatus(TipoStatus.ORDEM_COMPRA);
					
				}
				
				if(rs.getString("tipo").equals(TipoStatus.ORDEM_PRODUCAO.getTipo())){
					
					status.setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
					
				}
				

				if(rs.getString("tipo").equals(TipoStatus.VENDA.getTipo())){
					
					status.setTipoStatus(TipoStatus.VENDA);
					
				}
				
				listaStatus.add(status);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexao.close();
				pstm.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return listaStatus;

	}
	
	

}
