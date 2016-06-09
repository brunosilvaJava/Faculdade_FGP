package ui;

import interfaces.ITelaManter;

import java.util.List;

import javax.swing.JOptionPane;

import ui.templates.ConsultarPanelView;
import vo.ClienteVO;
import enumeradores.TipoSolicitacao;

public class ConsultarClienteView extends ConsultarPanelView<ClienteVO>{

	public ConsultarClienteView() {
		super("Cliente", new String[]{ "Código", "Nome" });
	}
	
	@Override
	protected String[] definirGridItens(ClienteVO cliente) {
		
		String[] registro = new String[2];

		registro[0] = cliente.getCodPessoa();
		registro[1] = cliente.getNome();
		
		return registro;
		
	}

	@Override
	protected ITelaManter<ClienteVO> getTelaIncluir() {
		return new ManterClienteView(TipoSolicitacao.INCLUIR, "Cadastrar Cliente");
	}

	@Override
	protected ITelaManter<ClienteVO> getTelaAlterar() {
		return new ManterClienteView(TipoSolicitacao.DETALHAR, "Detalhar Cliente");
	}

	@Override
	public void deletar(ClienteVO cliente) {
		
		JOptionPane.showMessageDialog(null, "Deletar Cliente");
		
	}

	@Override
	public List<ClienteVO> consultar() {
		return null;
	}
}