package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CompraVO;
import vo.FormaPgtoVO;
import vo.FornecedorVO;
import vo.FuncionarioCantinaVO;
import vo.ItemCompraVO;
import vo.OrdemProducaoVO;
import vo.ProdutoVO;
import vo.StatusVO;
import bo.FuncionarioBO;
import bo.MateriaPrimaBO;
import bo.ProdutoVendaBO;
import daoservice.ICompraDAO;
import enumeradores.TipoGeradorCompra;
import enumeradores.TipoProduto;
import enumeradores.TipoStatus;

public class CompraDAO implements ICompraDAO {

	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	private ProdutoVendaBO prodVendaBo;
	private MateriaPrimaBO matPrimaBo;
	private FuncionarioBO funcionarioBo;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		prodVendaBo = new ProdutoVendaBO();
		matPrimaBo = new MateriaPrimaBO();
		funcionarioBo = new FuncionarioBO();
		
	}
	
	private Long getUltimoIdGerado(){
		
		Long id = null;
		
		try {
						
			PreparedStatement pstm = conexao.prepareStatement("select id_compra from compra where id_compra = (select max(id_compra) from compra)");
		
			rs = pstm.executeQuery();
			
			if(rs.next()){
				id = rs.getLong("id_compra");
			}
			
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
		}
		finally{
			
			try {
				pstm.close();
			} catch (SQLException e) {}
		
		}
		
		return id;
	}
	
	private boolean incluirItemCompra(ItemCompraVO itemCompra){
		
		try {
			
			PreparedStatement pstm = conexao.prepareStatement("insert into item_compra (qtde, valor, id_coringa_produto, tipo, id_compra) values (?,?,?,?,?)");
			
			pstm.setDouble(1, itemCompra.getQtde());
			pstm.setDouble(2, itemCompra.getValor());
			pstm.setLong(3, itemCompra.getProduto().getIdProduto());
			
			if(itemCompra.getProduto().getTipo().equals(TipoProduto.PRODUCAO)){
				pstm.setString(4, TipoProduto.PRODUCAO.getTipoProduto());
			}
			else if(itemCompra.getProduto().getTipo().equals(TipoProduto.REVENDA)){
				pstm.setString(4, TipoProduto.REVENDA.getTipoProduto());
			}
			else if(itemCompra.getProduto().getTipo().equals(TipoProduto.MATERIA_PRIMA)){
				pstm.setString(4, TipoProduto.MATERIA_PRIMA.getTipoProduto());
			}
			
			pstm.setLong(5, itemCompra.getCompra().getIdCompra());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {}
			return false;
		}
		finally{
			
			try {
				pstm.close();
			} catch (SQLException e) {}
		
		}
		
		return true;
		
	}
	
	@Override
	public CompraVO incluir(CompraVO compra) {
		
		java.sql.Date dataSql = new java.sql.Date(compra.getData().getTime());
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "insert into compra (data_compra, tipo_origem, id_coringa_origem, id_forma_pgto, id_fornecedor, id_status_compra, ativo) "
					+ "values (?,?,?,?,?,?,?)";
			
			pstm = conexao.prepareStatement(sql);
			pstm.setDate(1, dataSql);
			
			if(compra.getGeradorCompra() instanceof OrdemProducaoVO){
				
				OrdemProducaoVO ordemProducao = (OrdemProducaoVO) compra.getGeradorCompra();
				
				pstm.setString(2, TipoGeradorCompra.ORDEM_PRODUCAO.getTipo());
				pstm.setLong(3, ordemProducao.getIdOrdemProducao());
			}
			
			else{
				
				FuncionarioCantinaVO funcionarioCantina = (FuncionarioCantinaVO) compra.getGeradorCompra();
				pstm.setString(2, TipoGeradorCompra.FUNCIONARIO_CANTINA.getTipo());
				pstm.setLong(3, funcionarioCantina.getIdFuncionarioCantina());
				
			}
			
			pstm.setLong(4, compra.getFormaPgto().getIdFormaPgtoVenda());
			pstm.setLong(5, compra.getFornecedor().getIdFornecedor());
			pstm.setLong(6, compra.getStatus().getIdStatus());
			pstm.setInt(7, 1);
			
			pstm.executeUpdate();
			
			compra.setIdCompra(getUltimoIdGerado());
					
			for (ItemCompraVO itemCompra : compra.getItensCompra()) {
				
				itemCompra.setCompra(compra);
				
				if(!incluirItemCompra(itemCompra)){
					conexao.rollback();
				}
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {}
			return null;
		}finally{
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			}
			
			
		}
		
		return compra;
	}

	@Override
	public boolean alterar(CompraVO compra) {
		
		java.sql.Date dataSql = new java.sql.Date(compra.getData().getTime());
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"update compra "
					+ "set data_compra = ?, tipo_origem = ?, id_coringa_origem = ?, id_forma_pgto = ?, "
					+ "id_fornecedor = ?, id_status_compra = ? "
					+ "where id_compra = ?");
			
			pstm.setDate(1, dataSql);
			
			if(compra.getGeradorCompra() instanceof OrdemProducaoVO){
				
				OrdemProducaoVO ordemProducao = (OrdemProducaoVO) compra.getGeradorCompra();
				
				pstm.setString(2, TipoGeradorCompra.ORDEM_PRODUCAO.getTipo());
				pstm.setLong(3, ordemProducao.getIdOrdemProducao());
			}
			else{
				
				FuncionarioCantinaVO funcionarioCantina = (FuncionarioCantinaVO) compra.getGeradorCompra();
				pstm.setString(2, TipoGeradorCompra.FUNCIONARIO_CANTINA.getTipo());
				pstm.setLong(3, funcionarioCantina.getIdFuncionarioCantina());
				
			}
			
			pstm.setLong(4, compra.getFormaPgto().getIdFormaPgtoVenda());
			pstm.setLong(5, compra.getFornecedor().getIdFornecedor());
			pstm.setLong(6, compra.getStatus().getIdStatus());
			
			pstm.setLong(7, compra.getIdCompra());
			
			if(pstm.executeUpdate() > 0){
				
				List<ItemCompraVO> itensCompraBanco = consultarItensCompraPorCompra(compra);
				
				if(itensCompraBanco == null){
					conexao.rollback();
					return false;
				}
				
				List<ItemCompraVO> itensCompraAtual = compra.getItensCompra();
				
				List<ItemCompraVO> itensCompraIncluir = new ArrayList<ItemCompraVO>();
				List<ItemCompraVO> itensCompraAlterar = new ArrayList<ItemCompraVO>();
				List<ItemCompraVO> itensCompraExcluir = new ArrayList<ItemCompraVO>();
				
				for (ItemCompraVO itemCompraAtual : itensCompraAtual) {
					
					Boolean itemCompraNovo = true;
										
					for (ItemCompraVO itemCompraBanco : itensCompraBanco) {
												
						if(itemCompraAtual.getIdItemCompra() == itemCompraBanco.getIdItemCompra()){
							
							itensCompraAlterar.add(itemCompraAtual);
							itemCompraNovo = false;
															
						}
						
					}
					
					if(itemCompraNovo){
						itensCompraIncluir.add(itemCompraAtual);
					}
					
				}
				
				for (ItemCompraVO itemCompraBanco : itensCompraBanco) {
					
					Boolean itemCompraExcluir = true;
										
					for (ItemCompraVO itemCompraAtual : itensCompraAtual) {
											
						if(itemCompraBanco.getIdItemCompra() == itemCompraAtual.getIdItemCompra()){
							itemCompraExcluir = false;
						}
						
					}
					
					if(itemCompraExcluir){
						itensCompraExcluir.add(itemCompraBanco);
					}
					
				}
				
				if(itensCompraIncluir.size() > 0){
					
					pstm = conexao.prepareStatement("insert into item_compra (qtde, valor, id_coringa_produto, tipo, id_compra)"
							+ " values (?,?,?,?,?)");
										
					for (ItemCompraVO itemCompra : itensCompraIncluir){
						
						pstm.setDouble(1, itemCompra.getQtde());
						pstm.setDouble(2, itemCompra.getValor());
						pstm.setLong(3, itemCompra.getProduto().getIdProduto());
						pstm.setString(4, itemCompra.getProduto().getTipo().getTipoProduto());
						pstm.setLong(5, itemCompra.getCompra().getIdCompra());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
						
						
					}
				}
				
				if(itensCompraAlterar.size() > 0){
					
					pstm = conexao.prepareStatement("update item_compra set qtde = ?, valor = ?, id_coringa_produto = ?, tipo = ?, id_compra = ?  "
							+ "where id_item_compra = ?");
					
					for(ItemCompraVO itemCompra : itensCompraAlterar){
						
						pstm.setDouble(1, itemCompra.getQtde());
						pstm.setDouble(2, itemCompra.getValor());
						pstm.setLong(3, itemCompra.getProduto().getIdProduto());
						pstm.setString(4, itemCompra.getProduto().getTipo().getTipoProduto());
						pstm.setLong(5, itemCompra.getCompra().getIdCompra());
						pstm.setLong(6, itemCompra.getIdItemCompra());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
						
					}
				}
				
				if(itensCompraExcluir.size() > 0){
					
					pstm = conexao.prepareStatement("delete from item_compra where id_item_compra = ?");
					
					for(ItemCompraVO itemCompra : itensCompraExcluir){
						
						pstm.setLong(1, itemCompra.getIdItemCompra());
						
						if(pstm.executeUpdate() == 0){
							conexao.rollback();
							return false;
						}
					}
				} 
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}finally{
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean deletar(Long id) {
		return true;
	}
	
	@Override
	public List<CompraVO> consultar() {
		
		List<CompraVO> listaCompras = new ArrayList<CompraVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "select c.id_compra, c.data_compra, c.tipo_origem, c.id_coringa_origem, "
					+ "fp.id_forma_pgto, fp.descricao, f.id_fornecedor, f.nome, f.contato, s.id_status, s.id_status, s.descricao as descricao_status, s.tipo "
					+ "from compra c "
					+ "inner join forma_pgto fp on c.id_forma_pgto = fp.id_forma_pgto "
					+ "inner join fornecedor f on c.id_fornecedor = f.id_fornecedor "
					+ "inner join status s on c.id_status_compra = s.id_status "
					+ "where f.ativo = 1 and c.ativo = 1";
			
			pstm = conexao.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			CompraVO compra = null;
			
			while(rs.next()){
				
				compra = new CompraVO();
				
				compra.setIdCompra(rs.getLong("id_compra"));
				compra.setCodCompra(rs.getString("id_compra"));
				compra.setData(rs.getDate("data_compra"));
				
				compra.setFormaPgto(new FormaPgtoVO());
				compra.getFormaPgto().setIdFormaPgtoVenda(rs.getLong("id_forma_pgto"));
				compra.getFormaPgto().setDescricao(rs.getString("descricao"));
				
				compra.setFornecedor(new FornecedorVO());
				compra.getFornecedor().setIdFornecedor(rs.getLong("id_fornecedor"));
				compra.getFornecedor().setNome(rs.getString("nome"));
				compra.getFornecedor().setContato(rs.getString("contato"));
				
				String origem = rs.getString("tipo_origem");
				
				if(origem.equals(TipoGeradorCompra.FUNCIONARIO_CANTINA.getTipo())){
					
					compra.setGeradorCompra(funcionarioBo.consultarPorId(rs.getLong("id_coringa_origem")));
					
				}
				
				compra.setStatus(new StatusVO());
				compra.getStatus().setIdStatus(rs.getLong("id_status"));

				String  tipoStatus = rs.getString("tipo");
				
				if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
					compra.getStatus().setTipoStatus(TipoStatus.ORDEM_COMPRA);
				}
				
				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
					compra.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
				}
				
				compra.getStatus().setDescricao(rs.getString("descricao_status"));
				
				compra.setItensCompra(consultarItensCompraPorCompra(compra));
				
				listaCompras.add(compra);
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return listaCompras;
	}

	@Override
	public CompraVO consultarPorId(Long id) {
		
		return null;
	}
	
	private List<ItemCompraVO> consultarItensCompraPorCompra(CompraVO compra){
		
		List<ItemCompraVO> listaItensCompra =  new ArrayList<ItemCompraVO>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"select id_item_compra, qtde, valor, id_coringa_produto, tipo, id_compra "
					+ "from item_compra where id_compra = ?");
			
			pstm.setLong(1, compra.getIdCompra());
			
			rs = pstm.executeQuery();
			
			ItemCompraVO itemCompra = null;
			
			while(rs.next()){
				
				itemCompra = new ItemCompraVO();
				itemCompra.setIdItemCompra(rs.getLong("id_item_compra"));
				itemCompra.setQtde(rs.getDouble("qtde"));
				itemCompra.setValor(rs.getDouble("valor"));
				
				String tipo = rs.getString("tipo");
				
				ProdutoVO produto = null;
				
				if(tipo.equals(TipoProduto.REVENDA.getTipoProduto())){
					produto = prodVendaBo.consultarProdutoPorId(rs.getLong("id_coringa_produto"));
				}
				else if(tipo.equals(TipoProduto.MATERIA_PRIMA.getTipoProduto())){
					produto = matPrimaBo.consultarProdutoPorId(rs.getLong("id_coringa_produto"));
				}

				itemCompra.setProduto(produto);
				
				itemCompra.getProduto().setIdProduto(rs.getLong("id_coringa_produto"));
				
				itemCompra.setCompra(compra);
				
				listaItensCompra.add(itemCompra);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstm.close();
				if(rs != null){
					rs.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaItensCompra;
	}
	
	public boolean deletarCompra(CompraVO compra) {
		
		String sql = "update compra set ativo = 0 where id_compra = ?";
		
		try {
			
			conexao = fabrica.getConexao();
			pstm = conexao.prepareStatement(sql);
			
			pstm.setLong(1, compra.getIdCompra());
			
			pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}finally{
			
			try {
				conexao.close();
				pstm.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
