package model.data_structures;

public class Interseccion<K, V> {

	private K id;
	private V info;
	private Arco[] arcos;
	private boolean check;

	public Interseccion(K pid, V pinfo,Arco[] parcos)
	{
		id = pid;
		info = pinfo;
		arcos = parcos;
	}

	public int darCantidadArcos()
	{
		if(arcos == null)
		{
			return 0;
		}
		return arcos.length;
	}
	
	public Arco[] darArcos()
	{
		return arcos;
	}

	public void agregarArco(Arco i)
	{
		if(arcos == null)
		{
			arcos[0] = i;
		}
		else
		{
			int valor = arcos.length;
			arcos[valor] = i;
		}
	}
	
	public K darId()
	{
		return id;
	}
	
	public void cambiarInformacion(V newInfo)
	{
		info = newInfo;
	}
	
	public void marcar()
	{
		check = true;
	}
	
	public void desmarcar()
	{
		check = false;
	}
}
