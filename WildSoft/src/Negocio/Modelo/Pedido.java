package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	
	private Integer Numero_Pedido; 		// ID o Numero de pedido
	private Date 	Fecha_Hora_Pedido;		// Fecha y hora en la que se ingresa el pedido al sistema
	private ArrayList<Producto>	Lista_Productos;				// Lista de productos en el pedido
	private Enum 	Estado;				// No me acuerdo como hacer el enum, Fede hacelo
	// En caso que sea delivery:
	private Boolean Es_Delivery = false;
	private Cliente cliente;				
	private Date 	Hora_Entrega;			// Hora en la que se debe entregar el pedido
	
	/** GETTERS AND SETTERS **/
	
	public Integer getNumero_Pedido() {
		return Numero_Pedido;
	}
	public void setNumero_Pedido(Integer numero_Pedido) {
		Numero_Pedido = numero_Pedido;
	}
	public Date getFecha_Hora_Pedido() {
		return Fecha_Hora_Pedido;
	}
	public void setFecha_Hora_Pedido(Date fecha_Hora_Pedido) {
		Fecha_Hora_Pedido = fecha_Hora_Pedido;
	}
	public ArrayList<Producto> getLista_Productos() {
		return Lista_Productos;
	}
	public void setLista_Productos(ArrayList<Producto> lista_Productos) {
		Lista_Productos = lista_Productos;
	}
	public Enum getEstado() {
		return Estado;
	}
	public void setEstado(Enum estado) {
		Estado = estado;
	}
	public Boolean getEs_Delivery() {
		return Es_Delivery;
	}
	public void setEs_Delivery(Boolean es_Delivery) {
		Es_Delivery = es_Delivery;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getHora_Entrega() {
		return Hora_Entrega;
	}
	public void setHora_Entrega(Date hora_Entrega) {
		Hora_Entrega = hora_Entrega;
	}
	
	
	

	
}//--> FIN CLASE
