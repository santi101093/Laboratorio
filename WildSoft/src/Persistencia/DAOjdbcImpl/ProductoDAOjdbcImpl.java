package Persistencia.DAOjdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Negocio.Modelo.Producto;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ProductoDAO;

public class ProductoDAOjdbcImpl implements ProductoDAO {
	private ConectorMySQL conex = new ConectorMySQL();	

	public boolean AGREGAR_PRODUCTO(Producto p) {
		    String SentenciaSQL = " INSERT INTO Producto (PR_Nombre, PR_precio, PR_tipo_producto )VALUES ("+
				   "'"+  	p.getPR_nombre()			+"',"
				   +	p.getPR_precio()			    +","
				   +    p.getPR_tipo_producto()			+")";
		    
		   
			return conex.Insertar(SentenciaSQL);
		}

	public ArrayList<Producto> getVARIEDAD_DEL_PRODUCTO(String Tipo_Producto) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Producto, Tipo_producto WHERE producto.PR_vigente is null and producto.PR_tipo_producto = Tipo_producto.TP_id AND Tipo_producto.TP_nombre = '"+ Tipo_Producto + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto p = new Producto();				
				p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
				Arreglo.add(p);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	// Retorna todos los productos
	public ArrayList<Producto> GET_PRODUCTOS() {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Producto, Tipo_producto WHERE producto.PR_tipo_producto = Tipo_producto.TP_id and producto.PR_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto p = new Producto();				
				p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
				Arreglo.add(p);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	
	public boolean ELIMINAR_PRODUCTO(Producto p) {
		String SentenciaSQL = "UPDATE producto SET PR_vigente=0 WHERE PR_id="
				+ p.getPR_id();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}

	public ArrayList<String> getTipos_Producto() {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Tipo_producto where Tipo_producto.TP_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("TP_nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	public ArrayList<String> getTipo_Producto_STRING(Integer id) {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			st.executeQuery("SELECT * FROM Tipo_producto TP where TP.TP_ID=" + id );
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("TP_nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		
		return Arreglo;
	}
	
	public ArrayList<Integer> getTipo_Producto_INTEGER (String nombre) {
		ArrayList<Integer> Arreglo = new ArrayList<Integer>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			st.executeQuery("SELECT * FROM Tipo_producto TP where TP.TP_nombre = '" + nombre + "'" );
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getInt("TP_id"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		
		return Arreglo;
	}
	
	
	
	
	public boolean Modificar_Producto(Producto P) {
		String SentenciaSQL = "UPDATE Producto SET PR_nombre = '" + P.getPR_nombre() + "', PR_Observacion = '" + P.getPR_Observacion()+ 
							  "', PR_precio = '" + P.getPR_precio()+ "', PR_tipo_producto = '" + P.getPR_tipo_producto() + "' WHERE PR_id=" + P.getPR_id();
		return conex.Insertar(SentenciaSQL);
	}

	@Override
	public HashMap<Integer, String> obtenerCategorias() {
		HashMap<Integer, String> mapa = new HashMap<Integer, String>();
		try {

			conex.connectToMySQL();

			Statement st = conex.conexion.createStatement();
			st.executeQuery("select * from tipo_producto where TP_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				mapa.put(Fila.getInt("TP_id"), Fila.getString("TP_nombre"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return mapa;
	}

	@Override
	public HashMap<Integer, String> obtenerProductoshas() {
		HashMap<Integer, String> mapa = new HashMap<Integer, String>();
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement();
			st.executeQuery("select * from tipo_producto where TP_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				mapa.put(Fila.getInt("TP_id"), Fila.getString("TP_nombre"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return mapa;
	}

	@Override
	public Producto getProducto(String nombreProducto) {
		Producto p = null;
		try {
			p = new Producto();
			conex.connectToMySQL();
			PreparedStatement statement = conex.conexion.prepareStatement("select * from producto where PR_nombre=?");
            statement.setString(1, nombreProducto);
            ResultSet Fila = statement.executeQuery();
            while (Fila.next()) {
            	p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_Observacion(Fila.getString("PR_observacion"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
            }
            conex.cerrarConexion();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
        }
		return p;
	}
}// --> FIN

