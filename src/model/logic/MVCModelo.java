package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.management.Query;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.data_structures.Arco;
import model.data_structures.GrafoNoDirigido;
import model.data_structures.Informacion;
import model.data_structures.Interseccion;
import model.data_structures.Queue;

/**
 * Definicion del modelo del mundo
 *
 */

public class MVCModelo<K> {
	
	private GrafoNoDirigido<Integer,Informacion> grafo;
	
	public MVCModelo() throws IOException
	{
		String txtarcos = "./data/bogota_arcos.txt";
		String txtvertices = "./data/bogota_vertices.txt";
		Queue colaNodos = new Queue(null);
		int cantidad = 0;
		int contador = 0;
		
		FileReader lector2 = new FileReader(txtvertices);
		BufferedReader leer2 = new BufferedReader(lector2);
		String lineaActual2 = leer2.readLine();
		while(lineaActual2 != "" && lineaActual2 != null)
		{
			String[] valores = lineaActual2.split(";");
			if(valores[0].equals("id"))
			{
				
			}
			else
			{
			cantidad++;
			}
			lineaActual2 = leer2.readLine();
		}
		int cantidadVertices = 0;
		
		grafo = new GrafoNoDirigido<Integer,Informacion>(cantidad);
		FileReader lector = new FileReader(txtvertices);
		BufferedReader leer = new BufferedReader(lector);
		String lineaActual = leer.readLine();
		while(lineaActual != "" && lineaActual != null)
		{
			String[] valores = lineaActual.split(";");
			if(valores[0].equals("id"))
			{
				
			}
			else
			{
			int id = Integer.parseInt(valores[0]); 
			double lon = Double.parseDouble(valores[1]);
			double lat = Double.parseDouble(valores[2]);
			int mov = Integer.parseInt(valores[3]);
			Informacion info = new Informacion(lat, lon, mov);
			grafo.addVertex(id, info);
			cantidadVertices++;
			}
			lineaActual = leer.readLine();
		}
		System.out.println("Se han creado " + cantidadVertices + " v�rtices");
		
		int cantidadArcos = 0;
		FileReader lector3 = new FileReader(txtarcos);
		BufferedReader leer3 = new BufferedReader(lector3);
		String lineaActual3 = leer3.readLine();
		while(lineaActual3 != "" && lineaActual3 != null)
		{
			String[] valores = lineaActual3.split(" ");
			for(int i=1 ; i<valores.length ; i++)
			{
				int val1 = Integer.parseInt(valores[0]);
				int val2 = Integer.parseInt(valores[i]);
				Informacion info1 = grafo.getInfoVertex2(val1);
				Informacion info2 = grafo.getInfoVertex2(val2);
				if(info1 != null && info2 != null)
				{
					double sin1 = Math.pow(Math.sin((info2.darLatitud()-info1.darLatitud())/2), 2);
					double cos1 = Math.cos(info1.darLatitud());
					double cos2 = Math.cos(info2.darLatitud());
					double sin2 = Math.pow((Math.sin((info2.darLongitud()-info1.darLongitud())/2)), 2);
					double interno = Math.asin(Math.sqrt(sin1+(cos1*cos2*sin2)));
					double cost = 2*6371*interno;
					grafo.setArcAndCost(val1, val2, cost);
					cantidadArcos++;
				}
			}
			
			lineaActual3 = leer3.readLine();
		}
		System.out.println("Se han generado " + cantidadArcos + " arcos");
		crearArchivo();
	}

	public void crearArchivo() throws IOException
	{
		String ruta = "./data/mapa.html";
		int contador = 0;
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(ruta);
		}catch (Exception e) {
			e.printStackTrace();
		}
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">");
		writer.println("<meta charset=\"utf-8\">");
		writer.println("<title>Simple Polylines</title>");
		writer.println("<style>");
		writer.println("#map {");
		writer.println("height: 100%;");
		writer.println("}");
		writer.println("html,");
		writer.println("body {");
		writer.println("height: 100%;");
		writer.println("margin: 0;");
		writer.println("padding: 0;");
		writer.println("}");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div id=\"map\"></div>");
		writer.println("<script>");
		writer.println("function initMap() {");
		writer.println("var map = new google.maps.Map(document.getElementById('map'), {");
		writer.println("zoom: 5,");
		writer.println("center: {");
		writer.println("lat: 40.162838,");
		writer.println("lng: -3.494526");
		writer.println("},");
		writer.println("mapTypeId: 'roadmap'");
		writer.println("});");
		writer.println("var line;");
		writer.println("var path;");
		for(Interseccion<Integer,Informacion> inter: grafo.darVertices())
		{
			for(Arco<Integer> arcos : inter.darArcos())
			{
				if(arcos != null)
				{
					Informacion llegada = grafo.getInfoVertex2(arcos.darDestino());
					if(llegada != null)
					{
						Informacion info = (Informacion) inter.darInfo();
						writer.println("line = [");
						writer.println("{");
						writer.println("lat: " + info.darLatitud() + ",");
						writer.println("lng: " + info.darLongitud());
						writer.println("},");
						writer.println("{");
						writer.println("lat: " + llegada.darLatitud()+ ",");
						writer.println("lng: " + llegada.darLongitud());
						writer.println("}");
						writer.println("];");
						writer.println("path = new google.maps.Polyline({");
						writer.println("path: line,");
						writer.println("strokeColor: '#FF0000',");
						writer.println("strokeWeight: 2");
						writer.println("});");
						writer.println("path.setMap(map);");
						contador++;
						System.out.println(contador);
					}
				}
				
				
			}
			
		}
		
		writer.println("}");
		writer.println("</script>");
		writer.println("<script async defer src=\"https://maps.googleapis.com/maps/api/js?key=&callback=initMap\">");
		writer.println("</script>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
		System.out.println("Carga completada");
		persistirJson();
		
	}
	
	public void persistirJson()
	{
		String ruta = "./data/dataJson.json";
		int contador = 0;
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(ruta);
		}catch (Exception e) {
			e.printStackTrace();
		}
		writer.println("{");
		writer.println("\"type\":\"GrafoNoDirgido\",\"vertices\":[");
		int arcos2 = 0;
		int vertices2 = 0;
		for(Interseccion inter : grafo.darVertices())
		{
			if(inter != null)
			{
				vertices2++;
				Informacion info = (Informacion) inter.darInfo(); 
				writer.println("{\"type\":\"Interseccion\",");
				writer.println("\"id\":" + inter.darId()+",");
				writer.println("\"canArcos\":" + inter.darCantidadArcos()+",");
				writer.println("\"check\":" + inter.estaMarcado()+",");
				writer.println("\"info\":{\"type\":\"Informacion\",");
				writer.println("\"lat\":" + info.darLatitud()+",");
				writer.println("\"lon\":" + info.darLongitud()+",");
				writer.println("\"move\":" + info.darMovementeID()+",");
				writer.println("},");
				writer.println("\"arcos\":[");
				for(Arco arcos : inter.darArcos())
				{
					if(arcos != null)
					{
						writer.println("{\"type\":\"Arco\",");
						writer.println("\"destino\":" + arcos.darDestino()+",");
						writer.println("\"costo\":" + arcos.darCosto()+",");
						
						arcos2++;
					}
					if(arcos2 == inter.darCantidadArcos())
					{
						writer.println("}");
					}
					else
					{
						writer.println("},");
					}
				}
				if(grafo.V() == vertices2)
				{
					writer.println("},");
				}
				else
				{
					writer.println("}");
				}
			}
		}
		writer.println("]}");
		
	}
	
	public void cargarJson()
	{
		String path = "./data/dataJson.json";
		JsonReader reader;
		Gson gson = new Gson();
		try {
			reader = new JsonReader(new FileReader(path));
			GrafoNoDirigido lista3 = gson.fromJson(reader, GrafoNoDirigido.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		MVCModelo modelo = new MVCModelo();
	}
	
}



