/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

/**
 * Classe responsável por gerar cpf e cpnj.
 */
public class FormatadorCPFCNPJ {
	
	public static String formatarCnpj(int codigo) {
		String cnpj = "" + codigo;
		
		while(cnpj.length() < 14) {
			cnpj = "0" + cnpj;
		}
		//  AABBBCCCDDDDEE
		//  AA.BBB.CCC/DDDD.EE
		String bloco1 = cnpj.substring(0, 2);
		String bloco2 = cnpj.substring(2, 5);
		String bloco3 = cnpj.substring(5, 8);
		String bloco4 = cnpj.substring(8, 12);
		String bloco5 = cnpj.substring(12);
		
		return bloco1 +"."+bloco2 + "." + bloco3+"/"+bloco4+"-"+bloco5;
	}
	
	public static String formatarCpf(int codigo) {
		String cnpj = "" + codigo;
		
		while(cnpj.length() < 11) {
			cnpj = "0" + cnpj;
		}
		//  AABBBCCCDD
		//  AA.BBB.CCC-DD
		String bloco1 = cnpj.substring(0, 3);
		String bloco2 = cnpj.substring(3, 6);
		String bloco3 = cnpj.substring(6, 9);
		String bloco4 = cnpj.substring(9);
		
		return bloco1 +"."+bloco2 + "." + bloco3+"-"+bloco4;
	}	
}
