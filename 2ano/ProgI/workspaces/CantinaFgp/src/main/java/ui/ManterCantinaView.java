package ui;

import javax.swing.JOptionPane;

import ui.templates.ManterFrameView;
import vo.CantinaVO;
import enumeradores.TipoSolicitacao;

public class ManterCantinaView extends ManterFrameView<CantinaVO> {
	
	protected ManterCantinaView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}
	
	@Override
	public void abrirJanela(CantinaVO cantina) {

		this.setVisible(true);
		
	}

	@Override
	public boolean incluir(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Cantina incluída");
		return false;
	}

	@Override
	public boolean alterar(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Cantina alterada");		
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
