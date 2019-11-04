package model.data_structures;

public class Interseccion<K> {

	private K id;
	private double lon;
	private double lat;
	private int move;
	private Arco[] arcos;
	private boolean check;

	public Interseccion(K pid, double plon, double plat, int pmovid,Arco[] parcos)
	{
		id = pid;
		lon = plon;
		lat = plat;
		move = pmovid;
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
	
	public void cambiarInformacion(double plon, double plat, int pmove)
	{
		if(plon == 0)
		{
			
		}
		else
		{
			lon = plon;
		}
		if(plat == 0)
		{
			
		}
		else
		{
			lat = plat;
		}
		if(pmove == 0)
		{
			
		}
		else
		{
			move = pmove;
		}
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
