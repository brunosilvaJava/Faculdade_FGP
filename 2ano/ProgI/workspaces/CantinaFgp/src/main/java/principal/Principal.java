package principal;

import java.text.ParseException;

import ui.LoginView;

public class Principal {

	public static void main(String[] args) {
		
		try {
			
			new LoginView().abrirJanela();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
