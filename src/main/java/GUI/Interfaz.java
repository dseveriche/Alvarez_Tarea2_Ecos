package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;

import Logica.LeerArchivo;


/*
 * Nombre: Daniel Maurio Alvarez
 * Fecha : 10/02/2016
 */

public class Interfaz extends JFrame implements ActionListener {
	
		// ----------------------------------------------------------
		// Atributos
		// ----------------------------------------------------------
	
		// creaciond  objetos para la interfaz de la aplicacion
		JButton examinar = new JButton("Examinar directorio");
		JTextField ruta = new JTextField( );
		JFileChooser archivo = new JFileChooser();
		// objeto para almacenar los datos del archivo
		Hashtable<String, Integer> datos = new Hashtable<String, Integer>();
		// invocacion del archivo para lectura del archivos
		LeerArchivo leer = new LeerArchivo();
		/*
		 *Creacion de JFrame para la interfaz de la aplicacion la cual
		 *consite en un boton el cual selecionara el arcihvo para leer
		 *desps de haber leido el archivo mostrara un mensaje para de 
		 *la cantidad de lienas programdas, la cantida de metodos y de
		 *clases 
		 */
		
		// ----------------------------------------------------------
		// Constructores
		// ----------------------------------------------------------

		
		public Interfaz(){
			// metodo constructor del JFrame
			super("Cuenta lienas");
			// agregando evento al boton para selecionar archivo
			examinar.addActionListener( this);
			// agregando campos para la ruta del directorio
			add( ruta);
			// agregando boton la JFrame
			add( examinar);
			// asignar gestor de diseÃ±o al JFrame
			this.setLayout( new GridLayout( 2, 1, 2, 2));
			// asignar tamaÃ±o el JFrame
			pack();
			// JFrame visible
			setVisible( true);
			// JFrame no sea redimenciones
			setResizable(false);
			// asignar ubicacion en la pantalla
			setLocationRelativeTo( null);
			// evento para cerrar JFrame
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		}

		// ----------------------------------------------------------
		// Métodos
		// ----------------------------------------------------------

		@Override
		public void actionPerformed(ActionEvent e) {
			// objeto para seleccionar archivo para lectura
			datos = leer.LeerDirectorio( ruta.getText());
			JOptionPane.showMessageDialog( null, "\n Lineas: " + datos.get("Lineas") + "\n Metodos: " + datos.get("Metodos") + "\n Clases: " + datos.get("Clases") );
		}

}
