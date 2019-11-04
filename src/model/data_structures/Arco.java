package model.data_structures;

public class Arco {

	private int destino;
	private double costo;
	
	public Arco(int pdestino, double pcosto)
	{
		destino = pdestino;
		costo = pcosto;
	}
	
	public int darDestino()
	{
		return destino;
	}
	
	public double darCosto()
	{
		return costo;
	}
	
	public void cambiarCosto(double pcosto)
	{
		costo = pcosto;
	}
}
