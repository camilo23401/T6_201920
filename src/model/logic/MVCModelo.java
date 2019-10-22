package model.logic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo 
{
	/**
	 * Atributos del modelo del mundo
	 */
	private ArbolRojoNegro<Integer, String> arbolDatos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		arbolDatos = new ArbolRojoNegro<Integer, String>();
	}
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public void cargarInfoZonas() throws IOException
	{
		FileReader lector = new FileReader("data/bogota_cadastral.json");
		Object aux = JsonParser.parseReader(lector);
		JsonObject json = (JsonObject) aux;
		JsonElement elementoJson = json.get("features");
		JsonArray zonas = elementoJson.getAsJsonArray();
		int menor=0;
		int mayor=0;
		for(int i=0; i<zonas.size();i++)
		{
			JsonElement elementoActual = zonas.get(i);
			JsonObject objeto = elementoActual.getAsJsonObject();
			JsonElement geometry = objeto.get("geometry");
			JsonObject x = geometry.getAsJsonObject();
			JsonElement posicion = x.get("coordinates");
			JsonArray arregloCoordenadas = posicion.getAsJsonArray();
			JsonArray primera = arregloCoordenadas.get(0).getAsJsonArray();
			JsonArray complementoPrimera = primera.get(0).getAsJsonArray();
			JsonElement lectorProperties = objeto.get("properties");
			JsonObject properties = lectorProperties.getAsJsonObject();
			JsonElement id = properties.get("MOVEMENT_ID");
			int idNum = id.getAsInt();
			mayor=(idNum>mayor)?mayor=idNum:mayor;
			menor=(i==0)?menor=idNum:menor;
			menor=(menor>idNum)?menor=idNum:menor;
			JsonElement nombreZonal = properties.get("scanombre");
			String nombre = nombreZonal.getAsString();
			JsonElement lengArchivo = properties.get("shape_leng");
			double leng = lengArchivo.getAsDouble();
			JsonElement areaArchivo = properties.get("shape_area");
			double area = areaArchivo.getAsDouble();

			ZonaUber nueva = new ZonaUber(idNum, nombre, leng, area);
			for(JsonElement accesoSegunda : complementoPrimera)
			{
				JsonArray segunda = accesoSegunda.getAsJsonArray();
				double longitud = segunda.get(0).getAsDouble();
				double latitud = segunda.get(1).getAsDouble();
				Coordenadas actual = new Coordenadas(latitud, longitud);
				nueva.meterCoordenadas(actual);
			}
			arbolDatos.put(nueva.MOVEMENT_ID, nueva.scanombre+","+nueva.shape_leng+","+nueva.shape_area+","+nueva.darCantidadCordenadas());	
		}
		System.out.println("El número de zonas encontradas al cargar el archivo identificador de estas fue: "+arbolDatos.size());
		System.out.println("El valor maximo de ID fue "+ mayor);
		System.out.println("El valor minimo de ID fue "+ menor);
	}
	
	
	public String buscarZonaPorId(String buscada) {
		String datos="";
		datos=arbolDatos.get(Integer.parseInt(buscada));
		return datos;
	}
	
	public Iterator<String> buscarZonaPorIdRango(String inferior,String superior) {
		if(Integer.parseInt(superior)<Integer.parseInt(inferior)||Integer.parseInt(inferior)>Integer.parseInt(superior)||Integer.parseInt(inferior)<0||Integer.parseInt(superior)>arbolDatos.size()) {
			System.out.println("Los datos no fueron ingresados correctamente");
		}
		Iterator<String>retorno=arbolDatos.valuesInRange(Integer.parseInt(inferior), Integer.parseInt(superior));
		
		return retorno;
	}
	

}
