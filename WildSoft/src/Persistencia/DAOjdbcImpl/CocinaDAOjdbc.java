package Persistencia.DAOjdbcImpl;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.CocinaDAO;

public class CocinaDAOjdbc implements CocinaDAO{
	private ConectorMySQL conex= new ConectorMySQL();
	
	public boolean VISUALIZAR_PEDIDOS(ArrayList<Pedido> listaPedidos)
	{
		
		return false;
		
	}

	@Override
	public ArrayList<Pedido> getLISTA_PEDIDOS() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
