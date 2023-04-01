package ejecucion;

import empresa.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import empresa.Producto;

import javax.swing.*;

public class FVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCodigo;
	private JTextField textCantidad;
	private JRadioButton rdbtnDebito;
	private JRadioButton rdbtnEfectivo;
	private JRadioButton rdbtnCredito;
	
	private FCoutas coutaVentana;
	private int cuotas;
	private int codigoDeVenta = -999999;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FVenta dialog = new FVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FVenta() {
		setTitle("Venta");
		setBounds(100, 100, 449, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(25, 44, 107, 20);
		contentPanel.add(textCodigo);
		textCodigo.setColumns(10);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(154, 44, 86, 20);
		contentPanel.add(textCantidad);
		textCantidad.setColumns(10);
		
		rdbtnDebito = new JRadioButton("Pagar con Debito");
		rdbtnDebito.setBounds(25, 112, 141, 23);
		//contentPanel.add(rdbtnDebito);
		
		rdbtnEfectivo = new JRadioButton("Pagar con Efectivo");
		rdbtnEfectivo.setBounds(25, 138, 141, 23);
		//contentPanel.add(rdbtnEfectivo);
		
		rdbtnCredito = new JRadioButton("Pagar con Credito");
		rdbtnCredito.setBounds(25, 164, 141, 23);
		//contentPanel.add(rdbtnCredito);
		
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(rdbtnDebito);bg.add(rdbtnEfectivo);bg.add(rdbtnCredito);
		contentPanel.add(rdbtnDebito);contentPanel.add(rdbtnEfectivo);contentPanel.add(rdbtnCredito);
		
		JLabel lblCodigo = new JLabel("Codigo del Producto");
		lblCodigo.setBounds(25, 19, 107, 20);
		contentPanel.add(lblCodigo);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(154, 19, 86, 20);
		contentPanel.add(lblCantidad);
		
		JLabel lblMetodo = new JLabel("Metodo de Pago");
		lblMetodo.setBounds(35, 91, 97, 14);
		contentPanel.add(lblMetodo);
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
		
		try {
			// cancular el monto neto ( precio * cantindad )
			Producto aux = Home.listaProd.buscarProducto(Integer.parseInt(textCodigo.getText()));
			
			double montoNeto = aux.getPrecio() * Integer.parseInt(textCantidad.getText());
			
			// buscar las opciones
			
			if (rdbtnDebito.isSelected())
				;
			else if (rdbtnEfectivo.isSelected())
				montoNeto = montoNeto - montoNeto * 0.10;
			else if (rdbtnCredito.isSelected()) {
				coutaVentana = new FCoutas();
				coutaVentana.setModal(true);	
				coutaVentana.setVisible(true);
				
				cuotas = coutaVentana.getCouta();
				switch(cuotas) {
				case 2:
					montoNeto = montoNeto + montoNeto*0.06; 
					break;
				case 3:
					montoNeto = montoNeto + montoNeto*0.12; 
					break;
				case 6:
					montoNeto = montoNeto + montoNeto*0.2; 
					break;
				default:
					throw new Exception("Error de metodo");
				}
			}
			else {
				throw new Exception("Error de coutas");
			}
			
			// comprobar q hay stock (sino tirar error)
			if (Integer.parseInt(textCantidad.getText()) > aux.getStock() - aux.getStockMin())
				throw new Exception("Error no hay suffieciente Stock");

			FRecibo reciboVentana = new FRecibo(montoNeto);
			reciboVentana.setModal(true);
			reciboVentana.setVisible(true);
			
			codigoDeVenta = aux.getCodigo();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this,"ERROR:  error en el ingreso de datos (" + e +")");
		}
		
		
		this.setVisible(false);
	}
	private void cancelar() {
		this.setVisible(false);
	}
	public int getCodigoVenta() {
		return codigoDeVenta;
	}
	public int getCantidad() {
		return Integer.parseInt(textCantidad.getText());
	}
}
