package Negocio.Servicios;

import java.awt.EventQueue;

public class Ejecutar_WILDSOFT {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Principal_Negocio_Interfaz();
			}
		});
	}
}
