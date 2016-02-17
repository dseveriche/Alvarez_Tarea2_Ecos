package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/*
 * Nombre: Daniel Maurio Alvarez
 * Fecha : 14/02/2016
 */
public class LeerArchivo {
	
	// ----------------------------------------------------------
	// Atributos
	// ----------------------------------------------------------


	int clases, metodos, lineas;
	Hashtable<String, Integer> datos = new Hashtable<String, Integer>();
	String[] palabrasMetodos = { "for(", "class", "if(", "while(", "switch(", "do{", "try{", "catch", "new ", "}", ";"};
	ArrayList<String> listaDirectorio = new ArrayList<String>();
	
	// ----------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------

	
	/*
	 * a este metodo se le pasaran dos parametro uno vector y una
	 * cadena de texto validara la informacion de vector y si 
	 * concide  en un posicion de vector retornara un valor boleando
	 * el cual sera de verdadero de lo contrario para como valor
	 * falso
	 */
	
	
	public boolean buscarTexto( String[] palabras, String texto){
		boolean validarTexto = false;
		// se recorrera todo el vector para mira si hay una cadena de texto
		for( String x: palabras){
			if( texto.indexOf(x) >= 0)
				validarTexto = true;
		}
		
		return validarTexto;
	}
	/*
	 * este metodo almacenra los archivo con la sextencion java
	 * del directorio seleccionado 
	 * */
	public Hashtable<String, Integer> LeerDirectorio( String rutaDirectorio){
		//  crear contadores a las caracteisticas 
		int clases = 0, metodos = 0, lineas = 0;
		// vaciar lista de directorio
		listaDirectorio.clear();
		boolean buscarArchivos = true;
		// contador de direcctorios
		int contar = 0;
		// aÃ±edir ruta seleccionada
		listaDirectorio.add( rutaDirectorio);
		do{
			// creacion de obejto para los directorios
			File directorio = new File ( listaDirectorio.get(contar));
			// obtener lista de los archivo del directorio
			String[] archivos = directorio.list();
			for( String x: archivos){
				// captura de los archivos del direcotrio
				File archivo = new File( directorio.getPath(), x);
				// veridificacion si es un archivo 
				if( archivo.isDirectory()){
					// almacenar lista de directorios
					listaDirectorio.add(directorio.getPath() + "/" + x);
				}else{
					// validar si es un archivo programado de java
					if( x.indexOf( ".java") > 0 && x.indexOf( ".java.") == -1){
						// lectura de archivo java en lo directorios
						Hashtable datosArcihvo =  Leer( new File( directorio.getPath() + "/" + x));
						// contadores de las caraterisitcas de los archivos
						lineas  += (Integer)datosArcihvo.get("Lineas");
						metodos += (Integer)datosArcihvo.get("Metodos");
						clases  += (Integer)datosArcihvo.get("Clases");
					}
				}
			}
			// aumentado cuenta 
			contar++;
			if( listaDirectorio.size() == ( contar )){
				buscarArchivos = false;	
			}
		}while( buscarArchivos );

		Hashtable<String, Integer> datos = new Hashtable<String, Integer>();
		// alistando objeto para retornar datos
		datos.put( "Lineas", lineas);
		datos.put( "Clases", clases);
		datos.put( "Metodos", metodos);
		
		return datos;
	}
	
	/*
	 * este metodo se le pasara como parametro un archivo relizara
	 * la lectura la cual por medio de condiciones para saber si
	 * cuanta lineas, metodos y clases hay en un archivo
	 */
	public Hashtable<String, Integer> Leer( File rutaArchivo) {
		// TODO Auto-generated constructor stub
		lineas = 0;
		clases = 0;
		metodos = 0;

		boolean validacionComentario = false;
		// comenzando lectura del archivo
		try{
			// creando objeto de lectura de archivo
			FileReader archivo = new FileReader( rutaArchivo);
			// creando objeto de lectura de archivo
			BufferedReader lectura = new BufferedReader( archivo);
			// almacenar liena como una cadena de texto
			String linea = "";
			// leyendo cada liena por liena 
			while( linea != null){
				// almacenar liena del archivo
				linea = lectura.readLine();
				// validacion para contar clases
				if( linea.indexOf(" class ") >= 0 && linea.indexOf("\"") < 0)
					clases++;
				// validacion para contar metodos
				if( linea.indexOf( ";") < 0  && linea.indexOf("new ") < 0 && linea.indexOf("(") >= 0 ){
					if( buscarTexto( palabrasMetodos, linea) == false)
						metodos++;
				}
				// validacion para contar lineas programadas
				if( linea != null && linea.length() != 0 && linea.indexOf("//") < 0)
					lineas++;
				// validacion para el inicio de bloque de documentacion
				if( linea.indexOf("/*") >= 0 && linea.indexOf("\"") < 0)
					validacionComentario = true;
				// restar lineas de bloque de documentacion
				if( validacionComentario)
					lineas--;
				// validacion de finalizacion de bloque de documentacion
				if( linea.indexOf("*/") >= 0 && linea.indexOf("\"") < 0)
					validacionComentario = false;
			}	
		}catch( Exception ex){
			// resultado de error de la lectura del archivo
			System.out.println( ex);
		}
		// alistando objeto para retornar datos
		datos.put( "Lineas", lineas);
		datos.put( "Clases", clases);
		datos.put( "Metodos", metodos);
		// retorno de resultado de la lectura del archivo
		return datos;
	}

}
