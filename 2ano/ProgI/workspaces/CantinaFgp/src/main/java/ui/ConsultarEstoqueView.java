package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import utils.UtilFuncoes;
import vo.ItemCompraVO;
import vo.MateriaPrimaVO;
import vo.OrdemProducaoVO;
import vo.ProdutoCantinaVO;
import vo.ProdutoVendaVO;
import bo.MateriaPrimaBO;
import bo.ProdutoVendaBO;
import enumeradores.TipoProduto;
import enumeradores.TipoSolicitacao;

public class ConsultarEstoqueView extends JPanel{

	// Atributos da página
		
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlMenuLateral;
	private JPanel pnlRodape;
 
	private JButton btnGerarCompra;
	private JButton btnGerarOrdemProd;
	private JButton btnConsultar;
	private JButton btnFechar;	

	private JTable tabEstoque;
	private DefaultTableModel modeloTabEstoque;
	private JScrollPane barraTabEstoque;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	private JTextField txtCodProdPesq;
	private JTextField txtDescricaoProd;
	
	private JComboBox<String> cbxTipoProduto;
	
	private JCheckBox ccxAbaixoEstoque;
	
	private JLabel lblFiltrar;
	private JLabel lblCodProdPesq;
	private JLabel lblProdPesq;
	private JLabel lblTipoProduto;
	
	private MateriaPrimaBO matPrimaBo;
	private ProdutoVendaBO prodVendaBo;
	private List<TipoProduto> tiposProduto;
	
	private List<ProdutoCantinaVO> listaEstoqueProdutos;
	private List<ItemCompraVO> listaItensCompra;
	private List<OrdemProducaoVO> listaOrdemProducao;
		
	{
		btnGerarCompra = new JButton("Gerar OC");
		btnGerarCompra.setEnabled(false);
		btnGerarOrdemProd = new JButton("Gerar OP");
		btnGerarOrdemProd.setEnabled(false);
		
		listaItensCompra = new ArrayList<ItemCompraVO>();
		listaOrdemProducao = new ArrayList<OrdemProducaoVO>();
		
		cbxTipoProduto = new JComboBox<String>();
		
		prodVendaBo = new ProdutoVendaBO();
		matPrimaBo = new MateriaPrimaBO();
		
		tiposProduto = prodVendaBo.consultarTiposProduto();

		for (TipoProduto tipoProduto : tiposProduto) {
			
			cbxTipoProduto.addItem(tipoProduto.getDescricao());
			
		}
		
	}
	
	
	// Construtor
	
	public ConsultarEstoqueView() {
		criarPainel();
	}
	
	
	// Métodos concretos
	
	private void criarPainel(){
		
		pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setLayout(new BorderLayout());
		
		pnlCentro = new JPanel();	
		pnlCentro.setBackground(Color.LIGHT_GRAY);
		pnlCentro.setLayout(null);
		
		pnlMenuLateral = new JPanel();
		pnlMenuLateral.setLayout(new GridLayout(10,1));
		pnlMenuLateral.setBackground(Color.WHITE);
		
		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);
		
		lblTituloCabecalho = new JLabel("Estoque");
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		
		
		// Filtro / Consulta
		
		lblFiltrar = new JLabel("FILTRAR");
		lblCodProdPesq = new JLabel("Código");
		lblProdPesq = new JLabel("Descrição");
		lblTipoProduto = new JLabel("Tipo");
		
		txtCodProdPesq = new JTextField();
		
		txtDescricaoProd = new JTextField();

		ccxAbaixoEstoque = new JCheckBox("Abaixo do Estoque");
		ccxAbaixoEstoque.setBackground(pnlCentro.getBackground());
		ccxAbaixoEstoque.setFocusPainted(false);
		
		int espXLbl = 20;
		int espXTxt = 110;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblFiltrar.setBounds(espXLbl, espY, 50, altura);
		lblCodProdPesq.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblProdPesq.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblTipoProduto.setBounds(espXLbl, espY + espEntre * 3, 50, altura);

		txtCodProdPesq.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtDescricaoProd.setBounds(espXTxt, espY + espEntre * 2, 250, altura);
		cbxTipoProduto.setBounds(espXTxt, espY + espEntre * 3, 150, altura);
		ccxAbaixoEstoque.setBounds(espXTxt + 310, espY + espEntre, 150, altura);
		
		pnlCentro.add(lblFiltrar);
		pnlCentro.add(lblCodProdPesq);
		pnlCentro.add(lblProdPesq);
		pnlCentro.add(lblTipoProduto);
		pnlCentro.add(txtCodProdPesq);
		pnlCentro.add(txtDescricaoProd);
		pnlCentro.add(cbxTipoProduto);
		pnlCentro.add(ccxAbaixoEstoque);
		
		
		// Tabela de produtos
		
		tabEstoque = new JTable();
		modeloTabEstoque = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabEstoque = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		modeloTabEstoque.setColumnIdentifiers(new String[] {

				"Código","Produto",	"Tipo",	"Qtde", "Qtde Mínima", "Qtde Maxima"

		});
		
		modeloTabEstoque.setNumRows(0); // funciona para zerar o q tinha antes
		
		tabEstoque.setModel(modeloTabEstoque);		
		
		tabEstoque.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(e.getClickCount()==2){
					if(tabEstoque.getSelectedRow() != -1){

						ProdutoCantinaVO estoque = listaEstoqueProdutos.get(tabEstoque.getSelectedRow());	
																
						new ManterProdutoView(TipoSolicitacao.DETALHAR, "Detalhar Produto").abrirJanela(estoque.getProduto());

					}
				}
				
			}
		});
		
		barraTabEstoque.setViewportView(tabEstoque);
		
		barraTabEstoque.setBounds(10, 190, 645, 185);
		
		pnlCentro.add(barraTabEstoque);
		
		
		// BOTÕES
		
		btnGerarOrdemProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				listaOrdemProducao.clear();
				
				OrdemProducaoVO ordemProducao;
				
				for (ProdutoCantinaVO estoqueProduto : listaEstoqueProdutos) {
					
					if(estoqueProduto.getProduto().getTipo() == TipoProduto.PRODUCAO){
						
						int qtdeMin = UtilFuncoes.doubleToInteger(estoqueProduto.getQtdeMinima());
						int qtdeMax = UtilFuncoes.doubleToInteger(estoqueProduto.getQtdeMaxima());
						int qtdeAtual = UtilFuncoes.doubleToInteger(estoqueProduto.getQtdeAtual());
						
						if(qtdeAtual < qtdeMin){
							
							ordemProducao = new OrdemProducaoVO();
														
							ordemProducao.setProdutoVenda((ProdutoVendaVO) estoqueProduto.getProduto());
							ordemProducao.setQtde(qtdeMax - qtdeAtual);
							
							listaOrdemProducao.add(ordemProducao);
						
						}
						
					}
					
				}
				
				if(listaOrdemProducao.size() > 0){
										
					new GeradorView(UtilFuncoes.usuarioLogado.getFuncionario().getFuncionarioCantinas().get(0), listaOrdemProducao).abrirTela();
				}
				else{
					JOptionPane.showMessageDialog(null, "Não há produtos abaixo do estoque");
				}
				
			}
			
		});
		
		btnGerarCompra.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				
				listaItensCompra.clear();
				ItemCompraVO itemCompra;
				
				for (ProdutoCantinaVO estoqueProduto : listaEstoqueProdutos) {
														
					if(estoqueProduto.getProduto().getTipo() != TipoProduto.PRODUCAO){
						
						if(estoqueProduto.getQtdeAtual() < estoqueProduto.getQtdeMinima()){
							itemCompra = new ItemCompraVO();
							itemCompra.setProduto(estoqueProduto.getProduto());
							itemCompra.setQtde(estoqueProduto.getQtdeMaxima() - estoqueProduto.getQtdeAtual());
							itemCompra.setValor(estoqueProduto.getProduto().getPrecoCusto());
							listaItensCompra.add(itemCompra);
						}
						
					}
					
				}
				
				if(listaItensCompra.size() > 0){
					new GeradorView(null, listaItensCompra).abrirTela();
				}
				else{
					JOptionPane.showMessageDialog(null, "Não há produtos abaixo do estoque");
				}
				
			}
			
		});
		
		pnlMenuLateral.add(btnGerarCompra);
		pnlMenuLateral.add(btnGerarOrdemProd);
		
		btnFechar = new JButton("X");
		btnFechar.setBackground(Color.RED);
		btnFechar.setForeground(Color.WHITE);	
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				getParent().removeAll();
				
			}
			
		});
		
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
				
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				listaEstoqueProdutos = consultar();				
				carregarGridItens(listaEstoqueProdutos);
				
			}
			
		});
				
		pnlRodape.add(btnConsultar);
		
		// Definições página
				
		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlMenuLateral, BorderLayout.WEST);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.setSize(750, 450);
		
	}
	
	/**
	 * Insere na tabela os itens passados como parâmetro. 
	 * 
	 * @param estoqueProduto - 
	 */
	private void carregarGridItens(List<ProdutoCantinaVO> estoqueProduto) {

		modeloTabEstoque.setNumRows(0);
		
		if(estoqueProduto != null){
			
			for (ProdutoCantinaVO estoque : estoqueProduto) {
				
				String[] registro = new String[6];

				registro[0] = estoque.getProduto().getCodProduto();
				registro[1] = estoque.getProduto().getDescricao();
				registro[2] = estoque.getProduto().getTipo().getDescricao();
				registro[3] = estoque.getQtdeAtual().toString();
				registro[4] = estoque.getQtdeMinima().toString();
				registro[5] = estoque.getQtdeMaxima().toString();
				
				modeloTabEstoque.addRow(registro);	
				
			}

		}
		
	}

	public List<ProdutoCantinaVO> consultar() {
		
		List<ProdutoCantinaVO> listaPc = new ArrayList<ProdutoCantinaVO>();
		
		// Retornando matérias primas
		
		if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.MATERIA_PRIMA.getDescricao())){
			
			btnGerarCompra.setEnabled(true);
			btnGerarOrdemProd.setEnabled(false);
			
			List<MateriaPrimaVO> materiasPrimas = matPrimaBo.consultarTodosProdutos();
			
			for (MateriaPrimaVO materiaPrima : materiasPrimas) {
			
				materiaPrima.getEstoque().setProduto(materiaPrima);
				listaPc.add(materiaPrima.getEstoque());
				
			}
			
		}
		else{
			
			List<ProdutoVendaVO> produtosVenda = prodVendaBo.consultarTodosProdutos();
			
			for (ProdutoVendaVO produtoVenda : produtosVenda) {
				
				// retornando produtos produzidos
				
				if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.PRODUCAO.getDescricao())){
										
					if(produtoVenda.getTipo().equals(TipoProduto.PRODUCAO)){
						
						btnGerarCompra.setEnabled(false);
						btnGerarOrdemProd.setEnabled(true);
						
						produtoVenda.getEstoque().setProduto(produtoVenda);
						listaPc.add(produtoVenda.getEstoque());
						
					}
					
				}
				else{
					
					// retornando produtos de revenda
					
					if(cbxTipoProduto.getSelectedItem().equals(TipoProduto.REVENDA.getDescricao())){
						
						if(produtoVenda.getTipo().equals(TipoProduto.REVENDA)){
							
							btnGerarCompra.setEnabled(true);
							btnGerarOrdemProd.setEnabled(false);
							
							produtoVenda.getEstoque().setProduto(produtoVenda);
							listaPc.add(produtoVenda.getEstoque());
							
						}
						
					}
				}
							
			}			
			
		}
				
		return listaPc;
		
	}

}
