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
import vo.CompraVO;
import vo.GenericVO;
import bo.CompraBO;
import enumeradores.TipoSolicitacao;

public class ConsultarCompraView extends ConsultarPanelView<CompraVO> implements
		ITelaBuscar {

	// Atributos

	private JLabel lblFiltrar;
	private JLabel lblNumVenda;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	private JLabel lblCodProduto;
	private JLabel lblProduto;
	private JLabel lblCodFornecedor;
	private JLabel lblFornecedor;
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
	private JTextField txtCodFornecedor;
	private JTextField txtFornecedor;
	private JTextField txtCodFuncionario;
	private JTextField txtFuncionario;

	private JButton btnBuscarForn;
	private JButton btnBuscarProd;
	private JButton btnBuscarFunc;

	private String acaoPesquisar;
	private static final String PESQ_FORNECEDOR = "fornecedor";
	private static final String PESQ_PRODUTO = "produto";
	private static final String PESQ_FUNCIONARIO = "funcionario";

	private CompraBO compraBo;

	// Bloco de inicialização

	{

		lblFiltrar = new JLabel("FILTRAR");
		lblNumVenda = new JLabel("Número");
		lblCodFornecedor = new JLabel("Código");
		lblFornecedor = new JLabel("Fornecedor");
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
		txtCodFornecedor = new JTextField();
		txtFornecedor = new JTextField();
		txtCodProduto = new JTextField();
		txtProduto = new JTextField();
		txtCodFuncionario = new JTextField();
		txtFuncionario = new JTextField();

		txtFuncionario.setEditable(false);
		txtProduto.setEditable(false);
		txtFornecedor.setEditable(false);

		btnBuscarForn = new JButton("Consultar");
		btnBuscarProd = new JButton("Consultar");
		btnBuscarFunc = new JButton("Consultar");

		int espXLbl = 20;
		int espXTxt = 100;
		int espXLbl2 = 320;
		int espXTxt2 = 410;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;

		lblFiltrar.setBounds(espXLbl, espY, 50, altura);
		lblNumVenda.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblCodFornecedor.setBounds(espXLbl, espY + espEntre * 2, 50, altura);
		lblFornecedor.setBounds(espXLbl, espY + espEntre * 3, 80, altura);
		lblCodProduto.setBounds(espXLbl, espY + espEntre * 4, 50, altura);
		lblProduto.setBounds(espXLbl, espY + espEntre * 5, 80, altura);

		txtNumVenda.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtCodFornecedor.setBounds(espXTxt, espY + espEntre * 2, 50, altura);
		btnBuscarForn.setBounds(espXTxt + 60, espY + espEntre * 2, 100, altura);
		txtFornecedor.setBounds(espXTxt, espY + espEntre * 3, 180, altura);
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
		adicionarComponenteCentro(lblCodFornecedor);
		adicionarComponenteCentro(lblFornecedor);
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
		adicionarComponenteCentro(txtCodFornecedor);
		adicionarComponenteCentro(txtFornecedor);
		adicionarComponenteCentro(txtCodFuncionario);
		adicionarComponenteCentro(txtFuncionario);
		adicionarComponenteCentro(btnBuscarForn);
		adicionarComponenteCentro(btnBuscarFunc);
		adicionarComponenteCentro(btnBuscarProd);

		compraBo = new CompraBO();

	}

	// Construtores

	public ConsultarCompraView() {

		super("Compra", new String[] { "Código", "Fornecedor", "Data", "Status" }, 10, 275, 665, 190);

		acoesBotoes();

		this.setSize(750, 535);
	}

	// Métodos

	private void acoesBotoes() {

		btnBuscarFunc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_FUNCIONARIO;

				new BuscarDialogView(ConsultarCompraView.this, new String[] {
						"Código", "Funcionário" }).abrirJanela();

			}

		});

		btnBuscarProd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_PRODUTO;

				new BuscarDialogView(ConsultarCompraView.this, new String[] {
						"Código", "Produto" }).abrirJanela();

			}

		});

		btnBuscarForn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				acaoPesquisar = PESQ_FORNECEDOR;

				new BuscarDialogView(ConsultarCompraView.this, new String[] {
						"Código", "Fornecedor" }).abrirJanela();

			}

		});

	}

	@Override
	protected String[] definirGridItens(CompraVO compra) {

		String[] registro = new String[4];

		registro[0] = compra.getCodCompra();
		registro[1] = compra.getFornecedor().getNome();
		registro[2] = compra.getData().toString();
		registro[3] = compra.getStatus().getDescricao();

		return registro;

	}

	@Override
	protected ITelaManter<CompraVO> getTelaIncluir() {
		return new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra");
	}

	@Override
	protected ITelaManter<CompraVO> getTelaAlterar() {
		return new ManterCompraView(TipoSolicitacao.DETALHAR, "Detalhar Compra");
	}

	@Override
	public void deletar(CompraVO compra) {

		if (compraBo.deletar(compra)) {
			JOptionPane.showMessageDialog(null, "Compra excluída");
		}
		else{
			JOptionPane.showMessageDialog(null, "Não é permitido excluir uma compra concluída");
		}

	}

	@Override
	public List<CompraVO> consultar() {
		return compraBo.consultar();
	}

	// Métodos ITelaBuscar

	@Override
	public List<GenericVO> buscarItemPorCodigoENome(Map<String, String> parametrosBusca) {
		return null;
	}

	@Override
	public void carregarItemSelecionado(GenericVO item) {

	}

	@Override
	public String[] carregarGridTelaBusca(GenericVO item) {
		return null;
	}

}