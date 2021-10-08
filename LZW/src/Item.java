
public class Item {
	
	String s;
	int codigo;
	Item proximo;

	Item(String s, int codigo)
	{
		this.s = s;
		this.codigo = codigo;
		proximo = null;
	}
	
	Item()
	{
		
	}
}
