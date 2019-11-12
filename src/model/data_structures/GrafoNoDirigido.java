package model.data_structures;

import java.util.Scanner;


public class GrafoNoDirigido<K, V> {

	private int cantidadVertices;
	private Interseccion[] vertices;

	public GrafoNoDirigido(int n)
	{
		Interseccion[] vertices = new Interseccion[n];
		cantidadVertices = 0;
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
	
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		Interseccion inter = getInfoVertes(idVertex);
		inter.cambiarInformacion(infoVertex);
	}
	
	public double getCostArc(K idVertexIni, K idVertexFin)
	{
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertexIni)
			{
				for(Arco arc : inter.darArcos())
				{
					K id = (K)arc.darDestino();
					K id2 = (K) idVertexFin;
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
					K id = (K) arc.darDestino();
					K id2 = (K) idVertexFin;
					if(id == id2)
					{
						arc.cambiarCosto(cost);
						return;
					}
				}
			}
		}
	}
	
	public void setArc(K idVertexIni, K idVertexFin)
	{
		Arco i = new Arco<K>(idVertexFin, 0);
		for(Interseccion inter : vertices)
		{
				inter.agregarArco(i);
		}
	}
	
	public void addVertex(K idVertex, V infoVertex)
	{
		Interseccion inter = new Interseccion(idVertex, infoVertex, null);
		vertices[cantidadVertices] = inter;
		cantidadVertices++;
	}
	
	public Iterador<K> adj(K idVertex)
	{
		Queue<K> res= new Queue<K>(null);
		for(Interseccion inter : vertices)
		{
			if(inter.darId() == idVertex)
			{ 
				for(Arco arc : inter.darArcos())
				{
					K id = (K) arc.darDestino();
					res.enQueue(id);
				}
				return res.iterator();
			}
		}
		return res.iterator();
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
	
	public Interseccion[] darVertices()
	{
		return vertices;
	}
	
}
