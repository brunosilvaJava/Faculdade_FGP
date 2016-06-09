package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.UtilFuncoes;
import vo.UsuarioVO;
import bo.LoginBO;

public class LoginView extends JFrame {//JFrame é uma classe para janela
	
	// os atributos variáveis de referência são private para que o usuário não altere seus valores
	private JPanel painelCabecalho;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private Font fonteCentro;
	
	private JPanel painelCentro;
	private JPanel painelRodape;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JTextField tfUsuario;
	private JPasswordField psfCampoSenha;
	private JButton btnEntrar;
	private JButton btnSair;

	private LoginBO loginBo;

	private static final String DIGITE_LOGIN = "Digite seu login";
	
	public void abrirJanela() throws ParseException{
		
		loginBo = new LoginBO();
			
		// Cabeçalho
			
			painelCabecalho = new JPanel();
			lblTituloCabecalho = new JLabel();
			fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
			
			lblTituloCabecalho.setText("Cantina FGP");
			lblTituloCabecalho.setFont(fonteCabecalho);
			lblTituloCabecalho.setForeground(Color.WHITE);	
			
			// Definições do Cabeçalho
			
			painelCabecalho.add(lblTituloCabecalho);
			painelCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // cor para a borda
			painelCabecalho.setBackground(Color.decode("#000")); // cor de background
		
			
		// Painel Centro
			
			fonteCentro = new Font(Font.DIALOG, Font.ITALIC, 12);
						
			lblUsuario = new JLabel();
			tfUsuario = new JTextField();
					
			lblUsuario.setText("Usuário");
			lblUsuario.setBounds(new Rectangle(100, 35, 50, 30));
			lblUsuario.setForeground(Color.BLUE);
			lblUsuario.setFont(fonteCentro);
			
			tfUsuario.setText(DIGITE_LOGIN);
			tfUsuario.setBounds(new Rectangle(160, 35, 100, 30));
			tfUsuario.setForeground(Color.BLUE);
			
			tfUsuario.addFocusListener(new FocusListener() {
				
				// perdeu foco
				@Override
				public void focusLost(FocusEvent evento) {
					
					if(UtilFuncoes.isCampoVazio(tfUsuario.getText())){ 
						
						tfUsuario.setText(DIGITE_LOGIN);
						
					}
					else{
						String digitado = tfUsuario.getText();

						if(digitado.length() > 20){

							tfUsuario.setText(digitado.substring(0,20));
							
						}
					}
					
				}
				
				// ganhou foco
				@Override
				public void focusGained(FocusEvent evento) {
										
					if(tfUsuario.getText().equalsIgnoreCase(DIGITE_LOGIN)){
						
						tfUsuario.setText("");
						
					}
					
				}
				
			});
			
			lblSenha = new JLabel();
			psfCampoSenha = new JPasswordField();
			
			lblSenha.setText("Senha");
			lblSenha.setBounds(new Rectangle(100, 80, 50, 30));
			lblSenha.setFont(fonteCentro);
			
			psfCampoSenha.setBounds(new Rectangle(160, 80, 100, 30));
			
			psfCampoSenha.addKeyListener(new KeyAdapter() { 
				
				@Override
				public void keyTyped(KeyEvent evento) {
					
					if(!UtilFuncoes.isNumOuLetra(String.valueOf(evento.getKeyChar()))){
						evento.consume();
					}

				}
				
			});
			
			
			psfCampoSenha.addFocusListener(new FocusAdapter() {
				
				@Override
				public void focusLost(FocusEvent e) {
					
					String digitada = psfCampoSenha.getText();
					
					if(digitada != null && digitada.length() > 10){
						
						psfCampoSenha.setText(digitada.substring(0,10));
						
					}
					
				}
				
			});

			btnEntrar = new JButton();
			
			btnEntrar.setText("Entrar");
			btnEntrar.setBounds(new Rectangle(190, 130, 80, 30));
			btnEntrar.setFont(fonteCentro);
			
			btnEntrar.addActionListener(new ActionListener() {		
					
				@Override
				public void actionPerformed(ActionEvent e) {
					
					UsuarioVO usuario = new UsuarioVO();
					usuario.setLogin(tfUsuario.getText());
					usuario.setSenha(new String(psfCampoSenha.getPassword()));
					
					UsuarioVO usuarioLogado = loginBo.logarUsuario(usuario);
					
					if(usuarioLogado != null){
						
						UtilFuncoes.usuarioLogado = usuarioLogado;
						
						PrincipalView home = new PrincipalView();
						home.abrirJanela();
						LoginView.this.dispose();
						
					}
					else{
						
						JOptionPane.showMessageDialog(null, "Login e(ou) senha inválidos");
						
					}
				}
				
			});
			
						
			// botão de sair
			
			btnSair = new JButton();
			
			btnSair.setText("Sair");
			btnSair.setBounds(new Rectangle(90, 130, 80, 30));
			btnSair.setFont(fonteCentro);
			
			btnSair.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					fecharLogin();
					
				}
				
			});
			
					
			
			// Painel Centro
			
			painelCentro = new JPanel();
			
			painelCentro.add(btnEntrar);
			painelCentro.add(btnSair);
			painelCentro.add(lblUsuario);
			painelCentro.add(tfUsuario);
			painelCentro.add(lblSenha);
			painelCentro.add(psfCampoSenha);
			painelCentro.setLayout(null); // o posicionamento dos componentes dentro dele fica livre
			

//--------------------------
//--------------------------
			
		// Rodapé
			
			painelRodape = new JPanel();
			
			
		// Definições do JFrame
			
			this.addWindowListener(new WindowAdapter() {
				
				@Override
				public void windowClosing(WindowEvent e) {
					
					fecharLogin();
					
				}
				
			});
			
			this.setLayout(new BorderLayout());
			this.add(painelCabecalho, BorderLayout.NORTH);
			this.add(painelCentro, BorderLayout.CENTER);
			this.add(painelRodape, BorderLayout.SOUTH);
			
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.setSize(new Dimension(380, 280));
			this.setTitle("Login do Sistema");
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		
	}
	
	private void fecharLogin(){
		
		int opcao = JOptionPane.showOptionDialog(null, "Deseja realmente sair?", "SAIR DO SISTEMA", 
				JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, null, null);
		
		if(opcao == JOptionPane.YES_OPTION){

			LoginView.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			
		}
		
	}
	
}




