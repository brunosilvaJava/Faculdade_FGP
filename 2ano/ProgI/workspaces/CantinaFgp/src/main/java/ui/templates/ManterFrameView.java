package ui.templates;

import interfaces.ITelaManter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.GenericVO;
import enumeradores.TipoSolicitacao;

/**
 * Tela de manter padrão. As demais telas de manter devem herdar esta classe. As
 * telas de manter são utilizadas para detalhar e alterar um item do tipo
 * passado por Generic Type.
 * 
 * @author bruno.silva
 * 
 * @param <T>
 *            - Deve ser passado como generic type um objeto do tipo GenericVO.
 *            Este objeto será utilizado para definir o tipo dos parâmetros dos
 *            métodos da tela de manter.
 * 
 */
public abstract class ManterFrameView<T extends GenericVO> extends JFrame implements ITelaManter<T> {

	// Atributos da Janela

	private JPanel pnlCabecalho;
	private JPanel pnlCentro;
	private JPanel pnlRodape;
	protected JButton btnGravar;
	protected JButton btnAlterar;
	protected JButton btnLimpar;
	protected JButton btnCancelar;
	private JLabel lblTituloCabecalho;
	private Font fonteCabecalho;
	private StringBuilder msgErro;

	private ActionListener acaoBtnGravar;
	
	
	// Construtores

	protected ManterFrameView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		
		definicoesPagina(tituloCabecalho);
		
		definirAcaoGravar(solicitacao);
		
	}

	// Métodos concretos

	private void definirAcaoGravar(TipoSolicitacao solicitacao) {
		
		btnGravar.removeActionListener(acaoBtnGravar);
		
		if (solicitacao.equals(TipoSolicitacao.DETALHAR)) {

			btnLimpar.setEnabled(false);
			btnGravar.setEnabled(false);
			acaoGravarAlteraracao();
			
		} else {
			if (solicitacao.equals(TipoSolicitacao.INCLUIR)) {

				btnAlterar.setVisible(false);
				acaoGravarInclusao();
				
			}
		}

	}

	private void acaoGravarAlteraracao() {
		
		btnGravar.removeActionListener(acaoBtnGravar);

		acaoBtnGravar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				msgErro = new StringBuilder();
				
				if(isCamposValidos(msgErro)){
					if (alterar(msgErro)) {
						if(desabilitarCampos()){
							btnAlterar.setEnabled(true);
							btnLimpar.setEnabled(false);
							btnGravar.setEnabled(false);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, msgErro, "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, msgErro, "Erro", JOptionPane.ERROR_MESSAGE);
				}
				msgErro = new StringBuilder();
			}
		};		

		btnGravar.addActionListener(acaoBtnGravar);

	}

	private void acaoGravarInclusao() {
		
		btnGravar.removeActionListener(acaoBtnGravar);

		acaoBtnGravar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				msgErro = new StringBuilder();

				if(isCamposValidos(msgErro)){
					if (incluir(msgErro)) {
						if(desabilitarCampos()){
							acaoGravarAlteraracao();
							btnAlterar.setVisible(true);
							btnLimpar.setEnabled(false);
							btnGravar.setEnabled(false);							
						}
					}
					else{
						JOptionPane.showMessageDialog(null, msgErro, "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, msgErro, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		btnGravar.addActionListener(acaoBtnGravar);

	}

	private void definicoesPagina(String tituloCabecalho) {

		lblTituloCabecalho = new JLabel();
		lblTituloCabecalho.setText(tituloCabecalho);
		lblTituloCabecalho.setForeground(Color.WHITE);
		fonteCabecalho = new Font("Verdana", Font.BOLD, 20);
		lblTituloCabecalho.setFont(fonteCabecalho);

		pnlCabecalho = new JPanel();
		pnlCabecalho.add(lblTituloCabecalho);
		pnlCabecalho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlCabecalho.setBackground(Color.BLACK);

		pnlCentro = new JPanel();
		pnlCentro.setBackground(Color.GRAY);
		pnlCentro.setLayout(null);

		pnlRodape = new JPanel();
		pnlRodape.setBackground(Color.WHITE);

		// BOTÕES

		btnGravar = new JButton("Gravar");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ManterFrameView.this.limparCampos();

			}

		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ManterFrameView.this.dispose();

			}
		});

		pnlRodape.add(btnGravar);
		pnlRodape.add(btnLimpar);
		pnlRodape.add(btnCancelar);

		btnAlterar = new JButton("Alterar");

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(habilitarCampos()){
					
					btnAlterar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnGravar.setEnabled(true);
					
				}
				

			}
		});

		pnlRodape.add(btnAlterar);

		// Definições página

		this.setLayout(new BorderLayout());
		this.add(pnlCabecalho, BorderLayout.NORTH);
		this.add(pnlRodape, BorderLayout.SOUTH);
		this.add(pnlCentro, BorderLayout.CENTER);
		this.setResizable(false);
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);

	}

	protected void adicionarComponentesCentro(JComponent comp) {

		pnlCentro.add(comp);

	}

	// métodos abstratos

	protected abstract boolean habilitarCampos();
	protected abstract boolean desabilitarCampos();

	protected abstract void limparCampos();

}
