package ui.templates;

import interfaces.ITelaConsultar;
import interfaces.ITelaManter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.DialogOpcoesView;
import vo.GenericVO;


/**
 * Tela de consulta padrão. As demais telas de consulta devem herdar esta classe.
 * As telas de consulta são utilizadas para pesquisar e excluir itens do tipo passado por Generic Type.
 * 
 * @author bruno.silva
 *
 * @param <T> - Deve ser passado como generic type um objeto do tipo GenericVO. Este objeto será utilizado para 
 * definir o tipo dos parâmetros dos métodos da tela de consulta.
 */
public abstract class ConsultarPanelView<T extends GenericVO> extends JPanel implements ITelaConsultar<T>{
	
	
	// Atributos da Janela
	
	private JPanel pnlCabecalho;
	private JPanel pnlCentro;

	private JPanel pnlMenuLateral;
	private JPanel pnlRodape;

	private JTable tabGeneric;
	private DefaultTableModel modeloTabGeneric;
	private JScrollPane barraTabGeneric;

	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private JButton btnFechar;
	private JButton btnNovo;
	private JButton btnConsultar;
	
	// Lista de itens do tipo T para alimentar a tabela de consulta
	private List<T> listaGenericos;
	
	
		// Construtores
	
	/**
	 * @param tituloCabecalho - Título da jabela de consulta
	 * @param titulosTab - Títulos das colunas da tabela de consulta
	 */
	public ConsultarPanelView(String tituloCabecalho, String[] titulosTab) {
		
		criarPainel(tituloCabecalho, null, titulosTab, 10, 190, 665, 190);
		
	}
	
	/**
	 * @param tituloCabecalho - Título da jabela de consulta
	 * @param titulosTab - Títulos das colunas da tabela de consulta
	 * @param listaGenericos - Lista de itens do tipo definido no Generic Type que serão carregados na tabela
	 */
	public ConsultarPanelView(String tituloCabecalho, String[] titulosTab, List<T> listaGenericos) {
		
		criarPainel(tituloCabecalho, listaGenericos, titulosTab, 10, 190, 665, 190);
		
	}
	
	/**
	 * @param tituloCabecalho - Título da jabela de consulta
	 * @param titulosTab - Títulos das colunas da tabela de consulta
	 * @param listaGenericos - Lista de itens do tipo definido no Generic Type que serão carregados na tabela
	 * @param espX - Distância da lateral esquerda da tabela de consulta
	 * @param espY - Distância do topo da tabela de consulta
	 * @param larg - Largura da tabela de consulta
	 * @param alt - Altura da tabela de consulta
	 */
	public ConsultarPanelView(String tituloCabecalho, String[] titulosTab, List<T> listaGenericos, int espX, int espY, int larg, int alt) {
		
		criarPainel(tituloCabecalho, listaGenericos, titulosTab, espX, espY, larg, alt);
		
	}
	
	/**
	 * @param tituloCabecalho - Título da jabela de consulta
	 * @param titulosTab - Títulos das colunas da tabela de consulta
	 * @param espX - Distância da lateral esquerda da tabela de consulta
	 * @param espY - Distância do topo da tabela de consulta
	 * @param larg - Largura da tabela de consulta
	 * @param alt - Altura da tabela de consulta
	 */
	public ConsultarPanelView(String tituloCabecalho, String[] titulosTab, int espX, int espY, int larg, int alt) {
		
		criarPainel(tituloCabecalho, listaGenericos, titulosTab, espX, espY, larg, alt);
		
	}
	
	
	// Métodos Concretos
	
	// Método utilizado para criar o panel de consulta
	private void criarPainel(String tituloCabecalho, List<T> listaGenericos, String[] titulosTab,  int espX, int espY, int larg, int alt){
		
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
		
		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText(tituloCabecalho);
		lblTituloCabecalho.setForeground(Color.WHITE);	
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);
		pnlCabecalho.add(lblTituloCabecalho, BorderLayout.CENTER);

		setListaGenericos(listaGenericos);
		
		
		// Tabela de consulta
		
		tabGeneric = new JTable();
		tabGeneric.getTableHeader().setReorderingAllowed(false);
		
		modeloTabGeneric = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) { // faz com que os itens da grid não sejam editados
				return false;
			}
			
		};
		
		barraTabGeneric = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Insere títulos passados no construtor pela classe filha
		modeloTabGeneric.setColumnIdentifiers(titulosTab);
		
		tabGeneric.setModel(modeloTabGeneric);
		tabGeneric.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		barraTabGeneric.setViewportView(tabGeneric);
		
		// Defini posição e tamanho passados no construtor
		barraTabGeneric.setBounds(espX, espY, larg, alt);
		
		pnlCentro.add(barraTabGeneric);
		
		// Carrega tabela com a lista de itens através da lista passada no construtor pela classe filha 
		carregarGridItens(listaGenericos);
		
		// Defini ação ao clicar em um item da coluna
		mouseClickedTab();
		
		
		// BOTÕES
		
		btnFechar = new JButton("X");
		btnFechar.setBackground(Color.RED);
		btnFechar.setForeground(Color.WHITE);	
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ConsultarPanelView.this.setVisible(false);
				ConsultarPanelView.this.getParent().removeAll();
				
			}
			
		});
				
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ConsultarPanelView.this.getTelaIncluir().abrirJanela(); // abre tela incluir retornada pela classe filha através do método getTelaIncluir
				
			}
			
		});

		pnlMenuLateral.add(btnNovo);
		pnlCabecalho.add(btnFechar, BorderLayout.EAST);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setListaGenericos(consultar());
				carregarGridItens(getListaGenericos());
				
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
	
	
	// Métodos
	
	protected void adicionarComponenteCentro(JComponent comp){
		
		pnlCentro.add(comp);
		
	}
	
	/**
	 * Método utilizado para filha adicionar botão no menu lateral da tela
	 * 
	 * @param JButton - Botão que deverá ser inserido
	 */
	protected void adicionarBotao(JButton botao){
		pnlMenuLateral.add(botao);
	}
	
	/**
	 * Método para definir ação dar duplo clique em um item da tabela, método chamado por callback
	 */
	protected void mouseClickedTab() {
		
		tabGeneric.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) { 
					if(getTabGeneric().getSelectedRow() != -1){ // acerto ref. clique com botão direito
						
						// Traz o item do tipo T selecionado pelo usuário na tabela
						T item = ConsultarPanelView.this.getListaGenericos().get(getTabGeneric().getSelectedRow());	
																
						// Envia para o método abrirJanela da Dialog de opções o item selecionado, 
						// a janela de consulta solicitante e a telaAlterar (Manter) que deverá ser chamada caso o usuário clique em 'Detalhar'
						new DialogOpcoesView<T>().abrirJanela(item, ConsultarPanelView.this, getTelaAlterar());

					}
				}
				
			}
		});
	}
	
	/**
	 * Insere na tabela os itens passados como parâmetro. 
	 * 
	 * @param listaItens - Lista de itens do tipo T
	 */
	@Override
	public void carregarGridItens(List<T> listaItens) {

		getModeloTabGeneric().setNumRows(0); // funciona para zerar o q tinha antes
		
		if(listaItens != null){
			
			Iterator<T> iItem = listaItens.iterator();
			
			while(iItem.hasNext()){
				
				T item = iItem.next();
				
				// Insere na tabela o item retornado do método abstrato carregarGridItens, implementado na classe filha 
				getModeloTabGeneric().addRow(definirGridItens(item));	
				
			}
		}
		
	}	
	
	
	// Métodos abstratos
	
	/**
	 * Insere item do tipo T na tabela de consulta.
	 * Ao implementar este método, deve ser feita a lógica para inserir os valores dos atributos do tipo T 
	 * que devem ser apresentados na tabela de consulta.
	 * 
	 * @param item - Item do tipo T que deverá ser inserido na tabela
	 * @return String[] - Deve ser retornado um vetor de String com os valores na ordem que deverão ser inseridos na tabela de consulta
	 */
	protected abstract String[] definirGridItens(T item);
		
	/**
	 * Ao implementar este método, deve ser feita uma lógica para retornar uma tela de incluir um item do tipo T
	 * @return ITelaManter<T> - Retorna uma tela do tipo ITelaManter
	 */
	protected abstract ITelaManter<T> getTelaIncluir();

	/**
	 * Ao implementar este método, deve ser feita uma lógica para retornar uma tela de alterar um item do tipo T
	 * @return ITelaManter<T> - Retorna uma tela do tipo ITelaManter
	 */
	protected abstract ITelaManter<T> getTelaAlterar();
	
	
	// getters and setters
		
	protected JTable getTabGeneric() {
		return tabGeneric;
	}

	protected DefaultTableModel getModeloTabGeneric() {
		return modeloTabGeneric;
	}

	protected List<T> getListaGenericos() {
		return listaGenericos;
	}

	protected void setListaGenericos(List<T> listaGenericos) {
		this.listaGenericos = listaGenericos;
	}
	
}
