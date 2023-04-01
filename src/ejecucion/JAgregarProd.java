package ejecucion;

import empresa.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JAgregarProd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNuevoCodigo;
	private JTextField textNuevoPrecio;
	private JTextField textNuevoStock;
	private JTextField textNuevoStockMin;
	private JTextField textDescripcion;
	private Producto x;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JAgregarProd dialog = new JAgregarProd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JAgregarProd() {
		setTitle("AgregarProducto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textNuevoCodigo = new JTextField();
			textNuevoCodigo.setBounds(20, 44, 86, 20);
			contentPanel.add(textNuevoCodigo);
			textNuevoCodigo.setColumns(10);
		}
		{
			textNuevoPrecio = new JTextField();
			textNuevoPrecio.setBounds(20, 88, 86, 20);
			contentPanel.add(textNuevoPrecio);
			textNuevoPrecio.setColumns(10);
		}
		{
			textNuevoStock = new JTextField();
			textNuevoStock.setBounds(20, 134, 86, 20);
			contentPanel.add(textNuevoStock);
			textNuevoStock.setColumns(10);
		}
		{
			JLabel lblNuevoCodigo = new JLabel("Codigo del Producto");
			lblNuevoCodigo.setBounds(20, 26, 103, 14);
			contentPanel.add(lblNuevoCodigo);
		}
		{
			textNuevoStockMin = new JTextField();
			textNuevoStockMin.setBounds(20, 181, 86, 20);
			contentPanel.add(textNuevoStockMin);
			textNuevoStockMin.setColumns(10);
		}
		
		JLabel lblNuevoPrecio = new JLabel("Precio del Producto");
		lblNuevoPrecio.setBounds(23, 72, 100, 14);
		contentPanel.add(lblNuevoPrecio);
		
		JLabel lblNuevoStock = new JLabel("Stock del Producto");
		lblNuevoStock.setBounds(20, 118, 103, 14);
		contentPanel.add(lblNuevoStock);
		
		JLabel lblNuevoStockMin = new JLabel("Stock Minimo");
		lblNuevoStockMin.setBounds(20, 164, 103, 14);
		contentPanel.add(lblNuevoStockMin);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(159, 44, 215, 20);
		contentPanel.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion del producto");
		lblDescripcion.setBounds(159, 26, 159, 14);
		contentPanel.add(lblDescripcion);
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
	private void aceptar() {
		try{
			if (Integer.parseInt(textNuevoStock.getText()) <= Integer.parseInt(textNuevoStockMin.getText())) {
				throw new Exception("El stock ingresado no es suficiente");
			}
			x = new Producto(Integer.parseInt(textNuevoCodigo.getText()), textDescripcion.getText(), Double.parseDouble(textNuevoPrecio.getText()), Integer.parseInt(textNuevoStock.getText()), Integer.parseInt(textNuevoStockMin.getText()));
			this.setVisible(false);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this,"ERROR: error en el ingreso de datos(" + e + ")");
			cancelar();
		}
	}
	private void cancelar(){
		this.x = null;
		this.setVisible(false);
	}
	public Producto getProducto() { 
		return x;
	}
}
