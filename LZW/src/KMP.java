import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class KMP {




	public  static void main(String[] args)
	{
		Scanner leia = new Scanner(System.in);
		
		
		KMP kmp = new KMP(); 


		String s = kmp.ler_txt();

		String padrao_teste1;
		
		System.out.println("Digite o padrão buscado");
		
		padrao_teste1 = leia.nextLine();
		
		padrao_teste1 = "TCCTCCCAC";

		int[] vetor_tabela_prefixo = new int[padrao_teste1.length()];


		int contador = kmp.contar_quantas_vezes_padrao_aparece(s, padrao_teste1, vetor_tabela_prefixo);


		for(int cont=0;cont<padrao_teste1.length();cont++)
		{
			//System.out.println("V[" + cont+"] = " + vetor_tabela_prefixo[cont]);
		}


		System.out.println("\n\n"+ padrao_teste1 + " Aparece " + contador + " vez(es)");

	}


	public void preencher_tabela_prefixo_padrao(String s, int quantidade, int vetor_tabela_prefixo[])
	{
		int tamp = 0;
		int i=1;

		vetor_tabela_prefixo[0] = 0;

		while (i < quantidade)
		{

			if(s.charAt(i) == s.charAt(tamp))
			{
				tamp++;
				vetor_tabela_prefixo[i] = tamp;
				i++;
			}else
			{
				if(tamp != 0)
				{
					tamp = vetor_tabela_prefixo[tamp-1];
				}else
				{
					vetor_tabela_prefixo[i] = tamp;
					i++;
				}
			}

		}
	}

	
	public String ler_txt()
	{
		String retorno ="";

		Scanner leia = new Scanner(System.in);

		System.out.printf("Informe o caminho do arquivo texto:\n");
		String nome = leia.nextLine();
		
		try {
			FileReader arquivo = new FileReader(nome);
			BufferedReader ler_arquivo = new BufferedReader(arquivo);


			String temp = ler_arquivo.readLine();
			

			if(temp != null)
			{
				retorno+=temp+'\n';
			}



			while (temp != null) {


				temp = ler_arquivo.readLine(); // lê da segunda até a última linha7

				if(temp != null)
				{
					retorno+=temp+'\n';
				}
			}

			arquivo.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}

		System.out.println();
		return retorno;
	}
	
	
	
	public String ler_txt(String caminho)
	{
		String retorno ="";

		Scanner leia = new Scanner(System.in);

		
		
		try {
			FileReader arquivo = new FileReader(caminho);
			BufferedReader ler_arquivo = new BufferedReader(arquivo);


			String temp = ler_arquivo.readLine();
			

			if(temp != null)
			{
				retorno+=temp+'\n';
			}



			while (temp != null) {


				temp = ler_arquivo.readLine(); // lê da segunda até a última linha7

				if(temp != null)
				{
					retorno+=temp+'\n';
				}
			}

			arquivo.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}

		System.out.println();
		return retorno;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public int contar_quantas_vezes_padrao_aparece(String texto,String padrao ,int[] vetor_tabela_prefixo)
	{
		int contador = 0;

		preencher_tabela_prefixo_padrao(padrao, padrao.length(), vetor_tabela_prefixo);

		int cont_padrao = 0;

		for(int cont_texto = 0; cont_texto<texto.length(); cont_texto++)
		{
			
			//System.out.println("texto cont = " + cont_texto + ", Char = "+texto.charAt(cont_texto) );
			//System.out.println("padrao cont = " + cont_padrao + ", Char = "+padrao.charAt(cont_padrao)+"\n\n");
			
			
			
			if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
			{
				cont_padrao++;
				if(cont_padrao == padrao.length())
				{
					contador++;
					//System.out.println("\nachou posicao do texto = " + cont_texto +"\n");
					cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
				}
			}else
			{
				if(cont_padrao != 1 & cont_padrao !=0)
				{
					//System.out.println("Cont padrao [" + cont_padrao+"] virou ["+vetor_tabela_prefixo[cont_padrao-1]+"]");
					cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
					
					if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
					{
						cont_padrao++;
					}
					
				}else
				{
					if(cont_padrao == 1)
					{
						cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
						if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
						{
							cont_padrao++;
						}
					}else
					{
						if(cont_padrao == 0)
						{

							if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
							{
								cont_padrao++;
							}
						}
					}
					
				}
				
			}

		}
		return contador;
	}


	public int contar_quantas_vezes_padrao_aparece(String texto,String padrao)
	{
		
		int[] vetor_tabela_prefixo = new int[padrao.length()];
		
		
		int contador = 0;

		preencher_tabela_prefixo_padrao(padrao, padrao.length(), vetor_tabela_prefixo);

		int cont_padrao = 0;

		for(int cont_texto = 0; cont_texto<texto.length(); cont_texto++)
		{
			
			//System.out.println("texto cont = " + cont_texto + ", Char = "+texto.charAt(cont_texto) );
			//System.out.println("padrao cont = " + cont_padrao + ", Char = "+padrao.charAt(cont_padrao)+"\n\n");
			
			
			
			if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
			{
				cont_padrao++;
				if(cont_padrao == padrao.length())
				{
					contador++;
					//System.out.println("\nachou posicao do texto = " + cont_texto +"\n");
					cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
				}
			}else
			{
				if(cont_padrao != 1 & cont_padrao !=0)
				{
					//System.out.println("Cont padrao [" + cont_padrao+"] virou ["+vetor_tabela_prefixo[cont_padrao-1]+"]");
					cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
					
					if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
					{
						cont_padrao++;
					}
					
				}else
				{
					if(cont_padrao == 1)
					{
						cont_padrao = vetor_tabela_prefixo[cont_padrao-1];
						if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
						{
							cont_padrao++;
						}
					}else
					{
						if(cont_padrao == 0)
						{

							if(texto.charAt(cont_texto) == padrao.charAt(cont_padrao))
							{
								cont_padrao++;
							}
						}
					}
					
				}
				
			}

		}
		return contador;
	}





}




