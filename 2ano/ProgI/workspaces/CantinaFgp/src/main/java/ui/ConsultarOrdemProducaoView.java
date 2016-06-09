package ui;

import interfaces.ITelaManter;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import ui.templates.ConsultarPanelView;
import vo.OrdemProducaoVO;
import vo.StatusVO;
import bo.OrdemProducaoBO;
import enumeradores.TipoSolicitacao;

public class ConsultarOrdemProducaoView extends ConsultarPanelView<OrdemProducaoVO> {

	// Atributos tela

	private JLabel lblFiltro;
	private JLabel lblCodOrdemProd;
	private JLabel lblCodProd;
	private JLabel lblProd;
	private JLabel lblStatus;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	
	private JTextField txtCodOrdemProd;
	private JTextField txtCodProd;
	private JTextField txtProd;
	
	private JComboBox<StatusVO> cbxStatus;

	private JXDatePicker dtpDataInicial;
	private JXDatePicker dtpDataFinal;
	
	private OrdemProducaoBO ordemProdBo;
	
	// Bloco de inicialização
	
	{

		lblFiltro = new JLabel("FILTRAR");
		
		lblCodOrdemProd = new JLabel("Código");
		lblCodProd = new JLabel("Código Produto");
		lblProd = new JLabel("Produto");
		lblStatus = new JLabel("Status");
		lblDataInicial = new JLabel("Data Inicial");
		lblDataFinal = new JLabel("Data Final");

		txtCodOrdemProd = new JTextField();
		txtCodProd = new JTextField();
		txtProd = new JTextField();
		
		cbxStatus = new JComboBox<StatusVO>();
		
		dtpDataFinal = new JXDatePicker();
		dtpDataInicial = new JXDatePicker();
		
		int espXLbl = 20;
		int	espXLbl2 = 400;
		int espXTxt = espXLbl + 110;
		int espXTxt2 = espXLbl2 + 80;
		int espY = 20;
		int espEntre = 35;
		int altura = 30;
		
		lblFiltro.setBounds(espXLbl, espY, 50, altura);
		lblCodOrdemProd.setBounds(espXLbl, espY + espEntre, 50, altura);
		lblCodProd.setBounds(espXLbl, espY + espEntre*2, 100, altura);
		lblProd.setBounds(espXLbl, espY + espEntre*3, 50, altura);

		txtCodOrdemProd.setBounds(espXTxt, espY + espEntre, 50, altura);
		txtCodProd.setBounds(espXTxt, espY + espEntre*2, 50, altura);
		txtProd.setBounds(espXTxt, espY + espEntre*3, 230, altura);

		lblStatus.setBounds(espXLbl2, espY + espEntre, 50, altura);
		lblDataInicial.setBounds(espXLbl2, espY + espEntre*2, 100, altura);
		lblDataFinal.setBounds(espXLbl2, espY + espEntre*3, 100, altura);

		cbxStatus.setBounds(espXTxt2, espY + espEntre, 140, altura);
		dtpDataInicial.setBounds(espXTxt2, espY + espEntre*2, 140, altura);
		dtpDataFinal.setBounds(espXTxt2, espY + espEntre*3, 140, altura);
		
		adicionarComponenteCentro(lblFiltro);
		adicionarComponenteCentro(lblCodOrdemProd);
		adicionarComponenteCentro(lblCodProd);
		adicionarComponenteCentro(lblProd);
		adicionarComponenteCentro(txtCodOrdemProd);
		adicionarComponenteCentro(txtCodProd);
		adicionarComponenteCentro(txtProd);
		
		adicionarComponenteCentro(lblStatus);
		adicionarComponenteCentro(lblDataInicial);
		adicionarComponenteCentro(lblDataFinal);
		adicionarComponenteCentro(cbxStatus);
		adicionarComponenteCentro(dtpDataInicial);
		adicionarComponenteCentro(dtpDataFinal);
		
		ordemProdBo = new OrdemProducaoBO();
		
	}
	
	
	// Construtores
	
	public ConsultarOrdemProducaoView() {
		super("Ordem de Produção", new String[]{ "Código", "Data", "Produto", "Qtde", "Status"});
	}
	
	// Métodos
		
	@Override
	protected String[] definirGridItens(OrdemProducaoVO ordemProducao) {
			
		String[] registro = new String[5];

		registro[0] = ordemProducao.getCodOrdemProducao();
		registro[1] = ordemProducao.getData().toString();
		registro[2] = ordemProducao.getProdutoVenda().getDescricao();
		registro[3] = ordemProducao.getQtde().toString();
		registro[4] = ordemProducao.getStatus().getDescricao();
		
		return registro;
		
	}	
	
	@Override
	protected ITelaManter<OrdemProducaoVO> getTelaIncluir() {
		return new ManterOrdemProducaoView(TipoSolicitacao.INCLUIR, "Cadastrar Ordem de Produção");
	}

	@Override
	protected ITelaManter<OrdemProducaoVO> getTelaAlterar() {
		return new ManterOrdemProducaoView(TipoSolicitacao.DETALHAR, "Detalhar Ordem de Produção");
	}

	@Override
	public void deletar(OrdemProducaoVO ordemProducao) {

		if(ordemProdBo.deletar(ordemProducao)){
			JOptionPane.showMessageDialog(null, "Ordem de Produção excluída");
		}
		
	}

	@Override
	public List<OrdemProducaoVO> consultar() {
		
		return ordemProdBo.consultar();
	
	}
	
}
