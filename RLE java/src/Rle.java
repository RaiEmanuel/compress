import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Rle {

	public static void main(String[] args) throws IOException {
		
		Scanner leia = new Scanner(System.in);
		
		
		/*		Testes abaixos
		 * 
		 * 
		String teste = "wwwwaaadexxxxxxywww";
		ArrayList<Registro> codificacao = codificar(teste);
		
		
		String teste_codificacao = "aabbbccccddddd";
		String teste_decodificacao = "a2b3c4d5";
		
		System.out.println(codificar(teste_codificacao));
		System.out.println(decodificar(teste_decodificacao));
		*/


		// lendo o caminho do arquivo de entrada
		System.out.println("Digite o caminho do arquivo");
		String caminho = leia.nextLine();
		String lida = ler_arquivo(caminho);
		String resultado = "";
		System.out.println("Digite 1 para codificar e 2 para decodificar");
		
		
		int escolha;
		
		
		// Escolhendo se o usuario quer codificar ou decodificar o texto
		do {
			
			escolha = leia.nextInt();
		
			if(escolha == 1)
			{
				
				resultado = codificar(lida);
				//System.out.println(resultado);
				
			}else
			{
				
				if(escolha == 2)
				{
					
					resultado = decodificar(lida);
					//System.out.println(resultado);
				
				}else
				{
			
					System.out.println("Opção inválida, digite novamente");
				
				}
			}
			
			
		}while (escolha != 1 && escolha != 2);
		
		
		System.out.println("Digite o caminho para qual deverá ir ser armazenado o resultado:");
		leia.nextLine();
		String escrita = leia.nextLine();
		
		escrever_arquivo(escrita, resultado);
		
		leia.close();
	
	}
	
	
	
	
	public static String codificar(String entrada)
	{
		ArrayList<Registro> codificacao = new ArrayList<Registro>();
		
		
		if(entrada.equals(""))
		{
			
			return null;
		
		}else
		{
			
			
			char caractere_atual = entrada.charAt(0);
			int posicao_analisada = 1;
			int quantidade_atual = 1;
			
			
			// repetindo a analise até o último caractere da string
			
			while(posicao_analisada != entrada.length())
			{
				
				//	se o caractere analisado atualmente for igual ao caractere analisado na posicao corrente, 
				// apenas haverá um incremento do contador de repetição do caractere
				
				if(entrada.charAt(posicao_analisada) == caractere_atual)
				{
				
					quantidade_atual++;
				
				}else
				{
					
					// se o caractere analisado atualmente diferir do caractere analisado na posicao corrente, 
					// o antigo caractere será armazenado no registro, junto com seu contador de frequência
					
					
					codificacao.add(new Registro(caractere_atual,quantidade_atual));
					caractere_atual = entrada.charAt(posicao_analisada);
					quantidade_atual = 1;
					
				}
				
				
				posicao_analisada++;
				
				
				// adicionando o último caractere da string e sua frequência no registro
				
				if(posicao_analisada == entrada.length())
				{
			
					codificacao.add(new Registro(caractere_atual,quantidade_atual));
				
				}
					
			}
			
		}
					
		// retornando a string codificada
		
		return codigo_para_string(codificacao);

	}
	
	
	
	
	
	public static String codigo_para_string(ArrayList<Registro> codigo) 
	{
		
		// percorrendo a lista de registros e concatenando cada char com sua quantidade
		
		String saida = "";
		
		for(int cont = 0;cont<codigo.size();cont++)
		{
			
			saida+= codigo.get(cont).caractere;
			saida+= codigo.get(cont).quantidade;
		
		}
		
		return saida;

	}
	
			
	
	public static String decodificar(String entrada)
	{
		
		
		ArrayList<Registro> retorno = new ArrayList<Registro>();
		
		
		// percorrendo a string codificada e decodificando-a
		
		
		for(int cont=0;cont<entrada.length();cont++)
		{
			
			// se o caracere não for um dígito deverá ser repetido o tanto de vezes
			// que for indicado no valor inteiro em sequencia
			
			if(!Character.isDigit(entrada.charAt(cont)))
			{
				
				
				
				Registro temp = new Registro();
				temp.caractere = entrada.charAt(cont);
				
				String s_temp = "";
				int cont2;
				
				// salvando os dígitos numa string que em seguida será convertida para inteiro
				
				for(cont2=cont+1;cont2<entrada.length() && Character.isDigit(entrada.charAt(cont2));cont2++)
				{
			
					s_temp += entrada.charAt(cont2);
				
				}
				
				temp.quantidade += Integer.parseInt(s_temp);
						
				retorno.add(temp);
				cont = cont2-1;
				
			}
		}
		
		
		//retornando a string decodificada
		
		return codigo_para_string_2(retorno);
	
	}


	
	
	public static String codigo_para_string_2(ArrayList<Registro> codigo) 
	{
		
		// percorrer o vetor de registros e repetindo o caractere armazenado 
		// a quantia de vezes necessária
		
		String saida = "";
		
		for(int cont = 0;cont<codigo.size();cont++)
		{
			
			for(int cont2=0;cont2<codigo.get(cont).quantidade;cont2++)
			{	
				
				saida+=codigo.get(cont).caractere;
			
			}
						
			
		}
		
		return saida;

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
	
}
