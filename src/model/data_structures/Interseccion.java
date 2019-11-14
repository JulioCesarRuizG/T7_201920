package model.data_structures;

public class Interseccion<K, V> {

	private K id;
	private V info;
	private int CanArcos;
	private Arco<K>[] arcos;
	private boolean check;
	private Interseccion<K, V> conectado;

	public Interseccion(K pid, V pinfo,Arco<K>[] parcos)
	{
		id = pid;
		info = pinfo;
		arcos = new Arco[6];
	}

	public int darCantidadArcos()
	{
		if(arcos == null)
		{
			return 0;
		}
		return CanArcos;
	}
	
	public Arco<K>[] darArcos()
	{
		return arcos;
	}

	public void agregarArco(Arco i)
	{
		if(arcos == null)
		{
			arcos[0] = i;
			CanArcos++;
		}
		else
		{
			for(int j = 1; j<6 ; j++)
			{
				if(arcos[j] == null)
				{
					arcos[j] = i;
				}
			}
			CanArcos++;
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
	
	public V darInfo()
	{
		return info;
	}
	
	public boolean estaMarcado()
	{
		return check;
	}
	
	public void conectadoA(Interseccion a)
	{
		conectado = a;
	}
	
	public Interseccion darConexion()
	{
		return conectado;
	}
}
