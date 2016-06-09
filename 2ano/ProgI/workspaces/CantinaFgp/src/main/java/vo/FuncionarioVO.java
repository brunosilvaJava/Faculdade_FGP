package vo;

import java.util.List;

public final class FuncionarioVO extends PessoaVO {
	
	private UsuarioVO usuario;
	private List<FuncionarioCantinaVO> funcionarioCantinas;
	
	public FuncionarioVO() {

	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public List<FuncionarioCantinaVO> getFuncionarioCantinas() {
		return funcionarioCantinas;
	}

	public void setFuncionarioCantinas(List<FuncionarioCantinaVO> funcionarioCantinas) {
		this.funcionarioCantinas = funcionarioCantinas;
	}

}