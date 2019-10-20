package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.logic.MVCModelo;
import model.logic.ZonaUber;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				try
				{
					modelo.cargarInfoZonas();

				}
				catch(Exception e)
				{
					e.getMessage();
				}
				break;
			case 2:
				try
				{
					String buscado="";
					System.out.println("Ingresar id Zona Buscada");
					String idZona = "";
					idZona = lector.next();
					buscado=modelo.buscarZonaPorId(idZona);
					if(buscado==null) {
						System.out.println("No existe zona con el id ingresado");
					}
					else {
						String[]zonal=buscado.split(",");
						System.out.println("ID zona buscada: "+idZona);
						System.out.println("Nombre zona : "+zonal[0]);
						System.out.println("Perimetro Zona : "+zonal[1]);
						System.out.println("Area Zona : "+zonal[2]);
						System.out.println("Cantidad Cordenadas : "+zonal[3]);
					}
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				break;
			case 3:
				try
				{
					System.out.println("Ingresar limite inferior");
					String idZona = "";
					idZona = lector.next();
					System.out.println("Ingresar limite superior");
					String limiteSuperior="";
					limiteSuperior=lector.next();
					Iterator<String> buscado3=modelo.buscarZonaPorIdRango(idZona, limiteSuperior);
					int idZonacom=Integer.parseInt(idZona);
					while(buscado3.hasNext()) {
						String buscad4=buscado3.next();
						String[]zonal2=buscad4.split(",");
						System.out.println("ID zona buscada: "+idZonacom);
						System.out.println("Nombre zona : "+zonal2[0]);
						System.out.println("Perimetro Zona : "+zonal2[1]);
						System.out.println("Area Zona : "+zonal2[2]);
						System.out.println("Cantidad Cordenadas : "+zonal2[3]);
						idZonacom++;
					}
					
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				break;
			case 6: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
