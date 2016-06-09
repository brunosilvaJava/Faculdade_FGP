package ui;

import interfaces.ITelaManter;

import java.util.List;

import javax.swing.JOptionPane;

import ui.templates.ConsultarPanelView;
import vo.FuncionarioVO;
import enumeradores.TipoSolicitacao;

public class ConsultarFuncionarioView extends ConsultarPanelView<FuncionarioVO> {
	
	public ConsultarFuncionarioView() {
		super("Funcionário", new String[]{ "Codigo", "Nome" });
	}

	@Override
	protected String[] definirGridItens(FuncionarioVO funcionario) {
			
			String[] registro = new String[2];

			registro[0] = funcionario.getCodPessoa();
			registro[1] = funcionario.getNome();
			
			return registro;
		
	}

	@Override
	protected ITelaManter<FuncionarioVO> getTelaIncluir() {
		return new ManterFuncionarioView(TipoSolicitacao.INCLUIR, "Cadastrar Funcionário");
	}
	
	@Override
	protected ITelaManter<FuncionarioVO> getTelaAlterar() {
		return new ManterFuncionarioView(TipoSolicitacao.DETALHAR, "Detalhar Funcionário");
	}

	@Override
	public void deletar(FuncionarioVO funcionario) {

		JOptionPane.showMessageDialog(null, "Deletar Funcionario");
		
	}

	@Override
	public List<FuncionarioVO> consultar() {
		return null;
	}
	
}