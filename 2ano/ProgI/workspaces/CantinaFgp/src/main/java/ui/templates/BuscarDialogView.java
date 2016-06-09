package ui.templates;

import interfaces.ITelaBuscar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.GenericVO;

public class BuscarDialogView extends JDialog{
	
	// Atributos da Janela

	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;
	
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	
	private JTable tabItens;
	private DefaultTableModel modeloTabItens;
	private JScrollPane barraTabItens;
	private JLabel lblNomeItem;
	private JLabel lblCodItem;
	
	private String[] titulosTab;
	
	private JTextField txtNomeItem;
	private JTextField txtCodItem;
	
	private JButton btnPesquisar;
	
	private List<GenericVO> listaItens;
		
	private ITelaBuscar telaBuscar;

	public static final String CODIGO = "Código";
	public static final String NOME = "Nome";
	
	
	// Bloco de Inicialização
	
	{
		pnlCentro = new JPanel();
		pnlCabecalho = new JPanel();
		pnlRodape = new JPanel();
		
		tabItens = new JTable();
		lblNomeItem = new JLabel();
		lblCodItem = new JLabel();
		txtNomeItem = new JTextField();
		txtCodItem = new JTextField();
		btnPesquisar = new JButton();
		
		lblTituloCabecalho = new JLabel();
		
		modeloTabItens = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {return false;}
		};
		barraTabItens = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	
	// Construtores
	
	public BuscarDialogView(ITelaBuscar telaBuscar, String[] titulosTab) {
		this.telaBuscar = telaBuscar;
		this.titulosTab = titulosTab;
	}
		
	
	// Métodos
	
	public void abrirJanela(){
				
		definicoesPagina();

		btnPesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Map<String, String> parametros = new HashMap<String, String>();

				parametros.put(CODIGO, txtCodItem.getText());
				parametros.put(NOME, txtNomeItem.getText());
				
				listaItens = telaBuscar.buscarItemPorCodigoENome(parametros);
				
				carregarGridItens(listaItens);
				
			} 
		});
		
		tabItens.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(tabItens.getSelectedRow() != -1 && e.getClickCount()==2){

					telaBuscar.carregarItemSelecionado(listaItens.get(tabItens.getSelectedRow()));
					BuscarDialogView.this.dispose();
					
				}
				
			}
		});
		
		setVisible(true);
		
	}

	private void carregarGridItens(List<GenericVO> listaItens) {
		
		modeloTabItens.setNumRows(0);
		
		if(listaItens != null){
			
			for (GenericVO item : listaItens) {
				modeloTabItens.addRow(telaBuscar.carregarGridTelaBusca(item));
			}

		}
		
	}
	
	
	public void definicoesPagina(){

		// Cabeçalho
		
		pnlCabecalho.add(lblTituloCabecalho);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setBackground(Color.BLACK);
		
		lblTituloCabecalho.setText("Buscar");
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		
		// Centro
		
		pnlCentro.setBackground(Color.GRAY);
		pnlCentro.setLayout(null);

		lblCodItem.setText(CODIGO);
		lblNomeItem.setText(NOME);
		
		int espXLbl = 20;
		int espXTxt = espXLbl + 90;
		int espY = 20;
		int espEntre = 35;	
		int altura = 30;
		
		lblCodItem.setBounds(espXLbl, espY, 50, altura);
		lblNomeItem.setBounds(espXLbl, espY + espEntre, 50, altura);
		
		txtCodItem.setBounds(espXTxt, espY, 50, altura);
		txtNomeItem.setBounds(espXTxt, espY + espEntre, 150, altura);
		
		barraTabItens.setBounds(10, 100, 370, 185);
		
		// TABELA
		

		modeloTabItens.setColumnIdentifiers(titulosTab);
		tabItens.setModel(modeloTabItens);
		barraTabItens.setViewportView(tabItens);

		pnlCentro.setBackground(Color.LIGHT_GRAY);
		pnlCentro.add(barraTabItens);
		pnlCentro.add(lblNomeItem);
		pnlCentro.add(txtNomeItem);
		pnlCentro.add(lblCodItem);
		pnlCentro.add(txtCodItem);
		
		// Rodapé
		
		btnPesquisar.setText("Pesquisar");
		
		pnlRodape.setBackground(Color.WHITE);
		pnlRodape.add(btnPesquisar);
		
		this.setLayout(new BorderLayout());		
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlRodape, BorderLayout.SOUTH);		
		this.add(pnlCentro, BorderLayout.CENTER);
		this.setResizable(false);
		this.setSize(400, 400);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
	}

}
