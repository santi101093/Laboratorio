package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Solicitud_compra;
import Reportes.ReporteSolicitud;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Interfaz_Solicitud_Compra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Servicio_Solicitud_compra sv_solicitudCompra;
	private Principal_Negocio_Interfaz principal_neg_int;

	public Interfaz_Solicitud_Compra(Principal_Negocio_Interfaz principal_neg_int) {
		this.principal_neg_int = principal_neg_int;
		setBounds(100, 100, 969, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 933, 322);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Fecha", "Proveedor", "Precio", "Estado"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setColumnHeaderView(table);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Solicitud_de_Compra frame = new Solicitud_de_Compra(principal_neg_int);
				frame.setModal(true);
				frame.setVisible(true);
			}
		});
		btnAgregar.setBounds(20, 339, 90, 28);
		contentPanel.add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(120, 339, 90, 28);
		contentPanel.add(btnBorrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(220, 339, 90, 28);
		contentPanel.add(btnEditar);
		
		JButton btnGenerarSolicitud = new JButton("Generar Solicitud");
		btnGenerarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_Solicitud();
			}
		});
		btnGenerarSolicitud.setBounds(403, 342, 218, 25);
		contentPanel.add(btnGenerarSolicitud);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		inicializar();
	}

	private void inicializar() {
		
		sv_solicitudCompra = principal_neg_int.getSvSolicitudCompra();
		llenarTabla();
		
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODOS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void llenarTabla() {
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		ArrayList<Solicitud_compra> lista = sv_solicitudCompra.getLISTA_SOLICITUDES();
		for (int i = 0; i < lista.size(); i++) {
			String precio = "";
			if(lista.get(i).getPrecio()!=null){
				precio = String.valueOf(lista.get(i).getPrecio());
			}
			String[] fila = {String.valueOf(lista.get(i).getId()),
					lista.get(i).getFecha().toString(),
					lista.get(i).getProveedor().getNombre(),
					precio,
					lista.get(i).getEstado()};
			modelo.addRow(fila);
		}
		table.setModel(modelo);
	}
	
	
	//----------------------------------------------------------------------------------------------------------------
		private void Generar_Solicitud() {
			if(table!=null && table.getSelectedRow()!=-1){
				ReporteSolicitud RS = new ReporteSolicitud();
				Integer NUMERO_SOLICITUD =  (Integer) table.getValueAt(table.getSelectedRow(), 1);// TODO
				RS.Generar_Solicitud(NUMERO_SOLICITUD);
			}
		}
		
	//-------------------------------------------------------------------------------------------------------------------	
		
}