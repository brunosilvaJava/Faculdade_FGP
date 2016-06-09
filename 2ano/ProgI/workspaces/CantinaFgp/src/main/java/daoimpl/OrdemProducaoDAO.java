package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.FuncionarioCantinaVO;
import vo.FuncionarioVO;
import vo.OrdemProducaoVO;
import vo.ProdutoVendaVO;
import vo.StatusVO;
import vo.UnidadeProdutoVO;
import bo.ProdutoMateriaPrimaBO;
import daoservice.IOrdemProducaoDAO;
import daoservice.IProdutoMateriaPrimaDAO;
import enumeradores.TipoStatus;

public class OrdemProducaoDAO implements IOrdemProducaoDAO{
	
	private Connection conexao;
	private ConnectionFactory fabrica;
	private	PreparedStatement pstm;
	private ResultSet rs;
	
	ProdutoMateriaPrimaBO prodMatPrimaBo;
	IProdutoMateriaPrimaDAO prodMatPrimaDao;
	
	{
		
		fabrica = ConnectionFactory.getInstance();
		prodMatPrimaBo = new ProdutoMateriaPrimaBO();
		prodMatPrimaDao = new ProdutoMateriaPrimaDAO();
		
	}
	
	public Long getUltimoIdGerado(){
		
		Long id = null;
		
		try {
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("select id_ordem_producao from ordem_producao where id_ordem_producao = (select max(id_ordem_producao) from ordem_producao)");
		
			rs = pstm.executeQuery();
			
			if(rs.next()){
				id = rs.getLong("id_ordem_producao");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public OrdemProducaoVO incluir(OrdemProducaoVO ordemProd) {
		
		OrdemProducaoVO ordemProdInserida = ordemProd;
				
		try {
						
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement(
					"insert into ordem_producao(qtde, data_ordem_producao, id_produto, id_funcionario_cantina, id_status) "
					+ "values (?,?,?,?,?);");
			
			pstm.setInt(1, ordemProd.getQtde());
			pstm.setDate(2, new java.sql.Date(ordemProd.getData().getTime()));
			pstm.setLong(3, ordemProd.getProdutoVenda().getIdProduto());
			pstm.setLong(4, ordemProd.getFuncionarioCantina().getIdFuncionarioCantina());
			pstm.setLong(5, ordemProd.getStatus().getIdStatus());
			
			pstm.executeUpdate();
			
			Long idGerado = getUltimoIdGerado();

			ordemProdInserida.setCodOrdemProducao(idGerado.toString());
			ordemProdInserida.setIdOrdemProducao(idGerado);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return ordemProdInserida;
	}

	@Override
	public boolean alterar(OrdemProducaoVO ordemProducao) {
		
		try{
			
			conexao = fabrica.getConexao();
			
			pstm = conexao.prepareStatement("update ordem_producao "
					+ "set qtde = ?, id_produto = ?, id_funcionario_cantina = ?, id_status = ? "
					+ "where id_ordem_producao = ?"
			);

			pstm.setInt(1, ordemProducao.getQtde());
			pstm.setLong(2, ordemProducao.getProdutoVenda().getIdProduto());
			pstm.setLong(3, ordemProducao.getFuncionarioCantina().getIdFuncionarioCantina());
			pstm.setLong(4, ordemProducao.getStatus().getIdStatus());
			pstm.setLong(5, ordemProducao.getIdOrdemProducao());
			
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

	@Override
	public boolean deletar(Long id) {
		
		return true;
		
	}

	@Override
	public List<OrdemProducaoVO> consultar() {
		
		List<OrdemProducaoVO> listaOrdensProducao = new ArrayList<OrdemProducaoVO>();
		
		try {
			conexao = fabrica.getConexao();
			
			String sql = "select op.qtde, op.data_ordem_producao, op.id_ordem_producao, p.id_produto_venda, p.cod_produto, p.descricao, p.preco_venda, "
					+ "u.id_unidade,  u.descricao as descricao_unidade, u.abreviatura, f.id_funcionario_cantina, pf.cod_funcionario, "
					+ "pe.nome, s.id_status, s.descricao as descricao_status, s.tipo "
					+ "from ordem_producao op "
					+ "inner join produto_venda p on op.id_produto = p.id_produto_venda "
					+ "inner join funcionario_cantina f on op.id_funcionario_cantina = f.id_funcionario_cantina "
					+ "inner join pessoa_funcionario pf on pf.id_pessoa_funcionario = f.id_pessoa_funcionario "
					+ "inner join pessoa pe on pe.id_pessoa = pf.id_pessoa_funcionario "
					+ "inner join status s on op.id_status = s.id_status "
					+ "inner join unidade u on u.id_unidade = p.id_unidade "
					+ "where p.fabricado = 1 and p.ativo = 1 and u.ativo = 1";
			
			pstm = conexao.prepareStatement(sql);
			
			OrdemProducaoVO ordemProducao = null;
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				ordemProducao = new OrdemProducaoVO();
				ordemProducao.setIdOrdemProducao(rs.getLong("id_ordem_producao"));
				ordemProducao.setCodOrdemProducao(rs.getString("id_ordem_producao"));
				ordemProducao.setData(rs.getDate("data_ordem_producao"));
				ordemProducao.setQtde(rs.getInt("qtde"));
				
				ordemProducao.setFuncionarioCantina(new FuncionarioCantinaVO());
				ordemProducao.getFuncionarioCantina().setIdFuncionarioCantina(rs.getLong("id_funcionario_cantina"));
				ordemProducao.getFuncionarioCantina().setFuncionario(new FuncionarioVO());
				ordemProducao.getFuncionarioCantina().getFuncionario().setCodPessoa(rs.getString("cod_funcionario"));
				ordemProducao.getFuncionarioCantina().getFuncionario().setNome(rs.getString("nome"));
				
				ordemProducao.setProdutoVenda(new ProdutoVendaVO());
				ordemProducao.getProdutoVenda().setIdProduto(rs.getLong("id_produto_venda"));
				ordemProducao.getProdutoVenda().setCodProduto(rs.getString("cod_produto"));
				ordemProducao.getProdutoVenda().setDescricao(rs.getString("descricao"));
				ordemProducao.getProdutoVenda().setPrecoVenda(rs.getDouble("preco_venda"));
				ordemProducao.getProdutoVenda().setReceita(prodMatPrimaBo.consultarReceitaPorIdProduto(ordemProducao.getProdutoVenda().getIdProduto()));
				
				ordemProducao.getProdutoVenda().setUnidade(new UnidadeProdutoVO());
				ordemProducao.getProdutoVenda().getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
				ordemProducao.getProdutoVenda().getUnidade().setAbreviatura(rs.getString("abreviatura"));
				ordemProducao.getProdutoVenda().getUnidade().setDescricao(rs.getString("descricao_unidade"));
				
				ordemProducao.setStatus(new StatusVO());
				ordemProducao.getStatus().setIdStatus(rs.getLong("id_status"));
				
				String  tipoStatus = rs.getString("tipo");
				
				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
				}
				
				ordemProducao.getStatus().setDescricao(rs.getString("descricao_status"));
				
				
				listaOrdensProducao.add(ordemProducao);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
		
			try {
				
				if(rs != null){
					rs.close();
				}
				pstm.close();
				conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		
		}
		
		return listaOrdensProducao;
		
	}

	@Override
	public OrdemProducaoVO consultarPorId(Long id) {
		
		return null;
	}
	
//	public List<OrdemProducaoVO> filtrarOrdemProducao(){
//		
//		List<OrdemProducaoVO> listaOrdensProducao = new ArrayList<OrdemProducaoVO>();
//			
//		try {
//			conexao = fabrica.getConexao();
//			
//			String sql = "select op.qtde, op.data_ordem_producao, op.id_ordem_producao, p.id_produto_venda, p.cod_produto, p.descricao, p.preco_venda, "
//					+ "p.dias_vencimento, u.id_unidade,  u.descricao, u.abreviatura, f.id_funcionario_cantina, pf.cod_funcionario, "
//					+ "pe.nome, s.id_status, s.descricao, s.tipo "
//					+ "from ordem_producao op "
//					+ "inner join produto_venda p on op.id_produto = p.id_produto_venda "
//					+ "inner join funcionario_cantina f on op.id_funcionario_cantina = f.id_funcionario_cantina "
//					+ "inner join pessoa_funcionario pf on pf.id_pessoa_funcionario = f.id_pessoa_funcionario "
//					+ "inner join pessoa pe on pe.id_pessoa = pf.id_pessoa_funcionario "
//					+ "inner join status s on op.id_status = s.id_status "
//					+ "inner join unidade u on u.id_unidade = p.id_unidade "
//					+ "where p.fabricado = 1 and p.ativo = 1 and u.ativo = 1";
//			
//			pstm = conexao.prepareStatement(sql);
//			
//			OrdemProducaoVO ordemProducao = null;
//			
//			rs = pstm.executeQuery();
//			
//			if(rs.next()){
//				
//				
//				ordemProducao = new OrdemProducaoVO();
//				ordemProducao.setCodOrdemProducao(rs.getString("id_ordem_producao"));
//				ordemProducao.setData(rs.getDate("data_ordem_producao"));
//				ordemProducao.setQtde(rs.getInt("qtde"));
//				ordemProducao.setFuncionarioCantina(new FuncionarioCantinaVO());
//				ordemProducao.getFuncionarioCantina().setIdFuncionarioCantina(rs.getInt("id_funcionario_cantina"));
//				ordemProducao.getFuncionarioCantina().setFuncionario(new FuncionarioVO());
//				ordemProducao.getFuncionarioCantina().getFuncionario().setCodPessoa(rs.getString("cod_funcionario"));
//				ordemProducao.getFuncionarioCantina().getFuncionario().setNome(rs.getString("nome"));
//				ordemProducao.setIdOrdemProducao(rs.getLong("id_ordem_producao"));
//				ordemProducao.setProdutoVenda(new ProdutoVendaVO());
//				ordemProducao.getProdutoVenda().setIdProduto(rs.getLong("id_produto_venda"));
//				ordemProducao.getProdutoVenda().setCodProduto(rs.getString("cod_produto"));
//				ordemProducao.getProdutoVenda().setDescricao(rs.getString("descricao"));
//				ordemProducao.getProdutoVenda().setPrecoVenda(rs.getDouble("preco_venda"));
//				ordemProducao.getProdutoVenda().setDiasVencimento(rs.getInt("dias_vencimento"));
//				ordemProducao.getProdutoVenda().setUnidade(new UnidadeProdutoVO());
//				ordemProducao.getProdutoVenda().getUnidade().setIdUnidadeProduto(rs.getLong("id_unidade"));
//				ordemProducao.getProdutoVenda().getUnidade().setAbreviatura(rs.getString("abreviatura"));
//				ordemProducao.getProdutoVenda().getUnidade().setDescricao(rs.getString("descricao"));
//				ordemProducao.setStatus(new StatusVO());
//				ordemProducao.getStatus().setIdStatus(rs.getLong("id_status"));
//				String  tipoStatus = rs.getString("tipo");
//				if(tipoStatus.equals(TipoStatus.GENERICO)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.GENERICO);
//				}
//				
//				if(tipoStatus.equals(TipoStatus.ORDEM_COMPRA)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_COMPRA);
//				}
//				
//				if(tipoStatus.equals(TipoStatus.ORDEM_PRODUCAO)){
//					ordemProducao.getStatus().setTipoStatus(TipoStatus.ORDEM_PRODUCAO);
//				}
//				
//				ordemProducao.getStatus().setDescricao(rs.getString("descricao"));
//				
//				listaOrdensProducao.add(ordemProducao);
//				
//			}
//			
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//			return null;
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			return null;
//		}finally{
//			
//			try {
//				
//				if(rs != null){
//					
//					rs.close();
//				}
//				 pstm.close();
//				conexao.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return null;
//			}
//		
//		}
//		
//		return listaOrdensProducao;
//				
//	}

}
