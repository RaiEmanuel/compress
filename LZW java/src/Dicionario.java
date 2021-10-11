
public class Dicionario {
	
		static int controlador = 0;
	
		Item[] vetor;
	
		
		Dicionario()
		{
			vetor = new Item[31];
		}
		
		
		
		void adicionar(String s)
		{
			int posicao = funcao_hash(s);
			
			if(buscar(s) == null)
			{

				if(vetor[posicao] == null)
				{
					Item it = new Item(s,controlador);
					controlador++;
					vetor[posicao] = it;
					//System.out.println("Adicionou " + s);
				}else
				{
					//System.out.println("Adiciounou " + s);
					Item percorredor = vetor[posicao];
					
					while(percorredor.proximo != null)
					{
						percorredor = percorredor.proximo;
					}
					
					Item it = new Item(s,controlador);
					controlador++;
					//System.out.println("Adicionou " + s);
					percorredor.proximo = it;
					
				}
			}
			
			
			
		}
		
		void exibir()
		{
			for(int cont=0;cont<31;cont++)
			{
				
				if(vetor[cont] != null)
				{
					Item percorredor = vetor[cont];
					while (percorredor != null) 
					{
						System.out.println("Codigo =" + percorredor.codigo + " String = " + percorredor.s);
						percorredor = percorredor.proximo;
					}
				}
				
			}
		}
		
		Item buscar(String buscada)
		{
			
			
			int posicao_buscada = funcao_hash(buscada);
			
			if(vetor[posicao_buscada] != null)
			{
				
				Item percorredor = vetor[posicao_buscada];
				
				while(percorredor != null)
				{
					if(percorredor.s.equals(buscada))
					{
						return percorredor;
					}
					percorredor = percorredor.proximo;
				}
			
			}
			
			return null;
		}
		
		/*
		boolean remover (String s)
		{
			if(buscar(s) == null)
			{
				return false;
			}else
			{
				int posicao_buscada = funcao_hash(s);
				
				Item percorredor = vetor[posicao_buscada];
				
				while(percorredor != null)
				{
					
					if(percorredor.s.equals(s))
					{
						return true;
					}
					
					percorredor = percorredor.proximo;
				}
				
				return true;
			}
		}
		*/
		
		
		Item buscar(int buscada)
		{
			for(int cont=0;cont<31;cont++)
			{
				
				if(vetor[cont] != null)
				{
					Item percorredor = vetor[cont];
					while (percorredor != null) 
					{
						
						if(vetor[cont].codigo == buscada)
						{
							return vetor[cont];
						}
						
						percorredor = percorredor.proximo;
						
					}
				}
				
			}
			return null;
		}
		
		
		int funcao_hash(String s)
		{
			return s.hashCode() %31;
		}
}
