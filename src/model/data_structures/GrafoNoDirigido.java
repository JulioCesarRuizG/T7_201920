package model.data_structures;

import java.util.Scanner;


public class GrafoNoDirigido<K> {

	private int cantidadVertices;
	private Interseccion[] vertices;

	public GrafoNoDirigido(int n)
	{
		Interseccion[] vertices = new Interseccion[n];
	}

	public int V()
	{
		return cantidadVertices;
	}

	public int E()
	{
		int cantidad = 0;
		for(Interseccion inter : vertices)
		{
			cantidad = cantidad + inter.darCantidadArcos();
		}
		return cantidad;
	}
	
	public void AddEdge(K idVertexIni, K idVertexFin, double cost)
	{
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertexIni)
			{
				int id = (int) idVertexFin;
				Arco arc = new Arco(id, 0);
				inter.agregarArco(arc);
			}
		}
	}
	
	public Interseccion getInfoVertes(K idVertex)
	{
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertex)
			{
				return inter;
			}
		}
		return null;
	}
	
	public void setInfoVertex(K idVertex, double lon, double lat, int move)
	{
		Interseccion inter = getInfoVertes(idVertex);
		inter.cambiarInformacion(lon, lat, move);
	}
	
	public double getCostArc(K idVertexIni, K idVertexFin)
	{
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertexIni)
			{
				for(Arco arc : inter.darArcos())
				{
					int id = arc.darDestino();
					int id2 = (int) idVertexFin;
					if(id == id2)
					{
						return arc.darCosto();
					}
				}
			}
		}
		return -1;
	}
	
	public void setCostArc(K idVertexIni, K idVertexFin, double cost)
	{
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertexIni)
			{
				for(Arco arc : inter.darArcos())
				{
					int id = arc.darDestino();
					int id2 = (int) idVertexFin;
					if(id == id2)
					{
						arc.cambiarCosto(cost);
						return;
					}
				}
			}
		}
	}
	
	public void addVertex(K idVertex, double lon, double lat, int move)
	{
		Interseccion inter = new Interseccion(idVertex, lon, lat, move, null);
		vertices[cantidadVertices] = inter;
		cantidadVertices++;
	}
	
	public Iterable <K> adj (K idVertex)
	{
		//falta implementar
	}
	
	public void uncheck()
	{
		for(Interseccion inter : vertices)
		{
			inter.desmarcar();
		}
	}
	
	public void dfs(K s)
	{
		
		//falta implementar
	}
	
	public int cc()
	{
		//falta implementar
	}
	
	public Iterable<K> getCC(K idVertex)
	{
		//falta implementar
	}
	
}
