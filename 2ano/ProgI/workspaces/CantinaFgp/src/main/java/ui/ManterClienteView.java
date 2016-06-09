package ui;

import javax.swing.JOptionPane;

import ui.templates.ManterFrameView;
import vo.ClienteVO;
import enumeradores.TipoSolicitacao;

public class ManterClienteView extends ManterFrameView<ClienteVO> {


	protected ManterClienteView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(ClienteVO objeto) {
	
		this.setVisible(true);
		
	}	
	
	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Cliente incluído");
		return false;
	}

	@Override
	public boolean alterar(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Cliente alterado");		
		return true;
	}
	
	@Override
	public boolean isCamposValidos(StringBuilder msgErro) {
		return false;
	}

	@Override
	protected void limparCampos() {
		
		
	}

	@Override
	protected boolean habilitarCampos() {
		return false;
	}

	@Override
	protected boolean desabilitarCampos() {
		return false;
	}
	
}
