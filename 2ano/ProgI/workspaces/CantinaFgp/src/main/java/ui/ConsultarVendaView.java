package ui;

import interfaces.ITelaBuscar;
import interfaces.ITelaManter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.BuscarDialogView;
import ui.templates.ConsultarPanelView;
import vo.ClienteVO;
import vo.FuncionarioCantinaVO;
import vo.GenericVO;
import vo.ProdutoVendaVO;
import vo.VendaVO;
import enumeradores.TipoSolicitacao;

public class ConsultarVendaView extends ConsultarPanelView<VendaVO> implements ITelaBuscar{

	// Atributos
	
	private JLabel lblFiltrar;
	private JLabel lblNumVenda;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	private JLabel lblCodProduto;
	private JLabel lblProduto;
	private JLabel lblCodCliente;
	private JLabel lblCliente;
	private JLabel lblStatus;
	private JLabel lblFormaPgto;
	private JLabel lblCodFuncionario;
	private JLabel lblFuncionario;

	private JXDatePicker dtpDataInicial;
	private JXDatePicker dtpDataFinal;
	
	private JComboBox<String> cbxStatusVenda;
	private JComboBox<String> cbxFormaPgto;
	
	private JTextField txtNumVenda;
	private JTextField txtCodProduto;
	private JTextField txtProduto;
	private JTextField txtCodCliente;
	private JTextField txtCliente;
	private JTextField txtCodFuncionario;
	private JTextField txtFuncionario;

	private JButton btnBuscarCliente;
	private JButton btnBuscarProd;
	private JButton btnBuscarFunc;
	
	private String acaoPesquisar;
	private static final String PESQ_CLIENTE = "cliente";
	private static final String PESQ_PRODUTO = "produto";
	private static final String PESQ_FUNCIONARIO = "funcionario";
	
	// Bloco de inicialização
	
	{
				
		lblFiltrar = new JLabel("FILTRAR");
		lblNumVenda = new JLabel("Número");
		lblCodCliente = new JLabel("Código");
		lblCliente = new JLabel("Cliente");
		lblCodProduto = new JLabel("Código");
		lblProduto = new JLabel("Produto");
		lblCodFuncionario = new JLabel("Código");
		lblFuncionario = new JLabel("Funcionário");
		lblDataInicial = new JLabel("Data Inicial");
		lblDataFinal = new JLabel("Data Final");
		lblStatus = new JLabel("Status");
		lblFormaPgto = new JLabel("Pagamento");

		dtpDataInicial = new JXDatePicker();
		dtpDataFinal = new JXDatePicker();
		
		cbxStatusVenda = new JComboBox<String>();
		cbxFormaPgto = new JComboBox<String>();
		
		txtNumVenda = new JTextField();
		txtCodCliente = new JTextField();
		txtCliente = new JTextField();
		txtCodProduto = new JTextField();
		txtProduto = new JTextField();
		txtCodFuncionario = new JTextField();
		txtFuncionario = new JTextField();

		txtFuncionario.setEditable(false);
		txtProduto.setEditable(false);
		txtCliente.setEditable(false);
		
		btnBuscarCliente = new JButton("Consultar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_CLIENTE;
				
				new BuscarDialogView(ConsultarVendaView.this, new String[] {"Código","Cliente"}).abrirJanela();
				
			}
			
		});
		
		btnBuscarProd = new JButton("Consultar");
		btnBuscarProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_PRODUTO;
				
				new BuscarDialogView(ConsultarVendaView.this, new String[] {"Código","Produto"}).abrirJanela();
				
			}
			
		});
		
		btnBuscarFunc = new JButton("Consultar");
		btnBuscarFunc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_FUNCIONARIO;
				
				new BuscarDialogView(ConsultarVendaView.this, new String[] {"Código","Funcionário"}).abrirJanela();
				
			}
			
		});
		
		int espXLbl = 20;
		int espXTxt = 100;
		int espXLbl2 = 320;
		int espXTxt2 = 410;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		lblFiltrar.setBounds(espXLbl, espY, 50, altura);
		lblNumVenda.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblCodCliente.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblCliente.setBounds(espXLbl, espY + espEntre * 3, 80, altura);
		lblCodProduto.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblProduto.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtNumVenda.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtCodCliente.setBounds(espXTxt, espY + espEntre * 2, 50, altura);
		btnBuscarCliente.setBounds(espXTxt + 60, espY + espEntre * 2, 100, altura);
		txtCliente.setBounds(espXTxt, espY + espEntre * 3, 180, altura);
		txtCodProduto.setBounds(espXTxt, espY + espEntre * 4, 50, altura);
		btnBuscarProd.setBounds(espXTxt + 60, espY + espEntre * 4, 100, altura);
		txtProduto.setBounds(espXTxt, espY + espEntre * 5, 180, altura);
		
		lblCodFuncionario.setBounds(espXLbl2, espY + espEntre, 50, altura);
		lblFuncionario.setBounds(espXLbl2, espY + espEntre * 2, 80, altura);
		lblDataInicial.setBounds(espXLbl2, espY + espEntre * 3, 80, altura);
		lblDataFinal.setBounds(espXLbl2, espY + espEntre * 4, 80, altura);
		lblStatus.setBounds(espXLbl2, espY + espEntre * 5, 80, altura);
		lblFormaPgto.setBounds(espXLbl2, espY + espEntre * 6, 80, altura);
		
		txtCodFuncionario.setBounds(espXTxt2, espY + espEntre, 50, altura);
		btnBuscarFunc.setBounds(espXTxt2 + 60, espY + espEntre, 100, altura);
		txtFuncionario.setBounds(espXTxt2, espY + espEntre * 2, 180, altura);
		dtpDataInicial.setBounds(espXTxt2, espY + espEntre * 3, 130, altura);
		dtpDataFinal.setBounds(espXTxt2, espY + espEntre * 4, 130, altura);
		cbxStatusVenda.setBounds(espXTxt2, espY + espEntre * 5, 130, altura);
		cbxFormaPgto.setBounds(espXTxt2, espY + espEntre * 6, 130, altura);
		
		adicionarComponenteCentro(cbxStatusVenda);
		adicionarComponenteCentro(cbxFormaPgto);
		adicionarComponenteCentro(lblFiltrar);
		adicionarComponenteCentro(lblNumVenda);
		adicionarComponenteCentro(lblDataInicial);
		adicionarComponenteCentro(lblDataFinal);
		adicionarComponenteCentro(lblCodProduto);
		adicionarComponenteCentro(lblProduto);
		adicionarComponenteCentro(lblCodCliente);
		adicionarComponenteCentro(lblCliente);
		adicionarComponenteCentro(lblStatus);
		adicionarComponenteCentro(lblFormaPgto);
		adicionarComponenteCentro(lblCodFuncionario);
		adicionarComponenteCentro(lblFuncionario);
		adicionarComponenteCentro(dtpDataFinal);
		adicionarComponenteCentro(dtpDataInicial);
		adicionarComponenteCentro(txtNumVenda);
		adicionarComponenteCentro(dtpDataInicial);
		adicionarComponenteCentro(dtpDataFinal);
		adicionarComponenteCentro(txtCodProduto);
		adicionarComponenteCentro(txtProduto);
		adicionarComponenteCentro(txtCodCliente);
		adicionarComponenteCentro(txtCliente);
		adicionarComponenteCentro(txtCodFuncionario);
		adicionarComponenteCentro(txtFuncionario);
		adicionarComponenteCentro(btnBuscarCliente);
		adicionarComponenteCentro(btnBuscarFunc);
		adicionarComponenteCentro(btnBuscarProd);
		
	}
	
	// Construtores
	
	public ConsultarVendaView() {
		
		super("Venda", new String[] {"Código",	"Data"}, 10, 275, 665, 190);
		this.setSize(750, 535);
		
	}
	
	// Métodos

	@Override
	protected String[] definirGridItens(VendaVO venda) {
		
		String[] registro = new String[2];

		registro[0] = venda.getCodVenda();
		registro[1] = venda.getData().toString();
		
		return registro;
		
	}

	@Override
	protected ITelaManter<VendaVO> getTelaIncluir() {
		return new ManterVendaView(TipoSolicitacao.INCLUIR, "Cadastrar Venda");
	}
	
	@Override
	protected ITelaManter<VendaVO> getTelaAlterar() {
		return new ManterVendaView(TipoSolicitacao.DETALHAR, "Detalhar Venda");
	}

	@Override
	public void deletar(VendaVO venda) {

		JOptionPane.showMessageDialog(null, "Deletar Venda");
		
	}

	@Override
	public List<VendaVO> consultar() {
		return null;
	}

	// Métodos ITelaBuscar
	
	@Override
	public List<GenericVO> buscarItemPorCodigoENome(Map<String, String> parametros) {
		
		switch (acaoPesquisar) {
		
			case PESQ_PRODUTO:
				return null;
				
			case PESQ_CLIENTE:
				return null;
				
			case PESQ_FUNCIONARIO:
				return null;

		}
		
		return null;
	}

	@Override
	public void carregarItemSelecionado(GenericVO item) {
		
		switch (acaoPesquisar) {
		
			case PESQ_PRODUTO:
				ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
				
				txtCodProduto.setText(produtoVenda.getCodProduto());
				txtProduto.setText(produtoVenda.getDescricao());
				
			break;
				
			case PESQ_CLIENTE:
				ClienteVO cliente = (ClienteVO) item; 
				
				txtCodCliente.setText(cliente.getCodPessoa());
				txtCliente.setText(cliente.getNome());
				
			break;
				
			case PESQ_FUNCIONARIO:
				FuncionarioCantinaVO funcionario = (FuncionarioCantinaVO) item;
				
				txtCodFuncionario.setText(funcionario.getFuncionario().getCodPessoa());
				txtFuncionario.setText(funcionario.getFuncionario().getNome());
			
			break;

		}		
		
	}

	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
		
		String[] registro = null;
		
		switch (acaoPesquisar) {
		
			case PESQ_PRODUTO:
				
				ProdutoVendaVO produtoVenda = (ProdutoVendaVO) item; 
				
				registro = new String[2];
	
				registro[0] = produtoVenda.getCodProduto();
				registro[1] = produtoVenda.getDescricao();
				
				break;
				
			case PESQ_CLIENTE:
				
				ClienteVO cliente = (ClienteVO) item; 
				
				registro = new String[2];
	
				registro[0] = cliente.getCodPessoa();
				registro[1] = cliente.getNome();
				
				break;
				
			case PESQ_FUNCIONARIO:
				
				FuncionarioCantinaVO funcionario = (FuncionarioCantinaVO) item; 
				
				registro = new String[2];
	
				registro[0] = funcionario.getFuncionario().getCodPessoa();
				registro[1] = funcionario.getFuncionario().getNome();
				
				break;

		}
		
		return registro;
		
	}

}
