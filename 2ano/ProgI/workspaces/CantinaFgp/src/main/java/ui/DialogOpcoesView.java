package ui;

import interfaces.ITelaConsultar;
import interfaces.ITelaManter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.GenericVO;

public class DialogOpcoesView<T extends GenericVO> extends JDialog {

	private JPanel panel;
	private JButton btnDetalhar;
	private JButton btnExcluir;
	private T objeto;
	private ITelaConsultar<T> telaConsultar;
	private ITelaManter<T> telaManter;

	// BLOCO DE INICIALIZAÇÃO

	{

		panel = new JPanel();
		btnDetalhar = new JButton("Detalhar");
		btnExcluir = new JButton("Excluir");

	}

	public void abrirJanela(T objeto, ITelaConsultar<T> telaConsultar, ITelaManter<T> telaManter) {

		this.objeto = objeto;
		this.telaConsultar = telaConsultar;
		this.telaManter = telaManter;

		// ***** EVENTOS

		// * btnDetalhar

		btnDetalhar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DialogOpcoesView.this.dispose();

				DialogOpcoesView.this.telaManter.abrirJanela(DialogOpcoesView.this.objeto);

			}

		});

		// * btnExcluir

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int opcao = JOptionPane.showOptionDialog(null,
						"Deseja realmente excluir?", "Exclusão",
						JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null,
						null, null);

				if (opcao == JOptionPane.YES_OPTION) {

					DialogOpcoesView.this.dispose();

					DialogOpcoesView.this.telaConsultar.deletar(DialogOpcoesView.this.objeto);
					List<T> lista = DialogOpcoesView.this.telaConsultar.consultar();
					DialogOpcoesView.this.telaConsultar.carregarGridItens(lista);
				}

			}

		});

		definicoesPagina();

	}

	private void definicoesPagina() {

		panel.setLayout(new GridLayout(1, 3, 5, 20));

		panel.add(btnDetalhar);
		panel.add(btnExcluir);

		panel.setVisible(true);
		panel.setBounds(20, 30, 260, 30);

		this.add(panel);

		this.setLayout(null);
		this.setTitle("Escolha uma ação");
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 125);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
