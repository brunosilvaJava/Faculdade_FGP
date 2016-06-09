package ui;

import javax.swing.JOptionPane;

import ui.templates.ManterFrameView;
import vo.FuncionarioVO;
import enumeradores.TipoSolicitacao;

public class ManterFuncionarioView extends ManterFrameView<FuncionarioVO> {

	protected ManterFuncionarioView(TipoSolicitacao solicitacao, String tituloCabecalho) {
		super(solicitacao, tituloCabecalho);
	}

	@Override
	public void abrirJanela(FuncionarioVO objeto) {

		this.setVisible(true);
		
	}

	@Override
	public void abrirJanela() {
		
		this.setVisible(true);
		
	}

	@Override
	public boolean incluir(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Funcionario Incluído");
		return true;
	}

	@Override
	public boolean alterar(StringBuilder msgErro) {
		JOptionPane.showMessageDialog(null, "Funcionario Alterado");	
		return true;
	}
	
	@Override
	public boolean isCamposValidos(StringBuilder msgErro) {
		return true;
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
