package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vo.UsuarioVO;

public class UtilFuncoes {
	
	public static UsuarioVO usuarioLogado;

	public static boolean isCampoVazio(String campo) {
	
		return campo == null || campo.trim().equals("");
	
	}

	public static boolean isNumOuLetra(String campo) {
	
		return campo.matches("[a-zA-Z0-9]");
	
	}

		
	public static Integer doubleToInteger(Double valor){
		return valor.intValue();
	}
	
	public static Integer stringToInteger(String campo){
		Integer numerico = Integer.parseInt(campo);
		return numerico;
	}
	
	public static String criptografarMd5(String campo){
		
		String campoFormatado = null;
		
		try {
			
			//Biblioteca para indicar em qual tipo sera a criptografia ( no caso, md5)
			
			MessageDigest conversor = MessageDigest.getInstance("md5");
		
			//Converte um vetor de bytes que é passado pelo campo.getBytes
			
			conversor.update(campo.getBytes());
			
			//O conversor criptografa o vetor de bytes
			//O numero 1 serve para manter o padrao entre o BigInteger e o md5, sem ele, pode gerar valores diferentes entre os 2
			
			BigInteger numericoAux = new BigInteger(1,conversor.digest());
			
			//Indica de qual tipo o md5 ira criptografar ( 16 = Hexadecimal )
			
			campoFormatado = numericoAux.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			
			campoFormatado = null;
			
		}
		
		return campoFormatado;
	}
	
	public static boolean isValorNumerico(String campo){
		
		try{
			
			Double.parseDouble(campo);
						
		}
		catch(NumberFormatException e){
			
			return false;
			
		}
		
		return true;
				
	}
	
}
