package ui;

import interfaces.ITelaBuscar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.BuscarDialogView;
import ui.templates.ManterFrameView;
import vo.FornecedorVO;
import vo.GenericVO;
import vo.MateriaPrimaVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoMateriaPrimaVO;
import vo.ProdutoVO;
import vo.ProdutoVendaVO;
import vo.UnidadeProdutoVO;
import bo.MateriaPrimaBO;
import bo.ProdutoVendaBO;
import daoimpl.UnidadeProdutoDAO;
import daoservice.IUnidadeProdutoDAO;
import enumeradores.TipoProduto;
import enumeradores.TipoSolicitacao;
	
	public class ManterProdutoView extends ManterFrameView<ProdutoVO> implements ITelaBuscar{
		
		// Atributos Principais da Tela
		
		private JPanel pnlCampos;

		private JLabel lblCod;
		private JLabel lblNome;
		private JLabel lblLote;
		private JLabel lblTipoProduto;

		private JTextField txtCod;
		private JTextField txtNome;
		
		private JRadioButton rdoLote;
		private JRadioButton rdoMatPrima;
		private JRadioButton rdoProdProduzido;
		private JRadioButton rdoProdRevenda;
		
		private ButtonGroup btgTipoProdutoGroup;

		private static final Integer ABA_DADOS = 0;
		private static final Integer ABA_FORNECEDORES = 1;
		private static final Integer ABA_RECEITA = 2;
		private static final Integer ABA_LOTES = 3;
		private static final Integer ABA_MP_PROD = 4;
		
	
		// Atributos da Tabs
		
		private JTabbedPane tbsProdutos;
		
		// DADOS
		
		private JPanel pnlDados;
		
		private JLabel lblTitValores;
		private JLabel lblPrecoCusto;
		private JLabel lblPrecoVenda;
		
		private JTextField txtPrecoCusto;
		private JTextField txtPrecoVenda;
		
		private JLabel lblTitEstoque;
		private JLabel lblQtdeMin;
		private JLabel lblQtdeMax;
		private JLabel lblUnidade;
		
		private JTextField txtQtdeMin;
		private JTextField txtQtdeMax;
		private JComboBox<String> cbxUnidade;
		
		
		// RECEITA
		
		private JPanel pnlReceita;
		
		private JLabel lblCodMatPrimaRec;
		private JLabel lblMatPrimaRec;
		private JLabel lblQtdeMatPrima;
		private JLabel lblUnidMatPrima;
		
		private JTextField txtCodMatPrimaRec;
		private JTextField txtMatPrimaRec;
		private JTextField txtQtdeMatPrima;
		
		private JComboBox<String> cbxUnidMatPrima;
		
		private JButton btnBuscarMatPrima;
		private JButton btnAddMatPrima;
		
		private JTable tabMatPrimas;
		private DefaultTableModel modeloTabMatPrimas;
		private JScrollPane barraTabMatPrimas;
		
		
		// FORNECEDORES
		
		private JPanel pnlFornecedores;
		
		private JLabel lblCodFornecedor;
		private JLabel lblFornecedor;
		private JLabel lblContatoForn;
		
		private JTextField txtCodFornecedor;
		private JTextField txtFornecedor;
		private JTextField txtContatoForn;
				
		private JButton btnBuscarForn;
		private JButton btnAddForn;

		private JTable tabForn;
		private DefaultTableModel modeloTabForn;
		private JScrollPane barraTabForn;
		
		// LOTES
		
		private JPanel pnlLotes;
		
		private JLabel lblFiltrarLotes;
		private JLabel lblCodLote;
		private JLabel lblOrigemLote;
		private JLabel lblCodOrigem;
		private JLabel lblDatasLote;
		private JLabel lblDataVctoMin;
		private JLabel lblDataVctoMax;
		
		private JTextField txtCodLote;
		private JTextField txtCodOrigem;
		
		private JRadioButton rdoEstoqueLote;
		
		private JComboBox<String> cbxOrigemLote;

		private JXDatePicker dtpDataVctoMin;
		private JXDatePicker dtpDataVctoMax;
		
		private JButton btnFiltrarLotes;

		private JTable tabLotes;
		private DefaultTableModel modeloTabLotes;
		private JScrollPane barraTabLotes;

		// MATERIA PRIMA PRODUTOS
		
		private JPanel pnlProdMatPrima;
		
		private JTable tabProdMatPrima;
		private DefaultTableModel modeloTabProdMatPrima;
		private JScrollPane barraTabProdMatPrima;
		
		// ---
		
		// Atributos ITelaBusca
		
		private String acaoPesquisar;
		private static final String PESQ_MAT_PRIMA = "materiaPrima";
		private static final String PESQ_FORNECEDOR = "fornecedor";
		
		
		// Atributos 
		
		private ProdutoVendaBO prodVendaBo;
		private MateriaPrimaBO matPrimaBo;

		private MateriaPrimaVO materiaPrima;
		private ProdutoVO produto;
		private ProdutoVendaVO produtoVenda;
		private List<ProdutoMateriaPrimaVO> receita;
		
		private FornecedorVO fornecedor;
		private List<FornecedorVO> listaFornecedores;
		private List<UnidadeProdutoVO> unidadesProduto;
		
		private TipoSolicitacao solicitacao;
		
		private IUnidadeProdutoDAO unidadeDao;
		
		private Boolean editableTabMatPrima;
		
		// Bloco de Inicialização
		
		{
					
			// PRINCIPAL
			
			listaFornecedores = new ArrayList<FornecedorVO>();
	
			tbsProdutos = new JTabbedPane();
			pnlCampos = new JPanel();
			lblCod = new JLabel("Código");
			lblNome = new JLabel("Nome");
			lblLote = new JLabel("Lote");
			lblTipoProduto = new JLabel("TIPO");
			txtCod = new JTextField();
			txtNome = new JTextField();
			rdoLote = new JRadioButton();
			btgTipoProdutoGroup = new ButtonGroup();
			rdoMatPrima = new JRadioButton("Matéria Prima");
			rdoProdProduzido = new JRadioButton("Produto Produzido");
			rdoProdRevenda = new JRadioButton("Produto Revenda");
			rdoProdRevenda.setSelected(true);
			
			// TABS
			
			// DADOS
			
			pnlDados = new JPanel();
			pnlDados.setLayout(null);	

			lblTitValores = new JLabel();
			lblPrecoCusto = new JLabel();
			lblPrecoVenda = new JLabel();
			
			txtPrecoCusto = new JTextField();
			txtPrecoVenda = new JTextField();
			
			lblTitEstoque = new JLabel();
			lblQtdeMin = new JLabel();
			lblQtdeMax = new JLabel();
			lblUnidade = new JLabel();
						
			txtQtdeMax = new JTextField();
			txtQtdeMin = new JTextField();
			cbxUnidade = new JComboBox<String>();
			
			
			// FORNECEDORES
			
			pnlFornecedores = new JPanel();
			pnlFornecedores.setLayout(null);
			
			lblCodFornecedor = new JLabel("Código");
			lblFornecedor = new JLabel("Fornecedor");
			lblContatoForn = new JLabel("Contato");

			txtCodFornecedor = new JTextField();
			txtFornecedor = new JTextField();
			txtContatoForn = new JTextField();
						
			btnBuscarForn = new JButton("Buscar");
			btnAddForn = new JButton(" + ");
			
			tabForn = new JTable();
			modeloTabForn = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			barraTabForn = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			
			
			// RECEITA
			
			pnlReceita = new JPanel();
			
			lblCodMatPrimaRec = new JLabel("Código");
			lblMatPrimaRec = new JLabel("Matéria Prima");
			lblQtdeMatPrima = new JLabel("Quantidade");
			lblUnidMatPrima = new JLabel("Unidade");

			txtCodMatPrimaRec = new JTextField();
			txtMatPrimaRec = new JTextField();
			txtQtdeMatPrima = new JTextField();
			
			cbxUnidMatPrima = new JComboBox<String>();
			
			btnBuscarMatPrima = new JButton("Buscar");
			btnAddMatPrima = new JButton(" + ");
			
			tabMatPrimas = new JTable();
			modeloTabMatPrimas = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			barraTabMatPrimas = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			
			// MATERIA PRIMA PRODUTOS
			
			pnlProdMatPrima = new JPanel();
			pnlProdMatPrima.setLayout(null);
			
			tabProdMatPrima = new JTable();
			modeloTabProdMatPrima = new DefaultTableModel() {
				@Override public boolean isCellEditable(int row, int column) { return false; }
			};
			barraTabProdMatPrima = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			// ------------
			
			prodVendaBo = new ProdutoVendaBO();
			matPrimaBo = new MateriaPrimaBO();
			unidadesProduto = new ArrayList<UnidadeProdutoVO>();
			unidadeDao = new UnidadeProdutoDAO();
			
			unidadesProduto = unidadeDao.consultar();
			
			for (UnidadeProdutoVO unidade : unidadesProduto) {
				
				cbxUnidMatPrima.addItem(unidade.getDescricao());
				cbxUnidade.addItem(unidade.getDescricao());				
			}			
			
			// ---------------
			
			// PRINCIPAL
			
						txtCod.setEditable(false);
						
						int widthCampos = this.getWidth() - 25;
						int heightCampos = this.getHeight() - 122;
						
						pnlCampos.setBackground(Color.LIGHT_GRAY);
						pnlCampos.setBounds(10, 10, widthCampos, heightCampos);
						pnlCampos.setLayout(null);
						
						int yTbsProd = 150;
						tbsProdutos.setBounds(10, yTbsProd, widthCampos-20, heightCampos-yTbsProd-10);
						
						int espXLbl = 20;
						int espXTxt = espXLbl + 70;
						int espXLbl2 = 450;
						int espY = 20;
						int espEntre = 35;
						int altura = 30;			

						lblCod.setBounds(espXLbl, espY, 80, altura);
						lblNome.setBounds(espXLbl, espY + espEntre, 100, altura);
						lblLote.setBounds(espXLbl, espY + espEntre * 2, 100, altura);
						
						txtCod.setBounds(espXTxt, espY, 50, altura);
						txtNome.setBounds(espXTxt, espY + espEntre, 300, altura);
						rdoLote.setBounds(espXTxt - 30, espY + espEntre * 2, 80, altura);
						
						lblTipoProduto.setBounds(espXLbl2, espY, 200, altura);
									
						rdoProdRevenda.setBounds(espXLbl2, espY + espEntre, 125, altura);
						rdoProdProduzido.setBounds(espXLbl2, espY + espEntre * 2, 130, altura);
						rdoMatPrima.setBounds(espXLbl2, espY + espEntre * 3, 105, altura);
						
						rdoLote.setBackground(pnlCampos.getBackground());
						
						rdoMatPrima.setBackground(pnlCampos.getBackground());
						
						btgTipoProdutoGroup.add(rdoMatPrima);
						btgTipoProdutoGroup.add(rdoProdProduzido);
						btgTipoProdutoGroup.add(rdoProdRevenda);
						
						pnlCampos.add(lblCod);
						pnlCampos.add(txtCod);
						pnlCampos.add(lblNome);
						pnlCampos.add(txtNome);
						pnlCampos.add(lblLote);
						pnlCampos.add(rdoLote);
						pnlCampos.add(lblTipoProduto);
						pnlCampos.add(rdoMatPrima);
						pnlCampos.add(rdoProdProduzido);
						pnlCampos.add(rdoProdRevenda);
						pnlCampos.add(tbsProdutos);
						
						
						// TABS
						
						// DADOS
						
						lblTitValores.setText("VALORES");
						lblPrecoCusto.setText("Preço de custo");
						lblPrecoVenda.setText("Preço de venda");
						lblUnidade.setText("Unidade");
						lblTitEstoque.setText("ESTOQUE");
						lblQtdeMin.setText("Qtde Minima");
						lblQtdeMax.setText("Qtde Maxima");
						
						int espXLblDad = 20;
						int espXTxtDad = 120;

						lblTitValores.setBounds(espXLblDad, espY, 100, altura);
						lblPrecoCusto.setBounds(espXLblDad, espY + espEntre, 100, altura);
						lblPrecoVenda.setBounds(espXLblDad, espY + espEntre * 2, 100, altura);

						txtPrecoCusto.setBounds(espXTxtDad, espY + espEntre, 80, altura);
						txtPrecoVenda.setBounds(espXTxtDad, espY + espEntre * 2, 80, altura);
						
						int espXLblDad2 = 350;
						int espXTxtDad2 = 440;

						lblTitEstoque.setBounds(espXLblDad2, espY, 80, altura);
						lblQtdeMin.setBounds(espXLblDad2, espY + espEntre, 80, altura);
						lblQtdeMax.setBounds(espXLblDad2, espY + espEntre * 2, 80, altura);
						lblUnidade.setBounds(espXLblDad2,  espY + espEntre * 3, 80, altura);
						
						txtQtdeMin.setBounds(espXTxtDad2, espY + espEntre, 100, altura);
						txtQtdeMax.setBounds(espXTxtDad2,  espY + espEntre * 2, 100, altura);
						cbxUnidade.setBounds(espXTxtDad2, espY + espEntre * 3, 100, altura);
									
						pnlDados.add(lblTitValores);
						pnlDados.add(lblPrecoCusto);
						pnlDados.add(txtPrecoCusto);
						pnlDados.add(lblPrecoVenda);
						pnlDados.add(txtPrecoVenda);
						pnlDados.add(lblUnidade);
						pnlDados.add(cbxUnidade);
						pnlDados.add(lblTitEstoque);
						pnlDados.add(lblQtdeMin);
						pnlDados.add(lblQtdeMax);
						pnlDados.add(txtQtdeMin);
						pnlDados.add(txtQtdeMax);

						tbsProdutos.addTab("Dados", pnlDados);
					
						
						// FORNECEDORES
						
						btnAddForn.setEnabled(false);
									
						int espXLblForn = 20;
						int espXTxtForn = 120;

						lblCodFornecedor.setBounds(espXLblForn, espY, 50, altura);
						lblFornecedor.setBounds(espXLblForn, espY + espEntre, 80, altura);
						lblContatoForn.setBounds(espXLblForn, espY + espEntre * 2, 80, altura);

						txtCodFornecedor.setBounds(espXTxtForn, espY, 50, altura);
						btnBuscarForn.setBounds(espXTxtForn + 60, espY, 80, altura);
						txtFornecedor.setBounds(espXTxtForn, espY + espEntre, 210, altura);
						txtContatoForn.setBounds(espXTxtForn, espY + espEntre * 2, 150, altura);
						btnAddForn.setBounds(espXTxtForn + 160, espY + espEntre * 2, 50, altura);
						
						
						tabForn.setModel(modeloTabForn);
						modeloTabForn.setColumnIdentifiers(new String[] {"Código", "Fornecedor", "Contato"});
						barraTabForn.setViewportView(tabForn);
						int yTabForn = 130;
						barraTabForn.setBounds(0, yTabForn, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabForn);
												
						pnlFornecedores.add(barraTabForn);
						pnlFornecedores.add(lblCodFornecedor);
						pnlFornecedores.add(lblFornecedor);
						pnlFornecedores.add(lblContatoForn);
						pnlFornecedores.add(txtCodFornecedor);
						pnlFornecedores.add(txtFornecedor);
						pnlFornecedores.add(txtContatoForn);
						pnlFornecedores.add(btnBuscarForn);
						pnlFornecedores.add(btnAddForn);

						tbsProdutos.addTab("Fornecedores", pnlFornecedores);
						
						
						// RECEITA

						pnlReceita.setLayout(null);
						
						btnAddMatPrima.setEnabled(false);
									
						int espXLblRec = 20;
						int espXTxtRec = 120;

						lblCodMatPrimaRec.setBounds(espXLblRec, espY, 50, altura);
						lblMatPrimaRec.setBounds(espXLblRec, espY + espEntre, 80, altura);
						lblQtdeMatPrima.setBounds(espXLblRec, espY + espEntre * 2, 80, altura);

						txtCodMatPrimaRec.setBounds(espXTxtRec, espY, 50, altura);
						btnBuscarMatPrima.setBounds(espXTxtRec + 60, espY, 80, altura);
						txtMatPrimaRec.setBounds(espXTxtRec, espY + espEntre, 170, altura);
						txtQtdeMatPrima.setBounds(espXTxtRec, espY + espEntre * 2, 50, altura);
						btnAddMatPrima.setBounds(espXTxtRec + 60, espY + espEntre * 2, 50, altura);
						
						int espXLblRec2 = 350;
						int espXTxtRec2 = 420;

						lblUnidMatPrima.setBounds(espXLblRec2, espY, 50, altura);
						
						cbxUnidMatPrima.setBounds(espXTxtRec2, espY, 120, altura);
						
						
						// Tabela
						
						modeloTabMatPrimas.setColumnIdentifiers(new String[] {"Código", "Matéria-Prima", "Quantidade", "Unidade"});
						tabMatPrimas.setModel(modeloTabMatPrimas);
						barraTabMatPrimas.setViewportView(tabMatPrimas);
						int yTabMatPrima = 130;
						barraTabMatPrimas.setBounds(0, yTabMatPrima, tbsProdutos.getWidth(), tbsProdutos.getHeight() - yTabMatPrima);
						
						pnlReceita.add(barraTabMatPrimas);
						pnlReceita.add(lblCodMatPrimaRec);
						pnlReceita.add(lblMatPrimaRec);
						pnlReceita.add(lblQtdeMatPrima);
						pnlReceita.add(lblUnidMatPrima);
						pnlReceita.add(txtCodMatPrimaRec);
						pnlReceita.add(txtMatPrimaRec);
						pnlReceita.add(txtQtdeMatPrima);
						pnlReceita.add(cbxUnidMatPrima);
						pnlReceita.add(btnBuscarMatPrima);
						pnlReceita.add(btnAddMatPrima);
				
						tbsProdutos.addTab("Receita", pnlReceita);
						
				
						// LOTES
				
						pnlLotes = new JPanel();
						pnlLotes.setLayout(null);
				
						lblFiltrarLotes = new JLabel("FILTRAR");
						lblCodLote = new JLabel("Lote");
						lblOrigemLote = new JLabel("Origem");
						lblCodOrigem = new JLabel("Número");
						lblDatasLote = new JLabel("Vencimentos");
						lblDataVctoMin = new JLabel("Inicial");
						lblDataVctoMax = new JLabel("Final");
				
						txtCodLote = new JTextField();
						txtCodOrigem = new JTextField();
				
						rdoEstoqueLote = new JRadioButton("Com estoque");
						rdoEstoqueLote.setSelected(true);
				
						cbxOrigemLote = new JComboBox<String>();
				
						dtpDataVctoMin = new JXDatePicker();
						dtpDataVctoMax = new JXDatePicker();
				
						btnFiltrarLotes = new JButton("Filtrar");
				
						int espYLot = 10;
						int espXLblLot = 20;
						int espXTxtLot = 80;
				
						lblFiltrarLotes.setBounds(espXLblLot, espYLot, 50, altura);
						lblCodLote.setBounds(espXLblLot, espYLot + espEntre, 50, altura);
						lblOrigemLote.setBounds(espXLblLot, espYLot + espEntre * 2, 50, altura);
				
						txtCodLote.setBounds(espXTxtLot, espYLot + espEntre, 50, altura);
						cbxOrigemLote
								.setBounds(espXTxtLot, espYLot + espEntre * 2, 120, altura);
				
						int espXLblLot2 = 210;
						int espXTxtLot2 = 260;
				
						lblCodOrigem.setBounds(espXLblLot2, espYLot + espEntre * 2, 50, altura);
						txtCodOrigem.setBounds(espXTxtLot2, espYLot + espEntre * 2, 50, altura);
				
						int espXLblLot3 = 340;
						int espXTxtLot3 = 380;
				
						lblDatasLote.setBounds(espXLblLot3, espYLot, 80, altura);
						lblDataVctoMin.setBounds(espXLblLot3, espYLot + espEntre, 80, altura);
						lblDataVctoMax.setBounds(espXLblLot3, espYLot + espEntre * 2, 80,
								altura);
				
						dtpDataVctoMin.setBounds(espXTxtLot3, espYLot + espEntre, 130, altura);
						dtpDataVctoMax.setBounds(espXTxtLot3, espYLot + espEntre * 2, 130,
								altura);
				
						int espXTxtLot4 = 530;
				
						rdoEstoqueLote.setBounds(espXTxtLot4, espYLot + espEntre, 100, altura);
						btnFiltrarLotes.setBounds(espXTxtLot4, espYLot + espEntre * 2, 80,
								altura);
				
						// Tabela lote
						tabLotes = new JTable();
						modeloTabLotes = new DefaultTableModel() {
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						barraTabLotes = new JScrollPane(
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
						modeloTabLotes.setColumnIdentifiers(new String[] { "Lote", "Qtde",
								"Vencimento", "Origem", "Número" });
						tabLotes.setModel(modeloTabLotes);
						barraTabLotes.setViewportView(tabLotes);
						int yTabLotes = 130;
						barraTabLotes.setBounds(0, yTabLotes, tbsProdutos.getWidth(),
								tbsProdutos.getHeight() - yTabLotes);
				
						pnlLotes.add(barraTabLotes);
						pnlLotes.add(lblFiltrarLotes);
						pnlLotes.add(lblCodLote);
						pnlLotes.add(rdoEstoqueLote);
						pnlLotes.add(lblOrigemLote);
						pnlLotes.add(lblCodOrigem);
						pnlLotes.add(lblDatasLote);
						pnlLotes.add(lblDataVctoMin);
						pnlLotes.add(lblDataVctoMax);
						pnlLotes.add(txtCodLote);
						pnlLotes.add(txtCodOrigem);
						pnlLotes.add(cbxOrigemLote);
						pnlLotes.add(dtpDataVctoMin);
						pnlLotes.add(dtpDataVctoMax);
						pnlLotes.add(btnFiltrarLotes);
				
						tbsProdutos.addTab("Lotes", pnlLotes);
						
									
						// PRODUTO MATÉRIA PRIMA
						
						// Tabela
						
						modeloTabProdMatPrima.setColumnIdentifiers(new String[] {"Código", "Produto", "Quantidade", "Unidade"});
						tabProdMatPrima.setModel(modeloTabProdMatPrima);
						barraTabProdMatPrima.setViewportView(tabProdMatPrima);
						barraTabProdMatPrima.setBounds(0, 0, tbsProdutos.getWidth(), tbsProdutos.getHeight());
						
						pnlProdMatPrima.add(barraTabProdMatPrima);
						
						tbsProdutos.addTab("Produtos Fabricados", pnlProdMatPrima);
						
						tbsProdutos.setEnabledAt(ABA_DADOS, true);
						tbsProdutos.setEnabledAt(ABA_FORNECEDORES, true);
						tbsProdutos.setEnabledAt(ABA_RECEITA, false);
						tbsProdutos.setEnabledAt(ABA_LOTES, false);
						tbsProdutos.setEnabledAt(ABA_MP_PROD, false);
						
						produto = new ProdutoVendaVO();
			
		}
		
		// Construtores
		
		public ManterProdutoView(TipoSolicitacao solicitacao, String tituloCabecalho) {

			super(solicitacao, tituloCabecalho);
			
			this.solicitacao = solicitacao;
						
		}
		
		// Métodos
		
		@Override
		public void abrirJanela(){
					
			editableTabMatPrima = true;			
			
			definicoesPagina();
			
		}
		

		@Override
		public void abrirJanela(ProdutoVO produto) {
			
			this.produto = produto;
			
			rdoProdProduzido.setEnabled(false);
			rdoMatPrima.setEnabled(false);
			rdoProdRevenda.setEnabled(false);
			
			if(produto.getTipo().equals(TipoProduto.PRODUCAO)){
				tbsProdutos.setEnabledAt(ABA_RECEITA, true);
				tbsProdutos.setEnabledAt(ABA_FORNECEDORES, false);
				rdoProdProduzido.setSelected(true);
				rdoProdProduzido.setEnabled(true);
			}
			else if(produto.getTipo().equals(TipoProduto.MATERIA_PRIMA)){
				tbsProdutos.setEnabledAt(ABA_MP_PROD, true);
				lblPrecoVenda.setVisible(false);
				txtPrecoVenda.setVisible(false);
				
				rdoMatPrima.setSelected(true);
				rdoMatPrima.setEnabled(true);
				
			}
			else if(produto.getTipo().equals(TipoProduto.REVENDA)){
				
				rdoProdRevenda.setSelected(true);
				rdoProdRevenda.setEnabled(true);
				
			}
			
			txtCod.setText(produto.getCodProduto());
			txtNome.setText(produto.getDescricao());
			txtPrecoCusto.setText(produto.getPrecoCusto().toString());
			
			txtQtdeMax.setText(produto.getEstoque().getQtdeMaxima().toString());
			txtQtdeMin.setText(produto.getEstoque().getQtdeMinima().toString());
			System.out.println("aa: "+produto.getUnidade().getDescricao());
			cbxUnidade.setSelectedItem(produto.getUnidade().getDescricao());
			
			if(produto instanceof ProdutoVendaVO){
				
				produtoVenda = (ProdutoVendaVO) produto;
				txtPrecoVenda.setText(produtoVenda.getPrecoVenda().toString());
				receita = produtoVenda.getReceita();
				carregarGridMatPrima(receita);
				
			}
			
			desabilitarCampos();
			definicoesPagina();
			
		}
		
		private ActionListener getAcaoRdoLote(){
			return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(rdoLote.isSelected()){
						tbsProdutos.setEnabledAt(ABA_LOTES, true);
						tbsProdutos.setSelectedIndex(ABA_LOTES);
					}
					else{
						tbsProdutos.setEnabledAt(ABA_LOTES, false);
						tbsProdutos.setSelectedIndex(ABA_DADOS);
					}
					
				}
				
			};
		}
		
		private boolean deselecionarRdoProdProduzido(){
			
			if(receita.size() > 0){
				
				int x = JOptionPane.showConfirmDialog(null, 
						"Deseja realmente alterar o tipo? Se confirmar a alteração, as matérias-primas adicionadas serão perdidas",
						"Confirmação",
						JOptionPane.YES_OPTION);
				
				if(x == JOptionPane.NO_OPTION){
					rdoProdProduzido.setSelected(true);
					return false;
				}

				if(x == JOptionPane.YES_OPTION){
					receita = null;
					carregarGridMatPrima(receita);
				}
			
			}
			
			return true;
			
		}
		
		private void definicoesPagina(){
			
			rdoMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(deselecionarRdoProdProduzido()){
						if(rdoMatPrima.isSelected()){
							
							if(produto == null || !(produto instanceof MateriaPrimaVO)){
								produto = new MateriaPrimaVO();
							}
							
							tbsProdutos.setEnabledAt(ABA_FORNECEDORES, true);
							tbsProdutos.setEnabledAt(ABA_RECEITA, false);
							tbsProdutos.setEnabledAt(ABA_MP_PROD, true);
							
							if(tbsProdutos.getSelectedComponent().equals(pnlReceita)){
								tbsProdutos.setSelectedComponent(pnlDados);
							}
														
						}
					}
					
				}
			});
			
			rdoProdProduzido.setBackground(pnlCampos.getBackground());
			rdoProdProduzido.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(rdoProdProduzido.isSelected()){
						
						receita = new ArrayList<ProdutoMateriaPrimaVO>();
						
						if(produto == null || !(produto instanceof ProdutoVendaVO)){
							produto = new ProdutoVendaVO();
						}
						
						tbsProdutos.setEnabledAt(ABA_FORNECEDORES, false);
						tbsProdutos.setEnabledAt(ABA_RECEITA, true);
						tbsProdutos.setEnabledAt(ABA_MP_PROD, false);
						
						if(tbsProdutos.getSelectedComponent().equals(pnlFornecedores) || tbsProdutos.getSelectedComponent().equals(pnlProdMatPrima)){
							tbsProdutos.setSelectedComponent(pnlDados);
						}
						
					}
					
				}
			});
			
			rdoProdRevenda.setBackground(pnlCampos.getBackground());
			rdoProdRevenda.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(deselecionarRdoProdProduzido()){
						if(rdoProdRevenda.isSelected()){
							
							if(produto == null || !(produto instanceof ProdutoVendaVO)){
								produto = new ProdutoVendaVO();
							}
							
							tbsProdutos.setEnabledAt(ABA_FORNECEDORES, true);
							tbsProdutos.setEnabledAt(ABA_RECEITA, false);
							tbsProdutos.setEnabledAt(ABA_MP_PROD, false);
							
							if(tbsProdutos.getSelectedComponent().equals(pnlReceita) || tbsProdutos.getSelectedComponent().equals(pnlProdMatPrima)){
								tbsProdutos.setSelectedComponent(pnlDados);
							}
							
						}
					}
										
				}
			});
			
				btnBuscarForn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					acaoPesquisar = PESQ_FORNECEDOR;

					new BuscarDialogView(ManterProdutoView.this, new String[]{"Código", "Fornecedor", "Contato"}).abrirJanela();
					
				}
				
			});
			
			btnAddForn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(fornecedor!=null){
						
						if(adicionarFornecedor(fornecedor)){
						
							txtCodFornecedor.setText("");
							txtFornecedor.setText("");
							txtContatoForn.setText("");
							
							btnAddForn.setEnabled(false);
						
						}
						
					}
					
				}
				
			});
					
			
			tabForn.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(tabForn.getSelectedRow() != -1 && e.getClickCount() == 2){
						
						int x = JOptionPane.showConfirmDialog(null, 
															"Deseja realmente excluir o fornecedor?", 
															"Confirmação", 
															JOptionPane.YES_OPTION);
						
						if(x == JOptionPane.YES_NO_OPTION){
							
							listaFornecedores.remove(tabForn.getSelectedRow());
							carregarGridFornecedor(listaFornecedores);
							
						}
						
					}
					
				}
			});

			btnBuscarMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					acaoPesquisar = PESQ_MAT_PRIMA;

					new BuscarDialogView(ManterProdutoView.this, new String[]{"Código", "Matéria Prima", "Unidade"}).abrirJanela();
					
				}
				
			});
			
			btnAddMatPrima.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(materiaPrima!=null){
						
						if(adicionarMatPrima(materiaPrima)){
						
							txtCodMatPrimaRec.setText("");
							txtMatPrimaRec.setText("");
							txtQtdeMatPrima.setText("");
							cbxUnidMatPrima.setSelectedIndex(0);
							
							btnAddMatPrima.setEnabled(false);
						
						}
					}
					
				}
				
			});
			
			tabMatPrimas.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(editableTabMatPrima){
						if(tabMatPrimas.getSelectedRow() != -1 && e.getClickCount() == 2){
							
							int x = JOptionPane.showConfirmDialog(null, 
																"Deseja realmente excluir a matéria-prima?", 
																"Confirmação", 
																JOptionPane.YES_OPTION);
							
							if(x == JOptionPane.YES_NO_OPTION){
								
								receita.remove(tabMatPrimas.getSelectedRow());
								carregarGridMatPrima(receita);
								
							}
							
						}
					}
					
				}
			});
			
			tabProdMatPrima.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if(tabProdMatPrima.getSelectedRow() != -1 && e.getClickCount() == 2){
						
						new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto").abrirJanela();
						
					}
					
				}
			});
			
			if(solicitacao.equals(TipoSolicitacao.DETALHAR)){
				
				rdoLote.addActionListener(getAcaoRdoLote());
				
			}
			
			if(rdoLote.isSelected()){
				tbsProdutos.setEnabledAt(ABA_LOTES, true);
			}
						
			adicionarComponentesCentro(pnlCampos);
			
			this.setVisible(true);
			
		}
		
		
		public void carregarGridFornecedor(List<FornecedorVO> listaFornecedores) {
					
			modeloTabForn.setNumRows(0);
						
			if (listaFornecedores != null) {
				
				String[] registro = new String[4];
				
				for (FornecedorVO fornecedor : listaFornecedores) {
					
					registro[0] = fornecedor.getCodFornecedor();
					registro[1] = fornecedor.getNome();
					registro[2] = fornecedor.getContato();
					
					modeloTabForn.addRow(registro);
					
				}
	
			}
	
		}

		@Override
		public boolean incluir(StringBuilder msgErro) {
			
			ProdutoVO prodIncluido = null;
			
			if(rdoMatPrima.isSelected()){
				
				if(matPrimaBo.incluir(materiaPrima) != null){
					JOptionPane.showMessageDialog(null, "Matéria Prima Incluída");
				}
				
			}
			else{
				
				prodIncluido = prodVendaBo.incluir((ProdutoVendaVO) carregarProduto());
				
				if(prodIncluido != null){
					produto.setIdProduto(prodIncluido.getIdProduto());
					txtCod.setText(prodIncluido.getCodProduto());
					rdoLote.addActionListener(getAcaoRdoLote());
					JOptionPane.showMessageDialog(null, "Produto Incluído");
				}
								
			}
			
			return prodIncluido != null;
			
		}
		
		private ProdutoVO carregarProduto(){

			TipoProduto tipo;
			Boolean lote = false;
			
			if(rdoLote.isSelected()){
				lote = true;
			}
			
			if(rdoProdProduzido.isSelected()){				
				tipo = TipoProduto.PRODUCAO;
			}
			else{
				if(rdoMatPrima.isSelected()){
					tipo = TipoProduto.MATERIA_PRIMA;
				}
				else{
					tipo = TipoProduto.REVENDA;
				}
			}
			
			produto.setCodProduto(txtCod.getText());
			produto.setDescricao(txtNome.getText());
			produto.setTipo(tipo);
			produto.setPrecoCusto(Double.parseDouble(txtPrecoCusto.getText()));
			UnidadeProdutoVO unidadeSelecionada = unidadesProduto.get(cbxUnidade.getSelectedIndex());
			produto.setUnidade(unidadeSelecionada);
			ProdutoCantinaVO prodCantina = new ProdutoCantinaVO();
			prodCantina.setQtdeMinima(Double.parseDouble(txtQtdeMin.getText()));
			prodCantina.setQtdeMaxima(Double.parseDouble(txtQtdeMax.getText()));
			produto.setEstoque(prodCantina);
			produto.setLote(lote);
			
			if(produto instanceof ProdutoVendaVO){
				((ProdutoVendaVO) produto).setPrecoVenda(Double.parseDouble(txtPrecoVenda.getText()));
				((ProdutoVendaVO) produto).setReceita(receita);
			}
			
			return produto;
			
		}

		@Override
		public boolean alterar(StringBuilder msgErro) {
			
			if(produto instanceof ProdutoVendaVO){
				if(prodVendaBo.alterar((ProdutoVendaVO) carregarProduto())){
					
					JOptionPane.showMessageDialog(null, "Produto Alterado");	
					
					return true;					
				}
			} 
			else if(produto instanceof MateriaPrimaVO){
				if(matPrimaBo.alterar((MateriaPrimaVO) carregarProduto())){
					
					JOptionPane.showMessageDialog(null, "Matéria-prima Alterada");	
					
					return true;					
				}
			}
			
			return false;
		}	

		@Override
		public boolean isCamposValidos(StringBuilder msgErro) {
			return true;
		}

		@Override
		protected boolean habilitarCampos() {
			
			editableTabMatPrima = true;

			txtNome.setEditable(true);
			rdoLote.setEnabled(true);

			
			txtPrecoCusto.setEditable(true);
			txtPrecoVenda.setEditable(true);
			txtQtdeMax.setEditable(true);
			txtQtdeMin.setEditable(true);
			cbxUnidade.setEnabled(true);
			
			txtCodFornecedor.setEditable(true);
			txtFornecedor.setEditable(true);
			txtContatoForn.setEditable(true);
			btnBuscarForn.setEnabled(true);
			
			txtCodMatPrimaRec.setEditable(true);
			txtMatPrimaRec.setEditable(true);
			txtQtdeMatPrima.setEditable(true);
			cbxUnidMatPrima.setEnabled(true);
			btnBuscarMatPrima.setEnabled(true);
			
			return true;
		
		}

		@Override
		protected boolean desabilitarCampos(){
			
			editableTabMatPrima = false;
			
			txtNome.setEditable(false);
			rdoLote.setEnabled(false);
			
			if(rdoMatPrima.isSelected()){
				rdoProdProduzido.setEnabled(false);
				rdoProdRevenda.setEnabled(false);
			}
			else{
				if(rdoProdProduzido.isSelected()){
					rdoMatPrima.setEnabled(false);
					rdoProdRevenda.setEnabled(false);
				}
				else{
					rdoMatPrima.setEnabled(false);
					rdoProdProduzido.setEnabled(false);
				}
			}
			
			txtPrecoCusto.setEditable(false);
			txtPrecoVenda.setEditable(false);
			txtQtdeMax.setEditable(false);
			txtQtdeMin.setEditable(false);
			cbxUnidade.setEnabled(false);
			
			txtCodFornecedor.setEditable(false);
			txtFornecedor.setEditable(false);
			txtContatoForn.setEditable(false);
			btnBuscarForn.setEnabled(false);
			
			txtCodMatPrimaRec.setEditable(false);
			txtMatPrimaRec.setEditable(false);
			txtQtdeMatPrima.setEditable(false);
			cbxUnidMatPrima.setEnabled(false);
			btnBuscarMatPrima.setEnabled(false);	
			
			return true;
			
		}
		
		@Override
		protected void limparCampos() {
			
		}
		
		private Boolean adicionarMatPrima(MateriaPrimaVO materiaPrimaInserida){
			
			String qtdeTxt = txtQtdeMatPrima.getText();
			
			if(prodVendaBo.isCampoVazio(qtdeTxt)){
				
				JOptionPane.showMessageDialog(null, "Favor informar uma quantidade", "Campo Vazio", JOptionPane.YES_OPTION);
				
			}
			else{
				
				Double qtdeInserida = Double.parseDouble(qtdeTxt);
				
				if(qtdeInserida <= 0){
					
					JOptionPane.showMessageDialog(null, "Favor informar uma quantidade maior que zero", "Quantidade Inválida", JOptionPane.YES_OPTION);
					
				}
				else{
					
					UnidadeProdutoVO unidadeSelecionada = unidadesProduto.get(cbxUnidMatPrima.getSelectedIndex());
					Double qtdeReceita = 0d;
					int sizeReceita = receita.size();
					boolean itemNaReceita = false;
					
					for (int l = 0; l < sizeReceita; l++) {
						
						ProdutoMateriaPrimaVO materiaPrimaReceita = receita.get(l);
			
						if (materiaPrimaInserida.getCodProduto().equals(materiaPrimaReceita.getMateriaPrima().getCodProduto())) {
							
							if(!materiaPrimaReceita.getUnidade().getDescricao().equals(unidadeSelecionada.getDescricao())){
								
								int opc = JOptionPane.showConfirmDialog(null, "Deseja substituir a unidade da matéria-prima?");
								
								switch (opc) {
								case JOptionPane.YES_OPTION:
									
									materiaPrimaReceita.setUnidade(unidadeSelecionada);
									
									break;
									
								case JOptionPane.CANCEL_OPTION:
																		
									return false;

								default:
									break;
								}
								
							}
							
							qtdeReceita = receita.get(l).getQtde();
			
							materiaPrimaReceita.setQtde(qtdeReceita + qtdeInserida);
							
							itemNaReceita = true;
			
						}
			
					}
					
					if(!itemNaReceita){
						
						ProdutoMateriaPrimaVO prodMatPrima = new ProdutoMateriaPrimaVO();
						prodMatPrima.setProdutoFabricado((ProdutoVendaVO) produto);
						prodMatPrima.setMateriaPrima(materiaPrimaInserida);
						prodMatPrima.setQtde(qtdeInserida);
						prodMatPrima.setUnidade(unidadeSelecionada);
						
						receita.add(prodMatPrima);
						
					}
					
					carregarGridMatPrima(receita);
					
					return true;
				
				}
			
			}
			
			return false;
			
		}
		
		private boolean adicionarFornecedor(FornecedorVO fornecedor) {
	
			int qtdeFornecedores = listaFornecedores.size();
				
			for(int i = 0; i < qtdeFornecedores; i++){
				if (fornecedor.getCodFornecedor() == listaFornecedores.get(i).getCodFornecedor()) {
					
					JOptionPane.showMessageDialog(null, "Fornecedor já vinculado", "Fornecedor Inválido", JOptionPane.YES_OPTION);
					return true;
						
				}
			}

			listaFornecedores.add(fornecedor);
		
			carregarGridFornecedor(listaFornecedores);
	
			return true;
			
		}
		
		
		public void carregarGridMatPrima(List<ProdutoMateriaPrimaVO> receita) {
					
			modeloTabMatPrimas.setNumRows(0);
						
			if (receita != null) {
				
				String[] registro = new String[4];
				
				for (ProdutoMateriaPrimaVO itemReceita : receita) {
					
					registro[0] = itemReceita.getMateriaPrima().getCodProduto();
					registro[1] = itemReceita.getMateriaPrima().getDescricao();
					registro[2] = itemReceita.getQtde().toString();
					registro[3] = itemReceita.getUnidade().getDescricao() ;
					
					modeloTabMatPrimas.addRow(registro);
					
				}
	
			}
	
		}		
		
		
		// Métodos ITelaBuscar

		@Override
		public List<GenericVO> buscarItemPorCodigoENome(Map<String, String> parametros) {
			
			List<GenericVO> listaGenericos = null;
			
			switch (acaoPesquisar) {
			
				case PESQ_MAT_PRIMA:
					
					listaGenericos = new ArrayList<GenericVO>();
					
					List<MateriaPrimaVO> materiasPrimas = matPrimaBo.buscarProdutoPorCodigoENome(parametros.get(BuscarDialogView.CODIGO), parametros.get(BuscarDialogView.NOME));
					
					for (MateriaPrimaVO materiaPrima : materiasPrimas) {
						listaGenericos.add(materiaPrima);
					}
					
					break;
					
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
			
				case PESQ_MAT_PRIMA:
					
					btnAddMatPrima.setEnabled(true);
					
					materiaPrima = (MateriaPrimaVO) item;

					txtCodMatPrimaRec.setText(materiaPrima.getCodProduto());
					txtMatPrimaRec.setText(materiaPrima.getDescricao());
					
				break;
					
				case PESQ_FORNECEDOR:
				
					btnAddForn.setEnabled(true);
					
					fornecedor = (FornecedorVO) item;
					
					txtCodFornecedor.setText(fornecedor.getCodFornecedor());
					txtFornecedor.setText(fornecedor.getNome());
					txtContatoForn.setText(fornecedor.getContato());
					
				break;
	
			}
			
		}

		@Override
		public String[] carregarGridTelaBusca(GenericVO item) {

			String[] registro = null;
			
			switch (acaoPesquisar) {
			
				case PESQ_MAT_PRIMA:
					
					MateriaPrimaVO materiaPriam = (MateriaPrimaVO) item; 
					
					registro = new String[3];

					registro[0] = materiaPriam.getCodProduto();
					registro[1] = materiaPriam.getDescricao();
					registro[2] = materiaPriam.getUnidade().getAbreviatura();
										
				return registro;
					
				case PESQ_FORNECEDOR:
					
					FornecedorVO fornecedor = (FornecedorVO) item; 
					
					registro = new String[3];

					registro[0] = fornecedor.getCodFornecedor();
					registro[1] = fornecedor.getNome();
					registro[2] = fornecedor.getContato();
					
					
				return registro;

			}
			
			return registro;
		}
		
	}
