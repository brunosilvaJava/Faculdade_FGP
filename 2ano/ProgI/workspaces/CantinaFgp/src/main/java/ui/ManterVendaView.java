package ui;

import interfaces.ITelaBuscar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.BuscarDialogView;
import ui.templates.ManterFrameView;
import vo.ClienteVO;
import vo.GenericVO;
import vo.ItemVendaVO;
import vo.ProdutoVendaVO;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ManterVendaView extends ManterFrameView<VendaVO> implements ITelaBuscar {

	// Atributos Tela
	
	private JComboBox<String> cbxStatusVenda;
	private JComboBox<String> cbxFormaPgto;
	
	private JXDatePicker dtpDataVenda;
	
	private JTextField txtCodVenda;
	private JTextField txtCodProdVenda;
	private JTextField txtProdVenda;
	private JTextField txtQtdeProdVenda;
	private JTextField txtValorProdVenda;
	private JTextField txtDescProdVenda;
	private JTextField txtCodCliente;
	private JTextField txtCliente;
	private JTextField txtCodFuncionario;
	private JTextField txtFuncionario;
	
	private JLabel lblCodVenda;
	private JLabel lblCodProdVenda;
	private JLabel lblTitProduto;
	private JLabel lblProdVenda;
	private JLabel lblQtdeProdVenda;
	private JLabel lblValorProdVenda;
	private JLabel lblDescProdVenda;
	private JLabel lblTitCliente;
	private JLabel lblCodCliente;
	private JLabel lblCliente;
	private JLabel lblFuncionario;
	private JLabel lblStatusVenda;
	private JLabel lblDataVenda;
	private JLabel lblFormaPgto;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private Font fonteTotal;
	
	private JButton btnBuscarProd;
	private JButton btnBuscarCliente;
	private JButton btnAddProd;
	
	private String acaoPesquisar;
	private static final String PESQ_CLIENTE = "cliente";
	private static final String PESQ_PRODUTO = "produto";
	
	private List<ItemVendaVO> listaItensVenda;
	
	private JPanel pnlCampos;
	private JTable tabItemCompra;
	private DefaultTableModel modeloTabItemCompra;
	private JScrollPane barraTabItemCompra;
	
	// Bloco de inicialização
	
	{
		
		int widthCampos = this.getWidth();

		pnlCampos = new JPanel();
		pnlCampos.setBounds(10, 10, widthCampos-25, 480);
		pnlCampos.setLayout(null);
		pnlCampos.setBackground(Color.LIGHT_GRAY);
	
		dtpDataVenda = new JXDatePicker();
		
		cbxStatusVenda = new JComboBox<String>();
		cbxFormaPgto = new JComboBox<String>();
		
		lblFormaPgto = new JLabel("Pagamento");
		lblStatusVenda = new JLabel("Status");
		lblDataVenda = new JLabel("Data");
		lblCodVenda = new JLabel("Número");
		lblCodProdVenda = new JLabel("Código");
		lblProdVenda = new JLabel("Produto");
		lblQtdeProdVenda = new JLabel("Quantidade");
		lblValorProdVenda = new JLabel("Valor");
		lblTitProduto = new JLabel("PRODUTO");
		lblTitCliente = new JLabel("CLIENTE");
		lblCodCliente = new JLabel("Código");
		lblCliente = new JLabel("Cliente");
		lblTotal = new JLabel("TOTAL");	
		fonteTotal = new Font("Verdana", Font.BOLD, 20);
		lblTotal.setFont(fonteTotal);
		lblValorTotal = new JLabel("R$ 0,00");
		lblValorTotal.setFont(fonteTotal);
		lblDescProdVenda = new JLabel("Desconto");
		lblFuncionario = new JLabel("Funcionário");
		
		txtCodVenda = new JTextField();
		txtCodProdVenda = new JTextField();
		txtProdVenda = new JTextField();
		txtProdVenda.setEditable(false);
		txtQtdeProdVenda = new JTextField();
		txtValorProdVenda = new JTextField();
		txtDescProdVenda = new JTextField();
		txtCodCliente = new JTextField();
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCodFuncionario = new JTextField();
		txtFuncionario = new JTextField();
		txtFuncionario.setEditable(false);
		
		btnBuscarCliente = new JButton("Consultar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				acaoPesquisar = PESQ_CLIENTE;
				
				new BuscarDialogView(ManterVendaView.this, new String[] {"Código", "Nome"}).abrirJanela();
				
			}
			
		});
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				acaoPesquisar = PESQ_PRODUTO;
				
				new BuscarDialogView(ManterVendaView.this, new String[] {"Código", "Nome", "Valor de venda"}).abrirJanela();
												
			}
			
		});
		
		int espXLbl = 20;
		int espXTxt = espXLbl + 90;
		int	espXForn = 350;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblStatusVenda.setBounds(400, espY + espEntre, 70, altura);
		cbxStatusVenda.setBounds(460, espY + espEntre, 180, altura);
		
		lblDataVenda.setBounds(400, espY, 80, altura);
		dtpDataVenda.setBounds(460, espY, 130, altura);

		lblCodVenda.setBounds(espXLbl, espY, 50, altura);
		lblFuncionario.setBounds(espXLbl, espY + espEntre, 80, altura);
		lblTitProduto.setBounds(espXLbl, espY + espEntre * 2, 80, altura);
		lblCodProdVenda.setBounds(espXLbl, espY + espEntre * 3, 50, altura);
		lblProdVenda.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblQtdeProdVenda.setBounds(espXLbl, espY + espEntre * 5, 80, altura);
		lblValorProdVenda.setBounds(espXLbl, espY + espEntre * 6, 80, altura);

		txtCodVenda.setBounds(espXTxt, espY, 70, altura);
		txtCodFuncionario.setBounds(espXTxt, espY + espEntre, 70, altura);
		txtFuncionario.setBounds(espXTxt + 75, espY + espEntre, 180, altura);
		txtCodProdVenda.setBounds(espXTxt, espY + espEntre * 3, 70, altura);
		txtProdVenda.setBounds(espXTxt, espY + espEntre * 4, 220, altura);
		txtQtdeProdVenda.setBounds(espXTxt, espY + espEntre * 5, 70, altura);
		txtValorProdVenda.setBounds(espXTxt, espY + espEntre * 6, 70, altura);
		
		btnBuscarProd.setBounds(espXTxt + 80, espY + espEntre * 3, 100, altura);
		
		lblTitCliente.setBounds(espXLbl + espXForn, espY + espEntre * 2, 80, altura);
		lblCodCliente.setBounds(espXLbl + espXForn, espY + espEntre * 3, 80, altura);
		lblCliente.setBounds(espXLbl + espXForn, espY + espEntre * 4, 80, altura);
		
		txtCodCliente.setBounds(espXTxt + espXForn, espY + espEntre * 3, 70, altura);
		txtCliente.setBounds(espXTxt + espXForn, espY + espEntre * 4, 200, altura);

		btnBuscarCliente.setBounds(espXTxt + espXForn + 80, espY + espEntre * 3, 100, altura);
		
		lblFormaPgto.setBounds(espXLbl + espXForn, espY + espEntre * 5, 80, altura);
		cbxFormaPgto.setBounds(espXTxt + espXForn, espY + espEntre * 5, 120, altura);

		lblDescProdVenda.setBounds(espXLbl + espXForn - 100, espY + espEntre * 6 + 5, 80, altura);
		txtDescProdVenda.setBounds(espXLbl + espXForn - 30, espY + espEntre * 6 + 5, 80, altura);
		
		lblTotal.setBounds(espXLbl + espXForn + 80, espY + espEntre * 6 + 5, 80, altura);
		lblValorTotal.setBounds(espXTxt + espXForn + 80, espY + espEntre * 6 + 5, 120, altura);
		
		btnAddProd = new JButton("+");
		btnAddProd.setBounds(190, espY + espEntre * 6, 50, altura);

		tabItemCompra = new JTable();
		modeloTabItemCompra = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modeloTabItemCompra.setColumnIdentifiers(new String[] {

				"Código", "Produto", "Quantidade", "Valor", "Total"

		});

		tabItemCompra.setModel(modeloTabItemCompra);

		barraTabItemCompra = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		barraTabItemCompra.setViewportView(tabItemCompra);

		barraTabItemCompra.setBounds(10, 275, pnlCampos.getWidth() - 20, 195);

		pnlCampos.add(btnBuscarCliente);
		pnlCampos.add(btnBuscarProd);
		pnlCampos.add(barraTabItemCompra);
		
		pnlCampos.add(cbxStatusVenda);
		pnlCampos.add(cbxFormaPgto);
		
		pnlCampos.add(dtpDataVenda);
		
		pnlCampos.add(txtCodVenda);
		pnlCampos.add(txtCodProdVenda);
		pnlCampos.add(txtProdVenda);
		pnlCampos.add(txtQtdeProdVenda);
		pnlCampos.add(txtValorProdVenda);
		pnlCampos.add(txtDescProdVenda);
		pnlCampos.add(txtCodCliente);
		pnlCampos.add(txtCliente);
		pnlCampos.add(txtCodFuncionario);
		pnlCampos.add(txtFuncionario);
		pnlCampos.add(txtDescProdVenda);
		
		pnlCampos.add(lblCodVenda);
		pnlCampos.add(lblCodProdVenda);
		pnlCampos.add(lblTitProduto);
		pnlCampos.add(lblProdVenda);
		pnlCampos.add(lblQtdeProdVenda);
		pnlCampos.add(lblValorProdVenda);
		pnlCampos.add(lblDescProdVenda);
		pnlCampos.add(lblTitCliente);
		pnlCampos.add(lblCodCliente);
		pnlCampos.add(lblCliente);
		pnlCampos.add(lblFuncionario);
		pnlCampos.add(lblStatusVenda);
		pnlCampos.add(lblDataVenda);
		pnlCampos.add(lblFormaPgto);
		pnlCampos.add(lblTotal);
		pnlCampos.add(lblValorTotal);
		
		pnlCampos.add(btnBuscarProd);
		pnlCampos.add(btnBuscarCliente);
		pnlCampos.add(btnAddProd);
		
		adicionarComponentesCentro(pnlCampos);
		
	}
	
	// Construtores
	
	protected ManterVendaView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	
	// Métodos Concretos
	
	@Override
	public void abrirJanela(VendaVO venda) {
		
		this.setVisible(true);
	
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Venda Incluída");
		return true;
	}

	@Override
	public boolean alterar(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Venda Alterada");	
		return true;
	}	

	@Override
	public boolean isCamposValidos(StringBuilder msgErro) {
		
		
		return false;
	
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}


	@Override
	protected boolean desabilitarCampos() {
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	// Métodos ITelaBuscar

	@Override
	public List<GenericVO> buscarItemPorCodigoENome(Map<String, String> parametros) {
				
		switch (acaoPesquisar) {
			
			case PESQ_PRODUTO:
				
				return null;
				
			case PESQ_CLIENTE:
				
				return null;

		}
		
		return null;
	}


	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		
		if(item instanceof ProdutoVendaVO){
						
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
			
			txtCodProdVenda.setText(produtoVenda.getCodProduto());
			txtProdVenda.setText(produtoVenda.getDescricao());
			
		}
		else{
			if(item instanceof ClienteVO){
			
				ClienteVO cliente = (ClienteVO) item;
				
				txtCodCliente.setText(cliente.getCodPessoa());
				txtCliente.setText(cliente.getNome());
				
			}
			
		}
		
	}


	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
				
		if(item instanceof ProdutoVendaVO){
			
			ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
			
			String[] registro = new String[3];

			registro[0] = produtoVenda.getCodProduto();
			registro[1] = produtoVenda.getDescricao();
			registro[2] = produtoVenda.getPrecoVenda().toString();
			
			return registro;
			
		}
		else{
			if(item instanceof ClienteVO){
							
				ClienteVO cliente = (ClienteVO) item;
				
				String[] registro = new String[2];

				registro[0] = cliente.getCodPessoa();
				registro[1] = cliente.getNome();
				
				return registro;
				
			}
			
		}
		
		return null;
	}

}
