package ui;

import interfaces.IGeradorCompra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import utils.UtilFuncoes;
import vo.CompraVO;
import vo.FuncionarioCantinaVO;
import vo.ItemCompraVO;
import vo.OrdemProducaoVO;
import vo.StatusVO;
import enumeradores.TipoSolicitacao;
import enumeradores.TipoStatus;

public class GeradorView extends JDialog {

	// Objetos da janela
	
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;
	private JLabel lblTituloCabecalho;
	private JLabel lblSelecione;
	private Font fonteCabecalho;
	
	private JCheckBox ccxListarTodos;
	
	private JButton btnGerar;
	
	private JTable tabProdutos;
	private DefaultTableModel modeloTabProdCompra;
	private JScrollPane barraTabProdCompra;

	// Dependências
	
	private List<ItemCompraVO> listaItensCompra;
	private List<ItemCompraVO> listaItensCompraSelecionados;
	private List<OrdemProducaoVO> listaOrdensProducao;
	
	private FuncionarioCantinaVO funcionario;
	private IGeradorCompra geradorCompra;
	
	
	// Bloco de inicialização
	
	{
	
		pnlCabecalho = new JPanel();
		pnlCentro = new JPanel();
		pnlRodape = new JPanel();
		lblTituloCabecalho = new JLabel();
		lblSelecione = new JLabel();
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		btnGerar = new JButton("Gerar");
		tabProdutos = new JTable();
		modeloTabProdCompra = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		barraTabProdCompra = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		modeloTabProdCompra.setColumnIdentifiers(new String[] {

				"Código", "Produto", "Quantidade"

		});
		
	}
	
	// Construtores
	
	public GeradorView(IGeradorCompra geradorCompra, List<ItemCompraVO> listaItensCompra) {
			
		ccxListarTodos = new JCheckBox("Listar Todos");

		ccxListarTodos.setFocusPainted(false);
		ccxListarTodos.setBounds(380, 10, 100, 20);
		pnlCentro.add(ccxListarTodos);
		
		lblTituloCabecalho.setText("Gerar Compra");
		lblSelecione.setText("Selecione um ou mais produtos");
		this.geradorCompra = geradorCompra;
		this.listaItensCompra = listaItensCompra;
		
		gerarCompra();
		
	}
	
	public GeradorView(FuncionarioCantinaVO funcionario, List<OrdemProducaoVO> listaOrdensProducao) {
	
		lblTituloCabecalho.setText("Gerar Ordem de Produção");		
		lblSelecione.setText("Selecione um produto");
		this.funcionario = funcionario;
		this.listaOrdensProducao = listaOrdensProducao;
		tabProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		gerarOrdemProducao();
		
	}
	
	// Métodos
	
	public void abrirTela(){
				
		lblTituloCabecalho.setForeground(Color.WHITE);	
		lblTituloCabecalho.setFont(fonteCabecalho);
		
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);
		pnlCabecalho.setBackground(Color.BLACK);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pnlCentro.setLayout(null);
		pnlCentro.add(barraTabProdCompra);
						
		pnlRodape.setBackground(Color.WHITE);
		pnlRodape.add(btnGerar);
		
		lblSelecione.setBounds(10, 10, 200, 20);
		pnlCentro.add(lblSelecione);
				
		tabProdutos.setModel(modeloTabProdCompra);
		
		barraTabProdCompra.setViewportView(tabProdutos);

		barraTabProdCompra.setBounds(10, 40, 475, 290);
		
		this.setLayout(new BorderLayout());		
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(500, 450);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private void gerarCompra() {
		
		btnGerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				listaItensCompraSelecionados = new ArrayList<ItemCompraVO>();
					
				for(int x = 0; x < tabProdutos.getRowCount(); x++){
					
					if(tabProdutos.isRowSelected(x)){
						listaItensCompraSelecionados.add(GeradorView.this.listaItensCompra.get(x));
					}
					
				}
				
				if(listaItensCompraSelecionados.size() == 0){	
					JOptionPane.showMessageDialog(null, "Favor selecionar ao menos um produto.");				
				}
				else{
					GeradorView.this.dispose();
					
					CompraVO compra = new CompraVO(geradorCompra, listaItensCompraSelecionados);
					compra.setStatus(new StatusVO());
					compra.getStatus().setDescricao(TipoStatus.EM_ABERTO);
					compra.setGeradorCompra(UtilFuncoes.usuarioLogado.getFuncionario().getFuncionarioCantinas().get(0));
					
					new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra").abrirJanela(compra);
				}
				
			}
			
		});

		ccxListarTodos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ccxListarTodos.isSelected()){
					tabProdutos.addRowSelectionInterval(0, tabProdutos.getRowCount()-1);
				}
				else{
					tabProdutos.removeRowSelectionInterval(0, tabProdutos.getRowCount()-1);
				}		
			}
			
		});
		
		carregarGridItensCompra(listaItensCompra);
		
	}
		
	private void gerarOrdemProducao() {
				
		btnGerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(tabProdutos.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "Favor selecionar um produto");
				}
				else{
					
					OrdemProducaoVO ordemProducao = listaOrdensProducao.get(tabProdutos.getSelectedRow());
					ordemProducao.setFuncionarioCantina(funcionario);
					ordemProducao.setStatus(new StatusVO());
					ordemProducao.getStatus().setDescricao(TipoStatus.EM_ABERTO);

					GeradorView.this.dispose();
					new ManterOrdemProducaoView(TipoSolicitacao.INCLUIR, "Cadastrar Ordem de Produção").abrirJanela(ordemProducao);

				}
				
			}
		});
		
		carregarGridOrdensProducao(listaOrdensProducao);
		
	}
	

	/**
	 * 
	 * @param listaItensCompra
	 */
	private void carregarGridItensCompra(List<ItemCompraVO> listaItensCompra) {

		modeloTabProdCompra.setNumRows(0);
		
		if(listaItensCompra != null){
			
			Iterator<ItemCompraVO> iListaItensCompra = listaItensCompra.iterator();
			
			while(iListaItensCompra.hasNext()){
				
				ItemCompraVO itemCompra = iListaItensCompra.next();
				
				String[] registro = new String[3];

				registro[0] = itemCompra.getProduto().getCodProduto();
				registro[1] = itemCompra.getProduto().getDescricao();
				registro[2] = itemCompra.getQtde().toString();
				
				modeloTabProdCompra.addRow(registro);	
				
			}
		}
		
	}
	
	/**
	 * 
	 * @param ordensProducao
	 */
	private void carregarGridOrdensProducao(List<OrdemProducaoVO> ordensProducao) {

		modeloTabProdCompra.setNumRows(0);
		
		if(ordensProducao != null){
			
			Iterator<OrdemProducaoVO> iListaOrdemProducao = ordensProducao.iterator();
			
			while(iListaOrdemProducao.hasNext()){
				
				OrdemProducaoVO ordemProducao = iListaOrdemProducao.next();
				
				String[] registro = new String[3];

				registro[0] = ordemProducao.getProdutoVenda().getCodProduto();
				registro[1] = ordemProducao.getProdutoVenda().getDescricao();
				registro[2] = ordemProducao.getQtde().toString();
				
				modeloTabProdCompra.addRow(registro);	
				
			}
		
		}
		
	}

}
