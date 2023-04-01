package ejecucion;

import empresa.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FCatalogo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList listCatalogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FCatalogo dialog = new FCatalogo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FCatalogo() {
		setTitle("Catalogo");
		setBounds(100, 100, 490, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnAgregar = new JButton("Agregar Producto");
		btnAgregar.setBounds(36, 51, 137, 54);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		contentPanel.setLayout(null);
		contentPanel.add(btnAgregar);
		
		JButton btnModificar = new JButton("Actualizar Producto");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		btnModificar.setBounds(36, 135, 137, 54);
		contentPanel.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(36, 218, 137, 54);
		contentPanel.add(btnEliminar);
		
		listCatalogo = new JList();
		listCatalogo.setBounds(225, 11, 219, 316);
		listCatalogo.setListData(Home.listaProd.getLista().toArray());

		contentPanel.add(listCatalogo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}	
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void agregar() {
		JAgregarProd agregarP =new JAgregarProd();
		agregarP.setModal(true);
		agregarP.setVisible(true);
		
		Producto aux = agregarP.getProducto();
		
		if((aux != null) && (Home.listaProd.buscarProducto(aux.getCodigo()) == null))
			Home.listaProd.agrProd(aux);
		else if((aux != null) && (Home.listaProd.buscarProducto(aux.getCodigo()) != null)) {
			JOptionPane.showMessageDialog(this,"ERROR: el producto ya fue ingresado");    
		}
		
		listCatalogo.setListData(Home.listaProd.getLista().toArray());

	}
	private void eliminar() {
		JElminar elimVentana = new JElminar();
		
		elimVentana.setModal(true);
		elimVentana.setVisible(true);
				
		if ((elimVentana.getEliminar()) && (Home.listaProd.buscarProducto(elimVentana.getCodigo()) != null))
			Home.listaProd.eliminarProd(elimVentana.getCodigo());
		else if((elimVentana.getEliminar()) && (Home.listaProd.buscarProducto(elimVentana.getCodigo()) == null))
			JOptionPane.showMessageDialog(this,"ERROR: el producto no existe");
		
		listCatalogo.setListData(Home.listaProd.getLista().toArray());
	}
	private void actualizar() {
		JActualizarProd actVentana = new JActualizarProd();
		actVentana.setModal(true);
		actVentana.setVisible(true);
		
		if ((actVentana.getActualizar()) && (Home.listaProd.buscarProducto(actVentana.getCodigo()) != null)) // agregar ventana error para actualizar 
			Home.listaProd.actualizarProd(actVentana.getCodigo(), actVentana.getDesc(), actVentana.getPrecio(), actVentana.getStock(), actVentana.getStockMin()); 
		else if((actVentana.getActualizar()) && (Home.listaProd.buscarProducto(actVentana.getCodigo()) == null))
			JOptionPane.showMessageDialog(this,"ERROR: el producto no existe");    

		
		listCatalogo.setListData(Home.listaProd.getLista().toArray());
	}
	private void aceptar() {
		this.setVisible(false);
	}
	private void cancelar() {
		this.setVisible(false);
	}
}
