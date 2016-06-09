package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import enumeradores.TipoSolicitacao;

public class PrincipalView extends TelaView implements ComponentListener{
	
	private JMenuBar barraMenu;
	private JMenu menuConsulta;
	private JMenuItem subConsultaVendas;
	private JMenuItem subConsultaCompras;
	private JMenuItem subConsultaProdutos;
	private JMenuItem subConsultaClientes;
	private JMenuItem subConsultaFuncionarios;
	private JMenuItem subConsultaCantinas;
	private JMenu menuCadastrar;
	private JMenuItem subCadastrarVenda;
	private JMenuItem subCadastrarCompra;
	private JMenuItem subCadastrarProduto;
	private JMenuItem subCadastrarCliente;
	private JMenuItem subCadastrarFuncionario;
	private JMenuItem subCadastrarCantina;
	private JMenu menuOrdemProducao;
	private JMenuItem subConsultarOrdemProducao;
	private JMenuItem subCadastrarOrdemProducao;
	private JMenu menuEstoque;
	private JMenuItem subConsultarEstoque;
	
	private JPanel panelCentro;

	// BLOCO DE INICIALIZAÇÃO
	{
		
		abrirJanela();
		
	}


	// MÉTODOS
	
	
	public void abrirJanela() {
		
		barraMenu = new JMenuBar();
		
		menuConsulta = new JMenu();
		
		subConsultaVendas = new JMenuItem();
		subConsultaVendas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		
		subConsultaCompras = new JMenuItem();
		subConsultaCompras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		
		subConsultaProdutos = new JMenuItem();
		subConsultaProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
				
		subConsultaClientes = new JMenuItem();
		subConsultaClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		
		subConsultaFuncionarios = new JMenuItem();
		subConsultaFuncionarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
		
		subConsultaCantinas = new JMenuItem();
		subConsultaCantinas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		
		menuCadastrar = new JMenu();
		
		subCadastrarVenda = new JMenuItem();
		subCadastrarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		
		subCadastrarCompra = new JMenuItem();
		subCadastrarCompra.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		subCadastrarProduto = new JMenuItem();
		subCadastrarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
				
		subCadastrarCliente = new JMenuItem();
		subCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		
		subCadastrarFuncionario = new JMenuItem();
		subCadastrarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		
		subCadastrarCantina = new JMenuItem();
		subCadastrarCantina.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		menuOrdemProducao = new JMenu();
		
		subConsultarOrdemProducao = new JMenuItem();
		subConsultarOrdemProducao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
		
		subCadastrarOrdemProducao = new JMenuItem();
		subCadastrarOrdemProducao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		menuEstoque = new JMenu();
		
		subConsultarEstoque = new JMenuItem();
		subConsultarEstoque.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
		
		atribuirTextos();
		
		menuConsulta.add(subConsultaCantinas);
		menuConsulta.add(subConsultaVendas);
		menuConsulta.add(subConsultaCompras);
		menuConsulta.add(subConsultaProdutos);
		menuConsulta.add(subConsultaClientes);
		menuConsulta.add(subConsultaFuncionarios);
		menuConsulta.add(subConsultaCantinas);
		
		menuCadastrar.add(subCadastrarVenda);
		menuCadastrar.add(subCadastrarCompra);
		menuCadastrar.add(subCadastrarProduto);
		menuCadastrar.add(subCadastrarCliente);
		menuCadastrar.add(subCadastrarFuncionario);
		menuCadastrar.add(subCadastrarCantina);

		menuOrdemProducao.add(subConsultarOrdemProducao);
		menuOrdemProducao.add(subCadastrarOrdemProducao);

		menuEstoque.add(subConsultarEstoque);

		barraMenu.add(menuConsulta);
		barraMenu.add(menuCadastrar);
		barraMenu.add(menuOrdemProducao);
		barraMenu.add(menuEstoque);
		
		this.definicoesPagina();
		this.acoesBotoes();
		
	}
	
	private void acoesBotoes(){
		
		// Consultar 
		
		subConsultaProdutos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				panelCentro = new ConsultarProdutoView();
				
				abrirPanelConsulta(panelCentro);
				
			}
			
			
		});
		
		subConsultaVendas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarVendaView();
				
				abrirPanelConsulta(panelCentro);
				
			}
		});
		
		subConsultaCompras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarCompraView();
				
				abrirPanelConsulta(panelCentro);
				
			}
		});
		
		subConsultaClientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarClienteView();
				
				abrirPanelConsulta(panelCentro);
				
			}
		});
		
		subConsultaFuncionarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarFuncionarioView();
				
				abrirPanelConsulta(panelCentro);
				
			}
		});
		
		subConsultaCantinas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarCantinaView();
				
				abrirPanelConsulta(panelCentro);
				
			}
		});
		
		subConsultarOrdemProducao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarOrdemProducaoView();
				
				abrirPanelConsulta(panelCentro);
					
			}
		});
		
		subConsultarOrdemProducao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarOrdemProducaoView();
				
				abrirPanelConsulta(panelCentro);
					
			}
		});
		
		subConsultarEstoque.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				panelCentro = new ConsultarEstoqueView();
				
				abrirPanelConsulta(panelCentro);
					
			}
		});
		
		// Cadastrar
		
		subCadastrarOrdemProducao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterOrdemProducaoView(TipoSolicitacao.INCLUIR, "Cadastrar Ordem de Produção").abrirJanela();;
				
			}
		});
		
		subCadastrarProduto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterProdutoView(TipoSolicitacao.INCLUIR, "Cadastrar Produto").abrirJanela();
				
			}
		});
		
		subCadastrarCompra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterCompraView(TipoSolicitacao.INCLUIR, "Cadastrar Compra").abrirJanela();
				
			}
		});
		
		subCadastrarVenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterVendaView(TipoSolicitacao.INCLUIR, "Cadastrar Venda").abrirJanela();;
				
			}
		});
		
		subCadastrarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterClienteView(TipoSolicitacao.INCLUIR, "Cadastrar Cliente").abrirJanela();;
				
			}
		});
		
		subCadastrarFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterFuncionarioView(TipoSolicitacao.INCLUIR, "Cadastrar Funcionário").abrirJanela();;
				
			}
		});
		
		subCadastrarCantina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new ManterCantinaView(TipoSolicitacao.INCLUIR, "Cadastrar Cantina").abrirJanela();;
				
			}
		});
		
	}		

	private void abrirPanelConsulta(JPanel panelConsultar){

		this.getContentPane().setVisible(false);
		this.getContentPane().setVisible(true);
		
		this.getContentPane().removeAll();
		
		this.centralizarPanel(panelConsultar);
		
		this.getContentPane().add(panelConsultar);
		
		this.validate();
		
	}
	
	private void centralizarPanel(JPanel panel){
		
        int pontoLargura = ((getWidth() / 2) - (panel.getWidth() / 2) - 10); 
                
        panel.setLocation(pontoLargura, 0);  
        
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
		if(panelCentro != null){

			this.centralizarPanel(panelCentro);  
				
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}
	
	@Override
	protected void atribuirTextos() {

		menuConsulta.setText("Consultar");
		subConsultaVendas.setText("Vendas");
		subConsultaCompras.setText("Compras");
		subConsultaProdutos.setText("Produtos");
		subConsultaClientes.setText("Clientes");
		subConsultaFuncionarios.setText("Funcionários");
		subConsultaCantinas.setText("Cantinas");
		menuCadastrar.setText("Cadastrar");
		subCadastrarVenda.setText("Venda");
		subCadastrarCompra.setText("Compra");
		subCadastrarProduto.setText("Produto");
		subCadastrarCliente.setText("Cliente");
		subCadastrarFuncionario.setText("Funcionário");
		subCadastrarCantina.setText("Cantina");
		menuOrdemProducao.setText("Ordem de Produção");
		subConsultarOrdemProducao.setText("Consultar");
		subCadastrarOrdemProducao.setText("Novo");
		menuEstoque.setText("Estoque");
		subConsultarEstoque.setText("Consultar");
		
	}

	@Override
	protected void definicoesPagina() {

		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(barraMenu);
		this.setTitle("Cantina FGP");
		this.setExtendedState(PrincipalView.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800, 600));
		this.addComponentListener(this); 
		this.setVisible(true);
		
	}
	
	
}
