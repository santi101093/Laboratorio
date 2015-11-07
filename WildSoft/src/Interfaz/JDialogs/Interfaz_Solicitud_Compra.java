package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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

@SuppressWarnings("serial")
public class Interfaz_Solicitud_Compra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Servicio_Solicitud_compra sv_solicitudCompra;
	private Principal_Negocio_Interfaz Principal_neg_int;

	public Interfaz_Solicitud_Compra(final Principal_Negocio_Interfaz principal_neg_int) {
		setResizable(false);
		Principal_neg_int = principal_neg_int;
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
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AM_Solicitud_Compra frame = new AM_Solicitud_Compra(Principal_neg_int);
				frame.setModal(true);
				frame.setVisible(true);
			}
		});
		btnAgregar.setBounds(20, 339, 90, 28);
		contentPanel.add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount()>0){
					Integer id = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
					sv_solicitudCompra.eliminarSolicitudCompra(sv_solicitudCompra.obtenerSolicitud(id));
					llenarTabla();
				}
			}
		});
		btnBorrar.setBounds(120, 339, 90, 28);
		contentPanel.add(btnBorrar);
		
		JButton btnEditar = new JButton("Consultar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getRowCount()>0){
					Solicitud_compra sc = sv_solicitudCompra.obtenerSolicitud(Integer.valueOf((String)table.getValueAt(table.getSelectedRow(), 0)));
					dispose();
					AM_Solicitud_Compra frame = new AM_Solicitud_Compra(principal_neg_int);
					frame.setSolicictud(sc);
					frame.setModal(true);
					frame.setVisible(true);
				}
			}
		});
		btnEditar.setBounds(220, 339, 90, 28);
		contentPanel.add(btnEditar);
		
		JButton btnGenerarSolicitud = new JButton("Generar Solicitud");
		btnGenerarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_Solicitud();
				dispose();
			}
		});
		btnGenerarSolicitud.setBounds(424, 339, 218, 28);
		contentPanel.add(btnGenerarSolicitud);
		
		JButton btnNewButton = new JButton("Pagar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Estado_Solicitud= (String) table.getValueAt(table.getSelectedRow(), 4);
				System.out.println(Estado_Solicitud);
				if(table.getSelectedRow() > -1 ){
					if(Estado_Solicitud.equals("Pendiente"))
					{
						dispose();
						auxiliar frame = new auxiliar(principal_neg_int, Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
						frame.setVisible(true);
						frame.setAlwaysOnTop(true);
					}
				}
			}
		});
	
		btnNewButton.setBounds(322, 339, 90, 28);
		contentPanel.add(btnNewButton);
		
		JButton btnEnviarSolicitud = new JButton("Enviar Solicitud");
		btnEnviarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Enviar_solicitud_al_proveedor();
			}
		});
		btnEnviarSolicitud.setBounds(653, 339, 154, 28);
		contentPanel.add(btnEnviarSolicitud);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		inicializar();
	}

	private void Enviar_solicitud_al_proveedor() {
		if(table.getSelectedRow()!=-1){
			// SOLICITUD SELECCIONADA
			Solicitud_compra solicitud = sv_solicitudCompra.obtenerSolicitud(Integer.valueOf((String)table.getValueAt(table.getSelectedRow(), 0)));
			
			// Obtiene Adjunto que es la solicitud de compra en PDF
			ReporteSolicitud RS = new ReporteSolicitud();
			Integer NUMERO_SOLICITUD =  Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
			RS.Generar_Solicitud(NUMERO_SOLICITUD);
			
			// GENERO UN FILE PARA OBTENER SU UBICACION EN EL DIRECTORIO
			String Nombre = "SOLICITUD COMPRA N�"+solicitud.getId();
			File Archivo = new File("\\"+Nombre);
			
			// GENERA LA SOLICITUD EN PDF
			RS.EXPORT_TO_PDF(Archivo.getAbsolutePath(), Nombre);
			
			Principal_neg_int.getEmail_manager().ENVIAR_SOLICITUD_DE_COMPRA(solicitud.getProveedor().getMail(), solicitud);
			// ELIMINO EL ARCHIVO PDF DEL DISCO
			Archivo.delete();
		}
	}

	private void inicializar() {
		sv_solicitudCompra = Principal_neg_int.getSvSolicitudCompra();
		llenarTabla();
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODOS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void llenarTabla() {
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		ArrayList<Solicitud_compra> lista = sv_solicitudCompra.getLISTA_SOLICITUDES();
		for (int i = 0; i < lista.size(); i++) {
			String precio = " ";
			if(lista.get(i).getPrecio()!=null && lista.get(i).getPrecio()!=0){
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
			if(table.getSelectedRow()!=-1){
				ReporteSolicitud RS = new ReporteSolicitud();
				Integer NUMERO_SOLICITUD =  Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
				RS.Generar_Solicitud(NUMERO_SOLICITUD);
				RS.MOSTRAR_REPORTE();
			}
		}
}
