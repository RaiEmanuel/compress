import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner leia = new Scanner(System.in);

		System.out.println("Digite o caminho do arquivo de entrada");
		//String caminho = leia.nextLine();	
		String caminho = leia.nextLine();
		String texto = ler_arquivo(caminho);
		
		String codificacao = codificacao(texto);
		
		System.out.println("Digite o caminho para qual deverá ir ser armazenado o resultado:");
		String escrita = leia.nextLine();
		
		escrever_arquivo(escrita, codificacao);
		
		leia.close();
	}

	
	
	
	
	static public String ler_arquivo(String caminho) throws IOException
	{
		
		BufferedReader buffer_leitura = new BufferedReader(new FileReader(caminho));
		String linha = "";
		String retorno = linha;
		while (true) 
		{
			
			if (linha != null) 
			{
				
				retorno+=linha+'\n';
			} else
				break;
			linha = buffer_leitura.readLine();
			
		}
	
		buffer_leitura.close();
		
		
		
		// eliminando o primeiro e o último \n 
		
		retorno = retorno.substring(1,retorno.length()-1);
		//System.out.print("Linha\n" + retorno);
		return retorno;
	
	}
	
	
	
	
	static public void escrever_arquivo(String caminho, String conteudo) throws IOException
	{
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(caminho));
		buffWrite.append(conteudo);
		buffWrite.close();

	}
	
	
	static public String codificacao(String texto)
	{
		
		Dicionario dic = new Dicionario();
		String codificacao = "";
		
		char ch;
		for(int cont=0;cont<256;cont++)
		{
			ch = (char) cont;
			dic.adicionar(Character.toString(ch));
			
		}
		
		int c = 0;
		String analisada = Character.toString(texto.charAt(c));
		c++;
		for(int cont=1;cont<texto.length();cont++,c++)
		{
			//System.out.println("Analisada = "+ analisada);
			//System.out.println("C = " + texto.charAt(c));
			if (dic.buscar(analisada+texto.charAt(cont)) == null)
			{
				codificacao += dic.buscar(analisada).codigo +" ";
				dic.adicionar(analisada+texto.charAt(cont));
				analisada = Character.toString(texto.charAt(c));
			}else
			{
				analisada += Character.toString(texto.charAt(c));
			}
			
			
			if(cont == texto.length()-1)
			{
				codificacao += dic.buscar(Character.toString(texto.charAt(cont))).codigo;
			}
			
		}
		
		//dic.exibir();
		
		//System.out.println("\n\nCodificacao = " + codificacao);
		
		return codificacao;
		
	}
	
	
	
	
	
	
}
