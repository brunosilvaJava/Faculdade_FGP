package ui;

import interfaces.ITelaBuscar;
import interfaces.ITelaManter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ui.templates.BuscarDialogView;
import ui.templates.ConsultarPanelView;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.MateriaPrimaVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import bo.MateriaPrimaBO;
import bo.ProdutoVendaBO;
import enumeradores.TipoProduto;
import enumeradores.TipoSolicitacao;

public class ConsultarProdutoView extends ConsultarPanelView<ProdutoVO> implements ITelaBuscar {

	// Atributos Tela

	private JLabel lblFiltrar;
	
	private JLabel lblCodProduto;
	private JLabel lblDescricao;
	private JLabel lblCodFornecedor;
	private JLabel lblFornecedor;
	private JLabel lblTipoProduto;
	
	private JTextField txtCodProduto;
	private JTextField txtDescricao;
	private JTextField txtCodFornecedor;
	private JTextField txtFornecedor;

	private JRadioButton rdoLote;
	private JComboBox<String> cbxTipoProduto;
	
	private JButton btnBuscarForn;
	
	// ITelaBusca

	private String acaoPesquisar;
	private static final String PESQ_FORNECEDOR = "fornecedor";
	
	// -----
	
	private FornecedorVO fornecedor;
	private ProdutoVendaBO prodVendaBo;
	private MateriaPrimaBO matPrimaBo;
	private List<TipoProduto> tiposProduto;

	// Bloco de Inicialização
	
	{
	
		lblFiltrar = new JLabel("FILTRAR");
		
		lblCodProduto = new JLabel("Código");
		lblDescricao = new JLabel("Produto");
		lblCodFornecedor = new JLabel("Código");
		lblFornecedor = new JLabel("Fornecedor");
		lblTipoProduto = new JLabel("Tipo");
		
		txtCodProduto = new JTextField();
		txtDescricao = new JTextField();
		txtCodFornecedor = new JTextField();
		txtFornecedor = new JTextField();

		prodVendaBo = new ProdutoVendaBO();
		matPrimaBo = new MateriaPrimaBO();
		
		
		rdoLote = new JRadioButton("Lote");
		btnBuscarForn = new JButton("Consultar");
		
		cbxTipoProduto = new JComboBox<String>();
		
		tiposProduto = prodVendaBo.consultarTiposProduto();

		for (TipoProduto tipoProduto : tiposProduto) {
			
			cbxTipoProduto.addItem(tipoProduto.getDescricao());
			
		}
		
				
	}
	
	
	// Construtores
	
	public ConsultarProdutoView() {
		
		super("Produto", new String[] {"Código", "Descricao", "Tipo", "Estoque"}, 10, 220, 665, 190);
		this.setSize(750, 480);
		definicoesPagina();
		
	}
	
	// Métodos
	
	private void definicoesPagina(){
				
		int espXLbl = 20;
		int espXTxt = 100;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblFiltrar.setBounds(espXLbl, espY, 50, altura);

		lblCodProduto.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblDescricao.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblCodFornecedor.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblFornecedor.setBounds(espXLbl, espY + espEntre * 4, 80, altura);

		txtCodProduto.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtDescricao.setBounds(espXTxt, espY + espEntre * 2, 150, altura);
		txtCodFornecedor.setBounds(espXTxt, espY + espEntre * 3, 50, altura);
		btnBuscarForn.setBounds(espXTxt + 60, espY + espEntre * 3, 90, altura);
		txtFornecedor.setBounds(espXTxt, espY + espEntre * 4, 150, altura);

		int espXLbl2 = 330;
		int espXTxt2 = 380;

		lblTipoProduto.setBounds(espXLbl2, espY + espEntre, 80, altura);
		cbxTipoProduto.setBounds(espXTxt2, espY + espEntre, 125, altura);		

		rdoLote.setBounds(espXLbl2, espY + espEntre * 4, 50, altura);
		
		rdoLote.setBackground(Color.LIGHT_GRAY);
		
		btnBuscarForn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				acaoPesquisar = PESQ_FORNECEDOR;
				
				new BuscarDialogView(ConsultarProdutoView.this, new String[]{"Código", "Fornecedor"}).abrirJanela();;				
				
			}
			
		});
				
		adicionarComponenteCentro(lblFiltrar);
		adicionarComponenteCentro(lblCodProduto);
		adicionarComponenteCentro(lblDescricao);
		adicionarComponenteCentro(lblCodFornecedor);
		adicionarComponenteCentro(lblFornecedor);
		adicionarComponenteCentro(lblTipoProduto);
		
		adicionarComponenteCentro(txtCodProduto);
		adicionarComponenteCentro(txtDescricao);
		adicionarComponenteCentro(txtCodFornecedor);
		adicionarComponenteCentro(txtFornecedor);
		adicionarComponenteCentro(btnBuscarForn);

		adicionarComponenteCentro(rdoLote);
		adicionarComponenteCentro(cbxTipoProduto);
		
		//------
		
		
		
	}
	
	// Métodos ConsultarPanelView

	@Override
	protected String[] definirGridItens(ProdutoVO produto) {
		
		String[] registro = new String[4];

		registro[0] = produto.getCodProduto();
		registro[1] = produto.getDescricao();
		registro[2] = produto.getTipo().getDescricao();
		registro[3] = produto.getEstoque().getQtdeAtual().toString();
		
		return registro;
	}

	@Override
	protected ITelaManter<ProdutoVO> getTelaIncluir() {
		return new ManterProdutoView(TipoSolicitacao.INCLUIR, "Cadastrar Produto");
	}	
	
	@Override
	protected ITelaManter<ProdutoVO> getTelaAlterar() {
		return new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto");
	}
	
	// Métodos ITelaConsultar

	@Override
	public void deletar(ProdutoVO produto) {

		if(produto instanceof ProdutoVendaVO){
			
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO)produto;
			prodVendaBo.deletar(produtoVenda);
		}
		
		
		JOptionPane.showMessageDialog(null, "Produto Excluido");
				
	}

	@Override
	public List<ProdutoVO> consultar() {
		
		List<ProdutoVO> produtos = null;
		
		// Retornando matérias primas
		
		if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.MATERIA_PRIMA.getDescricao())){

			produtos = new ArrayList<ProdutoVO>();			
			List<MateriaPrimaVO> materiasPrimas = matPrimaBo.consultarTodosProdutos();
			
			for (MateriaPrimaVO materiaPrima : materiasPrimas) {
				produtos.add(materiaPrima);
			}
			
		}
		else{

			produtos = new ArrayList<ProdutoVO>();
			
			List<ProdutoVendaVO> produtosVenda = prodVendaBo.consultarTodosProdutos();
			
			for (ProdutoVendaVO produtoVenda : produtosVenda) {
				
				// retornando produtos produzidos
				
				if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.PRODUCAO.getDescricao())){
					
					if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
						produtos.add(produtoVenda);
					}
					
				}
				else{
					
					// retornando produtos de revenda
					
					if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.REVENDA.getDescricao())){
						
						if(produtoVenda.getTipo().equals(TipoProduto.REVENDA)){
							produtos.add(produtoVenda);
						}
						
					}
				}
							
			}			
			
		}
		
		return produtos;
		
	}

	// Métodos ITelaBuscar
	
	@Override
	public List<GenericVO> buscarItemPorCodigoENome(Map<String, String> parametros) {
		
		List<GenericVO> listaGenericos = null;
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
				
				listaGenericos = new ArrayList<GenericVO>();
				
				List<FornecedorVO> listaFornecedores = prodVendaBo.consultarTodosFornecedores();
				
				for (FornecedorVO fornecedor : listaFornecedores) {
					
					listaGenericos.add(fornecedor);
					
				}
				
				return listaGenericos;

		}
	
		return listaGenericos;
		
	}

	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
			
			fornecedor = (FornecedorVO) item;
			
			txtCodFornecedor.setText(fornecedor.getCodFornecedor());
			txtFornecedor.setText(fornecedor.getNome());

		}
		
	}

	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
		
		String[] registro = null;
		
		switch (acaoPesquisar) {
						
			case PESQ_FORNECEDOR:
				
				FornecedorVO fornecedor = (FornecedorVO) item; 
				
				registro = new String[3];
	
				registro[0] = fornecedor.getCodFornecedor();
				registro[1] = fornecedor.getNome();
				
			return registro;

		}
		
		return registro;
		
	}


}
