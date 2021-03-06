package Interfaz.Swing_Extends;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Listado_Pedidos extends JTable{
	private static final long serialVersionUID = -3526713004625297701L;
	
	private String FuenteTablas = "Arial Unicode MS"; //"Verdana";
	private int FuenteSize = 15;
	
	public JTable_Listado_Pedidos(DefaultTableModel model){
		setModel(model);
		
		// Ordenar tabla 
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		
		// Ocultar columna
		this.getColumnModel().getColumn(0).setMaxWidth(0);
		this.getColumnModel().getColumn(0).setMinWidth(0);
		this.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		// Centrar Celdas
		DefaultTableCellRenderer Centrar_Celda = new DefaultTableCellRenderer();
	    Centrar_Celda.setHorizontalAlignment(SwingConstants.CENTER);
		
	    this.getColumnModel().getColumn(1).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(2).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(3).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(5).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(6).setCellRenderer(Centrar_Celda);
	    
	    // setar tama�os de las columnas
		this.getColumnModel().getColumn(1).setMaxWidth(75);
		this.getColumnModel().getColumn(1).setMinWidth(75);
		this.getColumnModel().getColumn(2).setMinWidth(200);
		this.getColumnModel().getColumn(3).setMinWidth(200);
		this.getColumnModel().getColumn(4).setMinWidth(75);
		this.getColumnModel().getColumn(5).setMinWidth(75);
		this.getColumnModel().getColumn(6).setMinWidth(75);
		
	    // Fuente
	    this.setFont(new Font(FuenteTablas, Font.PLAIN, FuenteSize));
	    
	    // Altura de cada fila
		this.setRowHeight(25);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		Component c = super.prepareRenderer(renderer, row, column);
		if (!isRowSelected(row)){
			c.setBackground(getBackground());
//			int modelRow = convertRowIndexToModel(row);
			if(row%2==0) c.setBackground(new Color(240, 255, 255));
			
//			String Cancelado = (String)getModel().getValueAt(modelRow, 5);
//			if (Cancelado.equals("Cancelado")) c.setBackground(new Color(255,153,153));
			
		}
		return c;
	}
	
}	//----> Fin
