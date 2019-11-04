package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.data_structures.Interseccion;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	
	
	public MVCModelo() throws IOException
	{
		String txtarcos = "./data/bogota_arcos.txt";
		String txtvertices = "./data/bogota_vertices.txt";

		FileReader lector = new FileReader(txtarcos);
		BufferedReader leer = new BufferedReader(lector);
		String lineaActual = leer.readLine();
		while(lineaActual != "" && lineaActual != null)
		{
			String[] valores = lineaActual.split(";");
			int id = Integer.parseInt(valores[0]); 
			double lon = Double.parseDouble(valores[1]);
			double lat = Double.parseDouble(valores[2]);
			int mov = Integer.parseInt(valores[3]);
			lineaActual = leer.readLine();
			Interseccion inter = new Interseccion(id, lon, lat, mov, null);
		}
	}


}
