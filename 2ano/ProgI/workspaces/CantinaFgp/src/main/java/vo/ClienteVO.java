package vo;

import java.util.List;

public final class ClienteVO extends PessoaVO {
	
	private List<ClienteCantinaVO> clinteCantinas;
	
	public ClienteVO() {
	
	}

	public List<ClienteCantinaVO> getClinteCantinas() {
		return clinteCantinas;
	}

	public void setClinteCantinas(List<ClienteCantinaVO> clinteCantinas) {
		this.clinteCantinas = clinteCantinas;
	}
	
}
